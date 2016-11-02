/**
 * 
 */
package com.pedra.ideal.facades;

import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.order.InvalidCartException;

import com.pedra.ideal.data.PaymentRedirectData;


/**
 * @author Henrique Ordine
 * 
 */
public interface IdealPaymentFacade extends CheckoutFacade
{
	PaymentRedirectData requestPayment(final String clientIP) throws InvalidCartException;
}
