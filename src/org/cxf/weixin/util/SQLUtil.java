package org.cxf.weixin.util;

import java.sql.*;
import com.baidu.cloudservice.conf.Config;

public class SQLUtil {
	public static boolean checkPasswordFromAI(String userString,String passwordString) {
		boolean rslt = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
	    String sql = null; 
	    
	    String databaseName = Config.MYSQLNAME;
		String host = Config.MYSQLHOST;
		String port = Config.MYSQLPORT;
		String username = Config.USER;
		String sqlpassword = Config.PWD;
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
	  	String connName = dbUrl + serverName + databaseName;
	  	
	  	try {
	  		Class.forName(driverName);
	  		connection = DriverManager.getConnection(connName, username, sqlpassword);
	  	    stmt = connection.createStatement();
		    
		    sql = "SELECT * FROM AlumniInfo where userName='" + userString + "'";
		    System.out.println(sql);
		    rs=stmt.executeQuery(sql);
		    if (rs.next()) {
				String pass = rs.getString("password");
				if (pass.equalsIgnoreCase(passwordString)) {
					rslt = true;
				}
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rslt;
	}
	
	public static boolean checkPasswordFromUI(String userString,String passwordString) {
		boolean rslt = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
	    String sql = null; 
	    
	    String databaseName = Config.MYSQLNAME;
		String host = Config.MYSQLHOST;
		String port = Config.MYSQLPORT;
		String username = Config.USER;
		String sqlpassword = Config.PWD;
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
	  	String connName = dbUrl + serverName + databaseName;
	  	
	  	try {
	  		Class.forName(driverName);
	  		connection = DriverManager.getConnection(connName, username, sqlpassword);
	  	    stmt = connection.createStatement();
		    
		    sql = "SELECT * FROM AlumniInfo where userName='" + userString + "'";
		    System.out.println(sql);
		    rs=stmt.executeQuery(sql);
		    if (rs.next()) {
				String pass = rs.getString("password");
				if (pass.equalsIgnoreCase(passwordString)) {
					rslt = true;
				}
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	  	if (rslt) {
			System.out.println("1");
		}else {
			System.out.println("0");
		}
		return rslt;
	}
	
	public static boolean verifyUser(String nameString) {
		boolean rslt = false;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
	    String sql = null; 
	    
	    String databaseName = Config.MYSQLNAME;
		String host = Config.MYSQLHOST;
		String port = Config.MYSQLPORT;
		String username = Config.USER;
		String sqlpassword = Config.PWD;
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
	  	String connName = dbUrl + serverName + databaseName;
	  	
	  	try {
	  		Class.forName(driverName);
	  		connection = DriverManager.getConnection(connName, username, sqlpassword);
	  	    stmt = connection.createStatement();
		    
		    sql = "SELECT * FROM AlumniInfo where userName='" + nameString + "'";
		    rs=stmt.executeQuery(sql);
		    if (rs.next()) {
				String pass = rs.getString("verify");
				if (pass.equalsIgnoreCase("t")) {
					rslt = true;
				}
		    }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rslt;
	}
//	public static void main(String[] args) {
//		checkPassword("cxfcxf434", "cxfcxf");
//	}
}
