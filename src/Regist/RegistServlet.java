package Regist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnswerRegistDAO;
import dao.RegistDAO;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private int userID;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

        /********************セドリック用*******************/

		request.setCharacterEncoding("UTF-8");
        // Ajaxで渡されたテキストボックスの値を変数に格納
        String ajaxName = request.getParameter("Name");
        String ajaxGender = request.getParameter("Gender");

        System.out.println("ajaxName ------->" + ajaxName);
        System.out.println("ajaxGender ------->" + ajaxGender);

        /**********************セドリック用*******************/

        RegistDAO registDAO = new RegistDAO();
        registDAO.regist(ajaxName, ajaxGender);

        /**********************セドリック用*******************/

        AnswerRegistDAO ar = new AnswerRegistDAO();
        this.userID = ar.UserIdSearch();
        System.out.println("this userId ------->" + this.userID);

        response.setContentType("text/plain");
        response.getWriter().write(Integer.toString(this.userID));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		request.setCharacterEncoding("UTF-8");
        String idJsp = request.getParameter("getId");
        response.setContentType("text/plain");
        response.getWriter().write(Integer.toString(this.userID));

	}

}
