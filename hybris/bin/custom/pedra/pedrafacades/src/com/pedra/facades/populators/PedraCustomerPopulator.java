/**
 * 
 */
package com.pedra.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.pedra.facades.user.data.PedraCustomerData;


/**
 * @author henrique.ordine
 * 
 */
public class PedraCustomerPopulator implements Populator<CustomerModel, PedraCustomerData>
{


	@Override
	public void populate(final CustomerModel source, final PedraCustomerData target) throws ConversionException
	{
		target.setInternal(source.getIsInternal() == null ? false : source.getIsInternal().booleanValue());
	}

}
