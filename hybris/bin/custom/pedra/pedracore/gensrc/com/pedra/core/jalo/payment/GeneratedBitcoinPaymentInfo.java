/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.core.jalo.payment;

import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.pedra.core.jalo.payment.BitcoinPaymentInfo BitcoinPaymentInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBitcoinPaymentInfo extends PaymentInfo
{
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PaymentInfo.DEFAULT_INITIAL_ATTRIBUTES);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
}
