ACC.checkout = {

	bindAll: function ()
	{
		this.bindCheckO();
	},

	bindCheckO: function ()
	{
		var cartEntriesError = false;
		$('.doCheckoutBut').click(function ()
		{
			cartEntriesError = ACC.pickupinstore.validatePickupinStoreCartEntires();
			if (!cartEntriesError)
			{
				window.location = checkoutUrl;
			}
			return false;
		});


		// Alternative checkout flows options
		$('.doFlowSelectedChange').change(function ()
		{
			if ('multistep-pci' == $('#selectAltCheckoutFlow').attr('value'))
			{
				$('#selectPciOption').css('display', '');
			}
			else
			{
				$('#selectPciOption').css('display', 'none');
			}
		});


		// Alternative checkout flows version of the doCheckout method to handle the checkout buttons on the cart page.
		$('.doCheckoutBut').click(function ()
		{
			if (!cartEntriesError)
			{
				var flow = $('#selectAltCheckoutFlow').attr('value');
				if ('' == flow)
				{
					// No alternate flow specified, fallback to default behaviour
					window.location = checkoutUrl;
				}
				else
				{
					// Fix multistep-pci flow
					if ('multistep-pci' == flow)
					{
						flow = 'multistep';
					}
					var pci = $('#selectPciOption').attr('value');

					// Build up the redirect URL
					var redirectUrl = checkoutUrl + '/select-flow?flow=' + flow + '&pci=' + pci;
					window.location = redirectUrl;
				}
			}
			return false;
		});
	}
};

$(document).ready(function ()
{
	ACC.checkout.bindAll();
});
