package myMonitor.UserOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import myMonitor.Bean.User;
import myMonitor.JDBC.JDBCUtil;

public class SignUp {
	/* 注册用户添加进入数据库
	 * @param user 用户
	 * @return 验证成功返回1，用户名已存在返回0，数据库错误返回-1
	 */
	public static int signUp(User user) {
		String username=user.getUserName();
		String password=user.getPassWord();
		int state=1;
		//存进数据库
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			//获得数据连接
			conn=JDBCUtil.getConnection();
			stmt=conn.createStatement();
			//查询用户名是否存在
			String sql_select="select * from users where username='"+username+"';";//sql查询语句
			rs=stmt.executeQuery(sql_select);
			if(rs.next()) {
				state=0;//用户名已存在，添加失败
			}else {
				String sql_insert="INSERT INTO users(username,password)"
						+"VALUES('"+username+"','"+password+"');";
				int num=stmt.executeUpdate(sql_insert);//执行sql插入语句
				if(num>0) state=1;//添加成功
				else state=-1;//添加失败
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.release(stmt, conn);//关闭数据库资源
		}
		return state;
	}
	 
}
