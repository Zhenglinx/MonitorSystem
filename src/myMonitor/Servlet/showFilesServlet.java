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
		HttpSession session=request.getSession();//创建session会话，为选项卡展示数据
		String filePath;
		filePath=request.getParameter("path");//获取Ajax传递过来的参数
		session.setAttribute("currentPath", filePath);//把当前路径存进session,作用是为上传文件提供路径
		session.setAttribute("childrenNode",FilesSys.setSessionData(filePath));//把子文件目录存进session
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
