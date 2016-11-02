/**
 * 
 */
package com.pedra.commerce.service.impl;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.commands.request.AuthorizationRequest;
import de.hybris.platform.payment.commands.request.SubscriptionAuthorizationRequest;
import de.hybris.platform.payment.commands.result.AuthorizationResult;
import de.hybris.platform.payment.dto.BillingInfo;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import com.pedra.commerce.service.PedraPaymentService;


/**
 * @author henrique.ordine
 * 
 */
public class PedraPaymentServiceImpl extends DefaultPaymentServiceImpl implements PedraPaymentService
{

	private ModelService modelService;
	private CommonI18NService commonI18NService;


	@Override
	public PaymentTransactionEntryModel authorize(final String merchantTransactionCode, final BigDecimal amount,
			final Currency currency, final AddressModel deliveryAddress, final String subscriptionID, final String cv2,
			final String paymentProvider) throws AdapterException
	{
		final BillingInfo shippingInfo = this.createBillingInfo(deliveryAddress);

		final PaymentTransactionModel transaction = (PaymentTransactionModel) getModelService().create(
				PaymentTransactionModel.class);
		transaction.setCode(merchantTransactionCode);

		return authorizeInternal(transaction, amount, currency, shippingInfo, null, subscriptionID, cv2, paymentProvider);
	}

	@SuppressWarnings("unused")
	private BillingInfo createBillingInfo(final AddressModel deliveryAddress, final AddressModel paymentAddress,
			final CardInfo card)
	{
		if ((card != null) && (card.getBillingInfo() == null) && (paymentAddress != null))
		{
			final BillingInfo billingInfo = new BillingInfo();
			billingInfo.setCity(paymentAddress.getTown());
			if (paymentAddress.getCountry() != null)
			{
				billingInfo.setCountry(paymentAddress.getCountry().getIsocode());
			}
			billingInfo.setEmail(paymentAddress.getEmail());
			billingInfo.setFirstName(paymentAddress.getFirstname());
			billingInfo.setLastName(paymentAddress.getLastname());
			billingInfo.setPhoneNumber(paymentAddress.getPhone1());
			billingInfo.setPostalCode(paymentAddress.getPostalcode());
			if (paymentAddress.getRegion() != null)
			{
				billingInfo.setState(paymentAddress.getRegion().getName());
			}
			billingInfo.setStreet1(paymentAddress.getStreetname());
			billingInfo.setStreet2(paymentAddress.getStreetnumber());
			card.setBillingInfo(billingInfo);
		}

		BillingInfo shippingInfo = null;
		if (deliveryAddress == null)
		{
			if (card != null)
			{
				shippingInfo = card.getBillingInfo();
			}
		}
		else
		{
			shippingInfo = new BillingInfo();
			shippingInfo.setCity(deliveryAddress.getTown());
			if (deliveryAddress.getCountry() != null)
			{
				shippingInfo.setCountry(deliveryAddress.getCountry().getIsocode());
			}
			shippingInfo.setEmail(deliveryAddress.getEmail());
			shippingInfo.setFirstName(deliveryAddress.getFirstname());
			shippingInfo.setLastName(deliveryAddress.getLastname());
			shippingInfo.setPhoneNumber(deliveryAddress.getPhone1());
			shippingInfo.setPostalCode(deliveryAddress.getPostalcode());
			if (deliveryAddress.getRegion() != null)
			{
				shippingInfo.setState(deliveryAddress.getRegion().getName());
			}
			shippingInfo.setStreet1(deliveryAddress.getStreetname());
			shippingInfo.setStreet2(deliveryAddress.getStreetnumber());
		}
		return shippingInfo;
	}

	private BillingInfo createBillingInfo(final AddressModel address)
	{
		if (address == null)
		{
			return null;
		}

		final BillingInfo billingInfo = new BillingInfo();

		billingInfo.setCity(address.getTown());
		if (address.getCountry() != null)
		{
			billingInfo.setCountry(address.getCountry().getIsocode());
		}
		billingInfo.setEmail(address.getEmail());
		billingInfo.setFirstName(address.getFirstname());
		billingInfo.setLastName(address.getLastname());
		billingInfo.setPhoneNumber(address.getPhone1());
		billingInfo.setPostalCode(address.getPostalcode());
		if (address.getRegion() != null)
		{
			billingInfo.setState(address.getRegion().getName());
		}
		billingInfo.setStreet1(address.getStreetname());
		billingInfo.setStreet2(address.getStreetnumber());

		return billingInfo;
	}

	private PaymentTransactionEntryModel authorizeInternal(final PaymentTransactionModel transaction, final BigDecimal amount,
			final Currency currency, final BillingInfo shippingInfo, final CardInfo card, final String subscriptionID,
			final String cv2, final String paymentProvider) throws AdapterException
	{
		final String newEntryCode = getNewEntryCode(transaction);
		AuthorizationResult result;
		if (subscriptionID == null)
		{
			result = getCardPaymentService().authorize(new AuthorizationRequest(newEntryCode, card, currency, amount, shippingInfo));
		}
		else
		{
			result = getCardPaymentService().authorize(
					new SubscriptionAuthorizationRequest(newEntryCode, subscriptionID, currency, amount, shippingInfo, cv2,
							paymentProvider));
		}
		transaction.setRequestId(result.getRequestId());
		transaction.setRequestToken(result.getRequestToken());
		transaction.setPaymentProvider(result.getPaymentProvider());
		getModelService().save(transaction);

		final PaymentTransactionEntryModel entry = (PaymentTransactionEntryModel) getModelService().create(
				PaymentTransactionEntryModel.class);
		entry.setAmount(result.getTotalAmount());
		if (result.getCurrency() != null)
		{
			entry.setCurrency(getCommonI18NService().getCurrency(result.getCurrency().getCurrencyCode()));
		}
		entry.setType(PaymentTransactionType.AUTHORIZATION);
		entry.setTime((result.getAuthorizationTime() == null) ? new Date() : result.getAuthorizationTime());
		entry.setPaymentTransaction(transaction);
		entry.setRequestId(result.getRequestId());
		entry.setRequestToken(result.getRequestToken());
		entry.setTransactionStatus(result.getTransactionStatus().toString());
		entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
		entry.setCode(newEntryCode);
		if (subscriptionID != null)
		{
			entry.setSubscriptionID(subscriptionID);
		}
		getModelService().save(entry);
		getModelService().refresh(transaction);
		return entry;
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

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Override
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}



}
