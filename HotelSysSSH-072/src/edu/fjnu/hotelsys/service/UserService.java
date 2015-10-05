/**
 * 
 */
package edu.fjnu.hotelsys.service;

import edu.fjnu.hotelsys.domain.User;

/**
 * @author Administrator
 *
 */
public interface UserService {
	
	User checkUser(String userNo,String userPwd);

}
