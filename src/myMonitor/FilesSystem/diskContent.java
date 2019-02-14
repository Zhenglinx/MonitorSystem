package myMonitor.FilesSystem;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

//import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class diskContent {
    
	/*
	 *   ��ȡ�������̵�ʹ����
	 *  @return json={
	 *  	disk1:usePercent,
	 *  	...
	 *  }
	 */
    public static JSONObject disk() {
    	Sigar sigar=new Sigar();
    	JSONObject disk=new JSONObject();
    	try {
			FileSystem fsList[]=sigar.getFileSystemList();//��ȡ���д���
			//The device is not ready:DVD RW������Ҳ����һ����,��������
			for(int i=0;i<fsList.length-1;i++) {
				FileSystemUsage usage=sigar.getFileSystemUsage(fsList[i].getDirName());
				disk.put(fsList[i].getDirName(), usage.getUsePercent());	
			}	
		} catch (SigarException e) {
			e.printStackTrace();
		}
  
    	return disk;
    }  
    public static void main(String[] args) {
    	System.out.println(disk().toString());
    }
    
}
