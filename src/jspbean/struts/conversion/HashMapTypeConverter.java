package jspbean.struts.conversion;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.HashMap;
import java.util.Map;

public class HashMapTypeConverter extends StrutsTypeConverter {

  private final static Log log = LogFactory.getLog(HashMapTypeConverter.class);

  @SuppressWarnings("rawtypes")
  @Override
  public Object convertFromString(Map context, String[] values, Class toClass) {

    if (values == null || values[0].toString().indexOf(",") == -1) {
      HashMap<Long, Long> convertedMap = new HashMap<Long, Long>();
      return convertedMap;
    }
    int key = 0;
    HashMap<Long, Long> convertedMap = new HashMap<Long, Long>();
    String[] mapValues = values[0].toString().substring(1, values[0].length() - 1).split(",");
    try {
      while (key < mapValues.length) {
        String[] valueAndKey = mapValues[key].toString().trim().split("=");
        convertedMap.put(Long.valueOf(valueAndKey[0]), Long.valueOf(valueAndKey[1]));
        key++;
      }
    } catch (Exception e) {
      if (log.isDebugEnabled()) {
        log.debug("Conversion from String to Object failed in HashMapTypeConverter class" + mapValues);
      }
      throw new TypeConversionException(e);
    }
    return convertedMap;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public String convertToString(Map context, Object o) {
    Map map = (Map) o;
    return map == null ? null : map.toString();
  }
}