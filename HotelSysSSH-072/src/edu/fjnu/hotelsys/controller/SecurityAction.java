/**
 * 
 */
package edu.fjnu.hotelsys.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.fjnu.hotelsys.domain.User;
import edu.fjnu.hotelsys.exception.ApplicationException;
import edu.fjnu.hotelsys.service.UserService;
import edu.fjnu.hotelsys.service.UserServiceImpl;

/**
 * @author Administrator
 *
 */
public class SecurityAction extends BaseAction implements SessionAware{
	
	private Map<String, Object> session;
	private User user;
	private UserService userService=null;
		
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	//一个struts2 action 可以做大量的业务工作，这些操作往往都分散在action类的不同的方法中，每个方法都有结构几乎相同
	//的签名，比如：public String processMethodName(),系统根据返回的字符串，到配置中去查找对应的result.

	//执行一个action,默认就是调用这个action的execute方法1次。
//	@Override
//	public String execute(){
//		//System.out.println("securityAction execute now!");
//		return "loginPage";
//	}
	

	public String gotoLogin(){
		//System.out.println("securityAction execute now!");
		return "loginPage";
	}	
	
	public String login(){
		
//		UserService userService=new UserServiceImpl();
		
//		user.setUserNo("fjkaldjfl");
//		return "loginPage";
		
		try{
			User detailUser=userService.checkUser(user.getUserNo(), user.getUserPwd());
			session.put("loginedUser", detailUser);
		}catch(ApplicationException e){
			//struts action提供了内存数据存储机制，用于记录异常信息。
			//1. 如果当个表单域发生异常，可以使用addFieldError(fieldName,errMsg);
			//2. 如果整个action出现错误，与具体表单域无关，则使用addActionError来记录错误信息。
            this.addActionError(e.getMessage());
   		    return "loginPage";
		}
		return "mainAction";
	}

	public String gotoMain(){
		return "mainPage";
	}
	
	public String logout(){
		session.remove("loginedUser");
		session=null;
		return "gotoLoginAction";
	}
	
	@Override
	public void setSession(Map<String, Object> session ) {
		this.session=session;
	}
	
	
   
}
