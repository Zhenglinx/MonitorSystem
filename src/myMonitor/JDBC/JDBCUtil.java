package myMonitor.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	/*
	 * 获取数据库链接
	 * @return 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/myMonitor";
		String username="root";
		String password="000000";
		Connection connection=DriverManager.getConnection(url, username, password);
		return connection;
	}
	//关闭数据库，释放资源	
	public static void release(Statement stmt,Connection conn){
		if(stmt!=null){
			try{
				stmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			conn=null;
		}
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			rs=null;
		}
	release(stmt,conn);
	}
}
