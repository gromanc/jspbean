package jspbean.struts.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.entities.InterceptorConfig;
import com.opensymphony.xwork2.config.entities.InterceptorStackConfig;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.util.TextParseUtil;
import jspbean.hibernate.persistence.User;
import jspbean.hibernate.persistence.UserDAO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.dispatcher.Dispatcher;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationInterceptor implements Interceptor {

  private String par1;

  public AuthenticationInterceptor() {
    System.out.println("AuthenticationInterceptor");
  }

  public String getPar1() {
    return par1;
  }

  @Inject(value = "jspbean.par1", required = false)
  public void setPar1(String par1) {
    this.par1 = par1;
  }

  private String par2;

  public String getPar2() {
    return par2;
  }

  public void setPar2(String par2) {
    this.par2 = par2;
  }

  private final Map<String, String> interceptorConfigs = new HashMap<String, String>();

  public Map<String, String> getInterceptorConfigs() {
      return interceptorConfigs;
  }


  public void addInterceptorConfig(final String configName, final String configValue) {
      interceptorConfigs.put(configName, configValue);
  }

  private UserDAO userDAO;

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void destroy() {
  }

  public void init() {
  }

  public String intercept( ActionInvocation actionInvocation ) throws Exception {

    Map session = actionInvocation.getInvocationContext().getSession();
    List interceptors = actionInvocation.getProxy().getConfig().getInterceptors();
    Map<String, ResultConfig> results =  actionInvocation.getProxy().getConfig().getResults();
    PackageConfig packageConfig = Dispatcher.getInstance().getConfigurationManager().getConfiguration().getPackageConfig("default.package");
    Map<String, Object> interceptorConfigs = packageConfig.getInterceptorConfigs();
    InterceptorConfig interceptorConfig =  (InterceptorConfig)interceptorConfigs.get("authentication");
//    Map<String,String> params = interceptorConfig.getParams();
    Map<String, Object> params = ActionContext.getContext().getParameters();
    String[] s1 = (String[]) params.get("name");
    if (s1 != null ) {
      s1[0] = s1[0].toUpperCase();
    }
    String frob12 = TextParseUtil.translateVariables(this.interceptorConfigs.get("par3"), actionInvocation.getStack());

//    Container container = actionInvocation.getInvocationContext().getContainer();
//    container.setScopeStrategy(new Scope.Strategy() {
//
//      @Override
//      public <T> T findInRequest(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//      }
//
//      @Override
//      public <T> T findInSession(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//      }
//
//      @Override
//      public <T> T findInWizard(Class<T> type, String name, Callable<? extends T> factory) throws Exception {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//      }
//    });

//
//    User user = (User) session.get(Constants.USER);
//    if (user == null) {
//      return Action.LOGIN;
//    } else {
//      Action action = ( Action ) actionInvocation.getAction();
//      if (action instanceof UserAware) {
//        User freshUser = userDAO.findById(user.getId());
//        ((UserAware)action).setUser(freshUser);
//      }
//
//      System.out.println("Logged in: interceptor");
//      String result = actionInvocation.invoke();
    String result = actionInvocation.invoke();
      return  result;
//    }
  }

}
