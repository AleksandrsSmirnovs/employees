package work.employees.employeesTrainingTask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.employees.employeesTrainingTask.response.TitleResponse;
import work.employees.employeesTrainingTask.service.TitleService;

import java.util.List;

@RestController
public class TitleController {

    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/titles")
    public List<String> getAllTitles() {
        return titleService.getAllTitles();
    }

}
