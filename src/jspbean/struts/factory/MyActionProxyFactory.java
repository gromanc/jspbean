package jspbean.struts.factory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.DefaultActionProxyFactory;

public class MyActionProxyFactory extends DefaultActionProxyFactory {

  public MyActionProxyFactory() {
    super();
  }

  @Override
  public ActionProxy createActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName, boolean executeResult, boolean cleanupContext) {

    MyActionProxy proxy = new MyActionProxy(inv, namespace, actionName, methodName, executeResult, cleanupContext);
    container.inject(proxy);
    container.setScopeStrategy(new MyScopeStrategy());
    proxy.prepare();
    return proxy;
  }

}
