package com.techmaster.employeemanagement.service;

import java.util.List;

import com.techmaster.employeemanagement.model.Employee;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id);

    void addOrUpdate(Employee employee);

    void deleteById(long id);

}
