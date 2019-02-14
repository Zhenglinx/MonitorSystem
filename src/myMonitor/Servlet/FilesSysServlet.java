package myMonitor.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hyperic.sigar.SigarException;

import myMonitor.FilesSystem.FilesSys;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class FilesSysServlet
 */
@WebServlet("/FilesSysServlet")
public class FilesSysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilesSysServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray fileChildren;
		//获取节点id，即父目录路径
		String param=request.getParameter("id");
		//设置编码，防止出现乱码
		response.setCharacterEncoding("UTF-8");
		//如果参数为null， 即为第一次加载，故获取盘符作为响应返回
		if(param==null) {
			try {
				fileChildren = FilesSys.getDriveLetter();
				
				response.getWriter().print(fileChildren);
			} catch (SigarException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		//参数不为空，即为节点下拉，故获取子目录返回	
			fileChildren = FilesSys.getFileChildren(param);
			response.getWriter().print(fileChildren);
			
		}
		//System.out.println(session.getAttribute("childrenNode"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
