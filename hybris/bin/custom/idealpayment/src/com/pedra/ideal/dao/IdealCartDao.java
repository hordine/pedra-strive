/**
 * 
 */
package com.pedra.ideal.dao;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;


/**
 * @author hordine
 * 
 */
public interface IdealCartDao extends Dao
{
	CartModel getCartBySessionId(String sessionId);
}
