<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>arigato</title>
    <script type="text/javascript" src="../../js/libs/jquery-3.4.1.min.js"></script>
    <style type="text/css">
<!--
.img{
    position: absolute;
}
-->
</style>
</head>
<body>

    <div class="box">
        <img alt="" class="img" src="../../img/system_img/Activity_end.png">
    <div class="box__centered">
        <div class="box__contents">
            <h2 class="box__title">WEBDESIGNDAY</h2>
            <P class="box__text">
            MORE GOOD DESIGN<br>
            MORE INSPIRATION<br>
            MORE AND MORE
            </P>
        </div><!--box__contents-->
    </div><!--box__centered-->
</div><!--box-->


    <script>
var winH = $(window).height();
$('.box').outerHeight(winH);

$(window).on('load',function(){
    setBgImg($('.img'));
});

$(window).on('resize',function(){
    winH = $(window).height();
    $('.box').outerHeight(winH);
    setBgImg($('.img'));
});

function setBgImg(object){
    //画像サイズ取得
    var imgW = object.width();
    var imgH = object.height();
    console.log("width------>" + imgW);
    console.log("hight------>" + imgH);

    //ウィンドウサイズ取得
    var winW = $(window).width();
    var winH = $(window).height();

    //幅・高さの拡大率取得
    var scaleW = winW / imgW;
    var scaleH = winH / imgH;

    //幅・高さの拡大率の大きいものを取得
    var fixScale = Math.max(scaleW, scaleH);

    //画像の幅高さを設定
    var setW = imgW * fixScale;
    var setH = imgH * fixScale;

    //画像の位置を設定
    var moveX = Math.floor((winW - setW) / 2);
    var moveY = Math.floor((winH - setH) / 2);

    //設定した数値でスタイルを適用
    object.css({
        'width': setW,
        'height': setH,
        'left' : moveX,
        'top' : moveY
    });
}
</script>


</body>
</html>