/**
 * 
 */
package com.pedra.core.payment.commands.impl;

import de.hybris.platform.payment.commands.request.AbstractRequest;
import de.hybris.platform.payment.commands.result.AbstractResult;

import org.apache.log4j.Logger;


/**
 * @author henrique.ordine
 * 
 */
public abstract class GenericPedraCommand
{
	protected AbstractResult genericPerform(final AbstractRequest request, final AbstractResult result)
	{
		result.setMerchantTransactionCode(request.getMerchantTransactionCode());

		Logger.getLogger(super.getClass()).info(
				"Payment command: " + super.getClass() + " executed [status: " + result.getTransactionStatus().toString() + "]");

		return result;
	}
}
