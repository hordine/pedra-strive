/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.ideal.jalo.payment;

import com.pedra.ideal.constants.IdealpaymentConstants;
import com.pedra.ideal.jalo.payment.IdealPaymentResponse;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.pedra.ideal.jalo.payment.IdealPaymentRequest IdealPaymentRequest}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedIdealPaymentRequest extends GenericItem
{
	/** Qualifier of the <code>IdealPaymentRequest.responses</code> attribute **/
	public static final String RESPONSES = "responses";
	/** Qualifier of the <code>IdealPaymentRequest.resultFull</code> attribute **/
	public static final String RESULTFULL = "resultFull";
	/** Qualifier of the <code>IdealPaymentRequest.orderCode</code> attribute **/
	public static final String ORDERCODE = "orderCode";
	/** Qualifier of the <code>IdealPaymentRequest.redirectUrl</code> attribute **/
	public static final String REDIRECTURL = "redirectUrl";
	/** Qualifier of the <code>IdealPaymentRequest.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>IdealPaymentRequest.requestParams</code> attribute **/
	public static final String REQUESTPARAMS = "requestParams";
	/** Qualifier of the <code>IdealPaymentRequest.httpStatusLine</code> attribute **/
	public static final String HTTPSTATUSLINE = "httpStatusLine";
	/** Qualifier of the <code>IdealPaymentRequest.amount</code> attribute **/
	public static final String AMOUNT = "amount";
	/** Qualifier of the <code>IdealPaymentRequest.requestTime</code> attribute **/
	public static final String REQUESTTIME = "requestTime";
	/** Qualifier of the <code>IdealPaymentRequest.order</code> attribute **/
	public static final String ORDER = "order";
	/** Qualifier of the <code>IdealPaymentRequest.clientIP</code> attribute **/
	public static final String CLIENTIP = "clientIP";
	/**
	* {@link OneToManyHandler} for handling 1:n RESPONSES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<IdealPaymentResponse> RESPONSESHANDLER = new OneToManyHandler<IdealPaymentResponse>(
	IdealpaymentConstants.TC.IDEALPAYMENTRESPONSE,
	false,
	"request",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n ORDER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedIdealPaymentRequest> ORDERHANDLER = new BidirectionalOneToManyHandler<GeneratedIdealPaymentRequest>(
	IdealpaymentConstants.TC.IDEALPAYMENTREQUEST,
	false,
	"order",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(RESULTFULL, AttributeMode.INITIAL);
		tmp.put(ORDERCODE, AttributeMode.INITIAL);
		tmp.put(REDIRECTURL, AttributeMode.INITIAL);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(REQUESTPARAMS, AttributeMode.INITIAL);
		tmp.put(HTTPSTATUSLINE, AttributeMode.INITIAL);
		tmp.put(AMOUNT, AttributeMode.INITIAL);
		tmp.put(REQUESTTIME, AttributeMode.INITIAL);
		tmp.put(ORDER, AttributeMode.INITIAL);
		tmp.put(CLIENTIP, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.amount</code> attribute.
	 * @return the amount - Amount to pay
	 */
	public String getAmount(final SessionContext ctx)
	{
		return (String)getProperty( ctx, AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.amount</code> attribute.
	 * @return the amount - Amount to pay
	 */
	public String getAmount()
	{
		return getAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.amount</code> attribute. 
	 * @param value the amount - Amount to pay
	 */
	public void setAmount(final SessionContext ctx, final String value)
	{
		setProperty(ctx, AMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.amount</code> attribute. 
	 * @param value the amount - Amount to pay
	 */
	public void setAmount(final String value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.clientIP</code> attribute.
	 * @return the clientIP - IP of the client making the iDEAL payment
	 */
	public String getClientIP(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTIP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.clientIP</code> attribute.
	 * @return the clientIP - IP of the client making the iDEAL payment
	 */
	public String getClientIP()
	{
		return getClientIP( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.clientIP</code> attribute. 
	 * @param value the clientIP - IP of the client making the iDEAL payment
	 */
	public void setClientIP(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTIP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.clientIP</code> attribute. 
	 * @param value the clientIP - IP of the client making the iDEAL payment
	 */
	public void setClientIP(final String value)
	{
		setClientIP( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		ORDERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.currency</code> attribute.
	 * @return the currency - Currency to pay
	 */
	public String getCurrency(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.currency</code> attribute.
	 * @return the currency - Currency to pay
	 */
	public String getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.currency</code> attribute. 
	 * @param value the currency - Currency to pay
	 */
	public void setCurrency(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.currency</code> attribute. 
	 * @param value the currency - Currency to pay
	 */
	public void setCurrency(final String value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.httpStatusLine</code> attribute.
	 * @return the httpStatusLine - HTTP Status line returned from buckaroo
	 */
	public String getHttpStatusLine(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HTTPSTATUSLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.httpStatusLine</code> attribute.
	 * @return the httpStatusLine - HTTP Status line returned from buckaroo
	 */
	public String getHttpStatusLine()
	{
		return getHttpStatusLine( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.httpStatusLine</code> attribute. 
	 * @param value the httpStatusLine - HTTP Status line returned from buckaroo
	 */
	public void setHttpStatusLine(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HTTPSTATUSLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.httpStatusLine</code> attribute. 
	 * @param value the httpStatusLine - HTTP Status line returned from buckaroo
	 */
	public void setHttpStatusLine(final String value)
	{
		setHttpStatusLine( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder(final SessionContext ctx)
	{
		return (Order)getProperty( ctx, ORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.order</code> attribute.
	 * @return the order
	 */
	public Order getOrder()
	{
		return getOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final SessionContext ctx, final Order value)
	{
		ORDERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.order</code> attribute. 
	 * @param value the order
	 */
	public void setOrder(final Order value)
	{
		setOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.orderCode</code> attribute.
	 * @return the orderCode - Copy of the orders code
	 */
	public String getOrderCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ORDERCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.orderCode</code> attribute.
	 * @return the orderCode - Copy of the orders code
	 */
	public String getOrderCode()
	{
		return getOrderCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.orderCode</code> attribute. 
	 * @param value the orderCode - Copy of the orders code
	 */
	public void setOrderCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ORDERCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.orderCode</code> attribute. 
	 * @param value the orderCode - Copy of the orders code
	 */
	public void setOrderCode(final String value)
	{
		setOrderCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.redirectUrl</code> attribute.
	 * @return the redirectUrl - URL to redirect client to
	 */
	public String getRedirectUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REDIRECTURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.redirectUrl</code> attribute.
	 * @return the redirectUrl - URL to redirect client to
	 */
	public String getRedirectUrl()
	{
		return getRedirectUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.redirectUrl</code> attribute. 
	 * @param value the redirectUrl - URL to redirect client to
	 */
	public void setRedirectUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REDIRECTURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.redirectUrl</code> attribute. 
	 * @param value the redirectUrl - URL to redirect client to
	 */
	public void setRedirectUrl(final String value)
	{
		setRedirectUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.requestParams</code> attribute.
	 * @return the requestParams - Stores the raw request
	 */
	public String getRequestParams(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REQUESTPARAMS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.requestParams</code> attribute.
	 * @return the requestParams - Stores the raw request
	 */
	public String getRequestParams()
	{
		return getRequestParams( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.requestParams</code> attribute. 
	 * @param value the requestParams - Stores the raw request
	 */
	public void setRequestParams(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REQUESTPARAMS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.requestParams</code> attribute. 
	 * @param value the requestParams - Stores the raw request
	 */
	public void setRequestParams(final String value)
	{
		setRequestParams( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.requestTime</code> attribute.
	 * @return the requestTime - The time when the request was made
	 */
	public Date getRequestTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, REQUESTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.requestTime</code> attribute.
	 * @return the requestTime - The time when the request was made
	 */
	public Date getRequestTime()
	{
		return getRequestTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.requestTime</code> attribute. 
	 * @param value the requestTime - The time when the request was made
	 */
	public void setRequestTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, REQUESTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.requestTime</code> attribute. 
	 * @param value the requestTime - The time when the request was made
	 */
	public void setRequestTime(final Date value)
	{
		setRequestTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.responses</code> attribute.
	 * @return the responses
	 */
	public Collection<IdealPaymentResponse> getResponses(final SessionContext ctx)
	{
		return RESPONSESHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.responses</code> attribute.
	 * @return the responses
	 */
	public Collection<IdealPaymentResponse> getResponses()
	{
		return getResponses( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.responses</code> attribute. 
	 * @param value the responses
	 */
	public void setResponses(final SessionContext ctx, final Collection<IdealPaymentResponse> value)
	{
		RESPONSESHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.responses</code> attribute. 
	 * @param value the responses
	 */
	public void setResponses(final Collection<IdealPaymentResponse> value)
	{
		setResponses( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to responses. 
	 * @param value the item to add to responses
	 */
	public void addToResponses(final SessionContext ctx, final IdealPaymentResponse value)
	{
		RESPONSESHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to responses. 
	 * @param value the item to add to responses
	 */
	public void addToResponses(final IdealPaymentResponse value)
	{
		addToResponses( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from responses. 
	 * @param value the item to remove from responses
	 */
	public void removeFromResponses(final SessionContext ctx, final IdealPaymentResponse value)
	{
		RESPONSESHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from responses. 
	 * @param value the item to remove from responses
	 */
	public void removeFromResponses(final IdealPaymentResponse value)
	{
		removeFromResponses( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultFull</code> attribute.
	 * @return the resultFull - Stores the raw result
	 */
	public String getResultFull(final SessionContext ctx)
	{
		return (String)getProperty( ctx, RESULTFULL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultFull</code> attribute.
	 * @return the resultFull - Stores the raw result
	 */
	public String getResultFull()
	{
		return getResultFull( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultFull</code> attribute. 
	 * @param value the resultFull - Stores the raw result
	 */
	public void setResultFull(final SessionContext ctx, final String value)
	{
		setProperty(ctx, RESULTFULL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultFull</code> attribute. 
	 * @param value the resultFull - Stores the raw result
	 */
	public void setResultFull(final String value)
	{
		setResultFull( getSession().getSessionContext(), value );
	}
	
}
