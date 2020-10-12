package cn.hsx.demo;

import java.util.Date;

public class Customer {
	private String userId;
	private String userName;
	private String userPassword;
	private Date creYmd;

	public Customer() {

	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", creYmd="
				+ creYmd + "]";
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            セットする userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return creYmd
	 */
	public Date getCreYmd() {
		return creYmd;
	}

	/**
	 * @param creYmd
	 *            セットする creYmd
	 */
	public void setCreYmd(Date creYmd) {
		this.creYmd = creYmd;
	}

	public static void main(String[] args)  {

		System.out.println("hello world...");
	}
}
