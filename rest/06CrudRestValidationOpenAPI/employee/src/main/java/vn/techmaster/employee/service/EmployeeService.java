package vn.techmaster.employee.service;

import java.util.List;

import vn.techmaster.employee.model.Employee;

public interface EmployeeService {
  List<Employee> findAll();
  List<Employee> findByEmailId(String emailId);

  Employee findById(Long id);

  Employee save(Employee book);

  Employee update(long id, Employee book);

  void deleteById(long id);
}
