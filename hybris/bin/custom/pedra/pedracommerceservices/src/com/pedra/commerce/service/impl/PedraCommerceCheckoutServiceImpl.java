/**
 * 
 */
package com.pedra.commerce.service.impl;

import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCheckoutService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;

import org.apache.commons.lang.StringUtils;

import com.pedra.commerce.service.PedraCommerceCheckoutService;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCommerceCheckoutServiceImpl extends DefaultCommerceCheckoutService implements PedraCommerceCheckoutService
{


	@Override
	protected PaymentTransactionEntryModel authorizePaymentAmount(final CartModel cartModel, final String securityCode,
			final String paymentProvider, final BigDecimal amount)
	{
		PaymentTransactionEntryModel transactionEntryModel = null;
		final PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
		if ((paymentInfo instanceof CreditCardPaymentInfoModel)
				&& (StringUtils.isNotBlank(((CreditCardPaymentInfoModel) paymentInfo).getSubscriptionId())))
		{
			final Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
			final String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
			transactionEntryModel = getPaymentService().authorize(merchantTransactionCode, amount, currency,
					cartModel.getDeliveryAddress(), ((CreditCardPaymentInfoModel) paymentInfo).getSubscriptionId(), securityCode,
					paymentProvider);
			if (transactionEntryModel != null)
			{
				final PaymentTransactionModel paymentTransaction = transactionEntryModel.getPaymentTransaction();

				if ((TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus()))
						|| (TransactionStatus.REVIEW.name().equals(transactionEntryModel.getTransactionStatus())))
				{
					paymentTransaction.setOrder(cartModel);
					getModelService().saveAll(new Object[]
					{ cartModel, paymentTransaction });
				}
				else
				{
					getModelService().removeAll(Arrays.asList(new ItemModel[]
					{ paymentTransaction, transactionEntryModel }));
				}
			}
		}
		return transactionEntryModel;
	}

	@Override
	public OrderModel placeOrder(final CartModel cartModel) throws InvalidCartException
	{
		return super.placeOrder(cartModel);
	}


}
