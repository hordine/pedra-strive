/**
 * 
 */
package com.pedra.core.model;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;


/**
 * @author henrique.ordine
 * 
 */
public class MostSignificantProductHandler implements DynamicAttributeHandler<ProductModel, OrderModel>
{


	@SuppressWarnings("boxing")
	@Override
	public ProductModel get(final OrderModel order)
	{
		AbstractOrderEntryModel mostSignificant = null;
		if (order.getEntries() != null)
		{
			for (final AbstractOrderEntryModel entry : order.getEntries())
			{
				if (entry.getProduct() != null)
				{
					if (mostSignificant == null)
					{
						mostSignificant = entry;
					}
					if (entry.getQuantity() > mostSignificant.getQuantity())
					{
						mostSignificant = entry;
					}
				}
			}
		}
		if (mostSignificant != null)
		{
			return mostSignificant.getProduct();
		}
		return null;
	}

	@Override
	public void set(final OrderModel order, final ProductModel product)
	{
		// don't do anything

	}

}
