package com.pedra.ideal.web.util;

import javax.servlet.http.HttpServletRequest;


/**
 * Helper class for ip-address.<br>
 * 
 * @author Henrique Ordine
 */
public class IPUtil
{

	/**
	 * Get the client address for a httpServletRequest.<br>
	 * 
	 * @param request
	 *           the request in which to look up the IP address
	 * @return the ipAddress for a client
	 */
	public static String getClientIpAddr(final HttpServletRequest request)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}