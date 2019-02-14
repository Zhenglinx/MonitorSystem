package myMonitor.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hyperic.sigar.SigarException;

import myMonitor.CPU.CPU_Perf;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class CPUPerc
 */
@WebServlet("/CPUPerc")
public class CPUPercServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CPUPercServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			JSONArray CpuArr=CPU_Perf.cpu();
			response.setContentType("text/json");
			//���ñ��룬��ֹ��������,һ��Ҫ�У���Ȼ����ȫ�����룿����
			response.setCharacterEncoding("UTF-8");
			//������д��ǰ�ˣ���ʽΪ�ַ���
			response.getWriter().write(CpuArr.toString());//join()���У�Ҫ��toString()
			
		} catch (SigarException e) {
			// TODO Auto-generated catch block
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
