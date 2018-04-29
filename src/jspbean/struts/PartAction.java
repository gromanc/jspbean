package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PartAction extends ActionSupport {


  private InputStream stream;

  //getter here
  public InputStream getStream() {
    return stream;
  }

  private List<SearchResult> findList = new ArrayList<>();

  public List<SearchResult> getFindList() {
    return findList;
  }

  public void setFindList(List<SearchResult> findList) {
    this.findList = findList;
  }

  private String list() {
    JSONObject jo = new JSONObject();
    try {
      for (SearchResult part : findList) {
        jo.put("col1", part.getCol1());
        jo.put("col2", part.getCol2());
      }
      System.out.println("--------->:"+jo.toString());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return jo.toString();
  }

  @Action(value="part", interceptorRefs = {@InterceptorRef("json"),@InterceptorRef("defaultStack")},results = {
    @Result(name="stream", type="stream", params = {"contentType", "text/html", "inputName", "stream"}),
    @Result(name="stream2", type="stream", params = {"contentType", "application/json", "inputName", "stream"}),
    @Result(name="json", type="json", params={"root", "findList"})

  })
  public String finder() {
    findList.add(new SearchResult("val1", "val2"));
    stream = new ByteArrayInputStream(list().getBytes());
    return "json";
  }
}
