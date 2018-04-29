package jspbean.struts.vaidators;

import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.ValidatorSupport;

public class RetypeValidator extends ValidatorSupport {

  private String value = null;

  public RetypeValidator() {
    System.out.println("RetypeValidator: "+this);
  }

  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  private String retypeValue = null;

  public String getRetypeValue() {
    return retypeValue;
  }

  public void setRetypeValue(String value) {
    retypeValue = value;
  }

  @Override
  public void validate(Object object) throws ValidationException {
    System.out.println("Validating "+object);
    String value = (String) parse(this.value, String.class);
    String retypeValue = (String) parse(this.retypeValue, String.class);
    if (value != null && retypeValue != null && !value.equals(retypeValue))
      addActionError(getDefaultMessage());
  }
}
