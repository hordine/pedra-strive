/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.buckaroo.jalo;

import com.pedra.buckaroo.constants.BuckaroopaymentConstants;
import com.pedra.ideal.jalo.payment.IdealPaymentRequest;
import com.pedra.ideal.jalo.payment.IdealPaymentResponse;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;

/**
 * Generated class for type <code>BuckaroopaymentManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBuckaroopaymentManager extends Extension
{
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.apiErrorMessage</code> attribute.
	 * @return the apiErrorMessage - brq_apierrormessage sent from buckaroo
	 */
	public String getApiErrorMessage(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.APIERRORMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.apiErrorMessage</code> attribute.
	 * @return the apiErrorMessage - brq_apierrormessage sent from buckaroo
	 */
	public String getApiErrorMessage(final IdealPaymentRequest item)
	{
		return getApiErrorMessage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.apiErrorMessage</code> attribute. 
	 * @param value the apiErrorMessage - brq_apierrormessage sent from buckaroo
	 */
	public void setApiErrorMessage(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.APIERRORMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.apiErrorMessage</code> attribute. 
	 * @param value the apiErrorMessage - brq_apierrormessage sent from buckaroo
	 */
	public void setApiErrorMessage(final IdealPaymentRequest item, final String value)
	{
		setApiErrorMessage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.apiResult</code> attribute.
	 * @return the apiResult - brq_apiresult sent from buckaroo
	 */
	public String getApiResult(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.APIRESULT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.apiResult</code> attribute.
	 * @return the apiResult - brq_apiresult sent from buckaroo
	 */
	public String getApiResult(final IdealPaymentRequest item)
	{
		return getApiResult( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.apiResult</code> attribute. 
	 * @param value the apiResult - brq_apiresult sent from buckaroo
	 */
	public void setApiResult(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.APIRESULT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.apiResult</code> attribute. 
	 * @param value the apiResult - brq_apiresult sent from buckaroo
	 */
	public void setApiResult(final IdealPaymentRequest item, final String value)
	{
		setApiResult( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.culture</code> attribute.
	 * @return the culture - brq_culture sent to buckaroo
	 */
	public String getCulture(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.CULTURE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.culture</code> attribute.
	 * @return the culture - brq_culture sent to buckaroo
	 */
	public String getCulture(final IdealPaymentRequest item)
	{
		return getCulture( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.culture</code> attribute. 
	 * @param value the culture - brq_culture sent to buckaroo
	 */
	public void setCulture(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.CULTURE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.culture</code> attribute. 
	 * @param value the culture - brq_culture sent to buckaroo
	 */
	public void setCulture(final IdealPaymentRequest item, final String value)
	{
		setCulture( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.description</code> attribute.
	 * @return the description - brq_description sent to buckaroo
	 */
	public String getDescription(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.description</code> attribute.
	 * @return the description - brq_description sent to buckaroo
	 */
	public String getDescription(final IdealPaymentRequest item)
	{
		return getDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.description</code> attribute. 
	 * @param value the description - brq_description sent to buckaroo
	 */
	public void setDescription(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.description</code> attribute. 
	 * @param value the description - brq_description sent to buckaroo
	 */
	public void setDescription(final IdealPaymentRequest item, final String value)
	{
		setDescription( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return BuckaroopaymentConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultCode</code> attribute.
	 * @return the resultCode - Response, result code from buckaroo
	 */
	public String getResultCode(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultCode</code> attribute.
	 * @return the resultCode - Response, result code from buckaroo
	 */
	public String getResultCode(final IdealPaymentRequest item)
	{
		return getResultCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultCode</code> attribute. 
	 * @param value the resultCode - Response, result code from buckaroo
	 */
	public void setResultCode(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultCode</code> attribute. 
	 * @param value the resultCode - Response, result code from buckaroo
	 */
	public void setResultCode(final IdealPaymentRequest item, final String value)
	{
		setResultCode( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultMessage</code> attribute.
	 * @return the resultMessage - Response, result message from buckaroo
	 */
	public String getResultMessage(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultMessage</code> attribute.
	 * @return the resultMessage - Response, result message from buckaroo
	 */
	public String getResultMessage(final IdealPaymentRequest item)
	{
		return getResultMessage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultMessage</code> attribute. 
	 * @param value the resultMessage - Response, result message from buckaroo
	 */
	public void setResultMessage(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultMessage</code> attribute. 
	 * @param value the resultMessage - Response, result message from buckaroo
	 */
	public void setResultMessage(final IdealPaymentRequest item, final String value)
	{
		setResultMessage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultTransactionKeys</code> attribute.
	 * @return the resultTransactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public String getResultTransactionKeys(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTTRANSACTIONKEYS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.resultTransactionKeys</code> attribute.
	 * @return the resultTransactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public String getResultTransactionKeys(final IdealPaymentRequest item)
	{
		return getResultTransactionKeys( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultTransactionKeys</code> attribute. 
	 * @param value the resultTransactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public void setResultTransactionKeys(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.RESULTTRANSACTIONKEYS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.resultTransactionKeys</code> attribute. 
	 * @param value the resultTransactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public void setResultTransactionKeys(final IdealPaymentRequest item, final String value)
	{
		setResultTransactionKeys( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.statusCodeDetail</code> attribute.
	 * @return the statusCodeDetail - brq_statuscode_detail from Buckaroo
	 */
	public String getStatusCodeDetail(final SessionContext ctx, final IdealPaymentResponse item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.STATUSCODEDETAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.statusCodeDetail</code> attribute.
	 * @return the statusCodeDetail - brq_statuscode_detail from Buckaroo
	 */
	public String getStatusCodeDetail(final IdealPaymentResponse item)
	{
		return getStatusCodeDetail( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.statusCodeDetail</code> attribute. 
	 * @param value the statusCodeDetail - brq_statuscode_detail from Buckaroo
	 */
	public void setStatusCodeDetail(final SessionContext ctx, final IdealPaymentResponse item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.STATUSCODEDETAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.statusCodeDetail</code> attribute. 
	 * @param value the statusCodeDetail - brq_statuscode_detail from Buckaroo
	 */
	public void setStatusCodeDetail(final IdealPaymentResponse item, final String value)
	{
		setStatusCodeDetail( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.statusMessage</code> attribute.
	 * @return the statusMessage - brq_statusmessage from Buckaroo
	 */
	public String getStatusMessage(final SessionContext ctx, final IdealPaymentResponse item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.STATUSMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.statusMessage</code> attribute.
	 * @return the statusMessage - brq_statusmessage from Buckaroo
	 */
	public String getStatusMessage(final IdealPaymentResponse item)
	{
		return getStatusMessage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.statusMessage</code> attribute. 
	 * @param value the statusMessage - brq_statusmessage from Buckaroo
	 */
	public void setStatusMessage(final SessionContext ctx, final IdealPaymentResponse item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.STATUSMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.statusMessage</code> attribute. 
	 * @param value the statusMessage - brq_statusmessage from Buckaroo
	 */
	public void setStatusMessage(final IdealPaymentResponse item, final String value)
	{
		setStatusMessage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.transactionKeys</code> attribute.
	 * @return the transactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public String getTransactionKeys(final SessionContext ctx, final IdealPaymentResponse item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.TRANSACTIONKEYS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.transactionKeys</code> attribute.
	 * @return the transactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public String getTransactionKeys(final IdealPaymentResponse item)
	{
		return getTransactionKeys( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.transactionKeys</code> attribute. 
	 * @param value the transactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public void setTransactionKeys(final SessionContext ctx, final IdealPaymentResponse item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.TRANSACTIONKEYS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.transactionKeys</code> attribute. 
	 * @param value the transactionKeys - Transaction id according to buckaroo, may be several values separated by comma
	 */
	public void setTransactionKeys(final IdealPaymentResponse item, final String value)
	{
		setTransactionKeys( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.websiteKey</code> attribute.
	 * @return the websiteKey - Website key according to buckaroo
	 */
	public String getWebsiteKey(final SessionContext ctx, final IdealPaymentResponse item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.WEBSITEKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentResponse.websiteKey</code> attribute.
	 * @return the websiteKey - Website key according to buckaroo
	 */
	public String getWebsiteKey(final IdealPaymentResponse item)
	{
		return getWebsiteKey( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.websiteKey</code> attribute. 
	 * @param value the websiteKey - Website key according to buckaroo
	 */
	public void setWebsiteKey(final SessionContext ctx, final IdealPaymentResponse item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentResponse.WEBSITEKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentResponse.websiteKey</code> attribute. 
	 * @param value the websiteKey - Website key according to buckaroo
	 */
	public void setWebsiteKey(final IdealPaymentResponse item, final String value)
	{
		setWebsiteKey( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.websiteKey</code> attribute.
	 * @return the websiteKey - Website key according to buckaroo
	 */
	public String getWebsiteKey(final SessionContext ctx, final IdealPaymentRequest item)
	{
		return (String)item.getProperty( ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.WEBSITEKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>IdealPaymentRequest.websiteKey</code> attribute.
	 * @return the websiteKey - Website key according to buckaroo
	 */
	public String getWebsiteKey(final IdealPaymentRequest item)
	{
		return getWebsiteKey( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.websiteKey</code> attribute. 
	 * @param value the websiteKey - Website key according to buckaroo
	 */
	public void setWebsiteKey(final SessionContext ctx, final IdealPaymentRequest item, final String value)
	{
		item.setProperty(ctx, BuckaroopaymentConstants.Attributes.IdealPaymentRequest.WEBSITEKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>IdealPaymentRequest.websiteKey</code> attribute. 
	 * @param value the websiteKey - Website key according to buckaroo
	 */
	public void setWebsiteKey(final IdealPaymentRequest item, final String value)
	{
		setWebsiteKey( getSession().getSessionContext(), item, value );
	}
	
}
