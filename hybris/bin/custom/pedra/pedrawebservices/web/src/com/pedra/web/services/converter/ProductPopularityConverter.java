/**
 * 
 */
package com.pedra.web.services.converter;

import de.hybris.platform.converters.impl.AbstractConverter;

import java.util.ArrayList;
import java.util.List;

import com.pedra.core.data.ProductPopularityData;
import com.pedra.web.services.customdto.ProductPopularityDTO;
import com.pedra.web.services.customdto.ProductPopularityDTO;


/**
 * @author henrique.ordine
 * 
 */
public class ProductPopularityConverter extends AbstractConverter<List<ProductPopularityData>, List<ProductPopularityDTO>>
{

	@Override
	protected List<ProductPopularityDTO> createTarget()
	{
		return new ArrayList<ProductPopularityDTO>();
	}

	@Override
	public void populate(final List<ProductPopularityData> source, final List<ProductPopularityDTO> target)
	{
		if (source != null)
		{
			for (final ProductPopularityData productPopularity : source)
			{
				final ProductPopularityDTO dto = new ProductPopularityDTO();
				dto.setProductCode(productPopularity.getProductCode());
				dto.setQuantitySold(productPopularity.getQuantitySold());
				target.add(dto);
			}
		}

	}

}
