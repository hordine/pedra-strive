/**
 * 
 */
package com.pedra.web.services.customresource;

import de.hybris.platform.core.resource.product.ProductResource;
import de.hybris.platform.webservices.AbstractCollectionResource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pedra.commerce.service.PedraCommerceProductService;
import com.pedra.core.data.ProductPopularityData;
import com.pedra.web.services.converter.ProductPopularityConverter;
import com.pedra.web.services.customdto.DiagnosticDTO;
import com.pedra.web.services.customdto.ProductPopularitiesDTO;
import com.pedra.web.services.customdto.ProductPopularityDTO;
import com.pedra.web.services.customdto.impl.DiagnosticDTOImpl;


/**
 * @author henrique.ordine
 * 
 */
@Path("/diagnostic")
@SuppressWarnings("PMD")
public class DiagnosticResource extends AbstractCollectionResource<ProductResource>
{

	private PedraCommerceProductService pedraCommerceProductService;
	private ProductPopularityConverter productPopularityConverter;

	/**
	 * @param composedTypeName
	 */
	public DiagnosticResource(final String composedTypeName)
	{
		super(composedTypeName);
	}

	public DiagnosticResource()
	{
		super("diagnostic");
	}


	@GET
	public Response getDiagnosticData()
	{
		final DiagnosticDTO ci = new DiagnosticDTOImpl();
		getResponse().entity(ci);
		return getResponse().build();
	}

	@POST
	public Response getDiagnosticDataByPost()
	{
		final DiagnosticDTO ci = new DiagnosticDTOImpl();
		getResponse().entity(ci);
		return getResponse().build();
	}

	@GET
	@Path(value = "/productPopularity")
	public Response getProductPopularity()
	{
		final List<ProductPopularityData> productPopularityList = getPedraCommerceProductService().findProductPopularity();
		final List<ProductPopularityDTO> dtos = getProductPopularityConverter().convert(productPopularityList);
		final ProductPopularitiesDTO entity = new ProductPopularitiesDTO();
		entity.setProductPopularities(dtos);
		getResponse().type(MediaType.APPLICATION_JSON);
		getResponse().header("Accept", "application/json");
		getResponse().entity(entity);
		return getResponse().build();
	}

	public PedraCommerceProductService getPedraCommerceProductService()
	{
		return pedraCommerceProductService;
	}

	public void setPedraCommerceProductService(final PedraCommerceProductService pedraCommerceProductService)
	{
		this.pedraCommerceProductService = pedraCommerceProductService;
	}

	public ProductPopularityConverter getProductPopularityConverter()
	{
		return productPopularityConverter;
	}

	public void setProductPopularityConverter(final ProductPopularityConverter productPopularityConverter)
	{
		this.productPopularityConverter = productPopularityConverter;
	}

}
