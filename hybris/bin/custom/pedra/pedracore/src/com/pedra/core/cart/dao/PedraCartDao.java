/**
 * 
 */
package com.pedra.core.cart.dao;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;


/**
 * @author Henrique Ordine
 * 
 */
public interface PedraCartDao extends Dao
{
	CartModel getCartBySessionId(String sessionId);
}
