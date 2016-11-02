/**
 * 
 */
package com.pedra.core.model;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;


/**
 * @author henrique.ordine
 * 
 */
public class DynamicHybrisCustomerAttributeBean implements DynamicAttributeHandler<Boolean, CustomerModel>
{

	@Override
	public Boolean get(final CustomerModel model)
	{
		if (model == null)
		{
			throw new IllegalArgumentException("Item model is required");
		}

		final String email = model.getUid();
		return Boolean.valueOf(email != null && (email.endsWith("gmail.com")));

	}

	@Override
	public void set(final CustomerModel model, final Boolean value)
	{
		//throw new UnsupportedOperationException("Not supported yet.");
	}
}