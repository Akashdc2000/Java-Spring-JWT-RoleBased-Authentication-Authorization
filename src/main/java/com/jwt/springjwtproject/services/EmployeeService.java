package com.jwt.springjwtproject.services;

import com.jwt.springjwtproject.entity.Employee;

import java.util.List;


public interface EmployeeService {

    public Employee addEmployee(Employee employee);
    public List<Employee> getAllEmployees();

    public String deleteEmployee(String id);

    public Employee updateEmployee(Employee employee,String id);
    public Employee getEmployeeByID(String id);

    public List<Employee> getEmployeeByName(String name);
    public List<Employee> getEmployeeByDept(String dept);

    public List<Employee> getByDeptAndSalary(String dept,int salary);

    public List<Employee> getByDeptAndSalaryGreaterThan(String dept,int salary);
}
