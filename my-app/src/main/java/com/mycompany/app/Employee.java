package com.mycompany.app;

public class Employee {
  
    private int id;
  public int getId() {
    return id;
  }

  public void setId(int newId) {
    this.id = newId;
  }
    
  private String name;
  public String getName() {
    return name;
  }

  public void setName(String newName) {
    this.name = newName;
  }
  
    private String dateOfBirth;
  public String getdateOfBirth() {
    return dateOfBirth;
  }

  public void setdateOfBirth(String newdateOfBirth) {
    this.dateOfBirth = newdateOfBirth;
  }
  
    private String department;
  public String getdepartment() {
    return department;
  }

  public void setdepartment(String newdepartment) {
    this.department = newdepartment;
  }
  
    private int salary;
  public int getsalary() {
    return salary;
  }

  public void setsalary(int newsalary) {
    this.salary = newsalary;
  }
    
}