
$(document).ready(function(){
  setInterval(function(){
    $.ajax({
        url: "/MIRAI/HomeServlet",
        type: "GET",
        data: {
          ajaxValue : "#"
        }
      }).done(function (result) {
        // 通信成功時のコールバック
        console.log(result);
        if ( result == "1") {
          changePage();
          console.log("待っています。");
        }else {
          console.log("よっしゃ！");
        }
      }).fail(function (e) {
        // 通信失敗時のコールバック
        console.log(e);
        alert("読み込み失敗");
      }).always(function (result) {
        // 常に実行する処理
    });
  },3500);
});

/**
******************************
******************************
**/
function changePage() {
  var url = "../main_screen/question.jsp";
  location.replace(url);
}
