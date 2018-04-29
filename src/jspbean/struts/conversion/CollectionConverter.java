package jspbean.struts.conversion;

import com.opensymphony.xwork2.conversion.ObjectTypeDeterminer;
import com.opensymphony.xwork2.conversion.TypeConverter;
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.XWorkList;
import org.apache.struts2.util.StrutsTypeConverter;

import java.lang.reflect.Member;


import java.util.*;

public class CollectionConverter extends StrutsTypeConverter {

  private ObjectTypeDeterminer objectTypeDeterminer;

  @Inject
  public void setObjectTypeDeterminer(ObjectTypeDeterminer determiner) {
    this.objectTypeDeterminer = determiner;
  }

  public Object convertValue(Map<String, Object> context, Object target, Member member, String propertyName, Object value, Class toType) {
    Collection result;
    Class memberType = String.class;

    if (target != null) {
      memberType = objectTypeDeterminer.getElementClass(target.getClass(), propertyName, null);

      if (memberType == null) {
        memberType = String.class;
      }
    }

    if (toType.isAssignableFrom(value.getClass())) {
      // no need to do anything
      result = (Collection) value;
    } else if (value.getClass().isArray()) {
      Object[] objArray = (Object[]) value;
      TypeConverter converter = getTypeConverter(context);
      result = createCollection(toType, memberType, objArray.length);

      for (Object anObjArray : objArray) {
        Object convertedValue = converter.convertValue(context, target, member, propertyName, anObjArray, memberType);
        if (!TypeConverter.NO_CONVERSION_POSSIBLE.equals(convertedValue)) {
          result.add(convertedValue);
        }
      }
    } else if (Collection.class.isAssignableFrom(value.getClass())) {
      Collection col = (Collection) value;
      TypeConverter converter = getTypeConverter(context);
      result = createCollection(toType, memberType, col.size());

      for (Object aCol : col) {
        Object convertedValue = converter.convertValue(context, target, member, propertyName, aCol, memberType);
        if (!TypeConverter.NO_CONVERSION_POSSIBLE.equals(convertedValue)) {
          result.add(convertedValue);
        }
      }
    } else {
      result = createCollection(toType, memberType, -1);
      result.add(value);
    }

    return result;

  }

  private Collection createCollection(Class toType, Class memberType, int size) {
    Collection result;

    if (toType == Set.class) {
      if (size > 0) {
        result = new HashSet(size);
      } else {
        result = new HashSet();
      }
    } else if (toType == SortedSet.class) {
      result = new TreeSet();
    } else {
      if (size > 0) {
        result = new XWorkList(memberType, size);
      } else {
        result = new XWorkList(memberType);
      }
    }

    return result;
  }

  /**
   * Converts one or more String values to the specified class.
   *
   * @param context the action context
   * @param values  the String values to be converted, such as those submitted from an HTML form
   * @param toClass the class to convert to
   * @return the converted object
   */
  @Override
  public Object convertFromString(Map context, String[] values, Class toClass) {
    return convertValue(context, values, toClass);
  }

  /**
   * Converts the specified object to a String.
   *
   * @param context the action context
   * @param o       the object to be converted
   * @return the converted String
   */
  @Override
  public String convertToString(Map context, Object o) {
    if (o instanceof List) {
      List list = (List) o;
      return list.toString();
    } else if (o instanceof Map){
      Map map = (Map) o;
      return map.toString();
    }
    return null;
  }
}
