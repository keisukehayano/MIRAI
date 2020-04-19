var userId;
var userName;
var userGender;
var userPicture;

var audio1 = new Audio('../../song/activity_page_first_countdown.wav');
var audio2 = new Audio('../../song/activity_page_act00.mp3');

$(document).ready(function(){

  var counter = 1;
  var intervalId = setInterval(() => {
  	counter = counter - 1;
  	if(counter < 0) {
  		clearInterval(intervalId);
        animEffect();
  	}
  }, 1000);

  getUserId();
  startActivity();

  $("#Groupe_47").click(function(){
    endActivity();
  });

});


/**
*
*
*
*
*/

function animEffect(){
  	//B1
    $(".activity_sample").fadeIn(100);
  	$(".activity_sample").animate({
		width: '1920px',
		height: '1080px',
		left: '0px',
		top: '0px'
	}, 1000);

	$(".Trac__50").fadeIn(100);
  	$(".Trac__50").animate({
		left: '1696.976px',
		top: '-10.771px'
	}, 1000);
  	//B2
	$(".Trac__51").fadeIn(100);
  	$(".Trac__51").animate({
		left: '1696.976px',
		top: '851.229px'
	}, 1000);
  	//B3
	$(".Trac__52").fadeIn(100);
  	$(".Trac__52").animate({
		left: '-8.024px',
		top: '851.229px'
	}, 1000);
  	//B4
	$(".Trac__49").fadeIn(100);
  	$(".Trac__49").animate({
		left: '-8.024px',
		top: '-9.771px'
	}, 1000);


  	$("#moim").delay( 1000 ).animate({
		top: '0px'
	}, 1000);

  	$("#ssklskclkc").delay( 1000 ).animate({
		left: '0px'
	}, 1000);
	$("#Groupe_7").delay( 1000 ).animate({
		left: '-0.024px'
	}, 1000);

    $(".Trac__55").delay( 2000 ).fadeIn(750);
  	$(".Trac__55").animate({
		top: '704px'
	}, 500);
    $(".Trac__59").delay( 2000 ).fadeIn(750);
	$(".Trac__59").animate({
		top: '0px'
	}, 500);

//    $("#Groupe_6").delay( 2000 ).fadeIn(750);

    $("#Groupe_4").delay( 3000 ).slideDown("slow");
//$('.descrip').delay( 7000 ).t();

  var counter = 4;
	var intervalId = setInterval(() => {
		counter = counter - 1;
		if(counter < 0) {
			clearInterval(intervalId);
   			$('.descrip').t();
		}
	}, 1000);
}

/**
******************************
******************************
**/
function progress() {
	$("#Groupe_6").fadeIn(750);

	var bar = new ProgressBar.Circle(container, {
	  color: '#FFFFFF',
	  // This has to be the same size as the maximum width to
	  // prevent clipping
	  strokeWidth: 6,
	  trailWidth: 6,
	  trailColor: '#0000',
	  duration: 75000,
	  text: {
	    autoStyleContainer: false
	  },
	  from: { color: '#008af5', width: 5 },
	  to: { color: '#008af5', width: 9 },
	  // Set default step function for all animate calls
	  step: function(state, circle) {
	    circle.path.setAttribute('stroke', state.color);
	    circle.path.setAttribute('stroke-width', state.width);

	    var value = Math.round(75 - (circle.value() * 75));
	    if (value === 0) {
	      circle.setText('終');
				endActivity();
	    } else {
	      circle.setText(value);
	    }

	  }
	});
	bar.text.style.fontFamily = '"Raleway", Helvetica, sans-serif';
	bar.text.style.fontSize = '2rem';

	bar.animate(1.0);  // Number from 0.0 to 1.0
}


/**
******************************
******************************
**/


function getUserId() {
		$.ajax({
			url: "/MIRAI/RegistServlet",
			type: "POST",
			data: {
				getId : ''
			}
		}).done(function (result) {
			// 通信成功時のコールバック
			console.log(result);
      getUserData(result);
      getActivity(result);
      userId = result;
		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});
}


function getActivity(Id) {
		$.ajax({
			url: "/MIRAI/ResponseActivityServlet",
			type: "POST",
			data: {
				userId : Id
			}
		}).done(function (result) {
			// 通信成功時のコールバック
			console.log("通信成功")
			activityName = result;


      $(".activity_sample").attr("src", "../../video/" + activityName + ".mp4");

		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});

}



/**
******************************
******************************
**/
	function getUserData(Id) {
			$.ajax({
				url: "/MIRAI/ActivityServlet",
				type: "GET",
				data: {
					userId : Id
				}
			}).done(function (result) {
				// 通信成功時のコールバック
				console.log("通信成功")
				var array = result.split("$$");
				userName = array[0];
				userGender = array[1];
				userPicture = array[2];

				$(".AVATAR_IMAGE").attr("src", "../../img/user_img/profil_" + Id + ".png");

			}).fail(function (e) {
				// 通信失敗時のコールバック
				console.log(e);
				alert("読み込み失敗");
			}).always(function (result) {
				// 常に実行する処理
			});

	}





/**
******************************
******************************
**/
function startActivity() {
  var counter = 24;

  var intervalId = setInterval(() => {
  	counter = counter - 1;
    if(counter < 5) {
      audio1.play();
      $("#Groupe_4").slideUp("slow");
      $(".spanTex").fadeIn(100);
      $(".spanTex1").fadeIn(100);
      $(".spanTex").text(counter);
      $(".spanTex1").html( userName + "<br>準備できた？");
    }
    if(counter == 2) {
      $(".spanTex1").text("じゃあ、いくよ！");
    }
    if(counter == 1) {
      $(".spanTex1").text("じゃあ、いくよ！");
    }
  	if(counter == 0) {
  		clearInterval(intervalId);
      progress();
      $(".spanTex").text("スタート！！");
      $(".spanTex1").text("頑張れ！！");
      $(".spanTex").fadeOut(5500);
      $(".spanTex1").fadeOut(5500);

  	}
  }, 1000);
}



/**
******************************
******************************
**/
function endActivity() {
$(".spanTex").text("終了");
  $(".spanTex").fadeIn(3000);
  $(".spanTex").text("終了");

	delayExec();
}


function nextPage() {
			var url = "../main_screen/activity_end.jsp";
		  location.replace(url);
}

function delayExec() {
    setTimeout('nextPage();', 3000);
  }
