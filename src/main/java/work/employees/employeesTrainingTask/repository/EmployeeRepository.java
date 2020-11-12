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

    @Query(value = "SELECT * FROM employees AS e JOIN titles AS t ON e.emp_no = t.emp_no WHERE title = :title AND to_date > NOW()", nativeQuery = true)
    List<Employee> getEmployeesByTitle(@Param("title") String title);

    @Query(value = "SELECT MAX(emp_no) FROM employees", nativeQuery = true)
    Integer getMaxId();


}
