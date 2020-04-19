package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.sensorDAO;
/**
 * Servlet implementation class receiverservlet
 */
@WebServlet("/receiverservlet")
public class receiverservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public receiverservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);


		request.setCharacterEncoding("UTF-8");
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		JSONObject rootJson = new JSONObject(reqJson);
		String userId = rootJson.getString("userid");
		String itemNum = rootJson.getString("itemnum");
		String point = rootJson.getString("point");

		int intUserId = Integer.parseInt(userId);
		int intItemNum = Integer.parseInt(itemNum);
		int intPoint = Integer.parseInt(point);

		System.out.println("user_id:" + intUserId + "\nitemnum:" + intItemNum + " \n:" + intPoint);

		sensorDAO sensor = new sensorDAO();
		sensor.insertPoint(intUserId, intItemNum, intPoint);



		// 戻り値用のオブジェクト作成 特に意味はない
		JSONObject jsonTest = new JSONObject();
		jsonTest.put("userid", userId);
		jsonTest.put("itemnum", itemNum);
		jsonTest.put("point", point);
		String strJson = jsonTest.toString();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(strJson);

	}

}
