package jspbean.struts.factory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.DefaultActionProxy;

public class MyActionProxy extends DefaultActionProxy {

  protected MyActionProxy(ActionInvocation inv, String namespace, String actionName, String methodName, boolean executeResult, boolean cleanupContext) {
    super(inv, namespace, actionName, methodName, executeResult, cleanupContext);
  }

  @Override
  protected void prepare() {
    super.prepare();
  }
}
