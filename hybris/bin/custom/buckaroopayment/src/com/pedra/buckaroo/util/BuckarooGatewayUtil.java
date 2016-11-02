package com.pedra.buckaroo.util;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.util.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.pedra.buckaroo.constants.BuckarooConstants;
import com.pedra.ideal.model.payment.IdealPaymentRequestModel;


/**
 * Utility class for the Buckaroo payment gateway
 * 
 * @author Henrique Ordine
 */
public class BuckarooGatewayUtil
{

	private static final Logger LOG = Logger.getLogger(BuckarooGatewayUtil.class);

	public static final String BRQ_WEBSITEKEY = "BRQ_WEBSITEKEY";
	public static final String BRQ_CULTURE = "BRQ_CULTURE";
	public static final String BRQ_INVOICENUMBER = "BRQ_INVOICENUMBER";
	public static final String BRQ_ORDERNUMBER = "BRQ_ORDERNUMBER";
	public static final String BRQ_CURRENCY = "BRQ_CURRENCY";
	public static final String BRQ_AMOUNT = "BRQ_AMOUNT";
	public static final String BRQ_CLIENTIP = "BRQ_CLIENTIP";
	public static final String BRQ_CONTINUE_ON_INCOMPLETE = "BRQ_CONTINUE_ON_INCOMPLETE";
	public static final String BRQ_PUSH = "BRQ_PUSH";
	public static final String BRQ_PUSHFAILURE = "BRQ_PUSHFAILURE";
	public static final String BRQ_RETURN = "BRQ_RETURN";
	public static final String BRQ_TEST = "BRQ_TEST";
	public static final String BRQ_SIGNATURE = "BRQ_SIGNATURE";
	public static final String BRQ_DESCRIPTION = "BRQ_DESCRIPTION";
	public static final String FULL_RESPONSE = "FULL_RESPONSE";
	public static final String BRQ_REDIRECTURL = "BRQ_REDIRECTURL";
	public static final String BRQ_STATUSCODE = "BRQ_STATUSCODE";
	public static final String BRQ_STATUSMESSAGE = "BRQ_STATUSMESSAGE";
	public static final String BRQ_TRANSACTIONS = "BRQ_TRANSACTIONS";
	public static final String BRQ_APIRESULT = "BRQ_APIRESULT";
	public static final String BRQ_APIERRORMESSAGE = "BRQ_APIERRORMESSAGE";

	/**
	 * Utility method to create the buckaroo signature for a given set of request params. Incoming parameters may be URL
	 * encoded.
	 * 
	 * @param requestParams
	 *           map of request parameters and values
	 * @param secretKey
	 *           secret key to use, needs to be shared with buckaroo for them to get the same result.
	 */
	public static String calculateBuckarooSignature(final Map<String, String> requestParams, final String secretKey)
	{

		//Based on specification in BPE 3.0 Gateway NVP.1.37.pdf, section 5.1.

		//(1.) filter and (2.) "sort by key" the request parameters
		final Map<String, String> toHash = new TreeMap(String.CASE_INSENSITIVE_ORDER);
		for (final Map.Entry<String, String> entry : requestParams.entrySet())
		{
			if (isSignatureInputParams(entry.getKey()))
			{
				toHash.put(entry.getKey(), urlDecode(entry.getValue()));
			}
		}

		//(3.) concatenate into one string in the format key1=value1key2=value2
		final StringBuilder sb = new StringBuilder();
		for (final Map.Entry<String, String> entry : toHash.entrySet())
		{
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		//(4.) append the secret key to the end of the string
		sb.append(secretKey);

		//(5.) return the SHA1 has in hexadecimal format
		return DigestUtils.shaHex(sb.toString());
	}

	/**
	 * @param value
	 * @return
	 */
	private static String urlDecode(final String s)
	{
		if (s == null)
		{
			return null;
		}
		else
		{
			try
			{
				return URLDecoder.decode(s, "UTF-8");
			}
			catch (final UnsupportedEncodingException e)
			{
				LOG.error(e);
				return null;
			}
		}
	}

	/**
	 * Creates a map with all the request properties that will be sent to buckaroo, so that this map can be passed to
	 * {@link #calculateBuckarooSignature(Map, String)}
	 * 
	 * @param order
	 * @param clientIP
	 * @return
	 */
	public static Map<String, String> createMapForPaymentRequestSignature(final OrderModel order, final String clientIP)
	{
		final String transactionDesc = Config.getString(BuckarooConstants.CONFIG_TX_DESCRIPTION, "");
		final String pushUrl = Config.getString(BuckarooConstants.CONFIG_PUSH_URL, "");
		final String pushFailureUrl = Config.getString(BuckarooConstants.CONFIG_PUSH_FAILURE_URL, "");
		final String returnUrl = Config.getString(BuckarooConstants.CONFIG_RETURN_URL, "");
		final String testMode = Config.getString(BuckarooConstants.CONFIG_TEST_MODE, "");

		final Map<String, String> request = new LinkedHashMap<String, String>();
		request.put(BRQ_WEBSITEKEY, Config.getString(BuckarooConstants.CONFIG_WEBSITE_KEY, ""));
		request.put(BRQ_CULTURE, "nl-NL");
		request.put(BRQ_ORDERNUMBER, order.getCode());
		request.put(BRQ_INVOICENUMBER, order.getCode());
		request.put(BRQ_CURRENCY, order.getCurrency().getIsocode());
		request.put(BRQ_AMOUNT, order.getTotalPrice().toString());

		if (StringUtils.hasLength(transactionDesc))
		{
			request.put(BRQ_DESCRIPTION, transactionDesc);
		}
		request.put(BRQ_CLIENTIP, clientIP);
		request.put(BRQ_CONTINUE_ON_INCOMPLETE, "RedirectToHTML");

		if (StringUtils.hasLength(pushUrl))
		{
			request.put(BRQ_PUSH, pushUrl);
		}

		if (StringUtils.hasLength(pushFailureUrl))
		{
			request.put(BRQ_PUSHFAILURE, pushFailureUrl);
		}
		if (StringUtils.hasLength(returnUrl))
		{
			request.put(BRQ_RETURN, returnUrl);
		}


		if (StringUtils.hasLength(testMode))
		{
			request.put(BRQ_TEST, testMode);
		}
		return request;
	}

	public static HttpPost createHttpPostFromMap(final String signature, final String url, final String buckarooOperation,
			final Map<String, String> parameters)
	{
		final HttpPost httpPost = new HttpPost(url);
		final List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("op", buckarooOperation));
		nvps.add(new BasicNameValuePair(BRQ_SIGNATURE, signature));

		if (parameters != null)
		{
			for (final String key : parameters.keySet())
			{
				final String value = parameters.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}
		}
		try
		{
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		}
		catch (final UnsupportedEncodingException e)
		{
			LOG.error("Error encoding form entity for HTTP POST request to[" + url + "]", e);
		}
		return httpPost;
	}

	/**
	 * Check if the given key is one that buckaroo would recognize/use.
	 */
	public static boolean isBuckarooParam(final String key)
	{
		if (key == null)
		{
			return false;
		}
		else
		{
			final String lower = key.toLowerCase();
			return lower.startsWith("brq_") || lower.startsWith("add_") || lower.startsWith("cust_");
		}
	}

	public static Map<String, String> createMapFromResponse(final HttpEntity response)
	{
		final Map<String, String> result = new HashMap<String, String>();
		if (response != null)
		{
			try
			{
				final String entityString = EntityUtils.toString(response);
				LOG.debug("Response string from buckaroo: " + entityString);
				result.put(FULL_RESPONSE, entityString);
				if (StringUtils.hasLength(entityString))
				{
					final String[] nvps = StringUtils.delimitedListToStringArray(entityString, "&");
					for (final String nvp : nvps)
					{
						final String[] nvparray = StringUtils.delimitedListToStringArray(nvp, "=");
						final String value = nvparray.length > 1 ? nvparray[1] : null;
						if (nvparray[0] != null)
						{
							result.put(nvparray[0].toUpperCase(), value);
						}
						else
						{
							LOG.error("Received a null key in the pair [" + nvp + "] from the response from buckaroo");
						}
					}
				}
			}
			catch (final ParseException e)
			{
				LOG.error("Error parsing response", e);
			}
			catch (final IOException e)
			{
				LOG.error("Error parsing response", e);
			}
		}
		return result;
	}

	public static IdealPaymentRequestModel getPaymentRequestModelFromMap(final Map<String, String> map,
			IdealPaymentRequestModel paymentGatewayRequestModel)
	{
		if (map != null)
		{
			if (paymentGatewayRequestModel == null)
			{
				paymentGatewayRequestModel = new IdealPaymentRequestModel();
				paymentGatewayRequestModel.setCulture(map.get(BRQ_CULTURE));
				paymentGatewayRequestModel.setDescription(map.get(BRQ_DESCRIPTION));
				paymentGatewayRequestModel.setOrderCode(map.get(BRQ_ORDERNUMBER));
				paymentGatewayRequestModel.setWebsiteKey(map.get(BRQ_WEBSITEKEY));
				paymentGatewayRequestModel.setClientIP(map.get(BRQ_CLIENTIP));
			}
			paymentGatewayRequestModel.setRequestTime(new Date());
			paymentGatewayRequestModel.setCurrency(map.get(BRQ_CURRENCY));
			paymentGatewayRequestModel.setAmount(map.get(BRQ_AMOUNT));

			// parameters from the response:
			paymentGatewayRequestModel.setResultCode(map.get(BRQ_STATUSCODE));
			paymentGatewayRequestModel.setResultMessage(map.get(BRQ_STATUSMESSAGE));
			paymentGatewayRequestModel.setResultTransactionKeys(map.get(BRQ_TRANSACTIONS));
			try
			{
				if (map.get(BRQ_REDIRECTURL) != null)
				{
					paymentGatewayRequestModel.setRedirectUrl(URLDecoder.decode(map.get(BRQ_REDIRECTURL), "UTF-8"));
				}
				paymentGatewayRequestModel.setApiResult(map.get(BRQ_APIRESULT));
				paymentGatewayRequestModel.setApiErrorMessage(map.get(BRQ_APIERRORMESSAGE));
			}
			catch (final UnsupportedEncodingException e)
			{
				LOG.error("UnsupportedEncodingException decoding the redirectURL [" + map.get(BRQ_REDIRECTURL) + "]", e);
				paymentGatewayRequestModel.setResultMessage("UnsupportedEncodingException decoding the redirectURL: "
						+ e.getMessage());
			}
			paymentGatewayRequestModel.setResultFull(map.get(FULL_RESPONSE));
			return paymentGatewayRequestModel;
		}
		return paymentGatewayRequestModel;
	}


	/**
	 * Converts the Map<String, String[]> received from buckaroo into a Map<String, String>.
	 */
	public static Map<String, String> flattenHTTPParams(final Map<String, String[]> fullRequestParams)
	{
		final Map<String, String> flattened = new LinkedHashMap<String, String>();
		for (final Map.Entry<String, String[]> entry : fullRequestParams.entrySet())
		{
			final String key = entry.getKey();
			final String[] values = entry.getValue();

			if (values.length == 0)
			{
				//use ""
				flattened.put(key, "");
			}
			else if (values.length == 1)
			{
				flattened.put(key, values[0]);
			}
			else
			{
				throw new IllegalArgumentException("got too many values for request parameter: " + key);
			}
		}
		return flattened;
	}

	/**
	 * Convert a map of String->String[] to a map of String->String, ignoring any non buckaroo entries. Will throw
	 * exception .
	 * 
	 * @param fullRequestParams
	 *           the full request params
	 * @return the map
	 * @throws IllegalArgumentException
	 *            if any of the "buckaroo relevant" parameters have too many values.
	 */
	public static Map<String, String> flattenBuckarooParams(final Map<String, String[]> fullRequestParams)
			throws IllegalArgumentException
	{
		final Map<String, String> flattened = new LinkedHashMap<String, String>();
		for (final Map.Entry<String, String[]> entry : fullRequestParams.entrySet())
		{
			final String key = entry.getKey();
			final String[] values = entry.getValue();

			if (!isBuckarooParam(key))
			{
				continue;
			}

			if (values.length == 0)
			{
				//use ""
				flattened.put(key, "");
			}
			else if (values.length == 1)
			{
				flattened.put(key, values[0]);
			}
			else
			{
				throw new IllegalArgumentException("got too many values for request parameter: " + key);
			}
		}
		return flattened;
	}

	/**
	 * Converts the HTTP parameters received from buckaroo into a String.
	 * 
	 * @param params
	 * @return
	 */
	public static String requestParamsToString(final Map<String, String> params)
	{
		final StringBuilder strBuilder = new StringBuilder();
		if (params != null)
		{
			for (final String key : params.keySet())
			{
				String value = params.get(key);
				value = value == null ? "" : value;
				strBuilder.append(key + "=" + value + '\n');
			}
		}
		return strBuilder.toString();
	}


	/**
	 * Check if the given key is one that buckaroo wants to have in the hash.
	 */
	private static boolean isSignatureInputParams(final String key)
	{
		if (key == null || key.equalsIgnoreCase("brq_signature"))
		{
			return false;
		}
		else
		{
			return isBuckarooParam(key);
		}
	}
}
