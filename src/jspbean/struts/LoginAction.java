package jspbean.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.ValidationParameter;
import com.opensymphony.xwork2.validator.annotations.Validations;
import jspbean.hibernate.persistence.*;
import jspbean.struts.interceptor.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p> Validate a user login. </p>
 */
@Validations(customValidators = @CustomValidator(type="passwordValidator", message="the password is failed",
  parameters = { @ValidationParameter( name = "password", value = "${password}" ), @ValidationParameter( name = "username", value = "${username}" )}))
@Actions({
  @Action(value = "ajaxLogin", results = {
    @Result(name = "input", location="/pages/ajaxlogin.jsp"),
    @Result(name = "error", location="/pages/ajaxResult.jsp"),
    @Result(location = "/pages/ajaxloginsuccess.jsp")
  }, interceptorRefs = @InterceptorRef("validateWorkflowStack")),
  @Action(value = "ajaxLogin/*/*", params = {"username", "{1}", "password", "{2}" }, results = {
    @Result(name = "input", location="/pages/ajaxlogin.jsp"),
    @Result(name = "error", location="/pages/ajaxResult.jsp"),
    @Result(location = "/pages/ajaxloginsuccess.jsp")
  }, interceptorRefs = @InterceptorRef("validateWorkflowStack"))
})
public class LoginAction extends ActionSupport {
  private static final Log log = LogFactory.getLog(LoginAction.class);

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

  private UserDAO userDAO;

  @Autowired
  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  private UserCredentialDAO userCredentialDAO;

  @Autowired
  public void setUserCredentialDAO(UserCredentialDAO userCredentialDAO) {
    this.userCredentialDAO = userCredentialDAO;
  }

  private CredentialDAO credentialDAO;

  @Autowired
  public void setCredentialDAO(CredentialDAO credentialDAO) {
    this.credentialDAO = credentialDAO;
  }

  public String execute() {
    try {
    List<User> users = userDAO.getUsers();
    System.out.println("Validating login");
    List<Credential> list = credentialDAO.findByProperty(CredentialDAO.USERNAME, username);
    if(list.isEmpty()) {
      addActionError(getText("error.login"));
      return ERROR;
    }
    for (Credential credential : list){
      if(username.equals(credential.getUsername()) && password.equals(credential.getPassword())){
        Set<UserCredential> set = credentialDAO.getUserCredentials(credential);
        for (UserCredential uc: set){
          User u = userDAO.findById(uc.getUser().getId());
          Map<String, Object> session = ActionContext.getContext().getSession();
          session.put(Constants.USER, u);
          System.out.println("User "+ u.getName()+ "is logged in");
        }
        return SUCCESS;
      }
    }
    addActionError(getText("error.login"));
    return ERROR;
    } catch (Exception e) {
      log.error("LoginAction.execute failed", e);
      throw e;
    }
  }

  public void validate(){
    System.out.println("validate ");
  }

}