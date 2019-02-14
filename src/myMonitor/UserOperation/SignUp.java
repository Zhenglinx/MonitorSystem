package myMonitor.UserOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import myMonitor.Bean.User;
import myMonitor.JDBC.JDBCUtil;

public class SignUp {
	/* ע���û���ӽ������ݿ�
	 * @param user �û�
	 * @return ��֤�ɹ�����1���û����Ѵ��ڷ���0�����ݿ���󷵻�-1
	 */
	public static int signUp(User user) {
		String username=user.getUserName();
		String password=user.getPassWord();
		int state=1;
		//������ݿ�
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			//�����������
			conn=JDBCUtil.getConnection();
			stmt=conn.createStatement();
			//��ѯ�û����Ƿ����
			String sql_select="select * from users where username='"+username+"';";//sql��ѯ���
			rs=stmt.executeQuery(sql_select);
			if(rs.next()) {
				state=0;//�û����Ѵ��ڣ����ʧ��
			}else {
				String sql_insert="INSERT INTO users(username,password)"
						+"VALUES('"+username+"','"+password+"');";
				int num=stmt.executeUpdate(sql_insert);//ִ��sql�������
				if(num>0) state=1;//��ӳɹ�
				else state=-1;//���ʧ��
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.release(stmt, conn);//�ر����ݿ���Դ
		}
		return state;
	}
	 
}
