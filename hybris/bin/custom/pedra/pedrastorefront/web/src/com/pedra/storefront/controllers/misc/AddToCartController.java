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
package com.pedra.storefront.controllers.misc;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import com.pedra.storefront.controllers.AbstractController;
import com.pedra.storefront.controllers.ControllerConstants;
import com.pedra.storefront.forms.AddToCartForm;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller for Add to Cart functionality which is not specific to a certain page.
 */
@Controller
@Scope("tenant")
public class AddToCartController extends AbstractController
{
	private static final String TYPE_MISMATCH_ERROR_CODE = "typeMismatch";
	private static final String ERROR_MSG_TYPE = "errorMsg";
	private static final String QUANTITY_INVALID_BINDING_MESSAGE_KEY = "basket.error.quantity.invalid.binding";

	protected static final Logger LOG = Logger.getLogger(AddToCartController.class);

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@RequestMapping(value = "/cart/add", method = RequestMethod.POST, produces = "application/json")
	public String addToCart(@RequestParam("productCodePost") final String code, final Model model,
			@Valid final AddToCartForm form, final BindingResult bindingErrors)
	{
		if (bindingErrors.hasErrors())
		{
			return getViewWithBindingErrorMessages(model, bindingErrors);
		}

		final long qty = form.getQty();
		final ProductData productData = productFacade.getProductForCodeAndOptions(code,
				Arrays.asList(ProductOption.BASIC, ProductOption.PRICE));
		model.addAttribute("product", productData);

		model.addAttribute("cartData", cartFacade.getSessionCart());

		if (qty <= 0)
		{
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.quantity.invalid");
			return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
		}

		try
		{
			final CartModificationData cartModification = cartFacade.addToCart(code, qty);
			model.addAttribute("quantity", Long.valueOf(cartModification.getQuantityAdded()));
			model.addAttribute("entry", cartModification.getEntry());

			if (cartModification.getQuantityAdded() == 0L)
			{
				model.addAttribute(ERROR_MSG_TYPE, "basket.information.quantity.noItemsAdded." + cartModification.getStatusCode());
			}
			else if (cartModification.getQuantityAdded() < qty)
			{
				model.addAttribute(ERROR_MSG_TYPE,
						"basket.information.quantity.reducedNumberOfItemsAdded." + cartModification.getStatusCode());
			}

			// Put in the cart again after it has been modified
			model.addAttribute("cartData", cartFacade.getSessionCart());
		}
		catch (final CommerceCartModificationException ex)
		{
			model.addAttribute(ERROR_MSG_TYPE, "basket.error.occurred");
			model.addAttribute("quantity", Long.valueOf(0L));
			LOG.warn("Couldn't add product of code " + code + " to cart.", ex);
		}

		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	protected String getViewWithBindingErrorMessages(final Model model, final BindingResult bindingErrors)
	{
		for (final ObjectError error : bindingErrors.getAllErrors())
		{
			if (isTypeMismatchError(error))
			{
				model.addAttribute(ERROR_MSG_TYPE, QUANTITY_INVALID_BINDING_MESSAGE_KEY);
			}
			else
			{
				model.addAttribute(ERROR_MSG_TYPE, error.getDefaultMessage());
			}
		}
		return ControllerConstants.Views.Fragments.Cart.AddToCartPopup;
	}

	protected boolean isTypeMismatchError(final ObjectError error)
	{
		return error.getCode().equals(TYPE_MISMATCH_ERROR_CODE);
	}
}
