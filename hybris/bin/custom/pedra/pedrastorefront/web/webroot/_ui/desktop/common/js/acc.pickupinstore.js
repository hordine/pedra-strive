/* Extend jquery with a postJSON method */
jQuery.extend({
	postJSON: function (url, data, callback)
	{
		return jQuery.post(url, data, callback, "json");
	}
});

ACC.pickupinstore = {

	bindAll: function ()
	{
		with (ACC.pickupinstore)
		{
			bindPickupInStoreClick();
			bindPickupInStoreSearch();
			bindPickupHereInStoreButtonClick($('.pickup_store_results-list button'));
			bindPaginateStoreResultsButtons();
			bindPickupRadioButtonSelection();
			bindFindPickupStoresNearMeSearch();
			bindLoadingSpinnerOnClick('.add_to_cart_storepickup_form');
			bindLoadingSpinnerOnClick('.select_store_form');
		}
	},

	bindPickupInStoreClick: function ()
	{
		$(document).on('click', '.click_pickupInStore_Button', function (e)
		{
			var ele = $(this);
			var productId = "popup_store_pickup_form_" + $(this).attr('id');
			var cartItemProductPostfix = '';
			var productIdNUM = $(this).attr('id');
			productIdNUM = productIdNUM.split("_");
			productIdNUM = productIdNUM[1];

			if (productId !== null)
			{
				cartItemProductPostfix = '_' + productId;
			}

			var boxContent = $('#popup_store_pickup_form').clone();

			$.colorbox({
				html: boxContent,
				onComplete: function ()
				{
					boxContent.show();
					// set the ids for the pickup
					$("#colorbox #popup_store_pickup_form *").each(function ()
					{
						$(this).attr("id", $(this).attr("data-id"));
						$(this).removeAttr("data-id")
					});
					$("#colorbox #popup_store_pickup_form label[data-for=pickupQty]").attr("for", ele.attr("data-for"))
					$("#colorbox #popup_store_pickup_form label[data-for=pickupQty]").removeAttr("data-for")
					$("#colorbox  input#locationForSearch").focus()

					// set a unique id
					$("#colorbox #popup_store_pickup_form").attr("id", productId);


					// insert the product image
					$("#colorbox #" + productId + " .thumb").html(ele.attr("data-img"));

					// insert the product cart details
					$("#colorbox #" + productId + " .cart").html(ele.attr("data-productcart"));

					// insert the product name
					$("#colorbox #" + productId + " .details").html(ele.attr("data-productname"))

					// insert the form action
					$("#colorbox #" + productId + " form.form_field-input").attr("action", ele.attr("data-actionurl"))

					// set a unique id for the form
					$("#colorbox #" + productId + " form.form_field-input").attr("id", "pickup_in_store_search_form_product_" + productIdNUM)

					// set the quantity, if the quantity is undefined set the quantity to the data-value defined in the jsp
					$("#colorbox #" + productId + " #pickupQty").attr("value", ($('#qty').val() !== undefined ? $('#qty').val() : ele.attr("data-value")));

					// set the entry Number
					$("#colorbox #" + productId + " input.entryNumber").attr("value", ele.attr("data-entryNumber"))

					// set the cartPage bolean
					$("#colorbox #" + productId + " input#atCartPage").attr("value", ele.attr("data-cartPage"))

					// get the stores
					ACC.pickupinstore.rememberlocationSearchSubmit($('#atCartPage').val(), ele.attr("data-entryNumber"), ele.attr("data-actionurl"));
				}
			});
		});
	},

	bindPickupInStoreSearch: function ()
	{
		$(document).on('click', '#pickupstore_search_button', function (e)
		{
			ACC.pickupinstore.locationSearchSubmit($('#locationForSearch').val(), $('#atCartPage').val(), $('#pickupstore_search_button').parent().find('input.entryNumber').val(), $(this).parent('form').attr('action'));
			return false;
		});

		$(document).on('keypress', '#locationForSearch', function (e)
		{
			if (e.keyCode === 13)
			{
				ACC.pickupinstore.locationSearchSubmit($('#locationForSearch').val(), $('#atCartPage').val(), $('#pickupstore_search_button').parent().find('input.entryNumber').val(), $(this).parent('form').attr('action'));
				return false;
			}
		});
	},

	bindFindPickupStoresNearMeSearch: function ()
	{
		$('#find_pickupStoresNearMe_button').live('click', function (e)
		{
			var cartPageVal = $('#atCartPage').val();
			var entryNumber = $(this).parent().find('input.entryNumber').val();
			var formAction = $(this).parent('form').attr('action');
			navigator.geolocation.getCurrentPosition(
					function (position)
					{
						ACC.pickupinstore.locationSearchSubmit('', cartPageVal, entryNumber, formAction, position.coords.latitude, position.coords.longitude);
					},
					function (error)
					{
						console.log("An error occurred... The error code and message are: " + error.code + "/" + error.message);
					});
		});
	},

	bindPickupHereInStoreButtonClick: function (button)
	{
		button.live('click', function ()
		{
			$(this).prev('.hiddenPickupQty').val($('#pickupQty').val());
		});
	},

	bindLoadingSpinnerOnClick: function (form)
	{
		$(document).on('click', form, function (e)
		{
			$.colorbox.toggleLoadingOverlay();
		});
	},

	locationSearchSubmit: function (location, cartPage, entryNumber, productCode, latitude, longitude)
	{
		$.colorbox.toggleLoadingOverlay();
		$.ajax({
			url: productCode,
			data: {locationQuery: location, cartPage: cartPage, entryNumber: entryNumber, latitude: latitude, longitude: longitude},
			type: 'POST',
			success: function (response)
			{
				ACC.pickupinstore.refreshPickupInStoreColumn(response);
				$.colorbox.toggleLoadingOverlay();
				$('.pickup_store_results-item button').first().focus();
			}
		});
	},

	rememberlocationSearchSubmit: function (cartPage, entryNumber, formUrl)
	{
		$.ajax({
			url: formUrl,
			data: {
				cartPage: cartPage,
				entryNumber: entryNumber
			},
			type: 'GET',
			success: function (response)
			{

				ACC.pickupinstore.refreshPickupInStoreColumn(response);

				$('#locationForSearch').val(searchLocation);
			}
		});
	},

	bindPaginateStoreResultsButtons: function ()
	{
		$(['next', 'back']).each(function (i, buttonName)
		{
			$(document).on('click', '.paginate_' + buttonName + '_pickupstores_button', function (e)
			{
				var data = {
					location: $('#locationForSearch').val(),
					cartPage: $('#atCartPage').val(),
					entryNumber: $('.entryNumber').val(),
					page: $('#' + buttonName + '_page_value').val()
				};
				var url = $(this).parent('form').attr('action');

				ACC.pickupinstore.paginateResultsSubmit(url, data);
			});
		});
	},

	paginateResultsSubmit: function (url, data)
	{
		$.colorbox.toggleLoadingOverlay();
		$.ajax({
			url: url,
			data: data,
			type: 'GET',
			success: function (response)
			{
				ACC.pickupinstore.refreshPickupInStoreColumn(response);
				$.colorbox.toggleLoadingOverlay();
			}
		});
	},

	refreshPickupInStoreColumn: function (data)
	{
		$('#pickup_store_results').html(data);
		ACC.product.bindToAddToCartStorePickUpForm();
	},

	bindPickupRadioButtonSelection: function ()
	{
		$("form.cartEntryShippingModeForm").each(function ()
		{
			var formELE = $(this);
			formELE.find("input[checked]").click();
			formELE.find("input[name=shipMode]").change(function ()
			{
				if ($(this).val() == "pickUp")
				{
					ACC.pickupinstore.bindChangeToPickupinStoreTypeSelection(formELE);
				}
				else
				{
					ACC.pickupinstore.bindChangeToShippingTypeSelection(formELE);
				}
			})
		})
	},

	bindChangeToShippingTypeSelection: function (formELE)
	{
		formELE.find('.changeStoreLink').hide();
		formELE.removeClass("shipError");
		$('div#noStoreSelected').hide();
		formELE.find('input[type="radio"]').removeAttr("checked");
		formELE.find('input[type="radio"][value="ship"]').attr("checked", "checked");
		if (!ACC.pickupinstore.checkIfPointOfServiceIsEmpty(formELE))
		{
			formELE.submit();
		}
	},

	bindChangeToPickupinStoreTypeSelection: function (formELE)
	{
		formELE.find('.changeStoreLink').show();
		formELE.find('input[type="radio"]').removeAttr("checked");
		formELE.find('input[type="radio"][value="pickUp"]').attr("checked", "checked");
	},

	validatePickupinStoreCartEntires: function ()
	{
		var validationErrors = false;
		$("form.cartEntryShippingModeForm").each(function ()
		{
			var formid = "#" + $(this).attr('id');
			if ($(formid + ' input[value=pickUp][checked]').length && ACC.pickupinstore.checkIfPointOfServiceIsEmpty($(this)))
			{
				$(this).addClass("shipError");
				validationErrors = true;
			}
		});

		if (validationErrors)
		{
			$('div#noStoreSelected').show().focus();
			$(window).scrollTop(0);
		}
		return validationErrors;
	},

	checkIfPointOfServiceIsEmpty: function (cartEntryDeliveryModeForm)
	{
		return (!cartEntryDeliveryModeForm.find('.pointOfServiceName').text().trim().length);
	}
};

$(document).ready(function ()
{
	ACC.pickupinstore.bindAll();
});
