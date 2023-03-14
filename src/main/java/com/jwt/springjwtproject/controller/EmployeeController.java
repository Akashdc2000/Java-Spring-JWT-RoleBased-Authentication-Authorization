package com.jwt.springjwtproject.controller;


import com.jwt.springjwtproject.entity.Employee;
import com.jwt.springjwtproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }



    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEmployee(@PathVariable("id") String id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("id") String id){
        return employeeService.updateEmployee(employee,id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        return employeeService.getEmployeeByID(id);
    }


    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Employee> getEmployeeByName(@PathVariable("name")String name){
        return employeeService.getEmployeeByName(name);
    }

    @GetMapping("/dept/{dept}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Employee> getEmployeeByDept(@PathVariable("dept")String dept){
        return employeeService.getEmployeeByDept(dept);
    }

    @GetMapping("/dept/{dept}/salary/{salary}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Employee> getEmployeeByDeptAndSalary(@PathVariable("dept")String dept,@PathVariable("salary")int salary){
        return employeeService.getByDeptAndSalary(dept,salary);
    }

    @GetMapping("/condition/dept/{dept}/salary/{salary}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Employee> getEmployeeByDeptAndSalaryGreaterThan(@PathVariable("dept")String dept,@PathVariable("salary")int salary){
        return employeeService.getByDeptAndSalaryGreaterThan(dept,salary);
    }
}

