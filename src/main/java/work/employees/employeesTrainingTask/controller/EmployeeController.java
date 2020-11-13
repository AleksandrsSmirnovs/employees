package work.employees.employeesTrainingTask.controller;

import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.EmployeeDeleteResponse;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
                                                         @RequestParam(required = false, defaultValue = "lastName") String sortBy,
                                                         @RequestParam(required = false, defaultValue = "asc") String order,
                                                         @And ({
                                                           @Spec(path = "gender", params = "gender", spec = EqualIgnoreCase.class),
                                                           @Spec(path = "hireDate", params = "dateAfter", spec = GreaterThan.class),
                                                           @Spec(path = "hireDate", params = "dateBefore", spec= LessThan. class)
                                                   }) Specification<Employee> employeeSpec) {
        log.info("Received request - get all employees");
        return employeeService.getAllEmployees(pageNo, pageSize, sortBy, order, employeeSpec);
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
