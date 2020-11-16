package work.employees.employeesTrainingTask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.EmployeeDeleteResponse;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        log.info("Received request - get employee by id. Id = {}", id);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<SimpleEmployeeResponse> findAllEmployees(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                         @RequestParam(required = false, defaultValue = "last_name,asc") String[] sort,
                                                         @RequestParam(required = false) Character gender,
                                                         @RequestParam(required = false) String hireDateBefore,
                                                         @RequestParam(required = false) String hireDateAfter) {
        log.info("Received request - get all employees");
        return employeeService.getAllEmployees(pageNo, pageSize, sort, gender, hireDateBefore, hireDateAfter);
    }

    @DeleteMapping("/{id}")
    public EmployeeDeleteResponse deleteEmployee(@PathVariable Integer id) {
        log.info("Received request - delete employee by id. Id = {}", id);
        return employeeService.deleteEmployee(id);
    }

    @PostMapping
    public EmployeeResponse saveEmployee(@Validated @RequestBody CreateEmployeeRequest request, HttpServletResponse response) {
        log.info("Received request - save employee: {}", request);
        EmployeeResponse employeeResponse = employeeService.saveEmployee(request);
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/employees/" + employeeResponse.getEmployeeNumber()).toUriString());
        response.setStatus(response.SC_CREATED);
        return employeeResponse;
    }
}
