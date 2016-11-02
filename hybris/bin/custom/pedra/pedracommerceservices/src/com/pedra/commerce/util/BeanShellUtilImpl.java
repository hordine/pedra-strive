/**
 * 
 */
package com.pedra.commerce.util;

import de.hybris.platform.cockpit.model.meta.ObjectTemplate;
import de.hybris.platform.cockpit.services.config.UIComponentConfiguration;
import de.hybris.platform.cockpit.services.config.UIConfigurationService;
import de.hybris.platform.cockpit.services.meta.TypeService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.jalo.JaloSession;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pedra.commerce.service.PedraCommerceProductService;
import com.pedra.core.data.ProductPopularityData;


/**
 * @author henrique.ordine
 * 
 */
public class BeanShellUtilImpl implements BeanShellUtil
{

	private static final Logger LOG = Logger.getLogger(BeanShellUtilImpl.class);

	/**
	 * 
	 * Example bean shell call:
	 * 
	 * com.pedra.core.util.BeanShellUtil.cockpitConfiguration("Order.date", "listView",
	 * de.hybris.platform.cockpit.services.config.ListViewConfiguration.class);
	 * 
	 * 
	 * @param object
	 *           Example: "Order.date"
	 * @param code
	 *           Example: "listView"
	 * @param expectedClass
	 *           Example: ListViewConfiguration.class
	 */
	@Override
	public void cockpitConfiguration(final String object, final String code, final Class expectedClass)
	{
		final TypeService typeService = Registry.getApplicationContext().getBean(TypeService.class);
		final ObjectTemplate objectTemplate = typeService.getObjectTemplate(object);
		if (objectTemplate == null)
		{
			LOG.error("Object Template for[" + object + "] is null");
			return;
		}
		final UIConfigurationService configurationService = Registry.getApplicationContext().getBean(UIConfigurationService.class);
		final UIComponentConfiguration config = configurationService.getComponentConfiguration(objectTemplate, code, expectedClass);
		if (config == null)
		{
			LOG.info("Component configuration for [" + object + "," + code + "] is null");
			return;
		}
		LOG.info("Configuration class is[" + config.getClass().getName() + "]");
	}

	@Override
	public void closeSessionsByUser(final String uid)
	{
		final Collection<JaloSession> sessions = Registry.getCurrentTenant().getJaloConnection().getAllSessions();
		if (sessions != null)
		{
			for (final JaloSession session : sessions)
			{
				if (session.getUser() != null)
				{
					if (StringUtils.equals(uid, session.getUser().getUid()))
					{
						LOG.info("Closing JaloSession[" + session.getSessionID() + "]");
						session.close();
					}
				}
			}
		}
	}

	@Override
	public void showProductPopularity()
	{
		final PedraCommerceProductService prodService = Registry.getApplicationContext().getBean(PedraCommerceProductService.class);
		final List<ProductPopularityData> popularProducts = prodService.findProductPopularity();
		if (popularProducts != null)
		{
			LOG.info("Products returned: " + popularProducts.size());
			final Iterator<ProductPopularityData> it = popularProducts.iterator();
			while (it.hasNext())
			{
				final ProductPopularityData product = it.next();
				LOG.info("Iterator: ProductCode[" + product.getProductCode() + "]    quantitySold[" + product.getQuantitySold() + "]");
			}
			for (final ProductPopularityData product : popularProducts)
			{
				LOG.info("Will log lines for a product");
				LOG.info("For loop: ProductCode[" + product.getProductCode() + "]    quantitySold[" + product.getQuantitySold() + "]");
			}
		}
	}

}
