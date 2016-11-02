ACCMOB.messages = {

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

	createDialog: function (config)
	{
		var dialog = $(document.createElement('div'));
		dialog.simpledialog2(config);
	},

	bindGlobalMessages: function ()
	{
		var accErrorMsgs = $("#accErrorMsgs");
		if (accErrorMsgs.length > 0)
		{
			var config = ACCMOB.messages.defaultDialogConfig;
			$.extend(config, {
				blankContent: accErrorMsgs.html(),
				headerText: accErrorMsgs.data('headertext'),
				themeDialog: 'g',
				themeInput: 'e',
				themeButtonDefault: false,
				themeHeader: 'g'
			});
			ACCMOB.messages.createDialog(config);
		}

		var accInfoMsgs = $("#accInfoMsgs");
		if (accInfoMsgs.length > 0)
		{
			var config = ACCMOB.messages.defaultDialogConfig;
			$.extend(config, {
				blankContent: accInfoMsgs.html(),
				headerText: accInfoMsgs.data('headertext'),
				themeDialog: 'h',
				themeInput: 'e',
				themeButtonDefault: false,
				themeHeader: 'h'
			});
			ACCMOB.messages.createDialog(config);
		}

		var accConfMsgs = $("#accConfMsgs");
		if (accConfMsgs.length > 0)
		{
			var config = ACCMOB.messages.defaultDialogConfig;
			$.extend(config, {
				blankContent: accConfMsgs.html(),
				headerText: accConfMsgs.data('headertext'),
				themeDialog: 'i',
				themeInput: 'e',
				themeButtonDefault: false,
				themeHeader: 'i'
			});
			ACCMOB.messages.createDialog(config);
		}
	},

	bindFormErrors: function ()
	{
		var errors = $("#form-errors");

		if (errors.length > 0)
		{
			var config = ACCMOB.messages.defaultDialogConfig;
			$.extend(config, {
				blankContent: errors.data("message"),
				headerText: errors.data('headertext'),
				themeDialog: 'g',
				themeInput: 'e',
				themeButtonDefault: false,
				themeHeader: 'g'
			});
			ACCMOB.messages.createDialog(config);
		}
	},

	initialize: function ()
	{
		with (ACCMOB.messages)
		{
			bindGlobalMessages();
			bindFormErrors();
		}
	}
};

$(document).ready(function ()
{
	ACCMOB.messages.initialize();
});
