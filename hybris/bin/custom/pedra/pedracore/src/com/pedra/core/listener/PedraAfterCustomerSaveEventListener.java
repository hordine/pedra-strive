/**
 * 
 */
package com.pedra.core.listener;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.tx.AfterSaveEvent;
import de.hybris.platform.tx.AfterSaveListener;
import de.hybris.platform.util.mail.MailUtils;

import java.util.Collection;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * 
 */
public class PedraAfterCustomerSaveEventListener implements AfterSaveListener
{

	private static final Logger LOG = Logger.getLogger(PedraAfterCustomerSaveEventListener.class.getName());


	private ModelService modelService;

	/**
	 * Handles changes to the User Item Type
	 */
	@Override
	public void afterSave(final Collection<AfterSaveEvent> events)
	{
		for (final AfterSaveEvent event : events)
		{
			final int type = event.getType();
			if (AfterSaveEvent.UPDATE == type || AfterSaveEvent.CREATE == type)
			{
				final PK pk = event.getPk();

				final ItemModel item = modelService.get(pk);

				final String modelType = modelService.getModelType(item);

				LOG.debug("ModelType of " + item.getPk() + ": " + modelType);
				LOG.debug("Itemtype of " + item.getPk() + ": " + item.getItemtype());

				if (ProductModel._TYPECODE.equals(modelType))
				{
					LOG.debug("ProductModel being edited");
				}

				if (item instanceof ProductModel)
				{
					LOG.debug("ProductModel being edited");
				}


				// We could do this to find out the Type of the item being saved, but we'd have to query the DB for every item being saved, which might affect performance
				/*
				 * final ItemModel itemModel = modelService.get(pk);
				 * 
				 * if (itemModel instanceof CustomerModel) { LOG.debug("Customer model was saved..."); final CustomerModel
				 * customer = modelService.get(pk); LOG.debug("Customer model was saved..." + customer.getCustomerID()); }
				 */

				//4 is the TypeCode for the Customer type
				if (4 == pk.getTypeCode())
				{
					final CustomerModel customer = modelService.get(pk);
					afterCustomerSave(customer, event.getType());
				}


			}
		}

	}

	private void afterCustomerSave(final CustomerModel customer, final int eventType)
	{
		LOG.debug("<< afterCustomerSave(): " + customer.getCustomerID());
		HtmlEmail htmlEmail;
		try
		{
			htmlEmail = (HtmlEmail) MailUtils.getPreConfiguredEmail();
			htmlEmail.addTo("crmmail@prominent.nu");
			htmlEmail.setSubject("test");
		}
		catch (final EmailException e)
		{
			LOG.error("Error sending customer account changes by email", e);
		}

	}


	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
