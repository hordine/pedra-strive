ACC.cartremoveitem = {

	bindAll: function ()
	{
		this.bindCartRemoveProduct();
	},
	bindCartRemoveProduct: function ()
	{
		// TODO: please DRY me! 

		$('.submitRemoveProduct').on("click", function ()
		{

			// fix for unique ids
			var prodid = $(this).attr('id').split("_")[1];
			var productCode = $('#updateCartForm' + prodid).get(0).productCode.value;
			var initialCartQuantity = $('#updateCartForm' + prodid).get(0).initialQuantity.value;

			if (window.trackRemoveFromCart)
			{
				trackRemoveFromCart(productCode, initialCartQuantity);
			}

			$('#updateCartForm' + prodid).get(0).quantity.value = 0;
			$('#updateCartForm' + prodid).get(0).initialQuantity.value = 0;
			$('#updateCartForm' + prodid).get(0).submit();
		});

		$('.updateQuantityProduct').on("click", function ()
		{
			// fix for unique ids
			var prodid = $(this).attr('id').split("_")
			prodid = prodid[1];


			var productCode = $('#updateCartForm' + prodid).get(0).productCode.value;
			var initialCartQuantity = $('#updateCartForm' + prodid).get(0).initialQuantity.value;
			var newCartQuantity = $('#updateCartForm' + prodid).get(0).quantity.value;

			if (window.trackUpdateCart)
			{
				trackUpdateCart(productCode, initialCartQuantity, newCartQuantity);
			}

			$('#updateCartForm' + prodid).get(0).submit();

		});
	}
}

$(document).ready(function ()
{
	ACC.cartremoveitem.bindAll();
});
