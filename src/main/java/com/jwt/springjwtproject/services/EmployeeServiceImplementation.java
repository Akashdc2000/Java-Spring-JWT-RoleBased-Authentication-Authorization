package com.jwt.springjwtproject.services;

import com.jwt.springjwtproject.entity.Employee;
import com.jwt.springjwtproject.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        return  employeesRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @Override
    public String deleteEmployee(String id) {
        employeesRepository.deleteById(id);
        return "Employee Deleted Successfully...";
    }


    public Employee updateEmployee(Employee employee,String id){
        Employee emp=employeesRepository.findById(id).get();
        emp.setId(id);
        if(Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName()))
            emp.setName(employee.getName());
        if(employee.getSalary()>0)
            emp.setSalary(employee.getSalary());
        return  employeesRepository.save(emp);
    }

    public Employee getEmployeeByID(String id){
        return employeesRepository.findById(id).get();
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeesRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeeByDept(String dept) {
        return employeesRepository.findByDept(dept);
    }

    @Override
    public List<Employee> getByDeptAndSalary(String dept, int salary) {
        return employeesRepository.findByDeptAndSalary(dept,salary);
    }

    @Override
    public List<Employee> getByDeptAndSalaryGreaterThan(String dept, int salary) {
        return employeesRepository.findByDeptAndSalaryGreaterThan(dept,salary);
    }
}
