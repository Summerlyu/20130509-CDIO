package edu.fjnu.hotelsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fjnu.hotelsys.utils.DBUtils;
import edu.fjnu.hotelsys.utils.DBUtilsOld;

public class CPTest extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CPTest() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		long begin,end;
		Connection conn=null;
		
		begin=System.currentTimeMillis();
		for(int i=1;i<=100;i++){
			conn=DBUtilsOld.getInstance().getConn();
			DBUtilsOld.getInstance().ReleaseRes(conn, null, null);
		}
		
		end=System.currentTimeMillis();
		
		System.out.println("pure jdbc connection cost time:"+(end-begin));
		
		
		begin=System.currentTimeMillis();
		for(int i=1;i<=100;i++){
			conn=DBUtils.getInstance().getConn();
			DBUtils.getInstance().ReleaseRes(conn, null, null);
		}
		
		end=System.currentTimeMillis();
		
		System.out.println("proxool connection cost time:"+(end-begin));		
		
		
		

	}

}
