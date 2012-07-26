$(function(){
/* Fixed Nav ------------- */
var offset_top = 110;
var headline_pos = $('#main_nav').offset();
$(window).scroll(function()
{
var scroll_top = $(window).scrollTop();
if (scroll_top > (headline_pos.top-offset_top))
{
$('#MainMenu').addClass('fixed');
}
else
{
$('#MainMenu').removeClass('fixed');
}
}); 
});