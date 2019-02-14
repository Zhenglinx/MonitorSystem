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
	 *ɾ���ļ���Ŀ¼
	 * @param pathɾ���ļ���·��
	 * @return���ɹ�����true��ʧ�ܷ���false 
	 */
	public static boolean deleteFile(String path) {
		File delFile=new File(path);
		if(delFile.exists()) {
			//������ļ���ֱ��ɾ��
			if(delFile.isFile()) {
				delFile.delete();
			}
			//������ļ��У�����Ҫ����ɾ����Ŀ¼�����ļ�
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
	 *  ɾ��Ŀ¼
	 *  @param delfile�ļ�����
	 */
	public static void deleteDir(File delfile) {
		if(delfile.exists()) {//�ж��ļ��Ƿ����
			File[] flist=delfile.listFiles();
			for(File file:flist) {//����
				if(file.isDirectory()) {
					deleteDir(file);//Ŀ¼���ݹ�
				}else {
					file.delete();//�ļ���ֱ��ɾ��
				}
			}
			delfile.delete();//ɾ����Ŀ¼
		}
	}
	/*
	 * �������
	 */
	
	/*
	 * ѹ��
	 * @param filepath Ҫѹ�����ļ�/Ŀ¼·��
	 * @parma savepath ѹ�����ļ��ı���·������ѹ������ļ���������.zip��
	 */
	public static void compress(String filepath,String savepath) throws Exception {
		File file=new File(filepath);
		if(!file.exists()) {
			System.out.println("�ļ�������");
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
	 * ѹ���ļ�
	 * @param fileҪѹ�����ļ�����
	 * @param zosѹ���ļ������
	 * @param savePathѹ���ļ�����·��
	 * */
	private static void compressFile(File file, ZipOutputStream zos,String baseDir) throws Exception{
		if(!file.exists()) {return;}//�ļ�������
		try {
			//���ļ��Ž��ļ����������ٰ��ļ����������ӻ���������
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry=new ZipEntry(baseDir+file.getName());//ѹ�����ı���·��������
			zos.putNextEntry(entry);//��ʼд���µ� ZIP �ļ���Ŀ��������λ����Ŀ���ݵĿ�ʼ��
			int len;
			byte[] buf=new byte[1024];
			//��ѹ���ļ�д��
			while((len=bis.read(buf))>0) {
				zos.write(buf,0,len);
			}
			bis.close();//�ر���
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ѹ��Ŀ¼
	 * 
	 */
	private static void compressDir(File dir, ZipOutputStream zos,String baseDir) throws Exception {
		if(! dir.exists()) {return;}
		File[] flist=dir.listFiles();//��ȡ��һ��
		if(flist.length==0) {//��Ŀ¼
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
	 * �����ļ�/Ŀ¼ѹ��
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
