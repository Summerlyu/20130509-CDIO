/**
 * 
 */
package edu.fjnu.hotelsys.domain;

/**
 * @author ctd
 * 
 */
public class User extends ValueObject {

	private String userNo;
	private String userName;
	private String userPwd;

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
