/**
 * 
 */
package edu.fjnu.hotelsys.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author Administrator
 *
 */
public class AuthenticationInterceptor implements Interceptor {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String,Object> session=invocation.getInvocationContext().getSession();
		
		Object user=session.get("loginedUser");
		
		boolean isAuthenticated=(null==user)?false:true;
		
		if(isAuthenticated)
			 invocation.invoke();
		else
			 return "gotoLoginAction";
		
		return null;
	}

}
