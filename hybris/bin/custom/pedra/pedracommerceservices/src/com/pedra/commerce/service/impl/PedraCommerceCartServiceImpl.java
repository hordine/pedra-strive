/**
 * 
 */
package com.pedra.commerce.service.impl;

import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pedra.commerce.service.PedraCommerceCartService;
import com.pedra.core.cart.dao.PedraCartDao;


/**
 * @author hordine
 * 
 */
public class PedraCommerceCartServiceImpl extends DefaultCommerceCartService implements PedraCommerceCartService
{


	private static final Logger LOG = Logger.getLogger(PedraCommerceCartServiceImpl.class);

	private PedraCartDao pedraCartDao;
	private UserService userService;


	@Override
	public CartModel getCartBySessionId(final String sessionId)
	{
		CartModel cart = null;
		try
		{
			cart = getPedraCartDao().getCartBySessionId(sessionId);
			cart.getEntries();
			cart.getAllPromotionResults();
			cart.getGlobalDiscountValues();
			cart.getTotalDiscounts();
			calculateCart(cart);
			return cart;
		}
		catch (final ModelNotFoundException e)
		{
			LOG.error("Cart not found for sessionId[" + sessionId + "]");
		}
		catch (final AmbiguousIdentifierException e)
		{
			LOG.error("Multiple Carts found for sessionId[" + sessionId + "]");
		}
		return null;
	}

	@Override
	public void swapSessionCartAndOwner(final CartModel newSessionCart, final String newOwnerId)
	{
		if (newSessionCart == null || newOwnerId == null)
		{
			LOG.info("Not swaping session cart and cart owner because both parameters must not be null");
			return;
		}
		if (newSessionCart.getEntries() != null && CollectionUtils.isNotEmpty(newSessionCart.getEntries()))
		{
			final UserModel newOwner = getUserService().getUserForUID(newOwnerId);
			getCartService().setSessionCart(newSessionCart);
			// first delete previous shopping carts of this user's
			if (!CollectionUtils.isEmpty(newOwner.getCarts()))
			{
				getModelService().removeAll(newOwner.getCarts());
			}
			getCartService().changeCurrentCartUser(newOwner);
			newSessionCart.setSessionId(JaloSession.getCurrentSession().getHttpSessionId());
			newSessionCart.setUser(newOwner);
			getModelService().save(newSessionCart);
		}
		else
		{
			LOG.debug("Not swaping session cart with sessionId[" + newSessionCart.getSessionId() + "] because it has no entries");
		}
	}

	@Override
	public UserService getUserService()
	{
		return userService;
	}

	@Override
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public PedraCartDao getPedraCartDao()
	{
		return pedraCartDao;
	}

	public void setPedraCartDao(final PedraCartDao pedraCartDao)
	{
		this.pedraCartDao = pedraCartDao;
	}

	@Override
	public CartModel getAnonymousUserCartBySessionId(final String sessionId)
	{
		final Collection<CartModel> carts = getUserService().getAnonymousUser().getCarts();
		if (carts != null && !carts.isEmpty())
		{
			for (final CartModel cart : carts)
			{
				if (cart.getSessionId() != null && StringUtils.equals(cart.getSessionId(), sessionId))
				{
					return cart;
				}
			}
		}
		return null;
	}

}
