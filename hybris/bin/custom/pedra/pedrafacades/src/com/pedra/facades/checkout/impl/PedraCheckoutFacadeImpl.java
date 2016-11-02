/**
 * 
 */
package com.pedra.facades.checkout.impl;

import de.hybris.platform.acceleratorfacades.order.impl.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pedra.facades.checkout.PedraCheckoutFacade;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCheckoutFacadeImpl extends AcceleratorCheckoutFacade implements PedraCheckoutFacade
{

	private static Logger log = Logger.getLogger(PedraCheckoutFacadeImpl.class);

	@Override
	public OrderData placeOrder() throws InvalidCartException
	{
		log.debug("Performing a Pedra place order now...");
		return super.placeOrder();
	}

	@Override
	public boolean authorizePayment(final String securityCode)
	{
		final CartModel cartModel = getCart();
		if (cartModel != null)
		{
			final UserModel currentUser = getCurrentUserForCheckout();
			if (cartModel.getUser().equals(currentUser))
			{
				final CreditCardPaymentInfoModel creditCardPaymentInfoModel = (CreditCardPaymentInfoModel) cartModel.getPaymentInfo();
				if (creditCardPaymentInfoModel != null && StringUtils.isNotBlank(creditCardPaymentInfoModel.getSubscriptionId()))
				{
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizePayment(
							cartModel, securityCode, getPaymentProvider());

					return paymentTransactionEntryModel != null
							&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
									.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
				}
			}
		}

		return false;
	}

}
