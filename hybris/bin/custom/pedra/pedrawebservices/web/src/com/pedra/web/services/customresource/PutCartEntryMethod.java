/**
 * 
 */
package com.pedra.web.services.customresource;

import de.hybris.platform.core.dto.order.CartEntryDTO;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.resource.order.CartEntryResource;
import de.hybris.platform.webservices.HttpPutResponseBuilder;


/**
 * 
 * This method should be called after the processing of the PUT method in {@link CartEntryResource}.
 * 
 * It is declared as an after processing property of
 * 
 * @author henrique.ordine
 * 
 */
public class PutCartEntryMethod extends HttpPutResponseBuilder<CartEntryModel, CartEntryDTO>
{
	@Override
	public void afterProcessing(final CartEntryDTO dto, final CartEntryModel resource, final boolean wasCreated)
	{
		final CartModel order = resource.getOrder();
		if (resource.getQuantity().longValue() <= 0)
		{
			getResource().getServiceLocator().getModelService().remove(resource);
		}
		// recalculates cart
		getResource().getServiceLocator().getCartService().calculateCart(order);

	}


}
