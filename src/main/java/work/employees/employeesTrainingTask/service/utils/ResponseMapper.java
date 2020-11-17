package work.employees.employeesTrainingTask.service.utils;

import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Service
public class ResponseMapper {

    public TitleResponse createTitleResponse(Title title) {
        return new TitleResponse(title.getTitle());
    }

    public SimpleEmployeeResponse createSimpleEmployeeResponse(Employee entity) {
        return new SimpleEmployeeResponse(entity.getEmployeeNumber(),
                entity.getBirthDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getHireDate()
        );
    }

    public EmployeeResponse createEmployeeResponse(Employee entity) {
        return new EmployeeResponse(entity.getEmployeeNumber(),
                entity.getBirthDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getHireDate(),
                emptyIfNull(entity.getDepartments()).stream().map(this::createDepartmentResponseForEmployee).collect(toList()),
                emptyIfNull(entity.getManagedDepartments()).stream().map(this::createDepartmentResponseForEmployee).collect(toList()),
                emptyIfNull(entity.getSalaries()).stream().map(this::createSalaryResponseForEmployee).collect(toList()),
                emptyIfNull(entity.getTitles()).stream().map(this::createTitleResponseForEmployee).collect(toList()));
    }

    public DepartmentResponse createDepartmentResponseForEmployee(Department department) {
        return new DepartmentResponse(department.getDepartmentNumber(), department.getDepartmentName());
    }

    public SalaryResponse createSalaryResponseForEmployee(Salary salary) {
        return new SalaryResponse(salary.getSalary(), salary.getFromDate(), salary.getToDate());
    }

    public TitleResponse createTitleResponseForEmployee(Title title) {
        return new TitleResponse(title.getTitle(), title.getFromDate(), title.getToDate());
    }

    public DepartmentResponse createDepartmentResponse(Department department) {
        return new DepartmentResponse(department.getDepartmentNumber(), department.getDepartmentName());
    }


    public EmployeeDeleteResponse createEmployeeDeleteResponse(String message, Employee employee) {
        return new EmployeeDeleteResponse(message, createSimpleEmployeeResponse(employee));
    }

    public Employee createEmployeeFromCreateRequest(CreateEmployeeRequest request) {
        return new Employee(request.getEmployeeNumber(),
                request.getBirthDate(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getHireDate(),
                request.getDepartments(),
                request.getManagedDepartments(),
                request.getSalaries(),
                request.getTitles());
    }

}
