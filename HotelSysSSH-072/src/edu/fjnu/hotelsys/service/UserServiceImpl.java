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
	
	//ע������  (DI: Dependency Injection)
	//��ת���� (IOC) 
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.service.UserService#checkUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User checkUser(String userNo, String userPwd) {
		
		//UserDao userDao=new UserDaoJDBCImpl(); ��������
		
		User user=userDao.getUserByNo(userNo);
		
		if(!user.getUserPwd().equals(userPwd))
			 throw new ApplicationException("�û������������!");
		
		
		return user;
	}

}
