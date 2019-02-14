package myMonitor.Filter;

import java.io.File;
import java.io.FilenameFilter;

/*
 *     文件过滤器实现FilenameFilter接口
 *   过滤无权限访问的系统文件
*/

public class FilesFilter implements FilenameFilter{

	/*
	 * 过滤文件夹下的系统文件，不可访问文件，隐私文件
	 * @param dir 父目录文件对象
	 * @param pathName 子文件名
	 * @return 过滤返回false，否则返回true
	 */

	@Override
	public boolean accept(File dir, String pathName) {
		// TODO Auto-generated method stub
		
		//以“$”开头的多是系统文件
		if(pathName.startsWith("$")) {//系统文件
			return false;
		}else if(pathName.equals("Tomcat")) {//服务器目录，不给看
			return false;
		}else if(pathName.equals("Recovery")) {//隐藏文件
			return false;
		}else if(pathName.equals("KRECYCLE")) {//隐藏文件
			return false;
		}else if(pathName.equals("System Volume Information")) {//隐藏文件
			return false;
		}else if(pathName.equals("Documents and Settings")) {//隐藏文件
			return false;
		}else if(pathName.equals("Windows")) {//系统目录
			return false;
		}else if(pathName.equals("All Users")) {//无权限
			return false;
		}else if(pathName.equals("Default User")) {//无权限
			return false;
		}else if(pathName.equals("Default")) {//无权限目录
			return false;
		}else if(pathName.endsWith(".sys")) {//系统文件
			return false;
		}else if(pathName.endsWith(".Msi")) {
			return false;
		}else if(pathName.endsWith(".ini")) {//初始化文件
			return false;
		}
		else return true;
	}

}
