package myMonitor.Servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myMonitor.FilesSystem.FileController;

/**
 * Servlet implementation class DownloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filePath=request.getParameter("downloadPath");
		String savePath="";//ѹ��������·������ �ļ���.zip
		File f=new File(filePath);
		//ƴ���ļ�·��
		if(f.isFile()) {
			savePath="D:\\Zip"+filePath.substring(filePath.lastIndexOf("\\"), filePath.lastIndexOf("."))+".zip";
		}else {
			savePath="D:\\Zip"+filePath.substring(filePath.lastIndexOf("\\"))+".zip";
		}
		//ѹ����ѡ�ļ�
		try {
			FileController.compress(filePath, savePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����ѹ���ļ�
		File zip=new File(savePath);
		if( ! zip.exists()) {
			System.out.println("�ļ�������");
			return;
		}
		FileInputStream in=new FileInputStream(zip);
		//������Ӧͷ��������������ظ��ļ�
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(zip.getName().getBytes("utf-8"), "iso8859-1")); 
		response.addHeader("Content-Length", "" + zip.length());
		//�������Ӧ�����
		OutputStream out = response.getOutputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=in.read(buf))>0) {
			out.write(buf,0,len);
		}
		//�ر���
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
