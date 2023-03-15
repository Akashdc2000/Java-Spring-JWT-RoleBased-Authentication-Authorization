package com.jwt.springjwtproject.repository;

import com.jwt.springjwtproject.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends MongoRepository<Employee,String> {
     List<Employee> findByName(String name);
     List<Employee> findByDept(String dept);
     List<Employee> findByDeptAndSalary(String dept,int salary);
    List<Employee> findByDeptAndSalaryGreaterThan(String dept,int salary);


}
