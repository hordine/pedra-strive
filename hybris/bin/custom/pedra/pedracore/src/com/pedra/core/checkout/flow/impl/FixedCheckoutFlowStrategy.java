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
package com.pedra.core.checkout.flow.impl;

import com.pedra.core.checkout.flow.CheckoutFlowStrategy;
import com.pedra.core.enums.CheckoutFlowEnum;

import org.springframework.beans.factory.annotation.Required;


/**
 * 
 * Uses fixed {@link CheckoutFlowEnum} as result. Used most likely on the end of checkout flow strategy chain.
 * 
 * @since 4.6
 */
public class FixedCheckoutFlowStrategy implements CheckoutFlowStrategy
{
	private CheckoutFlowEnum checkoutFlow;

	@Override
	public CheckoutFlowEnum getCheckoutFlow()
	{
		return checkoutFlow;
	}

	@Required
	public void setCheckoutFlow(final CheckoutFlowEnum checkoutFlow)
	{
		this.checkoutFlow = checkoutFlow;
	}
}
