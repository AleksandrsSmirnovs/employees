package work.employees.employeesTrainingTask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.exception.ItemAlreadyExistsException;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.response.responseMapper.ResponseMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ResponseMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ResponseMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        return mapper.createResponseFromEmployeeEntity(entity);
    }

    public List<SimpleEmployeeResponse> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy, String order, Specification<Employee> employeeSpec) {
        Pageable paging = PageRequest.of(pageNo, pageSize, order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());
        Page<Employee> pagedResult = employeeRepository.findAll(employeeSpec, paging);
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
        if (request.getEmployeeNumber() == null || request.getEmployeeNumber() > employeeRepository.getMaxId()) {
            request.setEmployeeNumber(employeeRepository.getMaxId() + 1);
        } else {
            throw new ItemAlreadyExistsException("Item with id " + request.getEmployeeNumber() + " already exists");
        }
        return mapper.createResponseFromEmployeeEntity(employeeRepository.save(mapper.createEmployeeFromCreateRequest(request)));
    }


}
