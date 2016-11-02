/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.core.jalo;

import com.pedra.core.constants.PedraCoreConstants;
import com.pedra.core.jalo.PedraProduct;
import com.pedra.core.jalo.PedraSizeVariantProduct;
import com.pedra.core.jalo.PedraStyleVariantProduct;
import com.pedra.core.jalo.payment.BitcoinPaymentInfo;
import de.hybris.platform.customerreview.jalo.CustomerReview;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.User;
import java.util.Map;

/**
 * Generated class for type <code>PedraCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPedraCoreManager extends Extension
{
	public BitcoinPaymentInfo createBitcoinPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PedraCoreConstants.TC.BITCOINPAYMENTINFO );
			return (BitcoinPaymentInfo)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating BitcoinPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public BitcoinPaymentInfo createBitcoinPaymentInfo(final Map attributeValues)
	{
		return createBitcoinPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public PedraProduct createPedraProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PedraCoreConstants.TC.PEDRAPRODUCT );
			return (PedraProduct)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating PedraProduct : "+e.getMessage(), 0 );
		}
	}
	
	public PedraProduct createPedraProduct(final Map attributeValues)
	{
		return createPedraProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public PedraSizeVariantProduct createPedraSizeVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PedraCoreConstants.TC.PEDRASIZEVARIANTPRODUCT );
			return (PedraSizeVariantProduct)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating PedraSizeVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public PedraSizeVariantProduct createPedraSizeVariantProduct(final Map attributeValues)
	{
		return createPedraSizeVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	public PedraStyleVariantProduct createPedraStyleVariantProduct(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PedraCoreConstants.TC.PEDRASTYLEVARIANTPRODUCT );
			return (PedraStyleVariantProduct)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating PedraStyleVariantProduct : "+e.getMessage(), 0 );
		}
	}
	
	public PedraStyleVariantProduct createPedraStyleVariantProduct(final Map attributeValues)
	{
		return createPedraStyleVariantProduct( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerReview.email</code> attribute.
	 * @return the email
	 */
	public String getEmail(final SessionContext ctx, final CustomerReview item)
	{
		return (String)item.getProperty( ctx, PedraCoreConstants.Attributes.CustomerReview.EMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerReview.email</code> attribute.
	 * @return the email
	 */
	public String getEmail(final CustomerReview item)
	{
		return getEmail( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerReview.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final SessionContext ctx, final CustomerReview item, final String value)
	{
		item.setProperty(ctx, PedraCoreConstants.Attributes.CustomerReview.EMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerReview.email</code> attribute. 
	 * @param value the email
	 */
	public void setEmail(final CustomerReview item, final String value)
	{
		setEmail( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return PedraCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.internalOnly</code> attribute.
	 * @return the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public Boolean isInternalOnly(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PedraCoreConstants.Attributes.Product.INTERNALONLY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.internalOnly</code> attribute.
	 * @return the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public Boolean isInternalOnly(final Product item)
	{
		return isInternalOnly( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.internalOnly</code> attribute. 
	 * @return the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public boolean isInternalOnlyAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isInternalOnly( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.internalOnly</code> attribute. 
	 * @return the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public boolean isInternalOnlyAsPrimitive(final Product item)
	{
		return isInternalOnlyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.internalOnly</code> attribute. 
	 * @param value the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public void setInternalOnly(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PedraCoreConstants.Attributes.Product.INTERNALONLY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.internalOnly</code> attribute. 
	 * @param value the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public void setInternalOnly(final Product item, final Boolean value)
	{
		setInternalOnly( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.internalOnly</code> attribute. 
	 * @param value the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public void setInternalOnly(final SessionContext ctx, final Product item, final boolean value)
	{
		setInternalOnly( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.internalOnly</code> attribute. 
	 * @param value the internalOnly - Defines if the product should be sold to hybris employees only.
	 */
	public void setInternalOnly(final Product item, final boolean value)
	{
		setInternalOnly( getSession().getSessionContext(), item, value );
	}
	
}
