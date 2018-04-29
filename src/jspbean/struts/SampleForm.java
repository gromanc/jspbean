package jspbean.struts;

import jspbean.model.ListValue;
import org.apache.struts2.json.annotations.JSON;

import java.util.List;
import java.util.Map;

public class SampleForm {

  private List<String> languageList;
  private List<ListValue> languageObjList;
  private Map<String, String> languageMap;
  private List<String>        reloadList;
  private String              language;

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

  public List<String> getReloadList() {
    return reloadList;
  }

  public void setReloadList(List<String> reloadList) {
    this.reloadList = reloadList;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
