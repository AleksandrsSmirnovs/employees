package work.employees.employeesTrainingTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Department;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, String> {



}
