package work.employees.employeesTrainingTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
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
        log.info("Received request - get all titles");
        return titleService.getAllTitles(order);
    }

    @GetMapping("/{title}/employees")
    public List<SimpleEmployeeResponse> getEmployeesByTitle(@PathVariable String title,
                                                            @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                            @RequestParam(required = false, defaultValue = "lastName,asc") String[] sort,
                                                            @RequestParam(required = false) Character gender,
                                                            @RequestParam(required = false) String hireDateBefore,
                                                            @RequestParam(required = false) String hireDateAfter) {
        log.info("Received request - get employees by title : {}", title);
        return titleService.getEmployeesByTitle(title, pageNo, pageSize, sort, gender, hireDateBefore, hireDateAfter);
    }

}
