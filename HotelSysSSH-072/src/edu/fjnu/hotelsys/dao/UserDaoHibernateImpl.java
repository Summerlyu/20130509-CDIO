/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.fjnu.hotelsys.domain.User;

/**
 * @author Administrator
 *
 */
public class UserDaoHibernateImpl extends HibernateDaoSupport implements UserDao {

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.UserDao#getUserByNo(java.lang.String)
	 */
	@Override
	public User getUserByNo(String userNo) {
		return this.getHibernateTemplate().get(User.class, userNo);
	}

}
