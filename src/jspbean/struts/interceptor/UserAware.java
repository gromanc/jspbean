package jspbean.struts.interceptor;


/*
 * Simple interface for actions that want have the user object injected.  
 */

import jspbean.hibernate.persistence.User;

public interface UserAware {
	
	public void setUser(User user);
	
}