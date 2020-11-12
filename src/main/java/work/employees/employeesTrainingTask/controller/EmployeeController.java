package work.employees.employeesTrainingTask.controller;

import org.springframework.web.bind.annotation.*;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public EmployeeResponse findEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<EmployeeResponse> findAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employees/{id}")
    public EmployeeResponse deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
