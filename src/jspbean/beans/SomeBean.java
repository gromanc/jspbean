package jspbean.beans;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SomeBean {
  String punchOutRes = null;
  HttpServletResponse response;
  HttpServletRequest request;
  SSL ssl;

  private String[] Centres = new String[]{"-1","-1","-1","-1","-1","-1"};
  public String[] getCentres(){
    return Centres;
  }
  public void setCentres(String[] Centres){
    this.Centres = Centres;
  }
  public static enum Type {
      ROOT(0),RELAY(1),LEAF(2),NULL(3);

      private int value;

      Type(int value){
          this.value = value;
      }

      public int getValue(){
          return value;
      }
  }

  public SSL getSsl() {
    return ssl;
  }

  public void setSsl(SSL ssl) {
    this.ssl = ssl;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public void setResponse(HttpServletResponse response) {
    this.response = response;
    try {
      response.getWriter().println("Some Output");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
//    request.setAttribute("locale", request.getLocale().toString());
  }

  public void setPunchOutRes(String punchOutRes) {
    this.punchOutRes = punchOutRes;
  }

  public String getPunchOutRes() {
//    response.setContentType("text/xml");
    return punchOutRes;
  }

}
