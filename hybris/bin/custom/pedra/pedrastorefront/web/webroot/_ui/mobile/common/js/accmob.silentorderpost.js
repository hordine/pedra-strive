ACCMOB.silentorderpost = {

	bindUseDeliveryAddress: function ()
	{
		$('#useDeliveryAddress').change(function ()
		{
			ACCMOB.silentorderpost.useDeliveryAddressSelected();
		});
		if ($('#useDeliveryAddress').is(":checked"))
		{
			ACCMOB.silentorderpost.disableAddressForm();
		}
	},

	bindSubmitSilentOrderPostForm: function ()
	{
		$('.submit_silentOrderPostForm').click(function ()
		{
			$('.i18nAddressForm').filter(":hidden").remove();
			ACCMOB.silentorderpost.enableAddressForm();
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
	},

	isEmpty: function (obj)
	{
		if (typeof obj == 'undefined' || obj === null || obj === '') return true;
		return false;
	},

	fillAddressLines: function ()
	{
		$('#address\\.line1').val($('#useDeliveryAddressFields').data('line1'));
		$('#address\\.line2').val($('#useDeliveryAddressFields').data('line2'));
	},

	disableAddressForm: function ()
	{
		$('input[id^="address\\."]').prop('disabled',true);
	},

	enableAddressForm: function ()
	{
		$('input[id^="address\\."]').prop('disabled',false);
	},

	useDeliveryAddressSelected: function ()
	{
		if ($('#useDeliveryAddress').is(":checked"))
		{
			var data = $('#useDeliveryAddressFields').data();
			$('#address\\.country').val(data.countryisocode).change();
			$('#address\\.firstName').val(data.firstname);
			$('#address\\.surname').val(data.lastname);
			$('#address\\.townCity').val(data.town);
			$('#address\\.postcode').val(data.postalcode);
			$('#address\\.region').val(data.regionisocode).change();
			ACCMOB.silentorderpost.fillAddressLines();
			ACCMOB.silentorderpost.disableAddressForm();
		}
		else
		{
			$("[id^='address\\.']").each(function ()
			{
				$(this).val("");
				if ($(this).is('select'))
				{
					$(this).change();
				}
			});

			ACCMOB.silentorderpost.enableAddressForm();
		}
	}
}

$(document).ready(function ()
{

	with (ACCMOB.silentorderpost)
	{
		displayStartDateIssueNum();
		$('#silentOrderPostForm [tabindex="1"]').on("change", function ()
		{
			displayStartDateIssueNum();
		});
		bindUseDeliveryAddress()
		bindSubmitSilentOrderPostForm()
	}
});

