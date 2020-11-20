package work.employees.employeesTrainingTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.response.SimpleDepartmentResponse;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<SimpleDepartmentResponse> getAllDepartments(@RequestParam(value = "order", required = false, defaultValue = "asc") String order) {
        log.info("Received request - get all departments");
        return departmentService.getAllDepartments(order);
    }

    @GetMapping("/{departmentName}/employees")
    public List<SimpleEmployeeResponse> getEmployeesByDepartmentName(@PathVariable String departmentName,
                                                                     @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                     @RequestParam(required = false, defaultValue = "last_name,asc") String[] sort,
                                                                     @RequestParam(required = false) Character gender,
                                                                     @RequestParam(required = false) String hireDateBefore,
                                                                     @RequestParam(required = false) String hireDateAfter) {
        log.info("Received request - get employees by department name : {}", departmentName);
        return departmentService.getEmployeesByDepartmentName(departmentName, pageNo, pageSize, sort, gender, hireDateBefore, hireDateAfter);
    }
}
