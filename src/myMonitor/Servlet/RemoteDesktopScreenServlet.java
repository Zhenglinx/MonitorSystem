package myMonitor.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myMonitor.DesktopScreen.RemoteDesktopScreen;

/**
 * Servlet implementation class RemoteDesktopScreenServlet
 */
@WebServlet("/RemoteDesktopScreenServlet")
public class RemoteDesktopScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoteDesktopScreenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RemoteDesktopScreen rds=new RemoteDesktopScreen();
		//String filePath="/Tomcat/apache-tomcat-8.5.33/webapps/myMonitor/images";//����·��
		String filePath=this.getServletContext().getRealPath("/images/Screen");//����·��
		String fileName=rds.getFilename();//��ϵͳʱ����Ϊ��ͼ����
		try {
			rds.desktopScreen(filePath, fileName);//��ͼ
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().write(fileName);
		//System.out.println(fileName);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
