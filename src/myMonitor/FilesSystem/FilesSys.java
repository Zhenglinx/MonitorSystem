package myMonitor.FilesSystem;

import java.io.File;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import myMonitor.Filter.FilesFilter;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class FilesSys {
	/*
	 * /��ȡ������̷�	
	 * @return jsonArr=[{
	 * 	id:dirName,
	 * 	text:dirName,
	 *  state:close
	 * },{...},...]
	 */
	public static JSONArray getDriveLetter() throws SigarException {
		Sigar sigar=new Sigar();
		FileSystem flist[]=sigar.getFileSystemList();
		JSONArray diskArr=new JSONArray(); 
		JSONObject jsonFbean;
		for(int i=0;i<flist.length-1;i++) {
			//�����Ӧ��easyui Tree��json������json����
			
			jsonFbean = new JSONObject();//super()
			jsonFbean.put("id", flist[i].getDirName());
			jsonFbean.put("text", flist[i].getDirName());
			jsonFbean.put("state", "closed");
			//children�����ƺ�û�б�Ҫ
			diskArr.add(jsonFbean);
			
		}
		//System.out.println(diskArr.toString());
		return diskArr;//�����̷�����
	}
	/*
	*��ȡ���ļ�
	*����������
	*����ʱ�����ļ�
	* @param filePath ��Ŀ¼·��
	*/
	public static JSONArray getFileChildren(String filePath) {
		File file =new File(filePath);
		JSONArray diskArr=new JSONArray(); 
		JSONObject jsonFbean;
		FilesFilter myFilter=new FilesFilter();//�ļ�������
		
		//�ļ����ж�
		if(file.isDirectory()) {
			File[] flist=file.listFiles(myFilter);//��ȡ�ļ����µ����ļ�/�ļ��У����ҹ���
			for(int i=0;i<flist.length;i++) {
				//�����Ӧ��easyui Tree����json������json����
				
					if(flist[i].isDirectory()) {//�ļ���
						jsonFbean = new JSONObject();//super()
						jsonFbean.put("id", flist[i].getAbsolutePath());
						jsonFbean.put("text", flist[i].getName());
						jsonFbean.put("state", "closed");
						diskArr.add(jsonFbean);
					}else {//�ļ�
						jsonFbean = new JSONObject();//super()
						jsonFbean.put("id", flist[i].getAbsolutePath());
						jsonFbean.put("text", flist[i].getName());
						jsonFbean.put("state", "open");
						diskArr.add(jsonFbean);
					}
				}
		}
			
		//System.out.println(diskArr.toString());
		return diskArr;
	}
	
	//�̷���,��ʱ����
	/*
	public static JSONArray setSessionData() throws SigarException {
		Sigar sigar=new Sigar();
		FileSystem flist[]=sigar.getFileSystemList();
		JSONArray fileJsonArr=new JSONArray(); 
		JSONObject fileJson;
		for(int i=0;i<flist.length-1;i++) {
			
			fileJson = new JSONObject();//super()
			fileJson.put("is_file", true);
			fileJson.put("text", flist[i].getDirName());
			fileJson.put("type", null);
			fileJson.put("absolute_path", flist[i].getDirName());
			fileJsonArr.add(fileJson);
			
		}
		return fileJsonArr;
	}
	*/
	/*
	 * /���������չʾ�ļ�/Ŀ¼ʱ��ȡ����
	 * @param filePath ��Ŀ¼·��
	 * @return jsonArr ����session������
	 */
	public static JSONArray setSessionData(String filePath) {
		
		File file =new File(filePath);
		FilesFilter myFilter=new FilesFilter();//�ļ�������
		JSONArray fileJsonArr=new JSONArray(); 
		JSONObject fileJson;
		//System.out.println(filePath);
		//���ǰ�˵�������ļ�
		if(file.isFile()) {
			String fileName=file.getName();
			String type=fileName.substring(fileName.lastIndexOf(".")+1);//��ȡ���һ����.����ĩβ���ַ�
			fileJson=new JSONObject();
			fileJson.put("is_error", false);
			fileJson.put("is_file", true);
			fileJson.put("text", fileName);
			fileJson.put("type",type);
			fileJson.put("absolute_path", file.getAbsolutePath());
			fileJsonArr.add(fileJson);
		}
		//���ǰ�˵�������ļ��У��Ǿͻ�ȡ������һ��
		else {
			File[] flist=file.listFiles(myFilter);//�����ļ�/Ŀ¼
			try {
				for(int i=0;i<flist.length;i++) {
					
						if(flist[i].isDirectory()) {
							fileJson=new JSONObject();
							fileJson.put("is_error", false);
							fileJson.put("is_file", false);
							fileJson.put("text", flist[i].getName());
							fileJson.put("type",null);
							fileJson.put("absolute_path", flist[i].getAbsolutePath());
							fileJsonArr.add(fileJson);
						}else {
							//��ȡ�ļ���չ��
							String fileName=flist[i].getName();
							String type=fileName.substring(fileName.lastIndexOf(".")+1);//��ȡ���һ����.����ĩβ���ַ�
							fileJson=new JSONObject();
							fileJson.put("is_error", false);
							fileJson.put("is_file", true);
							fileJson.put("text", fileName);
							fileJson.put("type",type);
							fileJson.put("absolute_path", flist[i].getAbsolutePath());
							fileJsonArr.add(fileJson);
						}	
				}
			}catch(NullPointerException e){
				fileJson=new JSONObject();
				fileJson.put("is_error", true);
				fileJson.put("is_file", null);
				fileJson.put("text", "��Ȩ�޲�����");
				fileJson.put("type",null);
				fileJson.put("absolute_path",null);
				fileJsonArr.add(fileJson);
			}
		}
		return fileJsonArr;
	}
}
