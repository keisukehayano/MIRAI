package sub;
import dao.QuestionDAO;
public class QuestionBranch {
    /**************************
     * 質問1の分岐処理メソッド*
     **************************/
    public String FirstQestion(String answer1) {
        //変数宣言
        String secondQuestion = "";
        //解答によって次の質問文を変える処理
        if(answer1.equals("YES")) {
            secondQuestion = "階段とエスカレーターがある場合、階段を使おうという気にはなりますか？";
            // 質問2内容表示処理
        } else {
            secondQuestion = " 歩く習慣はありますか？";
            // 質問3内容表示処理
        }
        return secondQuestion;
    }
    /**************************
     * 質問2の分岐処理メソッド*
     **************************/
    public String SecondQestion(String answer2, String secondQuestion,int user_id) {
        //変数宣言
        String thirdQuestion = "";
        String answer1 = "";
        QuestionDAO q = new QuestionDAO();
        answer1 = q.QuestionAnswer(user_id, 1);
        //解答によって次の質問文を変える処理
        if(answer1.equals("YES")) {//yes
            if(answer2.equals("YES")) {
                thirdQuestion = "運動の習慣はありますか？";
                // 質問4表示処理
            } else {
                thirdQuestion = "ジムに通う習慣はありますか？";
                // 質問5表示処理
            }
        }else {//no
            if(answer2.equals("YES")) {
                thirdQuestion = "身体能力に自信はありますか？";
                //質問6表示処理
            } else {
                thirdQuestion = "踊ることは好きですか？";
                //質問7表示処理
            }
        }
        return thirdQuestion;
    }
    /**************************
     * 質問3の分岐処理メソッド*
     **************************/
    public String ThirdQestion(String answer3,String thirdQuestion,int user_id) {
        //変数宣言
        String activity = "";
        String answer1 = "";
        String answer2 = "";
        QuestionDAO q = new QuestionDAO();
        answer1 = q.QuestionAnswer(user_id, 1);
        answer2 = q.QuestionAnswer(user_id, 2);
        System.out.println("呼び出した答え==>"+answer1);
        System.out.println("呼び出した答え==>"+answer2);
        if(answer1.equals("YES") && answer2.equals("YES")) {//yesyes
            switch(answer3) {
                case "YES":
                    activity = "ACT5";//熱盛
                    //アクティビティ1移行処理
                break;
                case "NO":
                    activity = "ACT4";//特盛
                    //アクティビティ2移行処理
                break;
            }
        } else if(answer1.equals("YES") && answer2.equals("NO")) {//yesno
            switch(answer3) {
                case "YES":
                    activity = "ACT4";//特盛
                    //アクティビティ2移行処理
                break;
                case "NO":
                    activity = "ACT3";//大盛
                    //アクティビティ3移行処理
                break;
            }
        } else if(answer1.equals("NO") && answer2.equals("YES")) {//noyes
            switch(answer3) {
                case "YES":
                    activity = "ACT3";//大盛
                    //アクティビティ3移行処理
                break;
                case "NO":
                    activity = "ACT2";//中盛
                    //アクティビティ4移行処理
                break;
            }
        } else {//nono
            switch(answer3) {
                case "YES":
                    activity = "ACT2";//中盛
                    //アクティビティ4移行処理
                break;
                case "NO":
                    activity = "ACT1";//小盛
                    //アクティビティ5移行処理
                break;
            }
        }
        return activity;
    }
}

