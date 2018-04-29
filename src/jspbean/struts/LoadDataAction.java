package jspbean.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

@Action(value="load-on-scroll-example", results = {
  @Result(location = "/LoadData.jsp")
})
public class LoadDataAction extends ActionSupport {
  private static int COUNT = 6;

  private InputStream inputStream;

  //getter here
  public InputStream getInputStream() {
    return inputStream;
  }

  @Action(value="load-data", results = {
    @Result(type="stream", params = {"contentType", "text/html"})
  })
  public String loadData() throws Exception {
    String resp = "";
    Map<String, Object> session = ActionContext.getContext().getSession();
    int counter = session.get("counter")== null?COUNT:(int)session.get("counter");
    for(int i = 0; i <= 10; i++) {
      resp += "<p><span>"  + counter++ +
        "</span> This content is dynamically appended " +
        "to the existing content on scrolling.</p>" ;
    }
    session.put("counter", counter);
    inputStream = new ByteArrayInputStream(resp.getBytes());
    return SUCCESS;
  }
}
