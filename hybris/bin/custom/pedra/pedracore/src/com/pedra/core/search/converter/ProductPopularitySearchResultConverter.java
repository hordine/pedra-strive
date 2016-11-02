/**
 * 
 */
package com.pedra.core.search.converter;

import de.hybris.platform.converters.impl.AbstractConverter;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.ArrayList;
import java.util.List;

import com.pedra.core.data.ProductPopularityData;


/**
 * @author henrique.ordine
 * 
 */
public class ProductPopularitySearchResultConverter extends AbstractConverter<SearchResult, List<ProductPopularityData>>
{

	@Override
	protected List<ProductPopularityData> createTarget()
	{
		return new ArrayList<ProductPopularityData>();
	}

	@Override
	public void populate(final SearchResult source, final List<ProductPopularityData> target)
	{
		if (source != null)
		{
			final List<ArrayList> resultSet = source.getResult();
			for (final ArrayList row : resultSet)
			{
				final ProductPopularityData aProduct = new ProductPopularityData();
				aProduct.setProductCode((String) row.get(0));
				aProduct.setQuantitySold((Long) row.get(1));
				target.add(aProduct);
			}
		}

	}

}
