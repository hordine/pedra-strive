/**
 * 
 */
package com.pedra.core.data;

/**
 * @author henrique.ordine
 * 
 */
public class ProductPopularityData
{

	private String productCode;
	private Long quantitySold;

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}

	public Long getQuantitySold()
	{
		return quantitySold;
	}

	public void setQuantitySold(final Long quantitySold)
	{
		this.quantitySold = quantitySold;
	}

}
