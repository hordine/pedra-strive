ACCMOB.autocomplete = {
	suggestionClicked: false,

	createSuggestionsContainer: function ()
	{
		$('<div id="suggestions_container"><ul id="suggestions" data-ajax="false" data-role="listview" data-inset="true" data-theme="g" data-content-theme="g"></ul></div>').insertAfter('#header');
	},

	setUp: function (searchField)
	{
		ACCMOB.autocomplete.suggestionClicked = false;
		$('div#suggestions_container ul').empty();
		$('div#suggestions_container ul').listview();

		var url = searchField.data('autocomplete-url');
		var value = searchField.val();

		$('div#suggestions_container ul').listview('refresh');

		searchField.autocomplete({
			target: $('#suggestions'),
			source: url,
			link: ACCMOB.config.contextPath + '/search?text=',
			minLength: 2
		});
	},

	clearSuggestions: function ()
	{
		$("div#suggestions_container ul").empty();
	},

	initialize: function ()
	{
		var searchField = $("#search");

		ACCMOB.autocomplete.createSuggestionsContainer();

		searchField.live("focusout", function (e)
		{
			if (!ACCMOB.autocomplete.suggestionClicked)
			{
				ACCMOB.autocomplete.clearSuggestions();
			}
		});

		searchField.live("focusin", function ()
		{
			ACCMOB.autocomplete.setUp($("#search"));
		});

		$("div#suggestions_container ul > li a").live("mousedown", function ()
		{
			ACCMOB.autocomplete.suggestionClicked = true;
		});
	}
};

$(document).ready(function ()
{
	ACCMOB.autocomplete.initialize();
});
