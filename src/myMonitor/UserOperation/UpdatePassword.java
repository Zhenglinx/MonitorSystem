package myMonitor.UserOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import myMonitor.JDBC.JDBCUtil;

public class UpdatePassword {
	/*
	 * @param username 用户名，是数据表users中的主码
	 * @param oldpassword 原密码
	 * @param newpassword 新密码
	 * @return 成功返回1，否则返回0
	 */
	public static int updateInfo(String username,String oldpassword,String newpassword) throws ClassNotFoundException, SQLException {
		Connection conn;
		Statement stmt;
		ResultSet rs=null;	
		
		conn = JDBCUtil.getConnection();//创建数据库链接
		stmt=conn.createStatement();//创建查询语句对象
		String sql="select * from users where username='"+username+"' and password='"+oldpassword+"';";//sql查询语句
		rs=stmt.executeQuery(sql);
		if(rs.next()) {
			String update_sql="update users set password='"+newpassword+"' where username='"+username+"';";//修改语句
			int num=stmt.executeUpdate(update_sql);
			if(num>0) return 1;
			else return 0;
		}else
			return 0;
	}
}
