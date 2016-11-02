ACC.silentorderpost = {


	bindUseDeliveryAddress: function ()
	{
		$('#useDeliveryAddress').change(function ()
		{
			ACC.silentorderpost.useDeliveryAddressSelected();
		});
		if ($('#useDeliveryAddress').is(":checked"))
		{
			ACC.silentorderpost.disableAddressForm();
		}
	},

	bindSubmitSilentOrderPostForm: function ()
	{
		$('.submit_silentOrderPostForm').click(function ()
		{
			$('.i18nAddressForm').filter(":hidden").remove();
			ACC.silentorderpost.enableAddressForm();
			$('#silentOrderPostForm').submit();
		});
	},

	bindCycleFocusEvent: function ()
	{
		$('#lastInTheForm').blur(function ()
		{
			$('#silentOrderPostForm [tabindex$="10"]').focus();
		})
	},


	isEmpty: function (obj)
	{
		if (typeof obj == 'undefined' || obj === null || obj === '') return true;
		return false;
	},

	disableAddressForm: function ()
	{
		$('input[id^="address\\."]').prop('disabled', true);
	},

	enableAddressForm: function ()
	{
		$('input[id^="address\\."]').prop('disabled', false);
	},

	useDeliveryAddressSelected: function ()
	{
		if ($('#useDeliveryAddress').is(":checked"))
		{
			$('#address\\.country').val($('#useDeliveryAddress').data('countryisocode'));
			$('#address\\.country').change();
			$('#address\\.firstName').val($('#useDeliveryAddress').data('firstname'));
			$('#address\\.surname').val($('#useDeliveryAddress').data('lastname'));
			$('#address\\.townCity').val($('#useDeliveryAddress').data('town'));
			$('#address\\.postcode').val($('#useDeliveryAddress').data('postalcode'));
			$('#address\\.region').val($('#useDeliveryAddress').data('regionisocode'));
			$('#address\\.line1').val($('#useDeliveryAddress').data('line1'));
			$('#address\\.line2').val($('#useDeliveryAddress').data('line2'));
			ACC.silentorderpost.disableAddressForm();
		}
		else
		{
			$('input[id^="address\\."]').val("");
			$('#address\\.country').change();
			ACC.silentorderpost.enableAddressForm();
		}
	},

	displayStartDateIssueNum: function ()
	{
		var cardType = $('#silentOrderPostForm [tabindex="1"]').val();
		if (cardType == 'maestro' || cardType == 'switch')
		{
			$('#startDate').removeAttr('hidden');
			$('#issueNum').removeAttr('hidden');
		}
		else
		{
			$('#startDate').attr('hidden', 'true');
			$('#issueNum').attr('hidden', 'true');
		}
	}
}

$(document).ready(function ()
{
	with (ACC.silentorderpost)
	{
		$('#silentOrderPostForm [tabindex="1"]').change(function ()
		{
			displayStartDateIssueNum();
		});
		bindUseDeliveryAddress()
		bindSubmitSilentOrderPostForm();
	}
});
