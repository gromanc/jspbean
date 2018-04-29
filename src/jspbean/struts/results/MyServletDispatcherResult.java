package jspbean.struts.results;

import org.apache.struts2.dispatcher.ServletDispatcherResult;

public class MyServletDispatcherResult extends ServletDispatcherResult {
  private String customParam;

  public String getCustomParam() {
    return customParam;
  }

  public void setCustomParam(String customParam) {
    this.customParam = customParam;
  }
}
