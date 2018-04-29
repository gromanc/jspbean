package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;

import java.util.List;

/**
 * Created by Roma on 28.09.2014.
 */
@Result(location = "/checkbox-list.jsp", name = "success")
public class CheckboxListAction extends ActionSupport {
  List<String> myFruits;
  public List<String> getMyFruits() {
    return myFruits;
  }

  public void setMyFruits(List<String> myFruits) {
    this.myFruits = myFruits;
  }

}
