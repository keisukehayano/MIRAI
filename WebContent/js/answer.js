var question1 = false;
var question2 = false;
var question3 = false;

var questionText1 = "";
var questionText2 = "";
var questionText3 = "";

var answer0 = "READY";
var answer1 = "";
var answer2 = "";
var answer3 = "";

$(document).ready(function() {

	animEffect();
	getResponse();
	// sendResponse();
	// sendQuestion();
	questionAdmin(answer0);
});

/**
 * ***************************** *****************************
 */
function animEffect() {
	$("#Logo").fadeIn(1000);
	$("#Groupe_68").fadeIn(100);
	$("#Groupe_68").animate({
		left : '0px'
	}, 750);
	$("#Groupe_70").delay(750).animate({
		top : '0px'
	}, 750);
	$("#Groupe_69").delay(750).animate({
		top : '0px'
	}, 750);
}

/**
 * ***************************** *****************************
 */
function getResponse() {
	var response = '';
	$("#Groupe_70").click(function() {
		var audio = new Audio('../../song/answer_page_yes.wav');
		audio.play();
		response = 'NO';
		questionAdmin(response);
	});
	$("#Groupe_69").click(function() {
		var audio = new Audio('../../song/answer_page_no.wav');
		audio.play();
		response = 'YES';
		questionAdmin(response);
	});
}

/**
 * ***************************** *****************************
 */
function questionAdmin(response) {
	if (!question1 && !question2 && !question3) {
		questionOne(response);
		console.log("get first QUESTION ");
		console.log(question1, question2, question3);
		return true;
	}
	if (question1 && !question2 && !question3) {
		questionTwo(response);
		console.log("get second QUESTION ");
		console.log(question1, question2, question3);
		return true;
	}
	if (question1 && question2 && !question3) {
		questionThree(response);
		console.log("get third QUESTION ");
		console.log(question1, question2, question3);
		return true;
	}
	if (question1 && question2 && question3) {
		sendLastResponse(response);
		console.log("FINISH OKAY ");
		console.log(question1, question2, question3);
		return true;
	}
}

/**
 * ***************************** *****************************
 */
function questionOne(response) {
	question1 = true;
	$.ajax({
		url : "/MIRAI/QuestionBranchServlet",
		type : "GET",
		scriptCharset : 'UTF-8',
		data : {
			Answer0 : response
		}
	}).done(function(result) {
		// 通信成功時のコールバック
		console.log(result);
		questionText1 = result;
	}).fail(function(e) {
		// 通信失敗時のコールバック
		console.log(e);
		alert("読み込み失敗");
	}).always(function(result) {
		// 常に実行する処理
	});
}

/**
 * ***************************** *****************************
 */
function questionTwo(response) {
	question2 = true;
	answer1 = response;
	$.ajax({
		url : "/MIRAI/QuestionBranchServlet",
		type : "GET",
		scriptCharset : 'UTF-8',
		data : {
			Answer0 : answer0,
			Answer1 : answer1
		}
	}).done(function(result) {
		// 通信成功時のコールバック
		console.log(result);
		questionText2 = result;
	}).fail(function(e) {
		// 通信失敗時のコールバック
		console.log(e);
		alert("読み込み失敗");
	}).always(function(result) {
		// 常に実行する処理
	});
}

/**
 * ***************************** *****************************
 */
function questionThree(response) {
	question3 = true;
	answer2 = response;
	$.ajax({
		url : "/MIRAI/QuestionBranchServlet",
		type : "GET",
		scriptCharset : 'UTF-8',
		data : {
			Answer0 : answer0,
			Answer1 : answer1,
			Answer2 : answer2
		}
	}).done(function(result) {
		// 通信成功時のコールバック
		console.log(result);
		questionText3 = result;
	}).fail(function(e) {
		// 通信失敗時のコールバック
		console.log(e);
		alert("読み込み失敗");
	}).always(function(result) {
		// 常に実行する処理
	});
}

/**
 * ***************************** *****************************
 */
function sendLastResponse(response) {
	// question3 = true;
	answer3 = response;
	$.ajax({
		url : "/MIRAI/QuestionBranchServlet",
		type : "GET",
		scriptCharset : 'UTF-8',
		data : {
			Answer0 : answer0,
			Answer1 : answer1,
			Answer2 : answer2,
			Answer3 : answer3
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

	var url = "./afterAnswer.jsp";
	location.replace(url);
}
