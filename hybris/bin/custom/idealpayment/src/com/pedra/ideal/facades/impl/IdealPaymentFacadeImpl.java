/**
 * 
 */
package com.pedra.ideal.facades.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import org.apache.log4j.Logger;

import com.pedra.ideal.data.IdealStatusEnum;
import com.pedra.ideal.data.PaymentRedirectData;
import com.pedra.ideal.facades.IdealPaymentFacade;
import com.pedra.ideal.model.payment.IdealPaymentRequestModel;
import com.pedra.ideal.service.IdealPaymentService;



/**
 * @author Henrique Ordine
 * 
 */
public class IdealPaymentFacadeImpl extends DefaultCheckoutFacade implements IdealPaymentFacade
{

	private static final Logger LOG = Logger.getLogger(IdealPaymentFacadeImpl.class);

	private IdealPaymentService idealPaymentService;
	private BaseStoreService baseStoreService;
	private CalculationService calculationService;


	@Override
	public PaymentRedirectData requestPayment(final String clientIP) throws InvalidCartException
	{
		final PaymentRedirectData result = new PaymentRedirectData(null, false, null, null, null);

		if (clientIP == null)
		{
			result.setErrorMsg("Client IP cannot be null");
		}
		else
		{
			final OrderData order = placeOrder();
			if (order == null)
			{
				LOG.error("RequestPayment did not have an order!");
				throw new InvalidCartException("No cart found to create order from");
			}
			LOG.debug("RequestPayment[" + order.getCode() + "]");

			final CustomerModel customerModel = (CustomerModel) getUserService().getCurrentUser();
			final BaseStoreModel store = getBaseStoreService().getCurrentBaseStore();

			try
			{
				final OrderModel orderModel = getCustomerAccountService().getOrderForCode(customerModel, order.getCode(), store);
				final IdealPaymentRequestModel request = getIdealPaymentService().createTransactionRequest(orderModel, clientIP);
				result.setRedirectUrl(request.getRedirectUrl());
				LOG.info("(4) redirect URL : " + request.getRedirectUrl());
				result.setIdealStatusCode(IdealStatusEnum.getStatusForCode(request.getResultCode()));
				result.setOrderCode(request.getOrderCode());

				if (IdealStatusEnum.Awaiting_Consumer.getStatusCode().equals(request.getResultCode())
						|| IdealStatusEnum.Pending_Input.getStatusCode().equals(request.getResultCode()))
				{
					result.setSuccessful(true);
				}
				else
				{
					result.setErrorMsg(request.getApiErrorMessage());
				}
			}
			catch (final ModelNotFoundException e)
			{
				LOG.error("Error finding order[" + order.getCode() + "] for customer[" + customerModel.getUid() + "] in basestore["
						+ store.getUid() + "]", e);
			}
		}
		return result;
	}

	@Override
	public OrderData placeOrder() throws InvalidCartException
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			final UserModel currentUser = getUserService().getCurrentUser();
			if (cartModel.getUser().equals(currentUser))
			{
				beforePlaceOrder(cartModel);

				try
				{
					getCalculationService().calculate(cartModel);
				}
				catch (final CalculationException e)
				{
					LOG.debug("Calculation of session cart not possible.", e);
				}

				final OrderModel orderModel = placeOrder(cartModel);
				afterPlaceOrder(cartModel, orderModel);

				if (orderModel != null)
				{
					return getOrderConverter().convert(orderModel);
				}
			}
		}
		return null;
	}

	/**
	 * For replacing the default behavior which removed the session cart when an order was placed.
	 * 
	 * @param cartModel
	 *           the cart model
	 * @param orderModel
	 *           the order model
	 */
	@Override
	protected void afterPlaceOrder(final CartModel cartModel, final OrderModel orderModel)
	{
		if (cartModel != null && orderModel != null)
		{
			orderModel.setCartCode(cartModel.getCode());
			getModelService().save(orderModel);
		}

		// Do nothing - this means the session cart is still existing as well as the new order. Logic for
		// removing session cart is in DefaultPaymentGatewayService when the appropriate push response arrives.
		LOG.debug("afterPlaceOrder - skipping session cart removal");
	}

	public IdealPaymentService getIdealPaymentService()
	{
		return idealPaymentService;
	}

	public void setIdealPaymentService(final IdealPaymentService idealPaymentService)
	{
		this.idealPaymentService = idealPaymentService;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	public void setCalculationService(final CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}

}
