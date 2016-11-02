/**
 * 
 */
package com.pedra.core.product;

import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.pedra.core.model.PedraProductModel;
import com.pedra.core.product.dao.PedraProductDao;


/**
 * @author henrique.ordine
 * 
 */
@IntegrationTest
public class PedraProductDaoTest extends ServicelayerTransactionalTest
{
	/** As this is an integration test the test to class gets injected here. */
	@Resource
	private PedraProductDao pedraProductDao;

	/** ModelService used for creation of test data. */
	@Resource
	private ModelService modelService;

	@Test
	public void pedraProductDaoTest()
	{
		final CatalogModel catalog = new CatalogModel();
		catalog.setId("pedraProductDaoTest");
		catalog.setName("pedraProductDaoTest");
		modelService.save(catalog);
		final CatalogVersionModel cv = new CatalogVersionModel();
		cv.setVersion("test");
		cv.setActive(true);
		modelService.save(cv);
		final CategoryModel category = new CategoryModel();
		category.setCode("pedraProductDaoCategory");
		category.setName("pedraProductDaoCategory");
		category.setCatalogVersion(cv);
		modelService.save(category);
		final List<PedraProductModel> products = pedraProductDao.findOnlineProductsByCategory(category, Gender.FEMALE);
		assertTrue("PedraProducts should be empty", products.isEmpty());
	}
}
