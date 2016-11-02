/**
 * 
 */
package com.pedra.backoffice.widgets;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

import com.hybris.backoffice.search.SimpleCockpitSearchService;
import com.hybris.backoffice.search.data.SearchQueryData;
import com.hybris.cockpitng.annotations.SocketEvent;
import com.hybris.cockpitng.widgets.common.simplesearch.SimpleSearchController;


/**
 * @author henrique.ordine
 * 
 */
public class ThunderaListController extends SimpleSearchController
{

	private static final Logger LOG = LoggerFactory.getLogger(ThunderaListController.class);

	private static final String SOCKET_IN_ENABLED = "enabled";
	private static final String SOCKET_IN_TYPE = "type";

	@Autowired
	private SimpleCockpitSearchService<? extends Object> simpleCockpitSearchService;

	private String type;

	public ThunderaListController()
	{
		// more stuff to care about, someday
	}

	@Override
	public void initialize(final Component comp)
	{
		super.initialize(comp);

	}

	@Override
	@SocketEvent(socketId = SOCKET_IN_TYPE)
	public void setType(final String type)
	{
		this.type = type;
	}

	@SocketEvent(socketId = SOCKET_IN_ENABLED)
	public void performThunderaSearch()
	{
		if (StringUtils.isNotEmpty(this.type))
		{
			final SearchQueryData searchQueryData = buildQueryData("", this.type);
			searchQueryData.setSearchQueryText("Select * from {" + this.type + "}");
			sendOutput("pageable", this.simpleCockpitSearchService.search(searchQueryData));
		}
		else
		{
			LOG.warn("Type was not set. No search performed.");
		}
	}


}
