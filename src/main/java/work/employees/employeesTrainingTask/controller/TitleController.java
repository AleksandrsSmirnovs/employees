package work.employees.employeesTrainingTask.controller;

import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.TitleResponse;
import work.employees.employeesTrainingTask.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/titles")
public class TitleController {

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
        return titleService.getEmployeesByTitle(title);
    }

}
