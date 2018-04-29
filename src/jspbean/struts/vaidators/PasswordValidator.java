package jspbean.struts.vaidators;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.ValidatorSupport;

public class PasswordValidator extends ValidatorSupport {

  private String username = null;

  public String getUsername() {
    return username;
  }
  public void setUsername(String value) {
    username = value;
  }

  private String password = null;

  public String getPassword() {
    return password;
  }

  public void setPassword(String value) {
    password = value;
  }

  @Override
  public void validate(Object object) throws ValidationException {
    System.out.println("Validating "+object);
  }
}
