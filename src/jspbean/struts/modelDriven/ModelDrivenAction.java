
package jspbean.struts.modelDriven;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import jspbean.struts.Question;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.JSONPopulator;
import org.apache.struts2.json.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action to demonstrate simple model-driven feature of the framework.
 */
@Namespace("/modelDriven")
public class ModelDrivenAction extends ActionSupport implements ModelDriven<Gangster>, Preparable {

	private static final long serialVersionUID = 1271130427666936592L;

  private Gangster model = new Gangster();


  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @VisitorFieldValidator(appendPrefix = false)
  public Gangster getModel() {
 		return model;
 	}

//  public void setModel(Gangster model) {
//    this.model = model;
//  }


  private Map<Long, Question> questionMap = new HashMap<>();

  public Map<Long, Question> getQuestionMap() {
    return questionMap;
  }

  private Long questionId;

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  private List<Long> questionIds;

  public List<Long> getQuestionIds() {
    return questionIds;
  }

  public void setQuestionIds(List<Long> questionIds) {
    this.questionIds = questionIds;
  }

  private String question;

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  private Map<String, Question> myMap = new HashMap<>();

  public Map<String, Question> getMyMap() {
    return myMap;
  }

  public void setMyMap(Map<String, Question> myMap) {
    this.myMap = myMap;
  }

  public String input() throws Exception {
    System.out.println("Method input executed");
      return INPUT;
  }

  public String doDefault() throws Exception {
    System.out.println("Method doDefault executed");
    return SUCCESS;
  }

  public void validate(){
    System.out.println("validate");
  }


//  @Validations(fieldExpressions = @FieldExpressionValidator(fieldName = "bustedBefore", expression="bustedBefore == true", message = "bustedBefore should be set"))
    @SkipValidation
    @Action(value="modelDriven", results={
        @Result(location = "/modelDriven/modelDriven.jsp")
    })
   	public String execute() throws Exception {
		return SUCCESS;
	}

    @SkipValidation
    @Action(value="modelDrivenResult", results={
        @Result(location = "/modelDriven/modelDrivenResult.jsp"),
        @Result(name="input", location = "/modelDriven/modelDriven.jsp")
    }, interceptorRefs = {@InterceptorRef(value="defaultStack", params={
    //        "params.acceptParamNames", "(\\[\\d+\\]\\.)*\\w+((\\.\\w+)|(\\[\\d+\\])|(\\(\\d+\\))|(\\['\\w+'\\])|(\\('\\w+'\\)))*"
        "checkbox.uncheckedValue", "No"}),@InterceptorRef(value="json")}
    )
    public String result() throws Exception {
//      HttpServletRequest request = ServletActionContext.getRequest();
//      String json = IOUtils.toString(request.getInputStream());

//      Object obj = JSONUtil.deserialize(request.getReader());
//      if (obj instanceof Map) {
//        Map json = (Map) obj;
//
//        // clean up the values
//
//        // populate fields
//        JSONPopulator populator = new JSONPopulator();
//        populator.populateObject(model, json);
//      }

      return SUCCESS;
  }

  @Override
  public void prepare() throws Exception {
//    questionList.add(new Question(1l, "Question1", "ans1", "ans2","ans3"));
//    questionList.add(new Question(2l, "Question2", "ans1", "ans2","ans3"));
//    myMap.put("q1", new Question(1l, "Question1", "ans1", "ans2","ans3"));
//    myMap.put("q2", new Question(2l, "Question2", "ans1", "ans2","ans3"));

    questionMap.put(1l, new Question(1l, "Question1", "ans1", "ans2","ans3"));
    questionMap.put(2l, new Question(2l, "Question2", "ans1", "ans2","ans3"));
  }
}
