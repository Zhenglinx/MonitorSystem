package myMonitor.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myMonitor.FilesSystem.FilesSys;

/**
 * Servlet implementation class showFilesServlet
 */
@WebServlet("/showFilesServlet")
public class showFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showFilesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();//����session�Ự��Ϊѡ�չʾ����
		String filePath;
		filePath=request.getParameter("path");//��ȡAjax���ݹ����Ĳ���
		session.setAttribute("currentPath", filePath);//�ѵ�ǰ·�����session,������Ϊ�ϴ��ļ��ṩ·��
		session.setAttribute("childrenNode",FilesSys.setSessionData(filePath));//�����ļ�Ŀ¼���session
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
