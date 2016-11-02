/**
 * 
 */
package com.pedra.web.services.customdto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author henrique.ordine
 * 
 */
@XmlRootElement(name = "productPopularity")
public class ProductPopularityDTO
{

	private String productCode;
	private Long quantitySold;

	@XmlAttribute
	public String getProductCode()
	{
		return productCode;
	}

	@XmlAttribute
	public Long getQuantitySold()
	{
		return quantitySold;
	}

	public void setProductCode(final String productCode)
	{
		this.productCode = productCode;
	}

	public void setQuantitySold(final Long quantitySold)
	{
		this.quantitySold = quantitySold;
	}

}
