package work.employees.employeesTrainingTask.controller;

import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.response.EmployeeDeleteResponse;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeResponse> findAllEmployees(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(required = false, defaultValue = "lastName") String sortBy) {
        return employeeService.getAllEmployees(pageNo, pageSize, sortBy);
    }

    @DeleteMapping("/{id}")
    public EmployeeDeleteResponse deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping
    Employee newEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
