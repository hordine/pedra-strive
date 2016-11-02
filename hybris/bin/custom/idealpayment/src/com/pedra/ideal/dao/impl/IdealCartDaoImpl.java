/**
 * 
 */
package com.pedra.ideal.dao.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.Map;

import com.pedra.ideal.dao.IdealCartDao;


/**
 * @author hordine
 * 
 */
public class IdealCartDaoImpl implements IdealCartDao
{

	private FlexibleSearchService flexibleSearchService;

	@Override
	public CartModel getCartBySessionId(final String sessionId)
	{
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(CartModel.SESSIONID, sessionId);

		final StringBuilder builder = new StringBuilder();
		builder.append("SELECT {").append(CartModel.PK).append("} ");
		builder.append("FROM {").append(CartModel._TYPECODE).append("} ");
		builder.append("WHERE {").append(CartModel.SESSIONID).append("}=?sessionId ");
		builder.append("ORDER BY {").append(CartModel.PK).append("} ASC");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(builder.toString(), params);

		final CartModel result = getFlexibleSearchService().searchUnique(query);
		return result;

	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
