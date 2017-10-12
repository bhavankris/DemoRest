package com.test.springboot.health;

import com.test.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by Bhavan on 6/23/2017.
 */
@Component
public class EmployeeStatusHealthIndicator implements HealthIndicator{

    @Autowired
    EmployeeService employeeService;

    @Override
    public Health health() {
        int count = employeeService.getEmployees().size();
        if(count > 0){
            return Health.up().withDetail("Employee Count",count).build();
        }
        return Health.down().withDetail("Employee Count",count).build();
    }
}
