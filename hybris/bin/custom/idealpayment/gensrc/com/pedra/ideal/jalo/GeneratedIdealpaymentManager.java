/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.ideal.jalo;

import com.pedra.ideal.constants.IdealpaymentConstants;
import com.pedra.ideal.jalo.payment.IdealPaymentInfo;
import com.pedra.ideal.jalo.payment.IdealPaymentRequest;
import com.pedra.ideal.jalo.payment.IdealPaymentResponse;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Map;

/**
 * Generated class for type <code>IdealpaymentManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedIdealpaymentManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n REQUESTS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<IdealPaymentRequest> ORDERIDEALPAYMENTREQUESTRELATIONREQUESTSHANDLER = new OneToManyHandler<IdealPaymentRequest>(
	IdealpaymentConstants.TC.IDEALPAYMENTREQUEST,
	false,
	"order",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Order.cartCode</code> attribute.
	 * @return the cartCode - The cart code of the cart for which the order was placed.
	 */
	public String getCartCode(final SessionContext ctx, final Order item)
	{
		return (String)item.getProperty( ctx, IdealpaymentConstants.Attributes.Order.CARTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Order.cartCode</code> attribute.
	 * @return the cartCode - The cart code of the cart for which the order was placed.
	 */
	public String getCartCode(final Order item)
	{
		return getCartCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Order.cartCode</code> attribute. 
	 * @param value the cartCode - The cart code of the cart for which the order was placed.
	 */
	public void setCartCode(final SessionContext ctx, final Order item, final String value)
	{
		item.setProperty(ctx, IdealpaymentConstants.Attributes.Order.CARTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Order.cartCode</code> attribute. 
	 * @param value the cartCode - The cart code of the cart for which the order was placed.
	 */
	public void setCartCode(final Order item, final String value)
	{
		setCartCode( getSession().getSessionContext(), item, value );
	}
	
	public IdealPaymentInfo createIdealPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( IdealpaymentConstants.TC.IDEALPAYMENTINFO );
			return (IdealPaymentInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating IdealPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public IdealPaymentInfo createIdealPaymentInfo(final Map attributeValues)
	{
		return createIdealPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public IdealPaymentRequest createIdealPaymentRequest(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( IdealpaymentConstants.TC.IDEALPAYMENTREQUEST );
			return (IdealPaymentRequest)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating IdealPaymentRequest : "+e.getMessage(), 0 );
		}
	}
	
	public IdealPaymentRequest createIdealPaymentRequest(final Map attributeValues)
	{
		return createIdealPaymentRequest( getSession().getSessionContext(), attributeValues );
	}
	
	public IdealPaymentResponse createIdealPaymentResponse(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( IdealpaymentConstants.TC.IDEALPAYMENTRESPONSE );
			return (IdealPaymentResponse)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating IdealPaymentResponse : "+e.getMessage(), 0 );
		}
	}
	
	public IdealPaymentResponse createIdealPaymentResponse(final Map attributeValues)
	{
		return createIdealPaymentResponse( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return IdealpaymentConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Order.requests</code> attribute.
	 * @return the requests
	 */
	public Collection<IdealPaymentRequest> getRequests(final SessionContext ctx, final Order item)
	{
		return ORDERIDEALPAYMENTREQUESTRELATIONREQUESTSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Order.requests</code> attribute.
	 * @return the requests
	 */
	public Collection<IdealPaymentRequest> getRequests(final Order item)
	{
		return getRequests( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Order.requests</code> attribute. 
	 * @param value the requests
	 */
	public void setRequests(final SessionContext ctx, final Order item, final Collection<IdealPaymentRequest> value)
	{
		ORDERIDEALPAYMENTREQUESTRELATIONREQUESTSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Order.requests</code> attribute. 
	 * @param value the requests
	 */
	public void setRequests(final Order item, final Collection<IdealPaymentRequest> value)
	{
		setRequests( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to requests. 
	 * @param value the item to add to requests
	 */
	public void addToRequests(final SessionContext ctx, final Order item, final IdealPaymentRequest value)
	{
		ORDERIDEALPAYMENTREQUESTRELATIONREQUESTSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to requests. 
	 * @param value the item to add to requests
	 */
	public void addToRequests(final Order item, final IdealPaymentRequest value)
	{
		addToRequests( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from requests. 
	 * @param value the item to remove from requests
	 */
	public void removeFromRequests(final SessionContext ctx, final Order item, final IdealPaymentRequest value)
	{
		ORDERIDEALPAYMENTREQUESTRELATIONREQUESTSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from requests. 
	 * @param value the item to remove from requests
	 */
	public void removeFromRequests(final Order item, final IdealPaymentRequest value)
	{
		removeFromRequests( getSession().getSessionContext(), item, value );
	}
	
}
