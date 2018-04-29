package jspbean.struts.conversion;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

public class DoubleConverter extends StrutsTypeConverter {
  @Override
  public Object convertFromString(Map context, String[] values, Class toClass) {
    Object o = null;
    try {
      o = Double.valueOf(values[0]);
    } catch (NumberFormatException e){}
    return o;
  }

  @Override
  public String convertToString(Map context, Object o) {
    return o==null?"":o.toString();
  }
}
