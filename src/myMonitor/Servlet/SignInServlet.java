package myMonitor.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myMonitor.UserOperation.SignIn;

/**
 * Servlet implementation class SignInServlet
 */
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName=request.getParameter("username");
		String passWord=request.getParameter("password");
		//RequestDispatcher dispatcher;
		String message = null;
		try {
			HttpSession session=request.getSession();
			if(SignIn.signIn(session,userName, passWord)==1) {
				
				System.out.println("µÇÂ¼³É¹¦");
				message="1";
				//request.setAttribute("message", message);
				//request.getRequestDispatcher("../homepage.html").forward(request, response);
				//response.sendRedirect("/myMonitor/pages/homepage.jsp");
			}else {
				System.out.println("µÇÂ¼Ê§°Ü");
				message="0";
//				request.setAttribute("message", message);
//				dispatcher=request.getRequestDispatcher("../login.jsp");
//              dispatcher.forward(request, response);
			}
			response.getWriter().write(message);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
