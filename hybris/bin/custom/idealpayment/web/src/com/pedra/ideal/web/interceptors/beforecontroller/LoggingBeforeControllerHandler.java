/**
 * 
 */
package com.pedra.ideal.web.interceptors.beforecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;

import com.pedra.ideal.web.interceptors.BeforeControllerHandler;


/**
 * @author henrique.ordine
 * 
 */
public class LoggingBeforeControllerHandler implements BeforeControllerHandler
{

	private static final Logger LOG = Logger.getLogger(LoggingBeforeControllerHandler.class);



	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws Exception
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("URL[" + request.getRequestURL().toString() + "] served by:");
			LOG.debug("Handler[" + handler.getBean().getClass().getName() + "]");
		}
		return true;
	}

}
