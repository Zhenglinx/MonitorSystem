package myMonitor.UserOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import java.sql.ResultSet;

import myMonitor.Bean.User;
import myMonitor.JDBC.JDBCUtil;

public class SignIn {
	/*
	 * @param userName 用户名，是数据表users中的主码
	 * @param passWord 密码
	 * @return 验证成功返回1，否则返回0
	 */
	public static int signIn(HttpSession session,String userName,String passWord) throws ClassNotFoundException, SQLException {
		Connection conn;
		Statement stmt;
		ResultSet rs;	
		//创建数据库链接
		conn = JDBCUtil.getConnection();
		stmt=conn.createStatement();//创建查询语句对象
		rs=null;//创建结果集
		String sql="select * from users where username='"+userName+"' and password='"+passWord+"';";//sql查询语句
		rs=stmt.executeQuery(sql);
		//由于userName是主码，所以唯一，不用遍历ResultSet
		
		/*while(rs.next()) {
			System.out.print("id"+rs.getInt("id"));
			System.out.print(" username"+rs.getString("username"));
			System.out.println(" password"+);
			if(rs.getString("password").equals(passWord)) {
				
			}
		}*/
		if(rs.next()) {
			User user=new User();
			user.setUserName(rs.getString("username"));
			user.setPassWord(rs.getString("password"));
			session.setAttribute("user", user);//把用户信息存在session
			JDBCUtil.release(rs, stmt, conn);//释放资源
			return 1;//用户密码ok
		}
		else {
			JDBCUtil.release(rs, stmt, conn);//释放资源
			return 0;
		}	
	}
	/*public static void main(String[] args) {
		try {
			System.out.println(signIn("me", "123456"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
