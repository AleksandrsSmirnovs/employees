package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.DepartmentResponse;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.SalaryResponse;
import work.employees.employeesTrainingTask.response.TitleResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        return createResponseFromEmployeeEntity(entity);
    }

    public List<EmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeRepository.findAll(paging);
        return pagedResult.stream()
                .map(this::createResponseFromEmployeeEntity)
                .collect(toList());
    }

    public EmployeeResponse deleteEmployee(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        employeeRepository.deleteById(id);
        return createResponseFromEmployeeEntity(entity);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    private EmployeeResponse createResponseFromEmployeeEntity(Employee entity) {
        return new EmployeeResponse(entity.getEmployeeNumber(),
                entity.getBirthDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getHireDate(),
                entity.getDepartments().stream().map(this::createDepartmentResponseForEmployee).collect(toList()),
                entity.getManagedDepartments().stream().map(this::createDepartmentResponseForEmployee).collect(toList()),
                entity.getSalaries().stream().map(this::createSalaryResponseForEmployee).collect(toList()),
                entity.getTitles().stream().map(this::createTitleResponseForEmployee).collect(toList()));
    }

    private DepartmentResponse createDepartmentResponseForEmployee(Department department) {
        return new DepartmentResponse(department.getDepartmentNumber(), department.getDepartmentName());
    }

    private SalaryResponse createSalaryResponseForEmployee(Salary salary) {
        return new SalaryResponse(salary.getSalary(), salary.getFromDate(), salary.getToDate());
    }

    private TitleResponse createTitleResponseForEmployee(Title title) {
        return new TitleResponse(title.getTitle(), title.getFromDate(), title.getToDate());
    }



}
