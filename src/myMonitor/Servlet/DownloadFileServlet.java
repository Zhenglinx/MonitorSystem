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
		String savePath="";//压缩包保存路径，带 文件名.zip
		File f=new File(filePath);
		//拼接文件路径
		if(f.isFile()) {
			savePath="D:\\Zip"+filePath.substring(filePath.lastIndexOf("\\"), filePath.lastIndexOf("."))+".zip";
		}else {
			savePath="D:\\Zip"+filePath.substring(filePath.lastIndexOf("\\"))+".zip";
		}
		//压缩所选文件
		try {
			FileController.compress(filePath, savePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//下载压缩文件
		File zip=new File(savePath);
		if( ! zip.exists()) {
			System.out.println("文件不存在");
			return;
		}
		FileInputStream in=new FileInputStream(zip);
		//设置响应头，控制浏览器下载该文件
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(zip.getName().getBytes("utf-8"), "iso8859-1")); 
		response.addHeader("Content-Length", "" + zip.length());
		//浏览器响应输出流
		OutputStream out = response.getOutputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=in.read(buf))>0) {
			out.write(buf,0,len);
		}
		//关闭流
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
