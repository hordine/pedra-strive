ACCMOB.carousel = {

	initialize: function ()
	{
		this.bindJCarousel();
	},
	bindJCarousel: function ()
	{
		$(".slider_component").flexslider({
			animation: "slide"
		});
	}
}

$(window).load(function ()
{
	$('.flexslider').flexslider({
		animation: "slide"
	});
});

