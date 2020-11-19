package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.DepartmentEmployee;
import work.employees.employeesTrainingTask.domain.DepartmentManager;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.exception.ItemAlreadyExistsException;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.request.CreateEmployeeDepartmentRequest;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.service.utils.ResponseMapper;
import work.employees.employeesTrainingTask.service.utils.DataSorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ResponseMapper mapper;
    private final DataSorter sorter;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ResponseMapper mapper, DataSorter sorter) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
        this.sorter = sorter;
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        return mapper.createEmployeeResponse(entity);
    }

    public List<SimpleEmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String[] sort, Character gender, String hireDateBefore, String hireDateAfter) {
        String genderParam = gender == null ? "%" : gender.toString();
        String hireDateBeforeParam = Objects.requireNonNullElse(hireDateBefore, "9999-01-01");
        String hireDateAfterParam = Objects.requireNonNullElse(hireDateAfter, "0000-01-01");
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sorter.getOrders(sort)));
        List<Employee> pagedResult = employeeRepository.findAllWithParams(genderParam, hireDateBeforeParam, hireDateAfterParam, paging);
        return pagedResult.stream()
                .map(mapper::createSimpleEmployeeResponse)
                .collect(toList());
    }

    public EmployeeDeleteResponse deleteEmployee(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        employeeRepository.deleteById(id);
        return mapper.createEmployeeDeleteResponse("Employee deleted successfully", entity);
    }

    public EmployeeResponse saveEmployee(CreateEmployeeRequest request) {
        if (request.getEmployeeNumber() == null || employeeRepository.existsById(request.getEmployeeNumber())) {
            request.setEmployeeNumber(employeeRepository.getMaxId() + 1);
        } else {
            throw new ItemAlreadyExistsException("Item with id " + request.getEmployeeNumber() + " already exists");
        }
        Employee employee = mapper.createEmployeeFromCreateRequest(request);
//        employee.setDepartments(new ArrayList<>());
//        if (request.getDepartments() != null) {
//            for (CreateEmployeeDepartmentRequest createEmployeeDepartmentRequest : request.getDepartments()) {
//                Department department = departmentRepository.findById(createEmployeeDepartmentRequest.getDepartmentNumber())
//                        .orElse(new Department(createEmployeeDepartmentRequest.getDepartmentNumber(), createEmployeeDepartmentRequest.getDepartmentName()));
//                DepartmentEmployee departmentEmployee = mapper.createDepartmentEmployeeRelationship(
//                        employee,
//                        department,
//                        createEmployeeDepartmentRequest.getFromDate(),
//                        createEmployeeDepartmentRequest.getToDate()
//                );
//                employee.getDepartments().add(departmentEmployee);
//            }
////        }
//        employee.setSalaries(request.getSalaries());
        employeeRepository.save(employee);
        return mapper.createEmployeeResponse(employee);
    }

}
