package work.employees.employeesTrainingTask.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Department;

import java.util.List;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, String> {

    public List<Department> findAllByOrderByDepartmentNameDesc();

    public List<Department> findAllByOrderByDepartmentNameAsc();

}
