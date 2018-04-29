package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Action(value = "some", results = {
  @Result(type = "redirect", location="/")
})
public class SomeAction extends ActionSupport implements SessionAware,
  Serializable,
  ServletRequestAware,
  ServletResponseAware, ModelDriven<AbstractForm> {
  InputForm inputForm = new InputForm();
  CopyForm copyForm = new CopyForm();

  private ModelDriven<InputForm> inputModelDriven = new ModelDriven<InputForm>() {
      public InputForm getModel() {
        return inputForm;
      }
  };

  private ModelDriven<CopyForm> copyModelDriven = new ModelDriven<CopyForm>() {
      public CopyForm getModel() {
        return copyForm;
      }
  };

  @Override
  public AbstractForm getModel() {
    return inputModelDriven.getModel();  //To change body of implemented methods use File | Settings | File Templates.
  }

  public ModelDriven<InputForm> getInputModelDriven() {
    return inputModelDriven;
  }

  public ModelDriven<CopyForm> getCopyModelDriven() {
    return copyModelDriven;
  }

  public class SessionHelper {
      public void setUserSessionInfo(Map<String, Object> sessionMap,
                                     Map<String, Object> userMap,
                                     HttpServletRequest request) throws Exception {
          Object userId = userMap.get("userid");
          sessionMap.put("userid", 1);
      }
  }

  HttpServletRequest request;

  @Override
  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
  }


  HttpServletResponse response;

  @Override
  public void setServletResponse(HttpServletResponse response) {
    this.response = response;
  }

  Map<String, Object> session;

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public String execute() throws Exception {
    HttpSession httpSession = ServletActionContext.getRequest().getSession();
    HashMap<String, Object> attributes = new HashMap<String, Object>();
    Enumeration<String> enames = httpSession.getAttributeNames();
    while ( enames.hasMoreElements() ) {
        String name = enames.nextElement();
        if ( !name.equals( "JSESSIONID" ) ) {
            attributes.put( name, httpSession .getAttribute( name ) );
        }
    }
    SessionMap<String, Object> sessionMap  = ((SessionMap)session);
    sessionMap.invalidate();
    sessionMap.put("someAttribute", null);
    sessionMap.remove("someAttribute");
    sessionMap.entrySet();

    HashMap<String, Object> userInfoMap = new HashMap<String, Object>();
    for ( Map.Entry<String, Object> et : attributes.entrySet() ) {
        userInfoMap.put( et.getKey(), et.getValue() );
    }
    SessionHelper sessionHelper = new SessionHelper();
    sessionHelper.setUserSessionInfo(session, userInfoMap, request);
    return SUCCESS;
  }
}
