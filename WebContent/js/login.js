$(document).ready(function() {
	animEffect();
	loginStart();
});

/**
 *
 *
 *
 *
 */

function animEffect() {
	// logo
	$("#Groupe_45").fadeIn(750);
	$("#Groupe_50").fadeIn(1000);
	// 文字
	$("#Groupe_46").fadeIn(1500);
	$(".Rectangle_25").fadeIn(2000);
	$("#Groupe_47").animate({
		left : '55px',
		top : '464px'
	}, 750);
	$("#dg464d4").delay(1000).fadeIn(1000);
	$("#Groupe_48").animate({
		left : '241px',
		top : '464px'
	}, 750);
	$("#___A4_Text_2, #___A4_Text_3").delay(1000).fadeIn(2000);
	// ロゴの周りの円
	// wheel1
	$(".wheel1").fadeIn(100);
	$(".wheel1").animate({
		width : '130px',
		height : '130px',
		left : '123px',
		top : '73px'
	}, 1000);
	// wheel2
	$(".wheel2").fadeIn(100);
	$(".wheel2").animate({
		width : '165px',
		height : '165px',
		left : '105.5px',
		top : '56px'
	}, 1000);
	// wheel3
	$(".wheel3").fadeIn(100);
	$(".wheel3").animate({
		width : '185px',
		height : '185px',
		left : '96px',
		top : '46px'
	}, 1000);
	// wheel4
	$(".wheel4").fadeIn(100);
	$(".wheel4").animate({
		width : '215px',
		height : '215px',
		left : '81px',
		top : '31px'
	}, 1000);
	//
	$(".Groupe52").animate({
		left : '255px',
		top : '608px'
	}, 1500);
	//
	$(".Groupe51").animate({
		left : '-95px',
		top : '608px'
	}, 1500);
	$(".button").delay(2000).fadeIn(750);
}

/**
 * ***************************** *****************************
 */
function loginStart() {

	// 性別を選ぶこと
	var gender = ''
	$("#Groupe_48").click(function() {

		gender = '女性';
		$("#Groupe_48").find("path").css({
			"stroke" : "rgba(0,203,205,1)"
		});
		$("#Groupe_47").find("path").css({
			"stroke" : "rgba(32,143,229,1)"
		});
	});
	$("#Groupe_47").click(function() {
		gender = '男性';
		$("#Groupe_47").find("path").css({
			"stroke" : "rgba(0,203,205,1)"
		});
		$("#Groupe_48").find("path").css({
			"stroke" : "rgba(32,143,229,1)"
		});
	});
	// ログイン
	$(".button").click(function() {

		var audio = new Audio('../../song/login_page_kaishi_01.wav');
		audio.play();
		// 名前を取得
		var name = $("input[name=name]").val();
		if (!name || 0 === name.length) {
			$(".error_msg").text("名前を書いて、コラー!");
			$(".error_div").css({
				"display" : "block"
			});
		} else if (!gender || 0 === gender.length) {
			$(".error_msg").text("性別を選んで、コラー!");
			$(".error_div").css({
				"display" : "block"
			});
		} else {
			// send into DATABASE
			console.log(name + gender);
			$.ajax({
				url : "/MIRAI/RegistServlet",
				type : "GET",
				scriptCharset : 'UTF-8',
				data : {
					Name : name,
					Gender : gender
				}
			}).done(function(result) {
				// 通信成功時のコールバック
				console.log(result);
				localStorage.setItem('userId', result);
			}).fail(function(e) {
				// 通信失敗時のコールバック
				console.log(e);
				alert("読み込み失敗");
			}).always(function(result) {
				// 常に実行する処理
			});
			loginEnd();
		}
	});
}

/**
 * ***************************** *****************************
 */
function loginEnd() {
	$.ajax({
		url : "/MIRAI/HomeServlet",
		type : "GET",
		scriptCharset : 'UTF-8',
		data : {
			ajaxValue : '##'
		}
	}).done(function(result) {
		// 通信成功時のコールバック
		console.log(result);
	}).fail(function(e) {
		// 通信失敗時のコールバック
		console.log(e);
		alert("読み込み失敗");
	}).always(function(result) {
		// 常に実行する処理
	});
	// アンサー画面へ
	var url = "./answer.jsp";
	location.replace(url);
}
/**
 * ***************************** *****************************
 */
/*
 * function questionPage() { var data = ""; $.ajax({ url:
 * "http://localhost:8080/MIRAI/HomeServlet", type: "GET", data: {
 * questionPageUrl : '#' } }).done(function (result) { // 通信成功時のコールバック
 * console.log(result); data = result; }).fail(function (e) { // 通信失敗時のコールバック
 * console.log(e); alert("読み込み失敗"); }).always(function (result) { // 常に実行する処理
 * }); return data; }
 */
