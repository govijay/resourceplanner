package com.sap.ariba.cts.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenericUtil {

  private static Logger logger = LoggerFactory.getLogger(GenericUtil.class);


  public static void copyNonNullProperties(Object source, Object destination) {
    BeanUtils.copyProperties(source, destination,
            getNullPropertyNames(source));
  }

  private static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set emptyNames = new HashSet();
    for (java.beans.PropertyDescriptor pd : pds) {
      //check if value of this property is null or primitive then add it to the collection
      if (pd.getPropertyType().isPrimitive()) {
        emptyNames.add(pd.getName());
      }
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null) emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return (String[]) emptyNames.toArray(result);
  }

  public static Boolean objectHasProperty(Object obj, String propertyName) {
    List<Field> properties = getAllFields(obj);
    for (Field field : properties) {
      if (field.getName().equalsIgnoreCase(propertyName)) {
        return true;
      }
    }
    return false;
  }

  private static List<Field> getAllFields(Object obj) {
    List<Field> fields = new ArrayList<Field>();
    getAllFieldsRecursive(fields, obj.getClass());
    return fields;
  }

  private static List<Field> getAllFieldsRecursive(List<Field> fields, Class<?> type) {
    for (Field field : type.getDeclaredFields()) {
      fields.add(field);
    }

    if (type.getSuperclass() != null) {
      fields = getAllFieldsRecursive(fields, type.getSuperclass());
    }

    return fields;
  }
}
