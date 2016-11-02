/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.ideal.jalo.payment;

import com.pedra.ideal.constants.IdealpaymentConstants;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.pedra.ideal.jalo.payment.IdealPaymentInfo IdealPaymentInfo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedIdealPaymentInfo extends PaymentInfo
{
	/** Qualifier of the <code>IdealPaymentInfo.clientIP</code> attribute **/
	public static final String CLIENTIP = "clientIP";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(PaymentInfo.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CLIENTIP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentInfo.clientIP</code> attribute.
	 * @return the clientIP - IP of the client making the iDEAL payment
	 */
	public String getClientIP(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTIP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentInfo.clientIP</code> attribute.
	 * @return the clientIP - IP of the client making the iDEAL payment
	 */
	public String getClientIP()
	{
		return getClientIP( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentInfo.clientIP</code> attribute. 
	 * @param value the clientIP - IP of the client making the iDEAL payment
	 */
	public void setClientIP(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTIP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentInfo.clientIP</code> attribute. 
	 * @param value the clientIP - IP of the client making the iDEAL payment
	 */
	public void setClientIP(final String value)
	{
		setClientIP( getSession().getSessionContext(), value );
	}
	
}
