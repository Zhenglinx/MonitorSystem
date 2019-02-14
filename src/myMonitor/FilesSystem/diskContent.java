package myMonitor.FilesSystem;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

//import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class diskContent {
    
	/*
	 *   获取各个磁盘的使用量
	 *  @return json={
	 *  	disk1:usePercent,
	 *  	...
	 *  }
	 */
    public static JSONObject disk() {
    	Sigar sigar=new Sigar();
    	JSONObject disk=new JSONObject();
    	try {
			FileSystem fsList[]=sigar.getFileSystemList();//获取所有磁盘
			//The device is not ready:DVD RW驱动器也算是一个盘,不能算入
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
