/**
 * 
 */
package com.pedra.core.product.dao.impl;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pedra.core.data.ProductPopularityData;
import com.pedra.core.model.PedraProductModel;
import com.pedra.core.product.dao.PedraProductDao;
import com.pedra.core.search.converter.ProductPopularitySearchResultConverter;


/**
 * @author henrique.ordine
 * 
 */
public class PedraProductDaoImpl extends DefaultProductDao implements PedraProductDao
{


	@Autowired
	private ProductPopularitySearchResultConverter productPopularitySearchResultConverter;

	/**
	 * @param typecode
	 */
	public PedraProductDaoImpl(final String typecode)
	{
		super(typecode);
	}

	@Override
	public List<PedraProductModel> findOnlineProductsByCategory(final CategoryModel category, final Gender gender)
	{

		ServicesUtil.validateParameterNotNullStandardMessage("gender", gender);

		final StringBuilder query = new StringBuilder();
		query.append("SELECT {ccr:" + LinkModel.TARGET + "} FROM { " + PedraProductModel._CATEGORYPRODUCTRELATION);
		query.append("  AS ccr JOIN " + PedraProductModel._TYPECODE + " AS p ON {ccr:" + LinkModel.TARGET + "} = {p:"
				+ PedraProductModel.PK + "} } ");
		query.append("  WHERE {ccr:" + LinkModel.SOURCE + "} = ?" + CategoryModel._TYPECODE);
		query.append("    AND  ( {p:" + PedraProductModel.ONLINEDATE + "} IS NULL OR {p:");
		query.append(PedraProductModel.ONLINEDATE + "} < ?session.user." + UserModel.CURRENTDATE + " ) ");
		query.append("    AND ( {p:" + PedraProductModel.OFFLINEDATE + "} IS NULL OR {p:");
		query.append(PedraProductModel.OFFLINEDATE + "} > ?session.user." + UserModel.CURRENTDATE + " ) ");
		query.append("    AND ( {p:" + PedraProductModel.GENDERS + "} = ?gender");

		query.append("ORDER BY " + LinkModel.SEQUENCENUMBER + " ASC ");
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(CategoryModel._TYPECODE, category);
		params.put(UserModel.CURRENTDATE, new Date());
		params.put("gender", gender);

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
		searchQuery.addQueryParameters(params);
		searchQuery.setResultClassList(Collections.singletonList(ProductModel.class));
		final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);
		return searchResult.getResult();
	}

	@Override
	public List<ProductPopularityData> findProductPopularity()
	{
		final StringBuilder query = new StringBuilder();
		query.append("SELECT {pr:code} as productCode, sum({oe:quantity}) quantitySold");
		query.append("  FROM {OrderEntry as oe join Product as pr on {oe:product}={pr:pk}}");
		query.append("  group by {pr.code}");
		query.append("  order by quantitySold");

		final FlexibleSearchQuery fsQuery = new FlexibleSearchQuery(query.toString());
		final List<Class> resultClasses = new ArrayList<Class>();
		resultClasses.add(String.class);
		resultClasses.add(Long.class);
		fsQuery.setResultClassList(resultClasses);
		final SearchResult<ProductPopularityData> result = getFlexibleSearchService().search(fsQuery);
		return getProductPopularitySearchResultConverter().convert(result);
	}

	public ProductPopularitySearchResultConverter getProductPopularitySearchResultConverter()
	{
		return productPopularitySearchResultConverter;
	}

	public void setProductPopularitySearchResultConverter(
			final ProductPopularitySearchResultConverter productPopularitySearchResultConverter)
	{
		this.productPopularitySearchResultConverter = productPopularitySearchResultConverter;
	}

}
