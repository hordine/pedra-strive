/**
 * 
 */
package com.pedra.commerce.service;

import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;


/**
 * @author hordine
 * 
 */
public interface PedraCommerceCartService extends CommerceCartService
{
	CartModel getCartBySessionId(final String sessionId);

	void swapSessionCartAndOwner(final CartModel newSessionCart, final String newOwnerId);

	CartModel getAnonymousUserCartBySessionId(final String sessionId);
}
