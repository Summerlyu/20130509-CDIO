/**
 * 
 */
package edu.fjnu.hotelsys.exception;

/**
 * @author Administrator
 * hotelsys 自定义异常的基类，在系统的多个层之间只能传送的是自定义异常，而不能传送依赖某种技术的专有异常。
 */
public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	public ApplicationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
