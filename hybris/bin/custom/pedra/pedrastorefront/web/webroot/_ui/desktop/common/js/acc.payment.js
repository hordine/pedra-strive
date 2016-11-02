ACC.payment = {

	bindAddPaymentMethodButton: function ()
	{
		$('div.checkout_summary_flow_c .change_payment_method_button').on("click", function ()
		{
			var options = {
				url: getPaymentDetailsFormUrl,
				data: {createUpdateStatus: ''},
				target: '#popup_checkout_add_payment_method',
				type: 'GET',
				success: function (data)
				{
					//alert(data);
					ACC.payment.bindCreateUpdatePaymentDetailsForm();

					// Show the payment method popup
					$.colorbox({
						inline: true,
						href: "#popup_checkout_add_payment_method",
						height: false,
						overlayClose: false,
						onComplete: function ()
						{
							ACC.common.refreshScreenReaderBuffer();
						},
						onClosed: function ()
						{
							ACC.common.refreshScreenReaderBuffer();
						}
					});
				},
				error: function (xht, textStatus, ex)
				{
					alert("Failed to get payment details. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
				}
			};

			$(this).ajaxSubmit(options);

			return false;
		});
	},

	bindUseSavedCardButton: function ()
	{
		$('button.use_saved_card_button').on("click", function ()
		{
			$.ajax({
				url: getSavedCardsUrl,
				type: 'GET',
				dataType: 'json',
				success: function (data)
				{

					// Fill the available saved cards
					$('#saved_cards_tbody').html($('#savedCardsTemplate').tmpl({savedCards: data}));
					ACC.payment.bindUseThisSavedCardButton();
					ACC.payment.bindEnterNewPaymentButton();

					// Show the saved cards popup
					$.colorbox({
						inline: true,
						href: "#popup_checkout_saved_payment_method",
						height: false,
						innerHeight: "530px",
						onComplete: function ()
						{
							ACC.common.refreshScreenReaderBuffer();
						},
						onClosed: function ()
						{
							ACC.common.refreshScreenReaderBuffer();
						}
					});
				},
				error: function (xht, textStatus, ex)
				{
					alert("Failed to get saved cards. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
				}
			});

			return false;
		});
	},

	bindEnterNewPaymentButton: function ()
	{
		$('button.enter_new_payment_button').on("click", function ()
		{
			// Show the payment method popup
			$.colorbox({
				inline: true,
				href: "#popup_checkout_add_payment_method",
				height: false,
				innerHeight: "930px",
				onComplete: function ()
				{
					ACC.common.refreshScreenReaderBuffer();
				},
				onClosed: function ()
				{
					ACC.common.refreshScreenReaderBuffer();
				}
			});
			return false;
		});
	},

	bindCreateUpdatePaymentDetailsForm: function ()
	{
		ACC.payment.bindUseSavedCardButton();

		$('.create_update_payment_form').each(function ()
		{
			var options = {
				type: 'POST',
				beforeSubmit: function ()
				{
					$('#popup_checkout_add_payment_method').block({ message: "<img src='" + ACC.config.commonResourcePath + "/images/spinner.gif' />" });
				},
				success: function (data)
				{
					//alert(data);
					$('#popup_checkout_add_payment_method').html(data);
					var status = $('.create_update_payment_id').attr('status');
					if (status != null && "success" == status.toLowerCase())
					{
						ACC.refresh.getCheckoutCartDataAndRefreshPage();
						parent.$.colorbox.close();
					}
					else
					{
						ACC.payment.bindCreateUpdatePaymentDetailsForm();
					}
				},
				error: function (xht, textStatus, ex)
				{
					alert("Failed to create/update payment details. Error details [" + xht + ", " + textStatus + ", " + ex + "]");
				},
				complete: function ()
				{
					$('#popup_checkout_add_payment_method').unblock();
				}
			};

			$(this).ajaxForm(options);
		});

	},

	bindUseThisSavedCardButton: function ()
	{
		$('.use_this_saved_card_button').click(function ()
		{
			var paymentId = $(this).attr('data-payment');
			$.postJSON(setPaymentDetailsUrl, {paymentId: paymentId}, ACC.payment.handleSelectSavedCardSuccess);
			return false;
		});
	},

	handleSelectSavedCardSuccess: function (data)
	{
		if (data != null)
		{
			ACC.refresh.refreshPage(data);

			parent.$.colorbox.close();
		}
		else
		{
			alert("Failed to set payment details");
		}
	},

	refreshPaymentDetailsSection: function (data)
	{
		$('#checkout_summary_payment_div').replaceWith($('#paymentSummaryTemplate').tmpl(data));

		//bind edit payment details button
		ACC.payment.bindAddPaymentMethodButton();
		if (!typeof bindSecurityCodeWhatIs == 'undefined')
			bindSecurityCodeWhatIs();
	},

	bindPaymentCardTypeSelect: function ()
	{
		$("#cardType").change(function ()
		{
			var cardType = $(this).val();

			if (cardType == 'maestro' || cardType == 'switch')
			{
				$('#startDate, #issueNum').show();
			}
			else
			{
				$('#startDate, #issueNum').hide();
			}
		});
	}
}


$(document).ready(function ()
{
	ACC.payment.bindAddPaymentMethodButton();
	ACC.payment.bindPaymentCardTypeSelect();
	if (!typeof bindSecurityCodeWhatIs == 'undefined')
	{
		bindSecurityCodeWhatIs();
	}
});
	
	
	
	
