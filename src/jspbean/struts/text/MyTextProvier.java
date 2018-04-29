package jspbean.struts.text;

import com.opensymphony.xwork2.TextProviderSupport;
import com.opensymphony.xwork2.util.ValueStack;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Roma on 14.08.2015.
 */
public class MyTextProvier extends TextProviderSupport {

  protected Pattern allowedKeys = Pattern.compile("[a-zA-Z0-9._!/\\-]*");

  protected String cleanupKey(final String rawKey) {
    //1
//    if (allowedKeys.matcher(rawKey).matches()) {
//      return rawKey;
//    } else {
//      String cleanKey = rawKey;
//      for (String chunk : allowedKeys.split(rawKey)) {
//        cleanKey = cleanKey.replace(chunk, "");
//      }
//      return cleanKey;
//    }

    //2
//    return rawKey.replaceAll("[\\$\\{\\}]", "");
    return rawKey;
  }

  @Override
  public String getText(String key) {
    return super.getText(cleanupKey(key));
  }

  @Override
  public String getText(String key, String defaultValue) {
    return super.getText(cleanupKey(key), defaultValue);
  }

  @Override
  public String getText(String key, String defaultValue, String obj) {
    return super.getText(cleanupKey(key), defaultValue, obj);
  }

  @Override
  public String getText(String key, List<?> args) {
    return super.getText(cleanupKey(key), args);
  }

  @Override
  public String getText(String key, String[] args) {
    return super.getText(cleanupKey(key), args);
  }

  @Override
  public String getText(String key, String defaultValue, List<?> args) {
    return super.getText(cleanupKey(key), defaultValue, args);
  }

  @Override
  public String getText(String key, String defaultValue, String[] args) {
    return super.getText(cleanupKey(key), defaultValue, args);
  }

  @Override
  public String getText(String key, String defaultValue, List<?> args, ValueStack stack) {
    return super.getText(cleanupKey(key), defaultValue, args, stack);
  }

  @Override
  public String getText(String key, String defaultValue, String[] args, ValueStack stack) {
    return super.getText(cleanupKey(key), defaultValue, args, stack);
  }

}
