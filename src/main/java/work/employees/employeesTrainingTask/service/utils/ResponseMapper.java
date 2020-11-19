package work.employees.employeesTrainingTask.service.utils;

import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;

import java.util.Date;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

@Service
public class ResponseMapper {

    private final DepartmentRepository departmentRepository;

    public ResponseMapper(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public TitleResponse createTitleResponse(Title title) {
        return new TitleResponse(title.getTitleId().getTitle());
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

    public EmployeeResponse createEmployeeResponse(Employee employee) {
        return new EmployeeResponse(employee.getEmployeeNumber(),
                employee.getBirthDate(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getHireDate(),
                employee.getDepartments() == null ? emptyList() : createDepartmentResponseListForEmployee(employee.getDepartments()),
                employee.getManagedDepartments() == null ? emptyList() : createDepartmentManagerResponseListForEmployee(employee.getManagedDepartments()),
                emptyIfNull(employee.getSalaries()).stream().map(this::createSalaryResponseForEmployee).collect(toList()),
                emptyIfNull(employee.getTitles()).stream().map(this::createTitleResponseForEmployee).collect(toList()));
    }

    public List<DepartmentResponse> createDepartmentResponseListForEmployee(List<DepartmentEmployee> departmentEmployeeList) {
        return departmentEmployeeList
                .stream()
                .map(departmentEmployee -> new DepartmentResponse(
                        departmentEmployee.getDepartmentEmployeeId().getDepartmentNumber(),
                        departmentEmployee.getDepartment().getDepartmentName(),
                        departmentEmployee.getFromDate(),
                        departmentEmployee.getToDate()
                ))
                .collect(toList());
    }

    public List<DepartmentResponse> createDepartmentManagerResponseListForEmployee(List<DepartmentManager> departmentManagerList) {
        return departmentManagerList
                .stream()
                .map(departmentManager -> new DepartmentResponse(
                        departmentManager.getDepartmentManagerId().getDepartmentNumber(),
                        departmentManager.getDepartment().getDepartmentName(),
                        departmentManager.getFromDate(),
                        departmentManager.getToDate()
                ))
                .collect(toList());
    }

    public SalaryResponse createSalaryResponseForEmployee(Salary salary) {
        return new SalaryResponse(salary.getSalary(), salary.getSalaryId().getFromDate(), salary.getToDate());
    }

    public TitleResponse createTitleResponseForEmployee(Title title) {
        return new TitleResponse(title.getTitleId().getTitle(), title.getTitleId().getFromDate(), title.getToDate());
    }

    public DepartmentResponse createDepartmentResponse(Department department) {
        return new DepartmentResponse(department.getDepartmentNumber(), department.getDepartmentName());
    }

    public SimpleDepartmentResponse createSimpleDepartmentResponse(Department department) {
        return new SimpleDepartmentResponse(department.getDepartmentNumber(), department.getDepartmentName());
    }


    public EmployeeDeleteResponse createEmployeeDeleteResponse(String message, Employee employee) {
        return new EmployeeDeleteResponse(message, createSimpleEmployeeResponse(employee));
    }

    public Employee createEmployeeFromCreateRequest(CreateEmployeeRequest request) {
        Employee employee = new Employee(request.getEmployeeNumber(),
                request.getBirthDate(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getHireDate());
        employee.setDepartments(request.getDepartments().stream()
                .map(createEmployeeDepartmentRequest ->
                        new DepartmentEmployee(
                                createEmployeeDepartmentRequest.getFromDate(),
                                createEmployeeDepartmentRequest.getToDate(),
                                employee,
                                departmentRepository.findById(createEmployeeDepartmentRequest.getDepartmentNumber()).orElse(new Department())
                        ))
                .collect(toList()));
        employee.setManagedDepartments(request.getDepartments().stream()
                .map(createEmployeeDepartmentRequest ->
                        new DepartmentManager(
                                createEmployeeDepartmentRequest.getFromDate(),
                                createEmployeeDepartmentRequest.getToDate(),
                                employee,
                                departmentRepository.findById(createEmployeeDepartmentRequest.getDepartmentNumber()).orElse(new Department())
                        ))
                .collect(toList()));
        employee.setSalaries(request.getSalaries().stream()
                .map(createEmployeeSalaryRequest ->
                        new Salary(
                                new SalaryId(employee.getEmployeeNumber(), createEmployeeSalaryRequest.getFromDate()),
                                createEmployeeSalaryRequest.getSalary(),
                                createEmployeeSalaryRequest.getToDate()))
                .collect(toList()));
        employee.setTitles(request.getTitles().stream()
                .map(createEmployeeTitleRequest -> new Title(
                        new TitleId(employee.getEmployeeNumber(), createEmployeeTitleRequest.getTitle(), createEmployeeTitleRequest.getFromDate()),
                        createEmployeeTitleRequest.getToDate()
                ))
                .collect(toList()));
        return employee;
    }

}
