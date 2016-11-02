/**
 * 
 */
package com.pedra.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.pedra.facades.product.data.PedraProductData;


/**
 * @author henrique.ordine
 * 
 */
public class PedraProductPopulator implements Populator<ProductModel, PedraProductData>
{
	@Override
	public void populate(final ProductModel source, final PedraProductData target) throws ConversionException
	{
		target.setInternalOnly(source.getInternalOnly() == null ? false : source.getInternalOnly().booleanValue());
	}
}
