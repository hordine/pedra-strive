/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package com.pedra.storefront.forms.validation;

import com.pedra.storefront.forms.AddressForm;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Validator for address forms. Enforces the order of validation
 */
@Component("addressValidator")
public class AddressValidator implements Validator
{
	private static final String CN_COUNTRY_ISOCODE = "CN";
	private static final String JP_COUNTRY_ISOCODE = "JP";
	private static final String TITLE_KEY = "titleCode";
	private static final String FIRSTNAME_KEY = "firstName";
	private static final String LASTNAME_KEY = "lastName";
	private static final String LINE1_KEY = "line1";
	private static final String LINE2_KEY = "line2";
	private static final String TOWN_KEY = "townCity";
	private static final String POSTCODE_KEY = "postcode";
	private static final String REGION_KEY = "regionIso";
	private static final String COUNTRY_KEY = "countryIso";

	private static final String TITLE_ERR = "address.title.invalid";
	private static final String FIRSTNAME_ERR = "address.firstName.invalid";
	private static final String LASTNAME_ERR = "address.lastName.invalid";
	private static final String LINE1_ERR = "address.line1.invalid";
	private static final String LINE2_ERR = "address.line2.invalid";
	private static final String TOWN_ERR = "address.townCity.invalid";
	private static final String POSTCODE_ERR = "address.postcode.invalid";
	private static final String REGION_ERR = "address.regionIso.invalid";
	private static final String COUNTRY_ERR = "address.country.invalid";

	private static final int MAX_FIELD_LENGTH = 255;
	private static final int MAX_POSTCODE_LENGTH = 10;


	@Override
	public boolean supports(final Class<?> aClass)
	{
		return AddressForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final AddressForm addressForm = (AddressForm) object;
		final String isoCode = addressForm.getCountryIso();

		validateStringField(isoCode, COUNTRY_KEY, COUNTRY_ERR, MAX_FIELD_LENGTH, errors);

		// Some countries have different form validation requirements.
		if (isoCode.equals(CN_COUNTRY_ISOCODE))
		{
			validateChineseAddressForm(addressForm, errors);
		}
		else if (isoCode.equals(JP_COUNTRY_ISOCODE))
		{
			validateJapaneseAddressForm(addressForm, errors);
		}
		else
		{
			validateStandardAddress(addressForm, errors);
		}
	}

	protected void validateStandardAddress(final AddressForm standardForm, final Errors errors)
	{
		validateStringField(standardForm.getTitleCode(), TITLE_KEY, TITLE_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(standardForm.getFirstName(), FIRSTNAME_KEY, FIRSTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(standardForm.getLastName(), LASTNAME_KEY, LASTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(standardForm.getLine1(), LINE1_KEY, LINE1_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(standardForm.getTownCity(), TOWN_KEY, TOWN_ERR, MAX_FIELD_LENGTH, errors);
		validateFieldNotNull(standardForm.getRegionIso(), REGION_KEY, REGION_ERR, errors);
		validateStringField(standardForm.getPostcode(), POSTCODE_KEY, POSTCODE_ERR, MAX_POSTCODE_LENGTH, errors);
	}

	protected void validateChineseAddressForm(final AddressForm chineseForm, final Errors errors)
	{
		validateStringField(chineseForm.getPostcode(), POSTCODE_KEY, POSTCODE_ERR, MAX_POSTCODE_LENGTH, errors);
		validateStringField(chineseForm.getRegionIso(), REGION_KEY, REGION_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(chineseForm.getTownCity(), TOWN_KEY, TOWN_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(chineseForm.getLine1(), LINE1_KEY, LINE1_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(chineseForm.getLastName(), LASTNAME_KEY, LASTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(chineseForm.getFirstName(), FIRSTNAME_KEY, FIRSTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(chineseForm.getTitleCode(), TITLE_KEY, TITLE_ERR, MAX_FIELD_LENGTH, errors);
	}

	protected void validateJapaneseAddressForm(final AddressForm japaneseForm, final Errors errors)
	{
		validateStringField(japaneseForm.getLastName(), LASTNAME_KEY, LASTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(japaneseForm.getFirstName(), FIRSTNAME_KEY, FIRSTNAME_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(japaneseForm.getPostcode(), POSTCODE_KEY, POSTCODE_ERR, MAX_POSTCODE_LENGTH, errors);
		validateStringField(japaneseForm.getRegionIso(), REGION_KEY, REGION_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(japaneseForm.getTownCity(), TOWN_KEY, TOWN_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(japaneseForm.getLine2(), LINE2_KEY, LINE2_ERR, MAX_FIELD_LENGTH, errors);
		validateStringField(japaneseForm.getLine1(), LINE1_KEY, LINE1_ERR, MAX_FIELD_LENGTH, errors);
	}

	protected static void validateStringField(final String addressField, final String addressFieldKey,
			final String fieldErrorMessage, final int maxFieldLength, final Errors errors)
	{
		if (StringUtils.isEmpty(addressField) || (StringUtils.length(addressField) > maxFieldLength))
		{
			errors.rejectValue(addressFieldKey, fieldErrorMessage);
		}
	}

	protected static void validateFieldNotNull(final String addressField, final String addressFieldKey,
			final String fieldErrorMessage, final Errors errors)
	{
		if (addressField == null)
		{
			errors.rejectValue(addressFieldKey, fieldErrorMessage);
		}
	}
}
