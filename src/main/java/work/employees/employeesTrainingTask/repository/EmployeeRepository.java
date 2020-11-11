package work.employees.employeesTrainingTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
