package work.employees.employeesTrainingTask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import work.employees.employeesTrainingTask.response.DepartmentResponse;
import work.employees.employeesTrainingTask.service.DepartmentService;

import java.util.List;

@RestController
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<DepartmentResponse> getAllDepartments(@RequestParam(value = "order", required = false, defaultValue = "asc") String order) {
        return departmentService.getAllDepartments(order);
    }
}
