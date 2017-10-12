package com.test.springboot.service;

import com.test.springboot.model.Employee;

import java.util.List;

/**
 * Created by 984137 on 6/21/2017.
 */
public interface EmployeeService {
    public List<Employee> getEmployees();
    public Employee getEmployee(Long id);
    public Employee createEmployee(Employee employee);
    public Employee updateEmployee(Long id,Employee employee);
    public boolean deleteEmployee(Long id);
}
