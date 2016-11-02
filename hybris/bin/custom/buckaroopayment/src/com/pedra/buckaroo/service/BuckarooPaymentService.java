/**
 * 
 */
package com.pedra.buckaroo.service;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.Map;

import com.pedra.ideal.service.IdealPaymentService;


/**
 * @author henrique.ordine
 * 
 */
public interface BuckarooPaymentService extends IdealPaymentService
{
	/**
	 * Validate websiteKey, testMode and signature of a buckaroo request
	 * 
	 * throws IllegalArgumentException if request parameter is not valid
	 */
	void validateBasicPushResponseFields(String websiteKey, String testMode, String signature,
			Map<String, String[]> fullRequestParams) throws IllegalArgumentException;

	/**
	 * @param orderToProcess
	 * @param currency
	 * @param amount
	 * @param amountCredit
	 */
	void validateOrderPushResponseFields(OrderModel orderToProcess, String currency, String amount, String amountCredit)
			throws IllegalArgumentException;

}
