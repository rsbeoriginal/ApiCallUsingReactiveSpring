package com.example.ApiCallUsingReactiveSpring.model;

/**
 * @author rishi
 */
public class EmployeeModel {

  private Long id;

  private String name;

  private String department;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
