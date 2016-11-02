ACC.producttabs = {

	bindAll: function ()
	{
		ACC.producttabs.bindProdTabs();
	},

	bindProdTabs: function ()
	{
		if (!(typeof PRODUCTTABSARRAY == 'undefined' || PRODUCTTABSARRAY.length == 0))
		{
			for (var index = PRODUCTTABSARRAY.length - 1; index >= 0; index--)
			{
				$('#tab_01').after('<li><h2><a href="#tab-' + PRODUCTTABSARRAY[index].componentUid + '" class="' + PRODUCTTABSARRAY[index].titleClass + '">' + PRODUCTTABSARRAY[index].componentTitle + '</a></h2></li>');
				$('#prod_tabs').append('<div class="' + PRODUCTTABSARRAY[index].contentClass + '" id="tab-' + PRODUCTTABSARRAY[index].componentUid + '">' + PRODUCTTABSARRAY[index].componentContent + '</div>');
			}
		}

		if (!(typeof showReviewForm == 'undefined'))
		{
			$('#read_reviews_action').click(function (e)
			{
				e.preventDefault();
				ACC.producttabs.showReviewsAction();
				$('#reviews').show();
				$('#write_reviews').hide();
			});

			$(".tab_03").on("click", function ()
			{
				ACC.producttabs.showReviewsAction();
			});

			$('#write_review_action').click(function (e)
			{
				e.preventDefault();
				$('#reviews').hide();
				$('#write_reviews').show();
				$('#reviewForm input[name=headline]').focus();
			});
			if (showReviewForm)
			{
				$('#reviews').hide();
				$('#write_reviews').show();
			}
			$("#stars-wrapper").stars({
				inputType: "select"
			});
		}


	},
	selectTabs: function ()
	{
		$("#prod_tabs").tabs({ selected: 0 });
	},

	showReviewsAction: function ()
	{
		$.get(getPageOfReviewsUrl, function (result)
		{
			$('#reviews').html(result);
			$('#show_all_reviews_top_action, #show_all_reviews_bottom_action').on("click", function (e)
			{
				e.preventDefault();
				$.get(getAllReviewsUrl, function (result)
				{
					$('#reviews').html(result);
					$('#show_all_reviews_top_action, #show_all_reviews_bottom_action').hide();
					$('#write_review_action').click(function (e)
					{
						e.preventDefault();
						$('#reviews').hide();
						$('#write_reviews').show();
					});
				});
			});
			$('#write_review_action').on("click", function (e)
			{
				e.preventDefault();
				$('#reviews').hide();
				$('#write_reviews').show();
				$('#reviewForm input[name=headline]').focus();
			});
		});
	}
};

$(document).ready(function ()
{
	ACC.producttabs.bindAll();
});

