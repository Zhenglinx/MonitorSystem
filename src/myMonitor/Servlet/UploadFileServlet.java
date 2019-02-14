package myMonitor.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this.getServletContext().getRealPath("/WEB-INF/upload")
		
		String savePath =request.getParameter("path");
		System.out.println(savePath);
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"目录不存在，需要创建");
			//创建目录
			file.mkdir();
		}
		try {
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//3、判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				return;
			}
			//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合
			@SuppressWarnings("unchecked")
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				//获取上传的文件名称
				String fileName=item.getName();
				//System.out.println(fileName);
				//获取上传文件的输入流
				InputStream in=item.getInputStream();
				//创建一个文件输出流
				FileOutputStream out=new FileOutputStream(savePath+"\\"+fileName);
				//创建一个缓冲区,加快写速度
				byte buffer[]=new byte[1024];
				int len=0;
				while((len=in.read(buffer))>0) {
					out.write(buffer,0,len);
				}
				//关闭流
				in.close();
				out.close();
				//删除处理文件时生成的临时文件
				item.delete();
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

}
