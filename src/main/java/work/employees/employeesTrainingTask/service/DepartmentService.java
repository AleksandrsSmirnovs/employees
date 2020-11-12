package work.employees.employeesTrainingTask.service;

import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.response.DepartmentResponse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponse> getAllDepartments(String order) {
        List<Department> entityList = (List<Department>) departmentRepository.findAll();
        return order.equalsIgnoreCase("desc") ?
                entityList.stream().sorted(Comparator.comparing(Department::getDepartmentName).reversed()).map(this::createResponseFromDepartmentEntity).collect(toList()) :
                entityList.stream().sorted(Comparator.comparing(Department::getDepartmentName)).map(this::createResponseFromDepartmentEntity).collect(toList());
    }

    private DepartmentResponse createResponseFromDepartmentEntity(Department entity) {
        return new DepartmentResponse(entity.getDepartmentNumber(), entity.getDepartmentName());
    }

}
