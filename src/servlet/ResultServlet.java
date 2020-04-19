package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.ResultDAO;
import sub.User;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String nothing = request.getParameter("result_show");
		System.out.println("GET受け取り" + nothing);
		ResultDAO r = new ResultDAO();
		String Json = r.ResultAll();
		System.out.print(Json);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(Json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


		//ユーザーIdを受け取っている
		String user_id = request.getParameter("result_user_id");
		System.out.println("##POST##ユーザーID:" + user_id);




			//ユーザーIDを数値へキャストしてる

			//ユーザー1人分の情報を取得するためのDAOインスタンス作成
			ResultDAO oneUserGet = new ResultDAO();
			//DBからユーザー情報をオブジェクトで受け取り

			User userObject = oneUserGet.GetUserInfo();

			//オブジェクトから取り出し
			String userName = userObject.getName();
			String userGender = userObject.getGender();
			String userPict = userObject.getPicture();
			int sumPoint = userObject.getPoint();
			String userRank = userObject.getRank();

			System.out.println("ユーザー名" + userName);
			System.out.println("性別:" + userGender);
			System.out.println("画像:" + userPict);
			System.out.println("合計ポイント:" + sumPoint);
			System.out.println("順位:" + userRank);

			JSONObject rootJson = new JSONObject();

			rootJson.put("name", userName);
			rootJson.put("gender", userGender);
			rootJson.put("userPict", userPict);
			rootJson.put("sumpoint", sumPoint);
			rootJson.put("rank", userRank);
			//json文字列に変換
			String strJson = rootJson.toString();
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(strJson);

			System.out.println("ジェイソン文字列さようなら");


	}

}
