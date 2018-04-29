package jspbean.struts;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class Datepicker extends ActionSupport {

  private static final long serialVersionUID = 7641453994518254115L;

  private Date              dateValue;
  private Date              nameValue;
  private Date              minValue;
  private Date              maxValue;

  private Date              date0;

  public Date getDateValue()
  {
    return dateValue;
  }

  public Date getNameValue()
  {
    return nameValue;
  }

  public Date getMinValue()
  {
    return minValue;
  }

  public Date getMaxValue()
  {
    return maxValue;
  }

  public Date getDate0() {
    return date0;
  }

  public void setDate0(Date date0) {
    this.date0 = date0;
  }

  @Action(value = "datepicker", results = {
    @Result(location = "/datepicker.jsp", name = "success")
  }, interceptorRefs = {@InterceptorRef("token"), @InterceptorRef("defaultStack")})
  public String execute() throws Exception
  {

    Calendar c = Calendar.getInstance();
    c.roll(Calendar.WEEK_OF_YEAR, -1);

    dateValue = c.getTime();

    c.roll(Calendar.MONTH, -1);

    nameValue = c.getTime();

    c.setTime(new Date());
    c.roll(Calendar.MONTH, -1);
    minValue = c.getTime();

    c.roll(Calendar.MONTH, 2);
    maxValue = c.getTime();

    return SUCCESS;
  }

  @Action(value="savepicker", results = {
    @Result(name="input", location = "/detepicker.jsp"),
    @Result(name="success", type="redirectAction", location = "datepicker")

  },interceptorRefs = @InterceptorRef(value="appDefaultStack", params = {"validation.validateAnnotatedMethodOnly", "true"}))
  public String save() throws SQLException {
    return SUCCESS;
  }

}
