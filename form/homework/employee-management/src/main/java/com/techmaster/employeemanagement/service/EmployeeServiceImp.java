package com.techmaster.employeemanagement.service;

import java.util.List;

import com.techmaster.employeemanagement.exception.ResourceNotFoundException;
import com.techmaster.employeemanagement.model.Employee;
import com.techmaster.employeemanagement.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id không tồn tại"));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void addOrUpdate(Employee employee) {
        if (employee.getId() != 0) {
            this.findById(employee.getId());
        }
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteById(long id) {
        Employee employee = this.findById(id);
        this.employeeRepository.delete(employee);

    }

}
