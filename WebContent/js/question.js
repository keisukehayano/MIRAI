var allQuestion;
/*
var showQuestion1 = "";
var showQuestion2 = "";
var showQuestion3 = "";
*/
$(document).ready(function(){
	animEffect();
	resetLogin();
});



/**
*
*
*
*Ligne_2
*/

function animEffect(){
	//logo
    $(".Logo").fadeIn(550);

    //ロゴの周りの円
  	//wheel1
	$(".wheel1").fadeIn(100);
  	$(".wheel1").animate({
		width: '250px',
		height: '250px',
		left: '173px',
		top: '417px'
	}, 1000);
  	//wheel2
	$(".wheel2").fadeIn(100);
  	$(".wheel2").animate({
		width: '340px',
		height: '340px',
		left: '130px',
		top: '379px'
	}, 1000);
  	//wheel3
	$(".wheel3").fadeIn(100);
  	$(".wheel3").animate({
		width: '400px',
		height: '400px',
		left: '100px',
		top: '345px'
	}, 1000);
  	//wheel4
	$(".wheel4").fadeIn(100);
  	$(".wheel4").animate({
		width: '484px',
		height: '484px',
		left: '61px',
		top: '303px'
	}, 1000);

	$(".border_line").delay(1000).fadeIn(250);

	$("#Groupe_48").delay(1500).fadeIn(250);
	$("#Groupe_46").delay(1800).fadeIn(250);
	$("#Groupe_47").delay(2100).fadeIn(250);


	$("#Groupe_47").delay(2100).fadeIn(250);

	$(".Trac__1").delay(2100).animate({
		width: '250.627px',
	}, 1000);
	$(".Ligne_2").delay(2100).animate({
		width: '172px',
	}, 1000);
	$(".Trac__2").delay(2100).animate({
		width: '250.627px',
	}, 1000);

	$(".Ellipse_11_A1_Ellipse_7, .Ellipse_13").delay(2700).fadeIn(250);
	$(".Ellipse_15, .Ellipse_12_A1_Ellipse_10").delay(2700).fadeIn(250);
	$(".Ellipse_16, .Ellipse_14").delay(2700).fadeIn(250);

	$(".Trac__4_A1_Path_5, .Trac__6_A1_Path_6").delay(2900).fadeIn(250);
	$(".Trac__4, .Trac__6").delay(2900).fadeIn(250);
	$(".Trac__4_A1_Path_7, .Trac__6_A1_Path_8").delay(2900).fadeIn(250);

}

/**
******************************
******************************
**/
function resetLogin() {
	$.ajax({
		url: "/MIRAI/HomeServlet",
		type: "GET",
		data: {
			ajaxValue : '###'
		}
	}).done(function (result) {
		// 通信成功時のコールバック
		console.log(result);
		getQuestion();
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
function getQuestion(){
	setInterval(function(){
		$.ajax({
			url: "/MIRAI/QuestionBranchServlet",
			type: "POST",
			data: {
				FromQuestionJSP : "$$$"
		 	}
		}).done(function (result) {
			// 通信成功時のコールバック
			if (result == "START") {
					nextPage();
			}else {
				var array = result.split("$$");
				if ( array[0] != "" ) {
					showQuestion1(array[0]);
						if(array[3] == "YES"){
								$("#Groupe_60").fadeIn(100);
								$("#Groupe_60").find("image").attr("href", "../../img/system_img/question_img1.png");
								$("#Groupe_60").find("image").attr("xlink:href", "../../img/system_img/question_img1.png");
						}else if (array[3] == "NO") {
								$("#Groupe_60").fadeIn(100);
								$("#Groupe_60").find("image").attr("href", "../../img/system_img/question_img2.png");
								$("#Groupe_60").find("image").attr("xlink:href", "../../img/system_img/question_img2.png");
						}
				}
				if ( array[1] != "" ) {
					showQuestion2(array[1]);
						if(array[4] == "YES"){
								$("#Groupe_59").fadeIn(100);
								$("#Groupe_59").find("image").attr("href", "../../img/system_img/question_img1.png");
								$("#Groupe_59").find("image").attr("xlink:href", "../../img/system_img/question_img1.png");
						}else if (array[4] == "NO") {
								$("#Groupe_59").fadeIn(100);
								$("#Groupe_59").find("image").attr("href", "../../img/system_img/question_img2.png");
								$("#Groupe_59").find("image").attr("xlink:href", "../../img/system_img/question_img2.png");
						}
				}
				if ( array[2] != "" ) {
					showQuestion3(array[2]);
						if(array[5] == "YES"){
								$("#Groupe_66").fadeIn(100);
								$("#Groupe_66").find("image").attr("href", "../../img/system_img/question_img1.png");
								$("#Groupe_66").find("image").attr("xlink:href", "../../img/system_img/question_img1.png");
						}else if (array[5] == "NO") {
								$("#Groupe_66").fadeIn(100);
								$("#Groupe_66").find("image").attr("href", "../../img/system_img/question_img2.png");
								$("#Groupe_66").find("image").attr("xlink:href", "../../img/system_img/question_img2.png");
						}
				}
			}
			console.log(array);
		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});
	},1000);
}

/**
******************************
******************************
**/
function showQuestion1(question) {
			console.log(question);
			$("#Groupe_52").delay(1000).slideDown(500);
			$(".spanQ1").text(question);
}

/**
******************************
******************************
**/
function showQuestion2(question) {
			console.log(question);
			$("#Groupe_53").delay(1000).slideDown(500);
			$(".spanQ2").text(question);
}

/**
******************************
******************************
**/
function showQuestion3(question) {
			console.log(question);
			$("#Groupe_54").delay(1000).slideDown(500);
			$(".spanQ3").text(question);
/*			var audio = new Audio('../../song/question_page_showquestion_01.wav');
			audio.play(); */
}

/**
******************************
******************************
**/
function nextPage() {
			var url = "../main_screen/welcome.jsp";
		  location.replace(url);
}
