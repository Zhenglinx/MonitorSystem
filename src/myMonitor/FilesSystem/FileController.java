package myMonitor.FilesSystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileController {
	
	
	/*
	 *删除文件或目录
	 * @param path删除文件的路径
	 * @return：成功返回true，失败返回false 
	 */
	public static boolean deleteFile(String path) {
		File delFile=new File(path);
		if(delFile.exists()) {
			//如果是文件，直接删除
			if(delFile.isFile()) {
				delFile.delete();
			}
			//如果是文件夹，则需要遍历删除子目录和子文件
			else {
				deleteDir(delFile);
			}
			return true;
		}
		else {
			System.out.println("delete fail");
			return false;
		}
	}
	/*
	 *  删除目录
	 *  @param delfile文件对象
	 */
	public static void deleteDir(File delfile) {
		if(delfile.exists()) {//判断文件是否存在
			File[] flist=delfile.listFiles();
			for(File file:flist) {//遍历
				if(file.isDirectory()) {
					deleteDir(file);//目录，递归
				}else {
					file.delete();//文件，直接删除
				}
			}
			delfile.delete();//删除本目录
		}
	}
	/*
	 * 打包下载
	 */
	
	/*
	 * 压缩
	 * @param filepath 要压缩的文件/目录路径
	 * @parma savepath 压缩后文件的保存路，包含压缩后的文件名（××.zip）
	 */
	public static void compress(String filepath,String savepath) throws Exception {
		File file=new File(filepath);
		if(!file.exists()) {
			System.out.println("文件不存在");
			return;
		}
		File zipFile=new File(savepath);
		try {
			FileOutputStream fos=new FileOutputStream(zipFile);
			ZipOutputStream zos=new ZipOutputStream(fos);
			String baseDir = "";
			if(file.isFile()) {
				compressFile(file,zos,baseDir);
			}else {
				compressDir(file,zos,baseDir);
			}
			zos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 压缩文件
	 * @param file要压缩的文件对象
	 * @param zos压缩文件输出流
	 * @param savePath压缩文件保存路径
	 * */
	private static void compressFile(File file, ZipOutputStream zos,String baseDir) throws Exception{
		if(!file.exists()) {return;}//文件不存在
		try {
			//把文件放进文件输入流，再把文件输入流连接缓冲输入流
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry=new ZipEntry(baseDir+file.getName());//压缩包的保存路径和名字
			zos.putNextEntry(entry);//开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处
			int len;
			byte[] buf=new byte[1024];
			//把压缩文件写出
			while((len=bis.read(buf))>0) {
				zos.write(buf,0,len);
			}
			bis.close();//关闭流
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 压缩目录
	 * 
	 */
	private static void compressDir(File dir, ZipOutputStream zos,String baseDir) throws Exception {
		if(! dir.exists()) {return;}
		File[] flist=dir.listFiles();//获取下一级
		if(flist.length==0) {//空目录
			try {
				zos.putNextEntry(new ZipEntry(baseDir+dir.getName()+File.separator));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
			for(File file:flist) {
				
				compressType(file,zos, baseDir + dir.getName() + File.separator);	
			}		
	}
	/*
	 * 按照文件/目录压缩
	 */
	private static void compressType(File file, ZipOutputStream zos, String baseDir) throws Exception {
		// TODO Auto-generated method stub
		if(! file.exists()) {return;}
		if(file.isFile()) {
			compressFile(file, zos, baseDir);
		}else if(file.isDirectory()) {
			compressDir(file, zos, baseDir);
		}
	}
}
