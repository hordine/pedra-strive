/**
 * 
 */
package com.pedra.backoffice.widgets;

import org.apache.commons.lang3.BooleanUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;

import com.hybris.cockpitng.annotations.SocketEvent;
import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;


/**
 * @author henrique.ordine
 * 
 */
public class PedraListController extends DefaultWidgetController
{

	private static final String SOCKET_IN_ENABLED = "enabled";
	private static final String SOCKET_OUT_CREATE = "create";
	private static final String SOCKET_IN_TYPE = "type";
	private Button addNew;

	public PedraListController()
	{
		// someone might want to do something here one day
	}

	@Override
	public void initialize(final Component comp)
	{
		super.initialize(comp);
		addNew.setDisabled(true);

	}

	@SocketEvent(socketId = SOCKET_IN_ENABLED)
	public void activateAddButton(final Boolean enabled)
	{
		addNew.setDisabled(BooleanUtils.isNotTrue(enabled));
	}

	@SocketEvent(socketId = SOCKET_IN_TYPE)
	public void setType(final String type)
	{
		setValue("type", type);
	}

	@ViewEvent(componentID = "addNew", eventName = Events.ON_CLICK)
	public void runWizard()
	{
		sendOutput(SOCKET_OUT_CREATE, getValue("type", String.class));
	}



}
