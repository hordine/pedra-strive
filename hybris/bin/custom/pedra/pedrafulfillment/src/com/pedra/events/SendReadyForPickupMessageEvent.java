/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.pedra.events;

import de.hybris.platform.orderprocessing.events.ConsignmentProcessingEvent;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;


public class SendReadyForPickupMessageEvent extends ConsignmentProcessingEvent
{
	private static final long serialVersionUID = -1664757371801260365L;

	public SendReadyForPickupMessageEvent(final ConsignmentProcessModel process)
	{
		super(process);
	}
}
