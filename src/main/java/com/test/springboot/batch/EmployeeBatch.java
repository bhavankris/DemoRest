package com.test.springboot.batch;

import com.test.springboot.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Bhavan on 6/22/2017.
 */
@Component
public class EmployeeBatch {
    private Logger logger= LoggerFactory.getLogger(EmployeeBatch.class);

    @Autowired
    EmployeeService employeeService;

    //@Scheduled(cron = "0 * * * * *")
    public void cornJob(){
      logger.info("Entering corn job");
      logger.info("No of Employees in database"+employeeService.getEmployees().size());
      logger.info("Exiting corn job");
    }
    //@Scheduled(initialDelay = 5000,fixedRate = 5000)
    public void fixedRate(){
        logger.info("Entering corn job");
        logger.info("No of Employees in database"+employeeService.getEmployees().size());
        logger.info("Exiting corn job");
    }

    //@Scheduled(initialDelay = 5000,fixedDelay = 5000)
    public void fixedDelay() throws InterruptedException {
        logger.info("Entering corn job");
        Thread.sleep(10000);
        logger.info("No of Employees in database"+employeeService.getEmployees().size());
        logger.info("Exiting corn job");
    }
}
