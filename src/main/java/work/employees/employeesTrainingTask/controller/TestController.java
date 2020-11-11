package work.employees.employeesTrainingTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.employees.employeesTrainingTask.domain.Employee;

@RestController
@RequestMapping("/employees")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    public TestController() {
        log.info("I'm alive");
    }

    @GetMapping("/test")
    public Employee testResponse() {
        return null;
    }
}
