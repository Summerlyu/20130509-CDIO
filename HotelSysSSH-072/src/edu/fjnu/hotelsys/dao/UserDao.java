/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import edu.fjnu.hotelsys.domain.User;

/**
 * @author Administrator
 *
 */
public interface UserDao {
	
	User getUserByNo(String userNo);

}
