/**
 * 
 */
package com.pedra.core.interceptor;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.RemoveInterceptor;

import org.apache.log4j.Logger;


/**
 * @author henrique.ordine
 * 
 */
public class PedraOrderRemoveInterceptor implements RemoveInterceptor
{

	private static final Logger LOG = Logger.getLogger(PedraOrderRemoveInterceptor.class.getName());

	@Override
	public void onRemove(final Object paramObject, final InterceptorContext paramInterceptorContext) throws InterceptorException
	{
		final OrderModel model = (OrderModel) paramObject;
		LOG.warn("Order with number[" + model.getCode() + "] is being removed from the Database");
	}

}
