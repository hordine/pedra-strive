ACC.common = {

	refreshScreenReaderBuffer: function ()
	{
		// changes a value in a hidden form field in order
		// to trigger a buffer update in a screen reader
		$('#accesibility_refreshScreenReaderBufferField').attr('value', new Date().getTime());
	},

	bindAll: function ()
	{
		ACC.common.bindToUiCarouselLink();
	},

	bindToUiCarouselLink: function ()
	{
		$("ul.carousel > li a.popup").colorbox({
			onComplete: function ()
			{
				ACC.common.refreshScreenReaderBuffer();
				$.colorbox.resize()
			},

			onClosed: function ()
			{
				ACC.common.refreshScreenReaderBuffer();
			}
		});
	}
};

$(document).ready(function ()
{
	ACC.common.bindAll();
});

