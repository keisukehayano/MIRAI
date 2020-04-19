
$(document).ready(function(){
	firstStepPicture();
	var audio = new Audio('../../song/welcome_page_welcome_02.wav');
	audio.play();
});

/**
*
*
*/
function welcomeAnimEffect(){

	$(".logo").fadeOut(500);
	$("#my_camera").fadeOut(100);
    $(".snap").fadeIn(500);
    $(".snap").animate({
		width: '380px',
		height: '380px',
		left: '770px',
		top: '95px'
	}, 500);
	//wheel1
	$(".wheel1").fadeIn(500);
  	$(".wheel1").delay(500).animate({
		width: '400px',
		height: '400px',
		left: '759px',
		top: '85px'
	}, 500);
  	//wheel4
	$(".wheel4").fadeIn(500);
  	$(".wheel4").delay(500).animate({
		width: '480px',
		height: '480px',
		left: '720px',
		top: '46px',
	}, 500);
	//
    $("#Groupe_50").fadeIn(1000);
    $("#dasv").delay(3500).slideDown(500);
    $(".e0077e38ae057ac52f26fcd721bf65").delay(2000).slideDown(1000);
    $("#sdsdsds").delay(2000).slideDown(1000);
    $(".Ligne_3").delay(2500).animate({
	    width: '550px'
	}, 500);

}



function firstStepPicture(){
	//wheel1
	$(".wheel1").fadeIn(500);
  	$(".wheel1").delay(500).animate({
		width: '580px',
		height: '580px',
		left: '658px',
		top: '162px'
	}, 500);
  	//wheel4
	$(".wheel4").fadeIn(500);
  	$(".wheel4").delay(500).animate({
		width: '680px',
		height: '680px',
		left: '606px',
		top: '113px',
	}, 500);
	$(".logo").fadeIn(1000);
	$("#my_camera").delay(1000).fadeIn(500);
	/////////////////////////////
	Webcam.set({
		width: 250,
		height: 250,
		image_format: 'jpeg',
		jpeg_quality: 100
	});
	Webcam.attach( '#my_camera' );

	$("#my_camera").css({
		"position": "absolute",
		"width": "435px",
		"height": "435px",
		"overflow": "hidden",
		"top": "234px",
		"left": "732px",
		"box-shadow": "0 0 7px 0px black"
	});

	$("#my_camera").find("video").css({
		"position": "absolute",
		"width": "580px",
		"height": "700px",
		"top": "-133px"
	});

	countDown();
}


function countDown() {
	var counter = 10;
	var intervalId = setInterval(() => {
		counter = counter - 1;
		$(".countDown").text(counter);
		if(counter < 0) {
			clearInterval(intervalId);
			$(".countDown").css({ "display": "none" });
			console.log("CHEEZE");
			take_snapshot();
		}
	}, 1000);
}

/**
******************************
******************************
**/

function take_snapshot() {
	var audio = new Audio('../../song/welcome_page_snapshot_01.wav');
	audio.play();
	// take snapshot and get image data
	Webcam.snap( function(data_uri) {
		// display results in page
		$(".snap").attr("src", data_uri);
		console.log(data_uri.split(",")[1]);

			var userId;
			$.ajax({
				url: "/MIRAI/RegistServlet",
				type: "POST",
				data: {
					getId : '&&'
				}
			}).done(function (result) {
				// 通信成功時のコールバック
				console.log(result);
				userId = result;
				savePicture(userId, data_uri.split(",")[1]);
			}).fail(function (e) {
				// 通信失敗時のコールバック
				console.log(e);
				alert("読み込み失敗");
			}).always(function (result) {
				// 常に実行する処理
			});
	});
}


/**
******************************
******************************
**/
function savePicture(userId, picture) {

		$.ajax({
			url: "/MIRAI/WelcomeServlet",
			type: "POST",
			data: {
				userId : userId,
				picture : picture
			}
		}).done(function (result) {
			// 通信成功時のコールバック
			console.log(result);
			$("#userName").text(result);
		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});

	welcomeAnimEffect();
	activityPage();
}



/**
******************************
******************************
**/
function activityPage() {
	var counter = 13;
	var intervalId = setInterval(() => {
		counter = counter - 1;
		if(counter < 0) {
			clearInterval(intervalId);
			var url = "./activity.jsp";
			console.log(url);
			location.replace(url);
		}
	}, 1000);
}
