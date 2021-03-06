package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.SimpleDepartmentResponse;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.service.utils.ResponseMapper;
import work.employees.employeesTrainingTask.service.utils.DataSorter;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ResponseMapper mapper;
    private final DataSorter sorter;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, ResponseMapper mapper, DataSorter sorter) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.sorter = sorter;
    }

    public List<SimpleDepartmentResponse> getAllDepartments(String order) {
        List<Department> entityList = order.equalsIgnoreCase("desc") ? departmentRepository.findAllByOrderByDepartmentNameDesc() : departmentRepository.findAllByOrderByDepartmentNameAsc();
        return entityList.stream().map(mapper::createSimpleDepartmentResponse).collect(toList());
    }

    public List<SimpleEmployeeResponse> getEmployeesByDepartmentName(String departmentName, Integer pageNo, Integer pageSize, String[] sort, Character gender, String hireDateBefore, String hireDateAfter) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sorter.getOrders(sort)));
        String genderParam = gender == null ? "%" : gender.toString();
        String hireDateBeforeParam = Objects.requireNonNullElse(hireDateBefore, "9999-01-01");
        String hireDateAfterParam = Objects.requireNonNullElse(hireDateAfter, "0000-01-01");
        List<SimpleEmployeeResponse> responseList = employeeRepository
                .getEmployeesByDepartmentName(departmentName, genderParam, hireDateBeforeParam, hireDateAfterParam, paging)
                .stream()
                .map(mapper::createSimpleEmployeeResponse)
                .collect(toList());
        if (responseList.isEmpty()) {
            throw new ItemNotFoundException("Employees with department name " + departmentName + " not found");
        }
        return responseList;
    }


}
