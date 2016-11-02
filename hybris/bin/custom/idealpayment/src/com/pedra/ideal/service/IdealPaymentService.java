/**
 * 
 */
package com.pedra.ideal.service;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.PaymentService;

import com.pedra.ideal.model.payment.IdealPaymentRequestModel;


/**
 * @author Henrique Ordine
 * 
 */
public interface IdealPaymentService extends PaymentService
{
	IdealPaymentRequestModel createTransactionRequest(OrderModel order, String clientIP);

	void processBuckarooPushResponse(OrderModel orderModel, String buckarooStatus);
}
