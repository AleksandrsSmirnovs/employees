package work.employees.employeesTrainingTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

    @Query(value = "SELECT * FROM employees WHERE emp_no IN (SELECT emp_no FROM titles WHERE title = :title)", nativeQuery = true)
    List<Employee> getEmployeesByTitle(@Param("title") String title);

    @Query(value = "SELECT MAX(emp_no) FROM employees", nativeQuery = true)
    Integer getMaxId();
}
