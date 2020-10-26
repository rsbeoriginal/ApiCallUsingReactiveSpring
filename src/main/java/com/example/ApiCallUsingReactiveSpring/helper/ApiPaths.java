package com.example.ApiCallUsingReactiveSpring.helper;

/**
 * @author rishi
 */
public interface ApiPaths {

  String EMPLOYEE_PATH = "/employees";

  static String getEmployeePath(String host){
    return host + EMPLOYEE_PATH;
  }
}
