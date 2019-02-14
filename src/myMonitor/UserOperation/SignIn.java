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
	 * @param userName �û����������ݱ�users�е�����
	 * @param passWord ����
	 * @return ��֤�ɹ�����1�����򷵻�0
	 */
	public static int signIn(HttpSession session,String userName,String passWord) throws ClassNotFoundException, SQLException {
		Connection conn;
		Statement stmt;
		ResultSet rs;	
		//�������ݿ�����
		conn = JDBCUtil.getConnection();
		stmt=conn.createStatement();//������ѯ������
		rs=null;//���������
		String sql="select * from users where username='"+userName+"' and password='"+passWord+"';";//sql��ѯ���
		rs=stmt.executeQuery(sql);
		//����userName�����룬����Ψһ�����ñ���ResultSet
		
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
			session.setAttribute("user", user);//���û���Ϣ����session
			JDBCUtil.release(rs, stmt, conn);//�ͷ���Դ
			return 1;//�û�����ok
		}
		else {
			JDBCUtil.release(rs, stmt, conn);//�ͷ���Դ
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
