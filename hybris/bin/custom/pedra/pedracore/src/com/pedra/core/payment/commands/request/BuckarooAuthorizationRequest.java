/**
 * 
 */
package com.pedra.core.payment.commands.request;

import de.hybris.platform.payment.commands.request.AbstractRequest;


/**
 * @author henrique.ordine
 * 
 */
public class BuckarooAuthorizationRequest extends AbstractRequest
{

	/**
	 * @param merchantTransactionCode
	 */
	public BuckarooAuthorizationRequest(final String merchantTransactionCode)
	{
		super(merchantTransactionCode);
	}

}
