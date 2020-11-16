package work.employees.employeesTrainingTask.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    @Query(value = "SELECT * FROM employees AS e JOIN titles AS t ON e.emp_no = t.emp_no WHERE title = :title AND to_date > NOW() AND gender LIKE :gender AND hire_date < :hireDateBefore AND hire_date > :hireDateAfter", nativeQuery = true)
    List<Employee> getEmployeesByTitle(@Param("title") String title, @Param("gender") String gender, @Param("hireDateBefore") String hireDateBefore, @Param("hireDateAfter") String hireDateAfter, Pageable pageable);

    @Query(value = "SELECT MAX(emp_no) FROM employees", nativeQuery = true)
    Integer getMaxId();

    @Query(value = "SELECT * FROM employees AS e JOIN dept_emp AS de ON e.emp_no = de.emp_no JOIN departments AS d ON de.dept_no = d.dept_no WHERE d.dept_name = :departmentName AND de.to_date > NOW()", nativeQuery = true)
    List<Employee> getEmployeesByDepartmentName(@Param("departmentName") String departmentName, Pageable paging);

    @Query(value = "SELECT * FROM employees WHERE gender LIKE :gender AND hire_date < :hireDateBefore AND hire_date > :hireDateAfter", nativeQuery = true)
    List<Employee> findAllWithParams(@Param("gender") String gender, @Param("hireDateBefore") String hireDateBefore, @Param("hireDateAfter") String hireDateAfter, Pageable pageable);

}
