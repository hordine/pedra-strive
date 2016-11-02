ACCMOB.address = {

	defaultDialogConfig: {
		mode: 'blank',
		dialogForce: false,
		showModal: true,
		headerText: '',
		headerClose: true,
		animate: false,
		zindex: 9999,
		blankContent: '',
		themeDialog: 'b',
		themeInput: 'e',
		themeButtonDefault: false,
		themeHeader: 'a',
		callbackOpen: ACCMOB.common.lockScreen,
		callbackClose: ACCMOB.common.unlockScreen
	},

	countryIsoToFormIdMap: {
		US: "#addressFormUS",
		CA: "#addressFormCA",
		CN: "#addressFormCN",
		JP: "#addressFormJP",
		DE: "#defaultAddressForm",
		GB: "#defaultAddressForm"
	},

	bindCountrySpecificAddressForms: function ()
	{
		$('#countrySelector :input').change(function ()
		{
			ACCMOB.address.displayCountrySpecificAddressForm();
		})
	},

	displayCountrySpecificAddressForm: function ()
	{
		$('.i18nAddressForm').hide();
		var countrySelection = $('#countrySelector :input').val();
		var countryIsoToFormIdMap = ACCMOB.address.countryIsoToFormIdMap[countrySelection];
		if (countryIsoToFormIdMap != null)
		{
			$(countryIsoToFormIdMap).show();
			$(countryIsoToFormIdMap + ' #countryIso').attr("value", countrySelection);
		}
		else if (countrySelection !== undefined && countrySelection.length == 2)
		{
			$("#defaultAddressForm").show();
			$("#defaultAddressForm #countryIso").attr("value", countrySelection);
		}
	},

	displayCountrySelector: function ()
	{
		$('#countrySelector').show();
	},

	displaySuggestedAddressesPopup: function ()
	{
		var config = ACCMOB.address.defaultDialogConfig;
		$.extend(config, {blankContent: $("#popup_suggested_delivery_addresses").html()});
		var dialog = $(document.createElement('div'));
		dialog.simpledialog2(config);
	},

	initSuggestedAddressesPopup: function ()
	{
		var status = $('.add_edit_delivery_address_id').attr('status');
		if (status != null && "hasSuggestedAddresses" == status)
		{
			// NOTE: Jquery events were not triggering properly in this case.
			// This native js did the trick.
			document.onreadystatechange = function()
			{
				if (document.readyState === "complete")
				{
					ACCMOB.address.displaySuggestedAddressesPopup();
				}
			}
		}
	},

	initialize: function ()
	{
		with (ACCMOB.address)
		{
			displayCountrySelector();
			displayCountrySpecificAddressForm();
			bindCountrySpecificAddressForms();
			initSuggestedAddressesPopup();
		}
	}
}

$(document).ready(function ()
{
	ACCMOB.address.initialize();
});
