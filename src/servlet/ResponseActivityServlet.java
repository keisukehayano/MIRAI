package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseActivityServlet
 */
@WebServlet("/ResponseActivityServlet")
public class ResponseActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseActivityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


		//Idの取得
				String id = request.getParameter("userId");
				int user_id = Integer.parseInt(id);

				//変数宣言
				String activityName = "";

				Connection con = null;
				PreparedStatement st = null;
				ResultSet rs = null;

				try{

					//Mysqlのドライバーと接続する
					Class.forName("com.mysql.jdbc.Driver");

					//Connectionにデータベース名、ユーザー名、パスワードを代入することで
					//使用するデータベースを特定できる
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/aiscreann?charcterEncoding=utf8&serverTimezone=JST",
							"root","");

					//Statmentに使用するデータベース情報を代入する
					StringBuilder sb = new StringBuilder(100);
					sb.append("SELECT activity ");
					sb.append("FROM user ");
					sb.append("WHERE user_id = ?;");
					String sql = sb.toString();

					st = con.prepareStatement(sql);
					st.setInt(1, user_id);
					//参照系SQLを実行する
					rs = st.executeQuery();

					//アクティビティ名の取得
					rs.next();
					activityName = rs.getString("activity");

				} catch (ClassNotFoundException e) {
				} catch (SQLException e) {
				} finally {
					if (rs != null) {
						try{
							rs.close();
						} catch (SQLException e){
							//TODO: handle exception
						}
					}
					if (st !=null) {
						try {
							st.close();
						} catch (SQLException e) {
							//TODO: handle exception
						}
					}
				}

				response.setContentType("text/plain;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(activityName);

	}

}
