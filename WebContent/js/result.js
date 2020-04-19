var userId;
var beforScore;
var afterScore;
var beforResult;
var afterResult;
var differenceCounter = 1;

var chartValue;



$(document).ready(function(){
	console.log("####ready####");
	getRankByUser();
	getRank();
	angularGauge();


});

/**
 * ***********************************************************************
 * ***********************************************************************
 * ************************#######chart######*****************************
 * ***********************************************************************
 */

function angularGauge() {
    //アニメーション読み込み。
    am4core.useTheme(am4themes_animated);
    am4core.useTheme(am4themes_dark);


    //chart作成
    var chart = am4core.create("chartdiv", am4charts.GaugeChart);

    chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

    chart.innerRadius = -25;


    var axis = chart.xAxes.push(new am4charts.ValueAxis());
    axis.min = 0;
    axis.max = 4000;
    axis.strictMinMax = true;
    axis.renderer.grid.template.stroke = new am4core.InterfaceColorSet().getFor("background");
    axis.renderer.grid.template.strokeOpacity = 0.3;

    //カラーをセット
    var colorSet = new am4core.ColorSet();

    //色の範囲指定0~50
    var range0 = axis.axisRanges.create();
    range0.value = 0;
    range0.endValue = 2000;
    range0.axisFill.fillOpacity = 1;
    range0.axisFill.fill = colorSet.getIndex(0);
    range0.axisFill.zIndex = -1;

    //色の範囲指定50~80
    var range1 = axis.axisRanges.create();
    range1.value = 2000;
    range1.endValue = 3200;
    range1.axisFill.fillOpacity = 1;
    range1.axisFill.fill = colorSet.getIndex(2);
    range1.axisFill.zIndex = - 1;

    //色の範囲指定80~100
    var range2 = axis.axisRanges.create();
    range2.value = 3200;
    range2.endValue = 4000;
    range2.axisFill.fillOpacity = 1;
    range2.axisFill.fill = colorSet.getIndex(4);
    range2.axisFill.zIndex = - 1;

    var hand = chart.hands.push(new am4charts.ClockHand());

    //チャートを2秒後に描画
    chart.setTimeout(randomValue, 2000);


    function randomValue() {
        hand.showValue(chartValue, 1000, am4core.ease.cubicOut);

        chart.setTimeout(randomValue, 2000);
    }


}

//////////////////////////////////////////////////////////////////////////

function showChart (){
	am4core.ready(function() {
		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end
		// Create chart
		var chart = am4core.create("chartdiv", am4charts.PieChart);
		chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
		chart.data = [
		  {
		    country: "SEDO",
		    value: 120
		  },
		  {
		    country: "SEDO",
		    value: 63
		  },
		  {
		    country: "SE",
		    value: 71
		  },
		  {
		    country: "DOD",
		    value: 41
		  }
		];
		var series = chart.series.push(new am4charts.PieSeries());
		console.log(series);
		series.dataFields.value = "value";
		series.dataFields.radiusValue = "value";
		series.dataFields.category = "country";
		series.slices.template.cornerRadius = 6;
		//pieSeries.slices.template.stroke = am4core.color("#ffffff");
		series.colors.step = 3;
		pieSeries.labels.template.disabled = true;
		series.hiddenState.properties.endAngle = -90;
		chart.legend = new am4charts.Legend();
	}); // end am4core.ready()
}
/**
******************************
******************************
**/
//$("body").css("display","none");

function getRank(){
		$.ajax({
			url: "/MIRAI/ResultServlet",
			type: "GET",
			data: {
				result_show : '&&&'
			}
		}).done(function (result) {

			//カウンターにより交互に動かす
			if (differenceCounter % 2 != 0) {
				//beforResult = result.list.length;
				//console.log("beforResult------------->" + beforResult);

				beforScore = $('#ID1_000_000__A0_Text_54').find('span').html();
			  console.log("XXXXXBEFORSORE-------->" + beforScore);

			} else {
				//afterResult = result.list.length;
				//console.log("aftrerResult------------>" + afterResult);

				afterScore = $('#ID1_000_000__A0_Text_54').find('span').html();
				console.log("XXXXXAFTERSCORE-------->" + afterScore);

			}



			var strHtml = "";


			for(var i = 0; i < result.list.length; i++){
				//console.log(result.list[i].name +"$$"+result.list[i].gender +"$$"+result.list[i].path +"$$"+result.list[i].score+"$$"+result.list[i].id);
				imgUrl = result.list[i].path;
				//console.log("imagepath----->" + imgUrl);
				fileName = imgUrl.split("/");
				//console.log("filename-------->" + fileName);
				//console.log("../../img/user_img/" + fileName[9]);

						strHtml = strHtml +	"<div class='ranking_space' style='display: block;' data-id='"+ result.list[i].id +"'>"+
						"<img class='wheel2' src='../../img/system_img/ranking_background.svg'>" +
							"<svg class='IMAGE'>" +
						"<pattern elementId='__IMAGE_A0_Rectangle_3' id='__IMAGE_AVATAR' x='0' y='0' width='100%' height='100%'>" +
							"<image x='0' y='0' width='68px' height='68px' href='"+ "../../img/user_img/" + fileName[9] +"' xlink:href='"+ "../../img/user_img/" + fileName[9] +"'></image>" +
						"</pattern>" +
						"<img src='../../img/user_img/"+fileName[9]+"'x='0' y='0' width='68' height='68' class='rankImg'>"+
						//"<rect fill='url(#__IMAGE_AVATAR)' id='__IMAGE' rx='34' ry='34' x='0' y='0' width='68' height='68'>" +
						"</rect>" +
					"</svg>" +
					"<div id='Name'>" +
						"<span>"+ result.list[i].name +"</span>" +
					"</div>" +
					"<div id='kjkj'>" +
						"<span>"+ result.list[i].score +"</span><span style='font-family:Yu Mincho;'>点</span>" +
					"</div>" +
					"<div id='kkndknk'>" +
						"<div id='_6565'>" +
							"<span>位</span>" +
						"</div>" +
						"<div id='ID44'>" +
							"<span>"+ (i + 1) +"</span>" +
						"</div>" +
					"</div>" +
					"<svg class='flag_400'>" +
						"<pattern elementId='flag_400_A0_Rectangle_5' id='flag_400_A0_Rectangle_5_pattern' x='0' y='0' width='100%' height='100%'>" +
							"<image x='0' y='0' width='100%' height='100%' href='' xlink:href='../../img/system_img/flag_400_A0_Rectangle_15_pattern@2x.png'></image>" +
						"</pattern>" +
						"<rect fill='url(#flag_400_A0_Rectangle_5_pattern)' id='flag_400' rx='0' ry='0' x='0' y='0' width='35' height='23'>" +
						"</rect>" +
					"</svg>"+
					"</div>";

			}

			//変更がある場合のみ書き換え
			if(beforScore != afterScore) {
				console.log("!!!!!!!!!Rewrite!!!!!!!!!1")
			$("#Grille_de_r_p_tition_1").html(strHtml);

			}


		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});
}
$(".rankImg").css({"position":"relative","z-index":"1000","border-radius":"34px","top":"25px"});
/**
******************************
******************************
**/

/**
******************************
******************************
**/
function getRankByUser(){
		$.ajax({
			url: "/MIRAI/ResultServlet",
			type: "POST",
			data: {
				result_user_id : 'POST--->'
			}
		}).done(function (result) {
			// 通信成功時のコールバック

			//console.log("responseResult:" + result);
			//console.log("sumpoint---------->" + result.sumpoint);
			imgPathOne = result.userPict;
			fileNameOne = imgPathOne.split("/");

			//angularGauge(result.sumpoint);
			chartValue = result.sumpoint;
			console.log("ttttttt");

			$("#_____A0_Text_59").find('span').text(result.name);
			$(".__IMAGE_A0_Rectangle_58").find('image').attr('href',"../../img/user_img/" + fileNameOne[9]);
			$(".__IMAGE_A0_Rectangle_58").find('image').attr('xlink:href',"../../img/user_img/" + fileNameOne[9]);
			$("#ID1_000_000__A0_Text_54").find('span').text(result.sumpoint);
			$("#ID1_000").find('span').text(result.gender);
			$("#ID12").find('span').text(result.rank);



		}).fail(function (e) {
			// 通信失敗時のコールバック
			console.log(e);
			alert("読み込み失敗");
		}).always(function (result) {
			// 常に実行する処理
		});
	}



function doReloadNoCache() {

	// サーバーからリロード
	console.log("#####reloadOK#####");
	//odd & even judgment counter
	console.log("counter--------->" + differenceCounter);


	  getRankByUser();
    	getRank();
	  differenceCounter++;
}


setInterval(doReloadNoCache,5000);







