package com.example.todo.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

public class Utils {
    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source,target, getNullProperty(source));
    }
    public static String[] getNullProperty(Object source){
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds =  src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for(PropertyDescriptor pd: pds){
      Object srcValue = src.getPropertyDescriptor(pd.getName());
      if(srcValue==null){
          emptyNames.add(pd.getName());
      };
    }
    String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
