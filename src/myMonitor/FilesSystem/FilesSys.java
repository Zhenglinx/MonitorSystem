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
	 * /获取计算机盘符	
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
			//构造对应的easyui Tree的json对象及其json数组
			
			jsonFbean = new JSONObject();//super()
			jsonFbean.put("id", flist[i].getDirName());
			jsonFbean.put("text", flist[i].getDirName());
			jsonFbean.put("state", "closed");
			//children属性似乎没有必要
			diskArr.add(jsonFbean);
			
		}
		//System.out.println(diskArr.toString());
		return diskArr;//返回盘符数组
	}
	/*
	*获取子文件
	*树下拉加载
	*加载时过滤文件
	* @param filePath 父目录路径
	*/
	public static JSONArray getFileChildren(String filePath) {
		File file =new File(filePath);
		JSONArray diskArr=new JSONArray(); 
		JSONObject jsonFbean;
		FilesFilter myFilter=new FilesFilter();//文件过滤器
		
		//文件夹判断
		if(file.isDirectory()) {
			File[] flist=file.listFiles(myFilter);//获取文件夹下的子文件/文件夹，并且过滤
			for(int i=0;i<flist.length;i++) {
				//构造对应的easyui Tree夫人json对象及其json数组
				
					if(flist[i].isDirectory()) {//文件夹
						jsonFbean = new JSONObject();//super()
						jsonFbean.put("id", flist[i].getAbsolutePath());
						jsonFbean.put("text", flist[i].getName());
						jsonFbean.put("state", "closed");
						diskArr.add(jsonFbean);
					}else {//文件
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
	
	//盘符级,暂时废弃
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
	 * /在浏览器中展示文件/目录时获取数据
	 * @param filePath 父目录路径
	 * @return jsonArr 打造session的数据
	 */
	public static JSONArray setSessionData(String filePath) {
		
		File file =new File(filePath);
		FilesFilter myFilter=new FilesFilter();//文件过滤器
		JSONArray fileJsonArr=new JSONArray(); 
		JSONObject fileJson;
		//System.out.println(filePath);
		//如果前端点击的是文件
		if(file.isFile()) {
			String fileName=file.getName();
			String type=fileName.substring(fileName.lastIndexOf(".")+1);//截取最后一个“.”到末尾的字符
			fileJson=new JSONObject();
			fileJson.put("is_error", false);
			fileJson.put("is_file", true);
			fileJson.put("text", fileName);
			fileJson.put("type",type);
			fileJson.put("absolute_path", file.getAbsolutePath());
			fileJsonArr.add(fileJson);
		}
		//如果前端点击的是文件夹，那就获取它的下一层
		else {
			File[] flist=file.listFiles(myFilter);//过滤文件/目录
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
							//获取文件拓展名
							String fileName=flist[i].getName();
							String type=fileName.substring(fileName.lastIndexOf(".")+1);//截取最后一个“.”到末尾的字符
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
				fileJson.put("text", "无权限操作！");
				fileJson.put("type",null);
				fileJson.put("absolute_path",null);
				fileJsonArr.add(fileJson);
			}
		}
		return fileJsonArr;
	}
}
