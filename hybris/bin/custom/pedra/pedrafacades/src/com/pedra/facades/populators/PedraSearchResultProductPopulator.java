/**
 * 
 */
package com.pedra.facades.populators;

import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.pedra.facades.product.data.PedraProductData;


/**
 * @author henrique.ordine
 * 
 */
public class PedraSearchResultProductPopulator implements Populator<SearchResultValueData, PedraProductData>
{
	@Override
	public void populate(final SearchResultValueData source, final PedraProductData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		final Boolean internalOnlyFlag = (Boolean) source.getValues().get("internalOnly");
		target.setInternalOnly(internalOnlyFlag == null ? false : internalOnlyFlag.booleanValue());
	}
}
