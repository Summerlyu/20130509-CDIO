/**
 * 
 */
package edu.fjnu.hotelsys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fjnu.hotelsys.exception.DataAccessException;

/**
 * @author ctd
 *
 */
public class DBUtils {
	
	//用来把Connection绑定到当前线程上的变量
	private static ThreadLocal<Connection> threadStore = new ThreadLocal<Connection>();	
		
	private static DBUtils me=new DBUtils();
	
	private DBUtils() {}
	
	public static DBUtils getInstance()
	{
		return me;
	}
	
	static {
		//查看类路径中是否存在这个驱动入口类
		try {
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public synchronized Connection getConn() {

		Connection conn = threadStore.get();

		if (null == conn) {
			try {
				conn = DriverManager.getConnection("proxool.hotelsys-ds");
				threadStore.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return conn;

	}
	
	/**
	 * 释放数据库资源
	 * @param conn
	 * @param pstmt
	 * @param rset
	 */
	public void ReleaseRes(Connection conn,PreparedStatement pstmt,ResultSet rset)
	{
		try{
		  //if(rset!=null) rset.close();
		  if(pstmt!=null) pstmt.close();
		  if(conn!=null)  conn.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 释放连接
	 * @param conn
	 */
	public synchronized void releaseConn(Connection conn){
		
		if(null!=conn){
			threadStore.remove();
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DataAccessException (e.getMessage());
			}
		}
		
	}
	
	
	

}
