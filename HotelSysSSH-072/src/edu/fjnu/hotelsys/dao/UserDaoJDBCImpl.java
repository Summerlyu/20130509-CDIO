/**
 * 
 */
package edu.fjnu.hotelsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hotelsys.domain.Room;
import edu.fjnu.hotelsys.domain.User;
import edu.fjnu.hotelsys.exception.DataAccessException;
import edu.fjnu.hotelsys.utils.DBUtils;

/**
 * @author Administrator
 *
 */
public class UserDaoJDBCImpl implements UserDao {
	
	private static final String GET_USER_BYNO="select * from users where user_no=?";

	/* (non-Javadoc)
	 * @see edu.fjnu.hotelsys.dao.UserDao#getUserByNo(java.lang.String)
	 */
	@Override
	public User getUserByNo(String userNo) {
		
		Connection conn=DBUtils.getInstance().getConn();
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		User user=null;
		
		try {
			
		  pstmt=conn.prepareStatement(GET_USER_BYNO);
		  pstmt.setString(1, userNo);
		  rset=pstmt.executeQuery();
		  
		  if(rset.next()){
			 user=new User();
			 user.setUserNo(rset.getString("user_no"));
			 user.setUserName(rset.getString("user_name"));
			 user.setUserPwd(rset.getString("user_pwd"));
		  }
		  else
			 throw new DataAccessException("id为"+userNo+"的用户不存在，请检查!");
		  
 		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException("id为"+userNo+"的用户信息获取失败!");
		} finally{
		   DBUtils.getInstance().ReleaseRes(null, pstmt, rset);
		}
		
		return user;
	}

}
