/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.ideal.jalo.payment;

import com.pedra.ideal.constants.IdealpaymentConstants;
import com.pedra.ideal.jalo.payment.IdealPaymentRequest;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.pedra.ideal.jalo.payment.IdealPaymentResponse IdealPaymentResponse}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedIdealPaymentResponse extends GenericItem
{
	/** Qualifier of the <code>IdealPaymentResponse.responseFull</code> attribute **/
	public static final String RESPONSEFULL = "responseFull";
	/** Qualifier of the <code>IdealPaymentResponse.status</code> attribute **/
	public static final String STATUS = "status";
	/** Qualifier of the <code>IdealPaymentResponse.responseTime</code> attribute **/
	public static final String RESPONSETIME = "responseTime";
	/** Qualifier of the <code>IdealPaymentResponse.request</code> attribute **/
	public static final String REQUEST = "request";
	/** Qualifier of the <code>IdealPaymentResponse.orderCode</code> attribute **/
	public static final String ORDERCODE = "orderCode";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n REQUEST's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedIdealPaymentResponse> REQUESTHANDLER = new BidirectionalOneToManyHandler<GeneratedIdealPaymentResponse>(
	IdealpaymentConstants.TC.IDEALPAYMENTRESPONSE,
	false,
	"request",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(RESPONSEFULL, AttributeMode.INITIAL);
		tmp.put(STATUS, AttributeMode.INITIAL);
		tmp.put(RESPONSETIME, AttributeMode.INITIAL);
		tmp.put(REQUEST, AttributeMode.INITIAL);
		tmp.put(ORDERCODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		REQUESTHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.orderCode</code> attribute.
	 * @return the orderCode - Copy of the orders code
	 */
	public String getOrderCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.orderCode</code> attribute.
	 * @return the orderCode - Copy of the orders code
	 */
	public String getOrderCode()
	{
		return getOrderCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.orderCode</code> attribute. 
	 * @param value the orderCode - Copy of the orders code
	 */
	public void setOrderCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.orderCode</code> attribute. 
	 * @param value the orderCode - Copy of the orders code
	 */
	public void setOrderCode(final String value)
	{
		setOrderCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.request</code> attribute.
	 * @return the request
	 */
	public IdealPaymentRequest getRequest(final SessionContext ctx)
	{
		return (IdealPaymentRequest)getProperty( ctx, REQUEST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.request</code> attribute.
	 * @return the request
	 */
	public IdealPaymentRequest getRequest()
	{
		return getRequest( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.request</code> attribute. 
	 * @param value the request
	 */
	public void setRequest(final SessionContext ctx, final IdealPaymentRequest value)
	{
		REQUESTHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.request</code> attribute. 
	 * @param value the request
	 */
	public void setRequest(final IdealPaymentRequest value)
	{
		setRequest( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.responseFull</code> attribute.
	 * @return the responseFull - Stores the raw push response
	 */
	public String getResponseFull(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RESPONSEFULL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.responseFull</code> attribute.
	 * @return the responseFull - Stores the raw push response
	 */
	public String getResponseFull()
	{
		return getResponseFull( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.responseFull</code> attribute. 
	 * @param value the responseFull - Stores the raw push response
	 */
	public void setResponseFull(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RESPONSEFULL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.responseFull</code> attribute. 
	 * @param value the responseFull - Stores the raw push response
	 */
	public void setResponseFull(final String value)
	{
		setResponseFull( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.responseTime</code> attribute.
	 * @return the responseTime - The time when the push response was created at buckaroo
	 */
	public Date getResponseTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, RESPONSETIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.responseTime</code> attribute.
	 * @return the responseTime - The time when the push response was created at buckaroo
	 */
	public Date getResponseTime()
	{
		return getResponseTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.responseTime</code> attribute. 
	 * @param value the responseTime - The time when the push response was created at buckaroo
	 */
	public void setResponseTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, RESPONSETIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.responseTime</code> attribute. 
	 * @param value the responseTime - The time when the push response was created at buckaroo
	 */
	public void setResponseTime(final Date value)
	{
		setResponseTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.status</code> attribute.
	 * @return the status - Status code of the transaction
	 */
	public String getStatus(final SessionContext ctx)
	{
		return (String)getProperty( ctx, STATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.status</code> attribute.
	 * @return the status - Status code of the transaction
	 */
	public String getStatus()
	{
		return getStatus( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.status</code> attribute. 
	 * @param value the status - Status code of the transaction
	 */
	public void setStatus(final SessionContext ctx, final String value)
	{
		setProperty(ctx, STATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.status</code> attribute. 
	 * @param value the status - Status code of the transaction
	 */
	public void setStatus(final String value)
	{
		setStatus( getSession().getSessionContext(), value );
	}
	
}
