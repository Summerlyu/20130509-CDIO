package edu.fjnu.hotelsys.utils;

import java.sql.Connection;
import java.sql.SQLException;

import edu.fjnu.hotelsys.exception.DataAccessException;

public class TransactionManager {
	
	private Connection conn;
	
	public TransactionManager() {
		this.conn = DBUtils.getInstance().getConn();
		//System.out.println("transactionmanger:"+conn);
	}

	/**
	 * 开启事务
	 * @throws DAOException
	 */
	public void beginTransaction(){
		try {
			 //把事务提交方式改为手工提交
			 conn.setAutoCommit(false); 
		} catch (SQLException e) {
			throw new DataAccessException("开启事务时出现异常",e);
		}
	}
	
	/**
	 * 提交事务
	 * @throws DAOException
	 */
	public void commitAndClose(){
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new DataAccessException("提交事务时出现异常",e);
		}finally{
			DBUtils.getInstance().releaseConn(conn);
		}
	}
	
	/**
	 * 回滚事务
	 * @throws DAOException
	 */
	public void rollbackAndClose(){
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DataAccessException("回滚事务时出现异常",e);
		}finally{
			DBUtils.getInstance().releaseConn(conn);
		}
	}
	
	
}





















