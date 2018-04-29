package jspbean.struts;

import com.opensymphony.xwork2.ActionSupport;
import jspbean.model.ListValue;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.SMDMethod;
import org.apache.struts2.json.annotations.SMDMethodParameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Action(value="select", interceptorRefs = {@InterceptorRef("json"),@InterceptorRef("defaultStack")},
    results=@Result(location="/select.jsp"))
public class JsonSample extends ActionSupport {

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

  private int[] int_array;

  public int[] getInt_array() {
    return int_array;
  }

  public void setInt_array(int[] int_array) {
    this.int_array = int_array;
  }

  private void populateLists() {

    List<String> languageList = new ArrayList<>();
    List<ListValue> languageObjList = new ArrayList<>();
    Map<String, String> languageMap = new HashMap<>();

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
  }

  public void validate() {
    System.out.println("validate");
  }

  public String execute() throws Exception {
    return SUCCESS;
  }

  @Action(value = "jsonrpcsample",
      results = @Result(type = "json", params = {"enableSMD", "true"}),
      interceptorRefs = @InterceptorRef(value="json", params={"enableSMD", "true", "excludeProperties", "result\\.dob"}))
  public String executeAction() throws Exception {
    return SUCCESS;
  }

  @SMDMethod
  public List<ListValue> getLanguageObjList(){
    populateLists();
    return iform.getLanguageObjList();
  }

  private Tiny user;

  public Tiny getUser() {
    return user;
  }

  public void setUser(Tiny user) {
    this.user = user;
  }

  @SMDMethod
  public Tiny gotUser(@SMDMethodParameter(name = "userId")Long userId,@SMDMethodParameter(name = "stateId")Long stateId) {

    user = new Tiny();
    user.setName("Tiny");
    user.setLocation("India");

    try {
      user.setDob(new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH).parse("29-Feb-2000"));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return user;
  }

  @SMDMethod
  public Object[] putArray(@SMDMethodParameter(name = "pararray")Object[] pararray) {
    Object oa = pararray;
    System.out.println("oa="+oa);
    for(int i = 0; i < pararray.length; i++){
      System.out.println("pararray["+i+"]="+pararray[i]);
    }
    Arrays.sort(pararray, new Comparator<Object>(){
      @Override
      public int compare(Object o1, Object o2) {
        if (Long.parseLong(((List)o1).get(0).toString()) < Long.parseLong(((List)o2).get(0).toString())) return -1;
        else if (Long.parseLong(((List)o1).get(0).toString()) > Long.parseLong(((List)o2).get(0).toString())) return 1;
        else return 0;  //To change body of implemented methods use File | Settings | File Templates.
      }
    });
    System.out.println("After sort");
    for(int i = 0; i < pararray.length; i++){
      System.out.println("pararray["+i+"]="+pararray[i]);
    }
    return pararray;
  }

  @Action(value="jsonsample", interceptorRefs = {@InterceptorRef("json"),@InterceptorRef("defaultStack")},
      results=@Result(type="json", params = {"root","iform"}))
//      results=@Result(type="json", params = {"root", "iform", "wrapPrefix", "{\"iform\":", "wrapSuffix", "}"}))
  public String jsonsample() {

    populateLists();

    return SUCCESS;
  }

  @Action(value="doGetIntArray", interceptorRefs = {@InterceptorRef("defaultStack")},
      results=@Result(type="json", params = {"root","int_array"}))
  public String doGetIntArray() {
    return SUCCESS;
  }

  private Object[] objects;

  public Object[] getObjects() {
    return objects;
  }

  public void setObjects(Object[] objects) {
    this.objects = objects;
  }

  private String mode;

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  @Action(value="doPostObjectArray", interceptorRefs = {@InterceptorRef("defaultStack")},
      results=@Result(type="json", params = {"root","objects"}))
  public String doPostObjectArray() {
    return SUCCESS;

  }

  String json = "{\n" +
      "  \"id\": 10,\n" +
      "  \"groupName\": \"manager\",\n" +
      "  \"permissions\": [\n" +
      "    {\n" +
      "      \"id\": 18,\n" +
      "      \"name\": \"Group 1\",\n" +
      "      \"modules\": [\n" +
      "        {\n" +
      "          \"id\": 6,\n" +
      "          \"name\": \"Company 1\",\n" +
      "          \"groupId\": 18,\n" +
      "          \"stat\": true,\n" +
      "          \"moduleGrpName\": \"Group 1\"\n" +
      "        },\n" +
      "        {\n" +
      "          \"id\": 8,\n" +
      "          \"name\": \"Company 2\",\n" +
      "          \"groupId\": 18,\n" +
      "          \"stat\": true,\n" +
      "          \"moduleGrpName\": \"Group 1\"\n" +
      "        }\n" +
      "      ],\n" +
      "      \"stat\": \"false\"\n" +
      "    },\n" +
      "    {\n" +
      "      \"id\": 19,\n" +
      "      \"name\": \"Group 2\",\n" +
      "      \"modules\": [\n" +
      "        {\n" +
      "          \"id\": 17,\n" +
      "          \"name\": \"Company 3\",\n" +
      "          \"groupId\": 19,\n" +
      "          \"stat\": true,\n" +
      "          \"moduleGrpName\": \"Group 2\"\n" +
      "        },\n" +
      "        {\n" +
      "          \"id\": 15,\n" +
      "          \"name\": \"Company 4\",\n" +
      "          \"groupId\": 19,\n" +
      "          \"stat\": true,\n" +
      "          \"moduleGrpName\": \"Group 2\"\n" +
      "        }\n" +
      "      ],\n" +
      "      \"stat\": \"false\"\n" +
      "    }\n" +
      "  ]\n" +
      "}";

  public Map getUserMenu(){
    Map res = null;
    try {
      Object o = JSONUtil.deserialize(json);
      res = (Map)o;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return res;
  }
  public Map getUserMenu2(){
    return JSONObject.fromObject(json);
  }

}