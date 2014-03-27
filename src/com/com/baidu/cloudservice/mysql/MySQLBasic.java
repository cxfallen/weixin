package com.baidu.cloudservice.mysql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.net.URL;
import java.sql.*;
import java.io.PrintWriter;
import com.baidu.cloudservice.conf.Config;
/**
 * MySQL示例，�?过该示例可熟悉BAE平台MySQL的使用（CRUD�? */
public class MySQLBasic extends HttpServlet { 
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
        String sql = null;
        try {
          	/**�������ݿ�����Ҫ����Ҫ�أ��ɴ����ݿ�����ҳ�в鵽��Ӧ��Ϣ��****/
			String databaseName = Config.MYSQLNAME;
			String host = Config.MYSQLHOST;
			String port = Config.MYSQLPORT;
			String username = Config.USER;
			String password = Config.PWD;
			String driverName = "com.mysql.jdbc.Driver";
			String dbUrl = "jdbc:mysql://";
			String serverName = host + ":" + port + "/";
          	String connName = dbUrl + serverName + databaseName;
          
			/******�������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ�����******/
          	Class.forName(driverName);
			connection = DriverManager.getConnection(connName, username, password);
          	stmt = connection.createStatement();
          	/*������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ�����*/
			/**
			 * �������Ϳ���ʹ��������׼mysql���������������ݿ����			 */
			//����һ�����ݿ��
			sql = "create table if not exists test_mysql(" + 
						"id int primary key auto_increment," + 
						"no int, "+
						"name varchar(1024)," + 
						"key idx_no(no))";
			stmt.execute(sql);
          
          	//����
			sql = "insert into test_mysql(no, name) values(2007,'this is a test message')," + 
                  		"(2008,'this is another test message'),(2009,'xxxxxxxxxxxxxx')";
			stmt.execute(sql);
          	//ɾ��
			sql = "delete from test_mysql where no = 2008";
			stmt.execute(sql);
          
          	//�޸�
			sql = "update test_mysql set name = 'yyyyyy' where no = 2009";
			stmt.execute(sql);
          
          	//����
			sql = "select id,no,name from test_mysql";
          	rs = stmt.executeQuery(sql);
          	ResultSetMetaData rsmd = rs.getMetaData();		//����ֶ����Ա���

			for (int i=1;i<=rsmd.getColumnCount();i++)		//���ֶ�����������������
			{resp.getWriter().print(rsmd.getColumnName(i)+"\t");}
			resp.getWriter().print("\n--------------------------------------\n");
		
			while(rs.next())
			{
				resp.getWriter().printf("%-8d%-8d%-12s\n",rs.getInt("id"),rs.getInt("no"),rs.getString("name"));
			}
          
          	//删除�?			sql = "drop table if exists test_mysql";
			stmt.execute(sql);
			
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
		}
	}

}
