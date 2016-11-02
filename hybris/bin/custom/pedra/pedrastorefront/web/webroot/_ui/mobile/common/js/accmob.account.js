ACCMOB.account = {
    defaultDialogConfig: {
        mode:				'button',
        forceInput:			true,
        headerText:			'',
        buttonPrompt:		'',
        headerClose:		true,
        inputPassword:		false,
        animate:			false,
        safeNuke:			true,
        opacity:			0.0,
        buttons: {
            'OK':			{ click: function() { }, icon: "ok", theme: "d"},
            'Cancel':		{ click: function() { }, icon: "delete", theme: "b"}
        },
        callbackOpen:       ACCMOB.common.lockScreen,
        callbackClose:      ACCMOB.common.unlockScreen,
        themeDialog:		'b',
        themeInput:			'e',
        themeButtonDefault:	false,
        themeHeader:		'i'
    },

    createDialog: function(buttonPrompt, headerText, buttons, config) {
        $.extend(config, {buttonPrompt: buttonPrompt, buttons: buttons, headerText: headerText });
        var dialog = $(document.createElement('div'));
        dialog.simpledialog2(config);
        ACCMOB.common.lockScreen();
    },

    bindToRemoveAddressButton: function() {
        $('.removeAddressForm').live("submit", function(event) {
            if ($(this).data("removeConfirmed") == "true") {
                return true;
            } else {
                ACCMOB.common.preventDefault(event);
            }
        });

        $('.removeAddressButton').live("click", function(event) {
            var pid = $(this).attr("pid");
            var removePaymentCardForm = $("#removeAddressForm" + pid);
            ACCMOB.common.preventDefault(event);
            var config = ACCMOB.account.defaultDialogConfig;
            ACCMOB.account.createDialog( $(this).data('message'), $(this).data('headertext'), {
                'OK': { click: function() { removePaymentCardForm.data("removeConfirmed", "true"); removePaymentCardForm.submit(); return true; } },
                'Cancel': { click: function() { }, icon:  "delete", theme: "c" }
            } , config);
        });
    },

    bindToRemovePaymentCardButton:function ()
    {
        /* prevent payment remove form from submitting normaly */
        $('.removePaymentCardForm').live("submit", function(event) {
            if ($(this).data("removeConfirmed") == "true") {
                return true;
            } else {
                ACCMOB.common.preventDefault(event);
            }
        });

        $('.removePaymentCardButton').live("click", function(event) {
            var pid = $(this).attr("pid");
            var removePaymentCardForm = $("#removePaymentCardForm" + pid);
            ACCMOB.common.preventDefault(event);
            var config = ACCMOB.account.defaultDialogConfig;
            ACCMOB.account.createDialog( $(this).data('message'), $(this).data('headertext'), {
                'OK': { click: function() { removePaymentCardForm.data("removeConfirmed", "true"); removePaymentCardForm.submit(); return true; } },
                'Cancel': { click: function() { }, icon:  "delete", theme: "c" }
            } , config);
        });
    },

    bindToSetDefaultPaymentButton: function() {
        $('.setDefaultPayment').live("click", function() {
            $('#setDefaultPaymentDetails' + $(this).attr("pid")).submit();
            return false;
        });
    },

    bindToRemovePaymentDetailsButton: function() {
        $('.removePaymentDetail').live("click", function() {
            $('#removePaymentDetails' + $(this).attr("pid")).submit();
            return false;
        });
    },

    initialize: function() {
        with (ACCMOB.account) {
            bindToRemoveAddressButton();
            bindToSetDefaultPaymentButton();
            bindToRemovePaymentCardButton();
	        bindToRemovePaymentDetailsButton();
        }
    }

};

$(document).ready(function() {
    ACCMOB.account.initialize();
});
