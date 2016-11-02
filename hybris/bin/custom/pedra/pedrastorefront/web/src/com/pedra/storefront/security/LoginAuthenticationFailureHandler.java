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
package com.pedra.storefront.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException
	{
		// Store the j_username in the session
		request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", request.getParameter("j_username"));

		super.onAuthenticationFailure(request, response, exception);
	}
}
