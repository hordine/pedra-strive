/**
 * 
 */
package com.pedra.search.providers;

import de.hybris.platform.commerceservices.search.solrfacetsearch.provider.impl.ProductReviewAverageRatingValueProvider;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * @author henrique.ordine
 * 
 */
public class PedraProductReviewAverageRatingValueProvider extends ProductReviewAverageRatingValueProvider
{

	private static final Logger LOG = Logger.getLogger(PedraProductReviewAverageRatingValueProvider.class);

	@Override
	protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
			final LanguageModel language, final Object value)
	{
		String rangeName = null;
		try
		{
			rangeName = getRangeName(indexedProperty, value);
		}
		catch (final FieldValueProviderException e)
		{
			LOG.error("Could not get Range value", e);
		}
		final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
				language == null ? null : language.getIsocode());
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, rangeName == null ? value : rangeName));
		}
	}
}
