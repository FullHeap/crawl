package com.etoak.crawl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtil {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.1.5:1521:orcl";
	private static final String USER = "abs";
	private static final String PWD = "abs";
	
	
	/*private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1/jsj";
	private static final String USER = "root";
	private static final String PWD = "root";*/
	
	//直接连接数据库
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PWD);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
	
	//服务器连接池连接数据库
	public static Connection getConnectionX(){
		Connection conn = null;
		try{
			Context cx = new InitialContext();
			DataSource ds = (DataSource)cx.lookup("java:comp/env/lyz");
			conn = ds.getConnection();
			
		}catch(Exception e){
			System.out.println("数据库连接异常！");
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭所有连接Statement版
	public static void closeAll
	(Connection conn,Statement stmt,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!= null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	
}
