package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnswerRegistDAO;
import dao.RegistDAO;
import sub.QuestionBranch;
/**
 * Servlet implementation class QuestionBranchServlet
 */
@WebServlet("/QuestionBranchServlet")
public class QuestionBranchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String toQuestion1 = "";
    private String toQuestion2 = "";
    private String toQuestion3 = "";

    private String toA1 = "";
    private String toA2 = "";
    private String toA3 = "";

    private String startWelcomePage = "STOP";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionBranchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session =request.getSession();
        request.setCharacterEncoding("UTF-8");
        HomeClass homeClass = new HomeClass();
        /*******************ここからセドリック用**************************/
        // Ajaxで渡されたテキストボックスの値を変数に格納
        String ajaxAnswer0 = request.getParameter("Answer0");
        String ajaxAnswer1 = request.getParameter("Answer1");
        String ajaxAnswer2 = request.getParameter("Answer2");
        String ajaxAnswer3 = request.getParameter("Answer3");
        this.toA1 = ajaxAnswer1;
        this.toA2 = ajaxAnswer2;
        this.toA3 = ajaxAnswer3;
        //System.out.println(answer);
        /*******************ここまでセドリック用**************************/
        /*変数宣言*/
        String firstQuestion = " 運動は好きですか？";
        String secondQuestion = "QUESTION_2 ?";
        String thirdQuestion = "QUESTION_3 ?";
        String activity = "";
        System.out.println(""+ajaxAnswer1);
        System.out.println(""+ajaxAnswer2);
        System.out.println(""+ajaxAnswer3);
        /*使用するクラスの宣言*/
        QuestionBranch qb = new QuestionBranch();
        AnswerRegistDAO ar = new AnswerRegistDAO();
        /*ユーザーIDの検索*/
        int user_id = ar.UserIdSearch();
        /*遷移先判定*/
        if(ajaxAnswer1 != null && ajaxAnswer2 == null && ajaxAnswer3 == null) {
            if(ajaxAnswer1.equals("YES") || ajaxAnswer1.equals("NO")) {
                //質問の答え登録
                ar.AnswerRegist(user_id, 1, ajaxAnswer1);
                //質問2の遷移先判定
                secondQuestion = qb.FirstQestion(ajaxAnswer1);
                System.out.println("OK1");
            }
        }else
        if(ajaxAnswer1 != null && ajaxAnswer2 != null && ajaxAnswer3 == null) {
            if(ajaxAnswer2.equals("YES") || ajaxAnswer2.equals("NO")) {
                //質問の答え登録
                ar.AnswerRegist(user_id, 2, ajaxAnswer2);
                //質問3の遷移先判定
                thirdQuestion = qb.SecondQestion(ajaxAnswer2,secondQuestion,user_id);
                System.out.println("OK2");
            }
        }else
        if(ajaxAnswer1 != null && ajaxAnswer2 != null && ajaxAnswer3 != null) {
            if(ajaxAnswer3.equals("YES") || ajaxAnswer3.equals("NO")) {
                //質問の答え登録
                ar.AnswerRegist(user_id, 3, ajaxAnswer3);
                //アクティビティの遷移先判定
                activity = qb.ThirdQestion(ajaxAnswer3,thirdQuestion,user_id);
                //userテーブルへアクティビティ情報の登録
                RegistDAO rDAO = new RegistDAO();
                rDAO.registActivity(activity, user_id);
            }
        }
        /*******************ここからセドリック用**************************/
        String ajaxQuestion1 = firstQuestion;
        String ajaxQuestion2 = secondQuestion;
        String ajaxQuestion3 = thirdQuestion;
        ///
        response.setContentType("text/plain");
        if( ajaxAnswer0.equals("READY") && ajaxAnswer1 == null && ajaxAnswer2 == null && ajaxAnswer3 == null  ){
            this.toQuestion1 = "";
            this.toQuestion2 = "";
            this.toQuestion3 = "";
            response.setContentType("text/plain");
            this.toQuestion1 = ajaxQuestion1;
            this.startWelcomePage = "STOP";
            System.out.println("Q1------->" + this.toQuestion1);
            response.getWriter().write(ajaxQuestion1);
        }
        if( ajaxAnswer0.equals("READY") && ajaxAnswer1 != null && ajaxAnswer2 == null && ajaxAnswer3 == null  ){
            response.setContentType("text/plain;charset=utf-8");
            this.toQuestion2 = ajaxQuestion2;
            this.startWelcomePage = "STOP";
            System.out.println("Q2------->" + this.toQuestion2);
            response.getWriter().write(ajaxQuestion2);
        }
        if( ajaxAnswer0.equals("READY") && ajaxAnswer1 != null && ajaxAnswer2 != null && ajaxAnswer3 == null  ){
            response.setContentType("text/plain;charset=utf-8");
            this.toQuestion3 = ajaxQuestion3;
            this.startWelcomePage = "STOP";
            System.out.println("Q3------->" + this.toQuestion3);
            response.getWriter().write(ajaxQuestion3);
        }
        if( ajaxAnswer0.equals("READY") && ajaxAnswer1 != null && ajaxAnswer2 != null && ajaxAnswer3 != null  ){
            this.startWelcomePage = "START";
            response.setContentType("text/plain;charset=utf-8");
            response.getWriter().write("FINISH");
        }
        /*******************ここまでセドリック用**************************/
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doGet(request, response);
        request.setCharacterEncoding("UTF-8");
        String QuesJsp = request.getParameter("FromQuestionJSP;charset=utf-8");
        response.setContentType("text/plain;charset=utf-8");
        if(this.startWelcomePage.equals("START")){
            response.getWriter().write(this.startWelcomePage);
        }else{
            response.getWriter().write(this.toQuestion1 + "$$" + this.toQuestion2 + "$$" + this.toQuestion3 + "$$" + this.toA1 + "$$" + this.toA2 + "$$" + this.toA3);
        }
    }
}



