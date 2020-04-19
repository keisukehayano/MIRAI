package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Servlet implementation class userinfoservlet
 */
@WebServlet("/userinfoservlet")
public class userinfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public userinfoservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		// ここにデータベース登録処理を書く
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aiscreann?characterEncoding=utf8",
					"root", "");
			st = con.createStatement();
			String sql = "SELECT * FROM user;";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> rec = new ArrayList<String>();
				rec.add(rs.getString("user_id"));
				rec.add(rs.getString("name"));
				rec.add(rs.getString("gender"));
				list.add(rec);
			}
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		// 戻り値用のオブジェクト作成
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("userid", list.get(i).get(0));
			jsonObj.put("name", list.get(i).get(1));
			jsonObj.put("gender", list.get(i).get(2));
			jsonArray.put(jsonObj);
		}
		JSONObject rootJson = new JSONObject();
		rootJson.put("list", jsonArray);

		System.out.println(rootJson);
		String strJson = rootJson.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println(strJson);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
