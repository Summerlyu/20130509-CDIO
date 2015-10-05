/**
 * 
 */
package edu.fjnu.hotelsys.service;

import edu.fjnu.hotelsys.dao.UserDao;
import edu.fjnu.hotelsys.dao.UserDaoJDBCImpl;
import edu.fjnu.hotelsys.domain.User;
import edu.fjnu.hotelsys.exception.ApplicationException;

/**
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao=null;
	
	//注入依赖  (DI: Dependency Injection)
	//反转控制 (IOC) 
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.service.UserService#checkUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User checkUser(String userNo, String userPwd) {
		
		//UserDao userDao=new UserDaoJDBCImpl(); 主动控制
		
		User user=userDao.getUserByNo(userNo);
		
		if(!user.getUserPwd().equals(userPwd))
			 throw new ApplicationException("用户密码错误，请检查!");
		
		
		return user;
	}

}
