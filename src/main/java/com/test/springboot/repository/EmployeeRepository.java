package com.test.springboot.repository;

import com.test.springboot.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bhavan on 6/21/2017.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
