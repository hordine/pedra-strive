/**
 * 
 */
package com.pedra.commerce.service.impl;

import de.hybris.platform.commerceservices.product.impl.DefaultCommerceProductService;

import java.util.List;

import com.pedra.commerce.service.PedraCommerceProductService;
import com.pedra.core.data.ProductPopularityData;
import com.pedra.core.product.dao.PedraProductDao;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCommerceProductServiceImpl extends DefaultCommerceProductService implements PedraCommerceProductService
{

	private PedraProductDao pedraProductDao;

	@Override
	public List<ProductPopularityData> findProductPopularity()
	{
		return getPedraProductDao().findProductPopularity();
	}

	public PedraProductDao getPedraProductDao()
	{
		return pedraProductDao;
	}

	public void setPedraProductDao(final PedraProductDao pedraProductDao)
	{
		this.pedraProductDao = pedraProductDao;
	}

}
