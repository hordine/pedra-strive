/**
 * 
 */
package com.pedra.web.services.customdto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author henrique.ordine
 * 
 */
@XmlRootElement(name = "productPopularities")
public class ProductPopularitiesDTO
{

	private List<ProductPopularityDTO> productPopularities;

	/**
	 * 
	 */
	public ProductPopularitiesDTO()
	{
		// YTODO Auto-generated constructor stub
	}

	@XmlElement(name = "productPopularity")
	public List<ProductPopularityDTO> getProductPopularities()
	{
		return productPopularities;
	}

	public void setProductPopularities(final List<ProductPopularityDTO> productPopularities)
	{
		this.productPopularities = productPopularities;
	}

}
