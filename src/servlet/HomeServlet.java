package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

        //String nanimo = request.getParameter("nanimo");

		HomeClass homeClass = new HomeClass();

        String ajax = request.getParameter("ajaxValue");

        //String URL = "./question.jsp";


/*
        System.out.println(questionPageUrl);
        System.out.println(homeClass.Getter());
*/

        if(ajax.equals("#")){
    		response.setContentType("text/plain");
    		response.getWriter().write(homeClass.select());
        }
        else if(ajax.equals("##")){
        	homeClass.update("1");
    		response.setContentType("text/plain");
    		response.getWriter().write("Is Update");
        }
        else if(ajax.equals("###")){
        	homeClass.update("0");
    		response.setContentType("text/plain");
    		response.getWriter().write("Is Update");
        }





	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
