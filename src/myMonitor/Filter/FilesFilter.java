package myMonitor.Filter;

import java.io.File;
import java.io.FilenameFilter;

/*
 *     �ļ�������ʵ��FilenameFilter�ӿ�
 *   ������Ȩ�޷��ʵ�ϵͳ�ļ�
*/

public class FilesFilter implements FilenameFilter{

	/*
	 * �����ļ����µ�ϵͳ�ļ������ɷ����ļ�����˽�ļ�
	 * @param dir ��Ŀ¼�ļ�����
	 * @param pathName ���ļ���
	 * @return ���˷���false�����򷵻�true
	 */

	@Override
	public boolean accept(File dir, String pathName) {
		// TODO Auto-generated method stub
		
		//�ԡ�$����ͷ�Ķ���ϵͳ�ļ�
		if(pathName.startsWith("$")) {//ϵͳ�ļ�
			return false;
		}else if(pathName.equals("Tomcat")) {//������Ŀ¼��������
			return false;
		}else if(pathName.equals("Recovery")) {//�����ļ�
			return false;
		}else if(pathName.equals("KRECYCLE")) {//�����ļ�
			return false;
		}else if(pathName.equals("System Volume Information")) {//�����ļ�
			return false;
		}else if(pathName.equals("Documents and Settings")) {//�����ļ�
			return false;
		}else if(pathName.equals("Windows")) {//ϵͳĿ¼
			return false;
		}else if(pathName.equals("All Users")) {//��Ȩ��
			return false;
		}else if(pathName.equals("Default User")) {//��Ȩ��
			return false;
		}else if(pathName.equals("Default")) {//��Ȩ��Ŀ¼
			return false;
		}else if(pathName.endsWith(".sys")) {//ϵͳ�ļ�
			return false;
		}else if(pathName.endsWith(".Msi")) {
			return false;
		}else if(pathName.endsWith(".ini")) {//��ʼ���ļ�
			return false;
		}
		else return true;
	}

}
