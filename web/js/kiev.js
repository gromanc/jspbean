$(document).ready(function(){
  
//  $(".object").each(function(index){
//	var gr = "groupn" + index;
//	$(this).find("A > IMG").parent().attr("rel",gr);
//  });
//  $("P > A.mphoto").attr("rel","grupn")
//
//  $("A[href$=.jpg], A[href$=.jpeg], A[href$=.gif], A[href$=.bmp], A[href$=.png], A[href$=.JPG], A[href$=.IPEG], A[href$=.GIF], A[href$=.BMP], A[href$=.PNG]").fancybox();
  
  // AUTHFORM
	$(".showauth").click(function () {
		doShowAuth();
	});

	$(".close IMG").click(function () {
			doCloseAuth();
	});
	
  $(".town").click(function () {
    $(".city").slideToggle("slow");
  });

//  $(".city").corner("bl,br");
	
  $("input[type=submit]").css("padding","0 6px");
  
  $(".menu SPAN:last").css("border","none");

});

function doShowAuth(){
  if ($(".authpanel").is(":hidden")) {
    $(".authpanel").fadeIn(500);
  } else {
    $(".authpanel").fadeOut(500);
  }
}

function doCloseAuth(){
  $(".authpanel").fadeOut(500);
}
