ACCMOB.cartremoveitem = {

	bindAll: function ()
	{
		this.bindCartRemoveProduct();
	},
	bindCartRemoveProduct: function ()
	{
		$('.submitRemoveProduct').click(function ()
		{
			var productCode = $('#updateCartForm' + $(this).attr('id')).get(0).productCode.value;
			var initialCartQuantity = $('#quantity' + $(this).attr('id')).val();

			if (window.trackRemoveFromCart)
			{
				trackRemoveFromCart(productCode, initialCartQuantity);
			}

			$('#quantity' + $(this).attr('id')).attr('value', '0');
			$('#updateCartForm' + $(this).attr('id')).submit();
		});
	}
};

$(document).ready(function ()
{
	ACCMOB.cartremoveitem.bindAll();
});
