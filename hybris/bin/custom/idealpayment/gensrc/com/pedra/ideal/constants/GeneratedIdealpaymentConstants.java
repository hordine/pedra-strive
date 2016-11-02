/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Sep 11, 2014 3:39:51 PM                     ---
 * ----------------------------------------------------------------
 */
package com.pedra.ideal.constants;

/**
 * @deprecated use constants in Model classes instead
 */
@Deprecated
@SuppressWarnings({"unused","cast","PMD"})
public class GeneratedIdealpaymentConstants
{
	public static final String EXTENSIONNAME = "idealpayment";
	public static class TC
	{
		public static final String IDEALPAYMENTINFO = "IdealPaymentInfo".intern();
		public static final String IDEALPAYMENTREQUEST = "IdealPaymentRequest".intern();
		public static final String IDEALPAYMENTRESPONSE = "IdealPaymentResponse".intern();
	}
	public static class Attributes
	{
		public static class Order
		{
			public static final String CARTCODE = "cartCode".intern();
			public static final String REQUESTS = "requests".intern();
		}
	}
	public static class Relations
	{
		public static final String IDEALPAYMENTREQUESTRESPONSERELATION = "IdealPaymentRequestResponseRelation".intern();
		public static final String ORDERIDEALPAYMENTREQUESTRELATION = "OrderIdealPaymentRequestRelation".intern();
	}
	
	protected GeneratedIdealpaymentConstants()
	{
		// private constructor
	}
	
	
}
