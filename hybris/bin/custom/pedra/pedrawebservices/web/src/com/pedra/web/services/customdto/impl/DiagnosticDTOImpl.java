/**
 * 
 */
package com.pedra.web.services.customdto.impl;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.pedra.web.services.customdto.DiagnosticDTO;


/**
 * @author henrique.ordine
 * 
 */
@XmlRootElement(name = "diagnostic")
public class DiagnosticDTOImpl implements DiagnosticDTO
{

	@XmlElement
	@Override
	public String getMemoryInfo()
	{
		return String.format("JVM Memory Info, total:%sMB , free:%sMB , processors count:%s",//
				Long.valueOf(Runtime.getRuntime().maxMemory() / (1024 * 1024)),//
				Long.valueOf(Runtime.getRuntime().freeMemory() / (1024 * 1024)),//
				Long.valueOf(Runtime.getRuntime().availableProcessors()));
	}


	public void setMemoryInfo(final String info)
	{
		//
	}

	@XmlAttribute
	@Override
	public Integer getProductsCount()
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery("select {PK} from {Product}");

		final FlexibleSearchService service = Registry.getApplicationContext().getBean(FlexibleSearchService.class);

		JaloSession.getCurrentSession().getSessionContext().setAttribute("disableRestrictions", Boolean.TRUE);

		final SearchResult result = service.search(query);
		return Integer.valueOf(result.getTotalCount());
	}

	public void setProductsCount(final Integer count)
	{
		//
	}

	@XmlAttribute
	@Override
	public Integer getSessionsCount()
	{
		return Integer.valueOf(JaloConnection.getInstance().getAllSessions().size());
	}


	public void setSessionsCount(final Integer count)
	{
		//
	}

	@XmlAttribute
	@Override
	public Integer getUsersCount()
	{
		final SearchResult<UserModel> result = Registry.getApplicationContext().getBean(FlexibleSearchService.class)
				.search("select {PK} from {User}");
		return Integer.valueOf(result.getTotalCount());
	}

	public void setUsersCount(final Integer count)
	{
		//
	}


}