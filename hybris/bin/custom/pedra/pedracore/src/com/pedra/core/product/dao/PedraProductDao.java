/**
 * 
 */
package com.pedra.core.product.dao;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;

import com.pedra.core.data.ProductPopularityData;
import com.pedra.core.model.PedraProductModel;


/**
 * @author henrique.ordine
 * 
 */
public interface PedraProductDao extends ProductDao
{
	public List<PedraProductModel> findOnlineProductsByCategory(final CategoryModel category, Gender gender);

	public List<ProductPopularityData> findProductPopularity();
}
