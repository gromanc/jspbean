package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Act.java
 */
public class Act extends ActionSupport {
  private String object;

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

  @Override
  public String execute(){
    return SUCCESS;
  }
}
