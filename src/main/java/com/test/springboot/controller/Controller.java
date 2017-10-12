package com.test.springboot.controller;

import com.test.springboot.model.Employee;
import com.test.springboot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bhavan on 6/20/2017.
 */
@RestController
public class Controller {
    @Autowired
    EmployeeService employeeService;

    Logger logger= LoggerFactory.getLogger(Controller.class);

    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> getEmployess(){
        return new ResponseEntity<List<Employee>>(employeeService.getEmployees(),HttpStatus.OK);
    }

    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "id") Long id){
        logger.info("Entered get employee");
        Employee retuenedEmployee=employeeService.getEmployee(id);
        logger.info("Exited get employee");
        if(retuenedEmployee != null)
            return new ResponseEntity<Employee>(retuenedEmployee,HttpStatus.OK);
        else
        return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path="/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        logger.info("Entered create employee");
        if(employeeService.createEmployee(employee) != null){
            logger.info("Exited create employee");
            return new ResponseEntity<Employee>(HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path="/employees/{id}")
    public ResponseEntity<Employee> updateEmployee( @PathVariable Long id,@RequestBody Employee employee){
        logger.info("Entered updsate employee");
        if(employeeService.updateEmployee(id,employee) != null){
            logger.info("Entered update employee");
            return new ResponseEntity<Employee>(HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/employees/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id){
        logger.info("Entered delete employee");
        if(employeeService.deleteEmployee(id)){
            logger.info("Exited delete employee");
            return new ResponseEntity<Long>(HttpStatus.OK);
        }
        return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
    }
}

