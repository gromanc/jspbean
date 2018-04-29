package jspbean.struts.factory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.inject.Scope;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.Map;
import java.util.concurrent.Callable;

public class MyScopeStrategy implements Scope.Strategy {

  @Override
  public <T> T findInRequest(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
    return null;
  }

  @Override
  public <T> T findInSession(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
    ActionContext context = ActionContext.getContext();
    SessionMap<String, T> sessionMap = (SessionMap<String, T>) context.getSession();
    if (sessionMap == null) {
      sessionMap = new SessionMap<String, T>(ServletActionContext.getRequest());
      context.setSession((Map<String, Object>) sessionMap);
    }
    T obj = sessionMap.get(name);
    if (obj == null){
      obj =  factory.call();
      sessionMap.put(name, obj);
    }
    return obj;
  }

  @Override
  public <T> T findInWizard(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
    return null;
  }

}

