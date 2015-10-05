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
	
	//һ��struts2 action ������������ҵ��������Щ������������ɢ��action��Ĳ�ͬ�ķ����У�ÿ���������нṹ������ͬ
	//��ǩ�������磺public String processMethodName(),ϵͳ���ݷ��ص��ַ�������������ȥ���Ҷ�Ӧ��result.

	//ִ��һ��action,Ĭ�Ͼ��ǵ������action��execute����1�Ρ�
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
			//struts action�ṩ���ڴ����ݴ洢���ƣ����ڼ�¼�쳣��Ϣ��
			//1. ��������������쳣������ʹ��addFieldError(fieldName,errMsg);
			//2. �������action���ִ������������޹أ���ʹ��addActionError����¼������Ϣ��
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
