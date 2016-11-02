/**
 * 
 */
package com.pedra.buckaroo.service.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.AuthorizationCommand;
import de.hybris.platform.payment.commands.factory.CommandFactory;
import de.hybris.platform.payment.commands.factory.CommandFactoryRegistry;
import de.hybris.platform.payment.commands.factory.CommandNotSupportedException;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.Config;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.pedra.buckaroo.constants.BuckarooConstants;
import com.pedra.buckaroo.payment.BuckarooStatusEnum;
import com.pedra.buckaroo.service.BuckarooPaymentService;
import com.pedra.buckaroo.util.BuckarooGatewayUtil;
import com.pedra.ideal.model.payment.IdealPaymentRequestModel;
import com.pedra.ideal.service.impl.IdealPaymentServiceImpl;


/**
 * @author Henrique Ordine
 * 
 */
public class BuckarooPaymentServiceImpl extends IdealPaymentServiceImpl implements BuckarooPaymentService
{

	private static final Logger LOG = Logger.getLogger(BuckarooPaymentServiceImpl.class.getName());

	//allowed difference when comparing doubles
	private static final double DOUBLE_COMPARISON_DELTA = 0.0000001d;

	private CommandFactoryRegistry commandFactoryRegistry;
	private ConfigurationService configurationService;
	private I18NService i18NService;
	private UserService userService;
	private ModelService modelService;
	private CartService cartService;



	public AuthorizationResult authorize(final AuthorizationRequest request) throws AdapterException
	{
		try
		{
			final CommandFactory commandFactory = getCommandFactoryRegistry()
					.getFactory(BuckarooConstants.BUCKAROO_PAYMENT_PROVIDER);
			final AuthorizationCommand command = commandFactory.createCommand(AuthorizationCommand.class);
			final AuthorizationResult result = command.perform(request);
			result.setPaymentProvider(commandFactory.getPaymentProvider());
			return result;
		}
		catch (final CommandNotSupportedException e)
		{
			throw new AdapterException(e.getMessage(), e);
		}
	}

	@Override
	public IdealPaymentRequestModel createTransactionRequest(final OrderModel order, final String clientIP)
	{
		// build NVP request
		final CloseableHttpClient httpclient = HttpClients.createDefault();
		// get the signature for the request
		final Map<String, String> requestParams = BuckarooGatewayUtil.createMapForPaymentRequestSignature(order, clientIP);
		final String secretKey = Config.getString(BuckarooConstants.CONFIG_SECRET_KEY, "");
		final String bpeUrl = Config.getString(BuckarooConstants.CONFIG_ENDPOINT, "");
		if (secretKey == null)
		{
			throw new IllegalArgumentException("no configuration found for secretKey");
		}
		if (bpeUrl == null)
		{
			throw new IllegalArgumentException("no configuration found for endpoint url");
		}

		final String signature = BuckarooGatewayUtil.calculateBuckarooSignature(requestParams, secretKey);
		final HttpPost httpPost = BuckarooGatewayUtil.createHttpPostFromMap(signature, bpeUrl, "TransactionRequest", requestParams);

		//debug
		LOG.info("posting to url: [" + bpeUrl + "]");
		try
		{
			LOG.info("..POST entity : [" + URLDecoder.decode(EntityUtils.toString(httpPost.getEntity()), "UTF-8") + "]");
		}
		catch (final Exception e)
		{
			LOG.warn(e);
		}
		LOG.info("(1) audit request before sending it to buckaroo");
		final IdealPaymentRequestModel idealPaymentRequestModel = BuckarooGatewayUtil.getPaymentRequestModelFromMap(requestParams,
				null);
		try
		{
			idealPaymentRequestModel.setOrder(order);
			getModelService().save(idealPaymentRequestModel);
			LOG.info("(2) invoke the buckaroo /nvp api ");
			final CloseableHttpResponse response = httpclient.execute(httpPost);
			final Map<String, String> responseMap = BuckarooGatewayUtil.createMapFromResponse(response.getEntity());
			final BuckarooStatusEnum buckarooStatus = BuckarooStatusEnum.getStatusForCode(responseMap
					.get(BuckarooGatewayUtil.BRQ_STATUSCODE));
			BuckarooGatewayUtil.getPaymentRequestModelFromMap(responseMap, idealPaymentRequestModel);
			LOG.debug("BRQ_STATUSCODE from buckaroo: " + buckarooStatus);
			idealPaymentRequestModel.setHttpStatusLine(response.getStatusLine().getStatusCode() + " - "
					+ response.getStatusLine().getReasonPhrase());
			response.close();
			LOG.info("(3) audit the request after having sent it to buckaroo");
			getModelService().save(idealPaymentRequestModel);
			return idealPaymentRequestModel;
		}
		catch (final ClientProtocolException e)
		{
			LOG.error("Technical error sending the HTTP request to [" + bpeUrl + "]", e);
			idealPaymentRequestModel.setHttpStatusLine("ClientProtocolException sending the request [" + e.getMessage() + "]");
		}
		catch (final IOException e)
		{
			LOG.error("Technical error sending the HTTP request to [" + bpeUrl + "]", e);
			idealPaymentRequestModel.setHttpStatusLine("IOException sending the request [" + e.getMessage() + "]");
		}
		catch (final Throwable t)
		{
			LOG.error("Technical error sending the HTTP request to [" + bpeUrl + "]", t);
			idealPaymentRequestModel.setHttpStatusLine("Throwable sending the request [" + t.getMessage() + "]");
		}
		getModelService().save(idealPaymentRequestModel);
		return idealPaymentRequestModel;
	}

	@Override
	public void processBuckarooPushResponse(final OrderModel orderModel, final String buckarooStatus)
	{
		if (buckarooStatus == null)
		{
			throw new IllegalArgumentException("buckarooStatus may not be null");
		}

		final BuckarooStatusEnum status = BuckarooStatusEnum.getStatusForCode(buckarooStatus);
		if (status == null)
		{
			throw new IllegalArgumentException("unknown bucakroo status: [" + buckarooStatus + "]");
		}

		if (status.isFinalStatus())
		{
			LOG.debug("got final status: [" + buckarooStatus + "], updating order");
			if (status.equals(BuckarooStatusEnum.Success))
			{
				setOrderToPaid(orderModel);
				if (orderModel != null)
				{
					for (final CartModel cart : orderModel.getUser().getCarts())
					{
						if (StringUtils.equalsIgnoreCase(orderModel.getCartCode(), cart.getCode()))
						{
							getModelService().remove(cart);
							getModelService().refresh(orderModel);
							LOG.debug("Found the referenced cart and removed them.");
							break;
						}
					}
				}
			}
			else
			{
				setOrderToNotPaid(orderModel, status);
			}
		}
		else
		{
			LOG.debug("got non final status: [" + buckarooStatus + "], keeping order unchanged");
		}
	}

	@Override
	public void validateOrderPushResponseFields(final OrderModel orderToProcess, final String currency, final String amount,
			final String amountCredit) throws IllegalArgumentException
	{
		// check that amount was given, not amount credit
		if (StringUtils.isBlank(currency) || StringUtils.isBlank(amount))
		{
			throw new IllegalArgumentException("Invalid currency[" + currency + "] or amount[" + amount + "]");
		}
		// check order is not already paid / completed
		if (orderToProcess.getStatus() != null)
		{
			if (orderToProcess.getStatus().equals(OrderStatus.PAYMENT_CAPTURED)
					|| orderToProcess.getStatus().equals(OrderStatus.COMPLETED))
			{
				throw new IllegalArgumentException("Order status is already [" + orderToProcess.getStatus() + "]");
			}
		}
		// check currency and amount matches the orders
		if (orderToProcess.getCurrency() != null)
		{
			if (!StringUtils.equalsIgnoreCase(orderToProcess.getCurrency().getIsocode(), currency))
			{
				throw new IllegalArgumentException("Weird currency received, expected[" + orderToProcess.getCurrency().getIsocode()
						+ "] received[" + currency + "]");
			}
		}
		if (orderToProcess.getTotalPrice() != null)
		{
			final DoubleConverter priceConv = new DoubleConverter();

			//uses . as decimal separator
			priceConv.setUseLocaleFormat(true);
			priceConv.setLocale(Locale.ENGLISH);
			final Double amountDouble = (Double) priceConv.convert(Double.class, amount);

			if (!Precision.equals(amountDouble.doubleValue(), orderToProcess.getTotalPrice().doubleValue(), DOUBLE_COMPARISON_DELTA))
			{
				throw new IllegalArgumentException("Weird amount received, expected[" + orderToProcess.getTotalPrice()
						+ "] received[" + amountDouble + "]");
			}
		}
	}

	/**
	 * Validate websiteKey, testMode and signature of a buckaroo request
	 * 
	 * throws IllegalArgumentException if request parameter is not valid.
	 * 
	 * @param websiteKey
	 *           the website key
	 * @param testMode
	 *           the test mode
	 * @param signature
	 *           the signature
	 * @param fullRequestParams
	 *           the full request params
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 */
	@Override
	public void validateBasicPushResponseFields(final String websiteKey, final String testMode, final String signature,
			final Map<String, String[]> fullRequestParams) throws IllegalArgumentException
	{

		final Configuration config = configurationService.getConfiguration();

		final String configWebsiteKey = config.getString(BuckarooConstants.CONFIG_WEBSITE_KEY, "");
		final String configSecretKey = config.getString(BuckarooConstants.CONFIG_SECRET_KEY, "");
		final boolean configTestMode = config.getBoolean(BuckarooConstants.CONFIG_TEST_MODE, false);

		//check website key
		if (StringUtils.isBlank(websiteKey))
		{
			throw new IllegalArgumentException("brq_websitekey is required");
		}
		else if (!StringUtils.equals(configWebsiteKey, websiteKey))
		{
			LOG.warn("brq_websitekey not matching configured value");
			throw new IllegalArgumentException("brq_websitekey not matching configured value");
		}

		//check test mode
		boolean isTestMode;
		if (StringUtils.isBlank(testMode) || StringUtils.equalsIgnoreCase("false", testMode))
		{
			isTestMode = false;
		}
		else if (StringUtils.equalsIgnoreCase("true", testMode))
		{
			isTestMode = true;
		}
		else
		{
			//not a known mode
			throw new IllegalArgumentException("brq_test not true or false");
		}

		if (isTestMode != configTestMode)
		{
			throw new IllegalArgumentException("brq_test not matching configured value");
		}

		//check signature
		if (StringUtils.isBlank(signature))
		{
			throw new IllegalArgumentException("brq_signature is required");
		}
		else
		{
			final Map<String, String> flatParams = BuckarooGatewayUtil.flattenBuckarooParams(fullRequestParams);

			final String calculatedSig = BuckarooGatewayUtil.calculateBuckarooSignature(flatParams, configSecretKey);
			if (!calculatedSig.equals(signature))
			{
				LOG.warn("calculated signature: [" + calculatedSig + "] did not match request signature: [" + signature + "]");
				throw new IllegalArgumentException("brq_signature did not match");
			}
		}
	}



	/**
	 * Sets the order to paid.
	 * 
	 * @param orderModel
	 *           the new order to paid
	 */
	private void setOrderToPaid(final OrderModel orderModel)
	{
		orderModel.setStatus(OrderStatus.PAYMENT_AUTHORIZED);
		orderModel.setPaymentStatus(PaymentStatus.PAID);
		getModelService().save(orderModel);
	}

	/**
	 * Handles all other statuses than Success.
	 * 
	 * @param orderModel
	 *           the order model
	 * @param status
	 *           the status
	 */
	private void setOrderToNotPaid(final OrderModel orderModel, final BuckarooStatusEnum status)
	{
		switch (status)
		{
			case Failed:
			case Validation_Failure:
			case Technical_Failure:
				orderModel.setStatus(OrderStatus.PROCESSING_ERROR);
				break;
			case Cancelled_By_Merchant:
			case Cancelled_By_User:
				orderModel.setStatus(OrderStatus.CANCELLED);
				break;
			case Rejected:
				orderModel.setStatus(OrderStatus.PAYMENT_NOT_AUTHORIZED);
				break;
			default:
				LOG.warn("Handling of Buckaroo status [" + status + "] not yet implemented!");
		}

		orderModel.setPaymentStatus(PaymentStatus.NOTPAID);
		getModelService().save(orderModel);
	}

	public ConfigurationService getConfigurationService()
	{
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	public I18NService getI18NService()
	{
		return i18NService;
	}

	public void setI18NService(final I18NService i18nService)
	{
		i18NService = i18nService;
	}

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	public CommandFactoryRegistry getCommandFactoryRegistry()
	{
		return commandFactoryRegistry;
	}


	public void setCommandFactoryRegistry(final CommandFactoryRegistry commandFactoryRegistry)
	{
		this.commandFactoryRegistry = commandFactoryRegistry;
	}



}
