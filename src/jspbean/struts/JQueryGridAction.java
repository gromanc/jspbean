package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Actions({
  @Action(value = "jqueryGrid", results = {
    @Result(location="/pages/jqueryGrid.jsp"),
    @Result(name = "error", location="/pages/ajaxResult.jsp")
  }),
  @Action(value = "extjsGrid", results = {
    @Result(location="/pages/extjsGrid.jsp"),
    @Result(name = "error", location="/pages/ajaxResult.jsp"),
  })

})
public class JQueryGridAction  extends ActionSupport {

  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String execute() {

//    HttpServletRequest request = ServletActionContext.getRequest();
//    HttpServletResponse response = ServletActionContext.getResponse();
//    try {
////      request.getRequestDispatcher("showAjaxLoginForm").forward(request, response);
//      ServletActionContext.getServletContext().getRequestDispatcher("/showAjaxLoginForm").forward(request, response);
//    } catch (ServletException e) {
//      e.printStackTrace();  //Todo change body of catch statement.
//    } catch (IOException e) {
//      e.printStackTrace();  //Todo change body of catch statement.
//    }
//    return NONE;
    return SUCCESS;
  }

}
