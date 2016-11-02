/**
 * 
 */
package com.pedra.ideal.data;

/**
 * @author hordine
 * 
 */
public class PaymentRedirectData
{
	private String redirectUrl;
	private boolean successful;
	private IdealStatusEnum idealStatusCode;
	private String orderCode;
	private String errorMsg;

	/**
	 * Instantiates a new payment redirect data.
	 * 
	 * @param redirectUrl
	 *           the redirect url
	 * @param successful
	 *           the successful
	 * @param orderCode
	 *           the order code
	 * @param errorMsg
	 *           the error msg
	 * @param idealStatusCode
	 *           the ideal status code
	 */
	public PaymentRedirectData(final String redirectUrl, final boolean successful, final IdealStatusEnum idealStatusCode,
			final String orderCode, final String errorMsg)
	{
		super();
		this.redirectUrl = redirectUrl;
		this.successful = successful;
		this.idealStatusCode = idealStatusCode;
		this.orderCode = orderCode;
		this.errorMsg = errorMsg;
	}

	public String getRedirectUrl()
	{
		return redirectUrl;
	}

	public void setRedirectUrl(final String redirectUrl)
	{
		this.redirectUrl = redirectUrl;
	}

	public boolean isSuccessful()
	{
		return successful;
	}

	public void setSuccessful(final boolean successful)
	{
		this.successful = successful;
	}

	public String getOrderCode()
	{
		return orderCode;
	}

	public void setOrderCode(final String orderCode)
	{
		this.orderCode = orderCode;
	}

	public String getErrorMsg()
	{
		return errorMsg;
	}

	public void setErrorMsg(final String errorMsg)
	{
		this.errorMsg = errorMsg;
	}

	public IdealStatusEnum getIdealStatusCode()
	{
		return idealStatusCode;
	}

	public void setIdealStatusCode(final IdealStatusEnum idealStatusCode)
	{
		this.idealStatusCode = idealStatusCode;
	}


}
