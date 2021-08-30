//the alert is collapsible yay
$(".alert").on("click", function() {
	$(this).hide("slow");
});

//apprearance
$("input.variation").on("click", function() {
	if ($(this).val() > 3) {
		$("body").css("background", "#2a313b");
		$("footer").attr('class','dark');
	} else {
		$("body").css("background", "#f9f9f9");
		$("footer").attr('class','');
	}
});