/**
 * 
 */
package com.pedra.core.additions;

/**
 * @author henrique.ordine
 *
 */
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pedra.core.constants.GeneratedPedraCoreConstants.Attributes.Customer;
import com.pedra.core.constants.GeneratedPedraCoreConstants.Attributes.Product;


/**
 * Test class for verifying correct type code generation
 * 
 * @author sebastian.mahr $Id: AttributeTest.java 2632 2011-07-13 07:44:55Z Mahr.Hybris $
 */
@SuppressWarnings("deprecation")
@UnitTest
public class GeneratedTypesTest
{

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(GeneratedTypesTest.class.getName());


	@Before
	public void setUp()
	{
		//
	}

	@After
	public void tearDown()
	{
		// implement code executed after each test here
	}

	/**
	 * Test Product if type is extended correctly
	 */
	@Test
	public void testProductAttribute()
	{
		assertTrue(ProductModel.INTERNALONLY.equals(Product.INTERNALONLY));
	}

	/**
	 * Test Customer if type is extended correctly
	 */
	@Test
	public void testCustomerAttribute()
	{
		assertTrue(CustomerModel.ISINTERNAL.equals(Customer.ISINTERNAL));
	}
}