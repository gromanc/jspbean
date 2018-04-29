package jspbean.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import nl.captcha.Captcha;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterAction extends ActionSupport {
  private String userId;
  private String userEmail1;
  private String userEmail2;
  private String userPassword1;
  private String userPassword2;
  private String captchaResponse;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserEmail1() {
    return userEmail1;
  }

  public void setUserEmail1(String userEmail1) {
    this.userEmail1 = userEmail1;
  }

  public String getUserEmail2() {
    return userEmail2;
  }

  public void setUserEmail2(String userEmail2) {
    this.userEmail2 = userEmail2;
  }

  public String getUserPassword1() {
    return userPassword1;
  }

  public void setUserPassword1(String userPassword1) {
    this.userPassword1 = userPassword1;
  }

  public String getUserPassword2() {
    return userPassword2;
  }

  public void setUserPassword2(String userPassword2) {
    this.userPassword2 = userPassword2;
  }

  public String getCaptchaResponse() {
    return captchaResponse;
  }

  public void setCaptchaResponse(String captchaResponse) {
    this.captchaResponse = captchaResponse;
  }

  private InputStream inputStream;

  //getter here
  public InputStream getInputStream() {
    return inputStream;
  }

  public String create() {
    //RegisterAction is the form bean of the current action and captcha response is the field of user input

    String answer = (String) ActionContext.getContext().getSession().get("CorrectAnswer");
    if (answer == null || getCaptchaResponse()==null || !answer.equals(getCaptchaResponse())){
      addFieldError("captchaResponse", getText("error.captcha"));
    }
    return SUCCESS;
  }

  @Action(value = "captcha", results = {@Result(type="stream", params = {"contentType", "image/jpeg"})})
  public String captcha() {
    try {
      Captcha captcha = new Captcha.Builder(200, 50).addText(new DefaultTextProducer()).gimp(new DropShadowGimpyRenderer()).build();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      //write the image
      CaptchaServletUtil.writeImage(outputStream, captcha.getImage());
      //store the answer for this in session
      ActionContext.getContext().getSession().put("CorrectAnswer", captcha.getAnswer());
      //return image
      inputStream = new ByteArrayInputStream(outputStream.toByteArray());
      return SUCCESS;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

}
