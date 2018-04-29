package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import jspbean.model.ListValue;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Action(value="multiselect", interceptorRefs = {@InterceptorRef("json"),@InterceptorRef("defaultStack")},
  results=@Result(location="/pages/multiselect.jsp"))
public class MultiselectSample extends ActionSupport {

  List<String> languageList = new ArrayList<>();
  List<ListValue> languageObjList = new ArrayList<>();
  Map<String, String> languageMap = new HashMap<>();

  public List<String> getLanguageList() {
    return languageList;
  }

  public void setLanguageList(List<String> languageList) {
    this.languageList = languageList;
  }

  public List<ListValue> getLanguageObjList() {
    return languageObjList;
  }

  public void setLanguageObjList(List<ListValue> languageObjList) {
    this.languageObjList = languageObjList;
  }

  public Map<String, String> getLanguageMap() {
    return languageMap;
  }

  public void setLanguageMap(Map<String, String> languageMap) {
    this.languageMap = languageMap;
  }

  private SampleForm iform = new SampleForm();

  private Double dblField;

  public Double getDblField() {
    return dblField;
  }

  public void setDblField(Double dblField) {
    this.dblField = dblField;
  }

  public SampleForm getIform() {
    return iform;
  }

  public void setIform(SampleForm iform) {
    this.iform = iform;
  }

  public void validate() {
    System.out.println("validate");
  }

  public String execute() throws Exception {
    jsonsample();
    return SUCCESS;
  }

  @Action(value="jsonsample", interceptorRefs = {@InterceptorRef("json"),@InterceptorRef("defaultStack")},
    results=@Result(type="json", params = {"root","iform"}))
  public String jsonsample() {


    languageList.add("Java");
    languageList.add("PHP");
    languageList.add("C#");

    languageMap.put("J", "Java");
    languageMap.put("P", "PHP");
    languageMap.put("C", "C#");

    languageObjList.add(new ListValue("J", "Java"));
    languageObjList.add(new ListValue("P", "PHP"));
    languageObjList.add(new ListValue("C", "C#"));

    List<String> reloadList = new ArrayList<>();
    if (iform.getLanguage() != null && iform.getLanguage().equalsIgnoreCase("J"))
    {
      reloadList.add("Struts2");
      reloadList.add("MyFaces");
      reloadList.add("Tapestry");
    }
    else if (iform.getLanguage() != null && iform.getLanguage().equalsIgnoreCase("P"))
    {
      reloadList.add("CakePHP");
      reloadList.add("Symfony");
      reloadList.add("Zend");
    }
    else if (iform.getLanguage() != null && iform.getLanguage().equalsIgnoreCase("C"))
    {
      reloadList.add("NStruts");
      reloadList.add("ProMesh.NET");
      reloadList.add("Websharp");
    }

    iform.setLanguageList(languageList);
    iform.setLanguageObjList(languageObjList);
    iform.setLanguageMap(languageMap);
    iform.setReloadList(reloadList);

    return SUCCESS;
  }

}