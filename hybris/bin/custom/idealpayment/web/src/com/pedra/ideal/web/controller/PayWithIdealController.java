/**
 * 
 */
package com.pedra.ideal.web.controller;

import de.hybris.platform.commercefacades.order.OrderFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pedra.ideal.data.IdealStatusEnum;
import com.pedra.ideal.data.PaymentRedirectData;
import com.pedra.ideal.facades.IdealPaymentFacade;
import com.pedra.ideal.service.IdealCommerceCartService;
import com.pedra.ideal.web.util.IPUtil;


/**
 * @author Henrique Ordine
 * 
 */

@Controller
@Scope("tenant")
@RequestMapping(value = "/payment")
public class PayWithIdealController
{

	protected static final Logger LOG = Logger.getLogger(PayWithIdealController.class);

	@Resource(name = "idealPaymentFacade")
	private IdealPaymentFacade idealPaymentFacade;

	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	@Resource(name = "sessionService")
	private SessionService sessionService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "idealCommerceCartService")
	private IdealCommerceCartService idealCommerceCartService;

	@RequestMapping(value = "/request/cartSessionID/userID", method = RequestMethod.GET)
	public String requestPayment(final HttpServletRequest request, final HttpServletResponse response,
			@RequestParam(value = "cartSessionID", required = true) final String cartSessionID,
			@RequestParam(value = "userID", required = true) final String userID) throws UnsupportedEncodingException
	{
		LOG.debug("Requesting iDEAL payment...");
		final CartModel cart = idealCommerceCartService.getCartBySessionId(cartSessionID);
		idealCommerceCartService.swapSessionCartAndOwner(cart, userID);
		try
		{
			final String clientIP = IPUtil.getClientIpAddr(request);
			LOG.debug("ClientIP is [" + clientIP + "]");
			final PaymentRedirectData data = idealPaymentFacade.requestPayment(clientIP);
			if (data.getIdealStatusCode().equals(IdealStatusEnum.Awaiting_Consumer))
			{
				response.reset();
				response.setStatus(302);
				response.setHeader("Location", data.getRedirectUrl());
				return "";
			}
		}
		catch (final InvalidCartException e)
		{
			LOG.error("Shopping cart is not valid", e);
		}
		return "";
	}
}
