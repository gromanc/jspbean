package jspbean.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.freemarker.FreemarkerResult;

import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Action(value="freemarkeraction", results = {
  @Result(location = "/echo.jsp")
})
public class FreemarkerAction extends ActionSupport {
  String echo;
  boolean escape = false;

  public boolean isEscape() {
    return escape;
  }

  public void setEscape(boolean escape) {
    this.escape = escape;
  }

  public String getEcho() {
    return echo;
  }

  public void setEcho(String echo) {
    this.echo = echo;
  }

  public String getTodayDate() {
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
      return sdf.format(new Date());
  }

  public String getTodayTime() {
      SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
      return sdf.format(new Date());
  }

  public String execute() throws Exception {
    FreemarkerResult fr = new FreemarkerResult("/SendEmail.ftl");
    ActionContext.getContext().getContainer().inject(fr);
    Writer out = new StringWriter();
    fr.setWriter(out);
    Map<String, Object> session = ActionContext.getContext().getSession();
    session.put("todayDate", getTodayDate());
    fr.execute(ActionContext.getContext().getActionInvocation());
    echo = out.toString();
    return SUCCESS;
  }
}
