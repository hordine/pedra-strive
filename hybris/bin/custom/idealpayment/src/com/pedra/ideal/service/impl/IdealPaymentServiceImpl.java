/**
 * 
 */
package com.pedra.ideal.service.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.impl.DefaultPaymentServiceImpl;

import org.apache.log4j.Logger;

import com.pedra.ideal.model.payment.IdealPaymentRequestModel;
import com.pedra.ideal.service.IdealPaymentService;


/**
 * @author Henrique Ordine
 * 
 */
public class IdealPaymentServiceImpl extends DefaultPaymentServiceImpl implements IdealPaymentService
{

	private static final Logger LOG = Logger.getLogger(IdealPaymentServiceImpl.class.getName());


	@Override
	public IdealPaymentRequestModel createTransactionRequest(final OrderModel order, final String clientIP)
	{
		LOG.warn("Using default IdealPaymentService: override this service instead");
		return null;
	}


	@Override
	public void processBuckarooPushResponse(final OrderModel orderModel, final String buckarooStatus)
	{
		LOG.warn("Using default IdealPaymentService: override this service instead");
	}

}
