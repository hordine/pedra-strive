/**
 * 
 */
package com.pedra.commerce.service;

import de.hybris.platform.commerceservices.product.CommerceProductService;

import java.util.List;

import com.pedra.core.data.ProductPopularityData;


/**
 * @author henrique.ordine
 * 
 */
public interface PedraCommerceProductService extends CommerceProductService
{
	public List<ProductPopularityData> findProductPopularity();
}
