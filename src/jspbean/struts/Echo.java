package jspbean.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class Echo extends ActionSupport {

  private static final long serialVersionUID = 7968544374444173511L;
  private static final Log  log              = LogFactory.getLog(Echo.class);

  private String            echo;
  private boolean           escape           = true;

  @Action(value = "echo", results = {
    @Result(location = "/echo.jsp", name = "success")
  })
  public String execute() throws Exception
  {

    log.info("Echo : " + echo + ", escape : "+escape);

    return SUCCESS;
  }

  public String getEcho()
  {
    return echo;
  }

  public void setEcho(String echo)
  {
    this.echo = echo;
  }

  public boolean isEscape()
  {
    return escape;
  }

  public void setEscape(boolean escape)
  {
    this.escape = escape;
  }
}
