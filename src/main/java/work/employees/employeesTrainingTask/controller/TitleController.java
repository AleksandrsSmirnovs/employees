package work.employees.employeesTrainingTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.exception.ControllerExceptionHandler;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.TitleResponse;
import work.employees.employeesTrainingTask.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/titles")
public class TitleController {

    private static final Logger log = LoggerFactory.getLogger(TitleController.class);
    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping
    public List<String> getAllTitles(@RequestParam(value = "order", required = false, defaultValue = "asc") String order) {
        return titleService.getAllTitles(order);
    }

    @GetMapping("/{title}/employees")
    public List<EmployeeResponse> getEmployeesByTitle(@PathVariable String title) {
        log.info("Received request - get employees by title : {}", title);
        return titleService.getEmployeesByTitle(title);
    }

}
