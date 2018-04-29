package jspbean.struts;

import com.opensymphony.xwork2.Action;

public class AdminAction implements Action {
  @Override
  public String execute() throws Exception {
    System.out.println("AdminAction executed");
    return SUCCESS;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
