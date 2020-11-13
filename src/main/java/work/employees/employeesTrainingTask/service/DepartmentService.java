package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.DepartmentResponse;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.response.responseMapper.ResponseMapper;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ResponseMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ResponseMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<DepartmentResponse> getAllDepartments(String order) {
        List<Department> entityList = (List<Department>) departmentRepository.findAll();
        return order.equalsIgnoreCase("desc") ?
                entityList.stream().sorted(Comparator.comparing(Department::getDepartmentName).reversed()).map(mapper::createResponseFromDepartmentEntity).collect(toList()) :
                entityList.stream().sorted(Comparator.comparing(Department::getDepartmentName)).map(mapper::createResponseFromDepartmentEntity).collect(toList());
    }

    public List<SimpleEmployeeResponse> getEmployeesByDepartmentName(String departmentName, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<SimpleEmployeeResponse> responseList = employeeRepository.getEmployeesByDepartmentName(departmentName, paging).stream().map(mapper::createSimpleEmployeeResponse).collect(toList());
        if (responseList.isEmpty()) {
            throw new ItemNotFoundException("Employees with department name " + departmentName + " not found");
        }
        return responseList;
    }


}
