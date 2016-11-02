/**
 * 
 */
package com.pedra.core.product;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author henrique.ordine
 * 
 */

@UnitTest
public class MostSignificantProductTest
{

	@Test
	public void testHandler()
	{
		final OrderModel order = new OrderModel();
		final List<AbstractOrderEntryModel> entries = new ArrayList<AbstractOrderEntryModel>();
		final OrderEntryModel orderEntry1 = new OrderEntryModel();
		orderEntry1.setQuantity(3L);
		final ProductModel product1 = new ProductModel();
		product1.setCode("product1");
		orderEntry1.setProduct(product1);
		entries.add(orderEntry1);
		final OrderEntryModel orderEntry2 = new OrderEntryModel();
		orderEntry2.setQuantity(5L);
		final ProductModel product2 = new ProductModel();
		product2.setCode("product2");
		orderEntry2.setProduct(product2);
		entries.add(orderEntry2);
		order.setEntries(entries);
		final ProductModel mostSignificant = order.getMostSignificantProduct();
		Assert.assertEquals(mostSignificant.getCode(), "product2");
	}
}
