package jspbean.struts.modelDriven;

import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import jspbean.struts.Question;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A model class to be used by the simple Model-Driven example.
 */
@Component
public class Gangster implements Serializable {

	private static final long serialVersionUID = 3688389475320294992L;

	private String name;
	private Integer age;
	private String description;
	private String bustedBefore;

  private Gangster2 me;

  public Gangster2 getMe() {
    return me;
  }

  public void setMe(Gangster2 me) {
    this.me = me;
  }

  public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getBustedBefore() {
		return bustedBefore;
	}

	public void setBustedBefore(String bustedBefore) {
		this.bustedBefore = bustedBefore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

  @RequiredStringValidator(message = "The field 'Name' is empty", type = ValidatorType.FIELD)
	public void setName(String name) {
		this.name = name;
	}

	private List<Question> questionList = new ArrayList<>();

	public List<Question> getQuestionList(){
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	private String par1;

	public String getPar1() {
		return par1;
	}

	public void setPar1(String par1) {
		this.par1 = par1;
	}

}
