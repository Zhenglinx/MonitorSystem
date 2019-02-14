package myMonitor.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myMonitor.FilesSystem.diskContent;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class diskContentServlet
 */
@WebServlet("/diskContentServlet")
public class diskContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public diskContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//diskContent diskcontent=new diskContent();
		//调用方法获取磁盘信息
		JSONObject d=diskContent.disk();
		//设置响应编码，防止出现乱码
		response.setCharacterEncoding("UTF-8");
		//把json对象写回前端
		response.getWriter().print(d);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
