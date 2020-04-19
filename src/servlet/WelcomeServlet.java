package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistDAO;
import sub.Base64ToImage;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
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

		request.setCharacterEncoding("UTF-8");
        RegistDAO registDAO = new RegistDAO();
		Base64ToImage imageMaker = new Base64ToImage();

        String userId = request.getParameter("userId");
        String Picture = request.getParameter("picture");

        System.out.println(Picture);

		String fileName = "profil_" + userId;


		String imagePath = imageMaker.change(Picture, fileName);

        System.out.println(imagePath);

        registDAO.savePicture(imagePath, Integer.parseInt(userId));
        String UserName = registDAO.getUserName(Integer.parseInt(userId));

    	response.setContentType("text/plain");
    	response.setCharacterEncoding("UTF-8");
        response.getWriter().write(UserName);

	}

}
