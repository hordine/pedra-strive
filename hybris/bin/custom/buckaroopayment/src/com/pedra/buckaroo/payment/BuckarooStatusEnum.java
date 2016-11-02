/**
 * 
 */
package com.pedra.buckaroo.payment;

/**
 * List of status code from buckaroo payment gateway.
 * Based on document "BPE 3.0 Gateway NVP.1.37.pdf"
 * 
 * @author marcel.de.groot
 */
public enum BuckarooStatusEnum
{
	//final statuses
	Success("190", true), //The transaction has succeeded and the payment has been received/approved.
	Failed("490", true), //The transaction has failed. 
	Validation_Failure("491", true), //The transaction request contained errors and could not be processed correctly
	Technical_Failure("492", true), //Some technical failure prevented the completion of the transactions
	Cancelled_By_User("890", true), //The transaction was cancelled by the customer.
	Cancelled_By_Merchant("891", true), //The merchant cancelled the transaction.
	Rejected("690", true), //The transaction has been rejected by the (third party) payment provider.

	//non final statuses
	Pending_Input("790", false), //The transaction is on hold while the payment engine is waiting on further input from the consumer.
	Pending_Processing("791", false), //The transaction is being processed. 
	Awaiting_Consumer("792", false), //The Payment Engine is waiting for the consumer to return from a third party website, needed to complete the transaction.
	On_Hold("793", false) //The Payment Engine has put the payment on hold because the merchant has insufficient balance to perform the payment (eg. Refund).
	;

	private BuckarooStatusEnum(final String statusCode, final boolean isFinalStatus)
	{
		this.statusCode = statusCode;
		this.isFinalStatus = isFinalStatus;
	}

	private final String statusCode;
	private final boolean isFinalStatus;

	public String getStatusCode()
	{
		return statusCode;
	}

	public boolean isFinalStatus()
	{
		return isFinalStatus;
	}

	/** find the enum value with the given statusCode, or <code>null</code> if no match is found. */
	public static BuckarooStatusEnum getStatusForCode(final String statusCode)
	{
		BuckarooStatusEnum result = null;
		for (final BuckarooStatusEnum value : BuckarooStatusEnum.values())
		{
			if (value.getStatusCode().equals(statusCode))
			{
				result = value;
				break;
			}
		}
		return result;
	}
}