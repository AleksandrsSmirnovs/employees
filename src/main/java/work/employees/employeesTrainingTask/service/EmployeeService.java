package work.employees.employeesTrainingTask.service;

import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.EmployeeResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse getEmployeeById(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        return createResponseFromEmployeeEntity(entity);
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> entityList = (List<Employee>) employeeRepository.findAll();
        return entityList.stream()
                .map(this::createResponseFromEmployeeEntity)
                .collect(toList());
    }

    public EmployeeResponse deleteEmployee(Integer id) {
        Employee entity = employeeRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Employee with id " + id + " not found"));
        employeeRepository.deleteById(id);
        return createResponseFromEmployeeEntity(entity);
    }

    private EmployeeResponse createResponseFromEmployeeEntity(Employee entity) {
        return new EmployeeResponse(entity.getEmployeeNumber(),
                entity.getBirthDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getHireDate());
    }



}
