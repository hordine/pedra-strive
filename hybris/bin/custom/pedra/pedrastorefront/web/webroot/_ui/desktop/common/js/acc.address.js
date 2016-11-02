ACC.address = {
	countryIsoToFormIdMap: {
		US: "#addressFormUS",
		CA: "#addressFormCA",
		CN: "#addressFormCN",
		JP: "#addressFormJP",
		DE: "#defaultAddressForm",
		GB: "#defaultAddressForm"
	},

	handleChangeAddressButtonClick: function ()
	{
		$.getJSON(getDeliveryAddressesUrl, ACC.address.handleAddressDataLoad);
		return false;
	},

	handleAddressDataLoad: function (data)
	{
		ACC.address.setupDeliveryAddressPopupForm(data);

		// Show the delivery address popup
		$.colorbox({
			inline: true,
			href: "#popup_checkout_delivery_address",
			overlayClose: false,
			onOpen: function ()
			{
				// empty address form fields
				ACC.address.emptyAddressForm();
				$(document).on('change', '#saveAddress', function ()
				{
					var saveAddressChecked = $(this).prop('checked');
					$('#defaultAddress').prop('disabled', !saveAddressChecked);
					if (!saveAddressChecked)
					{
						$('#defaultAddress').prop('checked', false);
					}
				});
			},
			onComplete: function ()
			{
				ACC.common.refreshScreenReaderBuffer();
				$.colorbox.resize();
			},
			onClosed: function ()
			{
				ACC.common.refreshScreenReaderBuffer();
			}
		});
	},

	setupDeliveryAddressPopupForm: function (data)
	{
		// Fill the available delivery addresses
		$('.delivery_addresses_list').html($('#deliveryAddressesTemplate').tmpl({addresses: data}));
		// Handle selection of address
		$('.delivery_addresses_list button.use_address').click(ACC.address.handleSelectExistingAddressClick);
		// Handle edit address
		$('.delivery_addresses_list button.edit').click(ACC.address.handleEditAddressClick);
		// Handle set default address
		$('.delivery_addresses_list button.default').click(ACC.address.handleDefaultAddressClick);
	},

	emptyAddressForm: function ()
	{
		var options = {
			url: getDeliveryAddressFormUrl,
			data: {addressId: '', createUpdateStatus: ''},
			type: 'GET',
			success: function (data)
			{
				$('#create_update_address_form_container_div').html(data);
				ACC.address.bindCreateUpdateAddressForm();
			}
		};

		$.ajax(options);
	},

	handleSelectExistingAddressClick: function ()
	{
		var addressId = $(this).attr('data-address');
		$.postJSON(setDeliveryAddressUrl, {addressId: addressId}, ACC.address.handleSelectExitingAddressSuccess);
		return false;
	},

	handleEditAddressClick: function ()
	{
		var addressId = $(this).attr('data-address');
		var options = {
			url: getDeliveryAddressFormUrl,
			data: {addressId: addressId, createUpdateStatus: ''},
			target: '#create_update_address_form_container_div',
			type: 'GET',
			success: function (data)
			{
				ACC.address.bindCreateUpdateAddressForm();
				$.colorbox.resize();
			},
			error: function (xht, textStatus, ex)
			{
				alert("Failed to update cart. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
			}
		};

		$(this).ajaxSubmit(options);
		return false;
	},

	handleDefaultAddressClick: function ()
	{
		var addressId = $(this).attr('data-address');
		var options = {
			url: setDefaultAddressUrl,
			data: {addressId: addressId},
			type: 'GET',
			success: function (data)
			{
				ACC.address.setupDeliveryAddressPopupForm(data);
			},
			error: function (xht, textStatus, ex)
			{
				alert("Failed to update address book. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
			}
		};

		$(this).ajaxSubmit(options);
		return false;
	},

	handleSelectExitingAddressSuccess: function (data)
	{
		if (data != null)
		{
			ACC.refresh.refreshPage(data);
			parent.$.colorbox.close();
		}
		else
		{
			alert("Failed to set delivery address");
		}
	},

	bindCreateUpdateAddressForm: function ()
	{
		$('.create_update_address_form').each(function ()
		{
			var options = {
				type: 'POST',
				beforeSubmit: function ()
				{
					$('#checkout_delivery_address').block({ message: "<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif' />" });
				},
				success: function (data)
				{
					$('#create_update_address_form_container_div').html(data);
					var status = $('.create_update_address_id').attr('status');
					if (status != null && "success" === status.toLowerCase())
					{
						ACC.refresh.getCheckoutCartDataAndRefreshPage();
						parent.$.colorbox.close();
					}
					else
					{
						ACC.address.bindCreateUpdateAddressForm();
						$.colorbox.resize();
					}
				},
				error: function (xht, textStatus, ex)
				{
					alert("Failed to update cart. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
				},
				complete: function ()
				{
					$('#checkout_delivery_address').unblock();
				}
			};

			$(this).ajaxForm(options);
		});
	},

	refreshDeliveryAddressSection: function (data)
	{
		$('#checkout_summary_deliveryaddress_div').replaceWith($('#deliveryAddressSummaryTemplate').tmpl(data));
		//bind change address button
		$('div.checkout_summary_flow_a .change_address_button').click(ACC.address.handleChangeAddressButtonClick);
	},

	bindSuggestedDeliveryAddresses: function ()
	{
		var status = $('.add_edit_delivery_address_id').attr('status');
		if (status != null && "hasSuggestedAddresses" == status)
		{
			ACC.address.showSuggestedAddressesPopup();
		}
	},

	showSuggestedAddressesPopup: function ()
	{
		$.colorbox({
			inline: true,
			height: false,
			width: 450,
			href: "#popup_suggested_delivery_addresses",
			overlayClose: false,
			onComplete: function ()
			{
				$(this).colorbox.resize();
			}
		});
	},

	bindCountrySpecificAddressForms: function ()
	{
		$('#countrySelector :input').change(function ()
		{
			ACC.address.displayCountrySpecificAddressForm();
		})
	},

	displayCountrySpecificAddressForm: function ()
	{
		$('.i18nAddressForm').hide();
		var countrySelection =  $('#countrySelector :input').val();
		if (ACC.address.countryIsoToFormIdMap[countrySelection] != null)
		{
			$(ACC.address.countryIsoToFormIdMap[countrySelection]).show();
			$(ACC.address.countryIsoToFormIdMap[countrySelection] + ' #countryIso').attr("value", countrySelection);
		}
		else if (countrySelection != undefined && countrySelection.length == 2)
		{
			$("#defaultAddressForm").show();
			$("#defaultAddressForm #countryIso").attr("value", countrySelection);
		}
	},

	bindToChangeAddressButton: function ()
	{
		$('div.checkout_summary_flow_a .change_address_button').on("click", ACC.address.handleChangeAddressButtonClick);
	}
}

/* Extend jquery with a postJSON method */
jQuery.extend({
	postJSON: function (url, data, callback)
	{
		return jQuery.post(url, data, callback, "json");
	}
});

// Address Verification
$(document).ready(function ()
{
	$('#countrySelector').show();
	ACC.address.displayCountrySpecificAddressForm();
	ACC.address.bindToChangeAddressButton()
	ACC.address.bindCreateUpdateAddressForm()
	ACC.address.bindSuggestedDeliveryAddresses();
	ACC.address.bindCountrySpecificAddressForms();
});


