/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.pedra.storefront.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * A Spring MVC <code>HandlerInterceptor</code> which is responsible to enforce CSRF token validity on incoming posts
 * requests. The interceptor should be registered with Spring MVC servlet using the following syntax:
 * 
 * <pre>
 *   &lt;mvc:interceptors&gt;
 *        &lt;bean class="com.eyallupu.blog.springmvc.controller.csrf.CSRFHandlerInterceptor"/&gt;
 *   &lt;/mvc:interceptors&gt;
 * </pre>
 * 
 * @author Eyal Lupu
 * @see CSRFRequestDataValueProcessor
 * 
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter
{

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception
	{

		if (!request.getMethod().equalsIgnoreCase("POST"))
		{
			// Not a POST - allow the request
			return true;
		}
		else
		{
			// This is a POST request - need to check the CSRF token
			final String sessionToken = CSRFTokenManager.getTokenForSession(request.getSession());
			final String requestToken = CSRFTokenManager.getTokenFromRequest(request);
			if (sessionToken.equals(requestToken))
			{
				return true;
			}
			else
			{
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
				return false;
			}
		}
	}


}