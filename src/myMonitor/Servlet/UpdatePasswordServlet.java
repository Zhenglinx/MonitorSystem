package myMonitor.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myMonitor.Bean.User;
import myMonitor.UserOperation.UpdatePassword;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		int state=0;//״̬����0Ϊ���������޸�ʧ�ܣ�1Ϊ������ȷ���޸ĳɹ�
		String username=request.getParameter("username");
		String oldpassword=request.getParameter("oldPassword");
		String newpassword=request.getParameter("newPassword");
		//System.out.println(oldpassword+"     "+newpassword);
		try {
			state=UpdatePassword.updateInfo(username, oldpassword, newpassword);
			//�޸ĳɹ�������session
			if(state==1) {
				HttpSession session=request.getSession();
				User user=new User(username,newpassword);
				session.setAttribute("user", user);
			}
			response.getWriter().write(String.valueOf(state));//д��stateʱҪ�Ƚ���ת����string�����������������һ��updatePassword�ļ�
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
