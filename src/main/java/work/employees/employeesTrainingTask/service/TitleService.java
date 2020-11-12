package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.repository.TitleRepository;
import work.employees.employeesTrainingTask.response.DepartmentResponse;
import work.employees.employeesTrainingTask.response.EmployeeResponse;
import work.employees.employeesTrainingTask.response.SalaryResponse;
import work.employees.employeesTrainingTask.response.TitleResponse;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TitleService {

    private final TitleRepository titleRepository;
    private final EmployeeRepository employeeRepository;

    public TitleService(TitleRepository titleRepository, EmployeeRepository employeeRepository) {
        this.titleRepository = titleRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<String> getAllTitles(String order) {
        return order.equalsIgnoreCase("desc") ? titleRepository.getAllTitlesDesc() : titleRepository.getAllTitlesAsc();
    }

    public List<EmployeeResponse> getEmployeesByTitle(String title) {
        return employeeRepository.getEmployeesByTitle(title).stream().map(this::createResponseFromEmployeeEntity).collect(toList());
    }

    private TitleResponse createTitleResponse(Title title) {
        return new TitleResponse(title.getTitle());
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
