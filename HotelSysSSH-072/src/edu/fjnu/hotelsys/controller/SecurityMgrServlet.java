package edu.fjnu.hotelsys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fjnu.hotelsys.domain.User;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.UserService;
import edu.fjnu.hotelsys.service.UserServiceImpl;

public class SecurityMgrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SecurityMgrServlet() {
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

		//request.setCharacterEncoding("utf-8");
		
		String act=request.getParameter("act");
		
		if("toLogin".equals(act)){
			request.getRequestDispatcher("jsps/login.jsp").forward(request, response);
		}
		else if("login".equals(act)){
			
			String userNo=request.getParameter("userno");
			String userPwd=request.getParameter("userpwd");
			
			UserService userService=new UserServiceImpl();
			try{
				User user=userService.checkUser(userNo, userPwd);
				request.getSession().setAttribute("loginedUser", user);
			}catch(ApplicationException e){
				request.setAttribute("err", e.getMessage());
				request.getRequestDispatcher("jsps/login.jsp").forward(request, response);
				return;
			}
			
			response.sendRedirect("securityMgr?act=gotoMain");
		}
		else if("gotoMain".equals(act)){
			request.getRequestDispatcher("jsps/main.jsp").forward(request, response);
		}
		else if("logout".equals(act)){
			request.getSession().removeAttribute("loginedUser");
			request.getSession().invalidate();
			response.sendRedirect("securityMgr?act=toLogin");
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       doGet(request,response);
	}

}
