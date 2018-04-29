package jspbean.struts;

import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


//@Component
//@Scope("session")
//@Scoped(Scope.SESSION)
public class Session  {
  private Map<String, Object> map = new HashMap<>();

  public Session() {
    System.out.println("!!! Session !!!");
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}
