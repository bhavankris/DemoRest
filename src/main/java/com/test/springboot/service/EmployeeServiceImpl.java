package com.test.springboot.service;

import com.test.springboot.model.Employee;
import com.test.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bhavan on 6/21/2017.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CounterService counterService;

    @Override
    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    @Cacheable(value = "Employees",key = "#id")
    public Employee getEmployee(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false)
    @CachePut(value = "Employees", key="#result.id")
    public Employee createEmployee(Employee employee) {

        if(employee.getId()!= null){
            counterService.increment("counter.employees.notcreated");
            return null;
        }
        Employee createdEmployee=employeeRepository.save(employee);
        if(createdEmployee.getId() == 5 || createdEmployee.getId()==6){
            counterService.increment("counter.employees.notcreated");
            throw new RuntimeException("Reserved Number - cannot be used");
        }
        counterService.increment("counter.employees.created");
        return createdEmployee;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,readOnly = false)
    @CachePut(value = "Employees", key = "#id")
    public Employee updateEmployee(Long id, Employee employee) {
        Employee persistedEmployee = employeeRepository.findOne(id);
        if(persistedEmployee != null){
            persistedEmployee.setName(employee.getName());
            persistedEmployee.setAge(employee.getAge());
            employeeRepository.save(persistedEmployee);
            return persistedEmployee;
        }
        return null;
    }

    @Override
    @CacheEvict(value="Employee",allEntries = true)
    public boolean deleteEmployee(Long id) {
        if(employeeRepository.exists(id)) {
            employeeRepository.delete(id);
            return true;
        }
        return false;
    }
}
