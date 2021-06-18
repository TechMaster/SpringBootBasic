package com.techmaster.employeemanagement.repository;


import com.techmaster.employeemanagement.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    
}
