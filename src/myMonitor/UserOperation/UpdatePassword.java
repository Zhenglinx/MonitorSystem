package myMonitor.UserOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import myMonitor.JDBC.JDBCUtil;

public class UpdatePassword {
	/*
	 * @param username �û����������ݱ�users�е�����
	 * @param oldpassword ԭ����
	 * @param newpassword ������
	 * @return �ɹ�����1�����򷵻�0
	 */
	public static int updateInfo(String username,String oldpassword,String newpassword) throws ClassNotFoundException, SQLException {
		Connection conn;
		Statement stmt;
		ResultSet rs=null;	
		
		conn = JDBCUtil.getConnection();//�������ݿ�����
		stmt=conn.createStatement();//������ѯ������
		String sql="select * from users where username='"+username+"' and password='"+oldpassword+"';";//sql��ѯ���
		rs=stmt.executeQuery(sql);
		if(rs.next()) {
			String update_sql="update users set password='"+newpassword+"' where username='"+username+"';";//�޸����
			int num=stmt.executeUpdate(update_sql);
			if(num>0) return 1;
			else return 0;
		}else
			return 0;
	}
}
