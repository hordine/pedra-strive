ACC.autocomplete = {

	bindAll: function()
	{
		this.bindSearchAutocomplete();
	},

	bindSearchAutocomplete: function()
	{
		$( "#search" ).autocomplete({
			source: function( request, response ) {
				$.getJSON(
						ACC.autocompleteUrl, 
						{
							term : $('#search').val()
						},
						function(data) {
							response(data);
						}
					);
			},
			minLength: 2,
			open: function(event, ui) { $(".ui-menu").css("z-index", 10000); },
			close: function(event, ui) { $(".ui-menu").css("z-index", -1); },
			focus: function( event, ui ) {return false;},
			select: function(event, ui) {
				if(ui.item) {
					$('#search').val(ui.item.value.trim());
				}
				document.forms['search_form'].submit();
			},
			autoFocus: false

		});
	}
	
};

$(document).ready(function() {
	ACC.autocomplete.bindAll();
});
