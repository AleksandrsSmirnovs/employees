package work.employees.employeesTrainingTask.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import work.employees.employeesTrainingTask.domain.Department;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository victim;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldFindAllDepartmentsAsc() {
        Department dep1 = new Department("d001", "TestDep1");
        Department dep2 = new Department("d002", "TestDep2");
        Department dep3 = new Department("d003", "TestDep3");
        entityManager.persistAndFlush(dep1);
        entityManager.persistAndFlush(dep3);
        entityManager.persistAndFlush(dep2);
        List<Department> expected = List.of(dep1, dep2, dep3);
        List<Department> actual = victim.findAllByOrderByDepartmentNameAsc();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllDepartmentsDesc() {
        Department dep1 = new Department("d001", "TestDep1");
        Department dep2 = new Department("d002", "TestDep2");
        Department dep3 = new Department("d003", "TestDep3");
        entityManager.persistAndFlush(dep1);
        entityManager.persistAndFlush(dep3);
        entityManager.persistAndFlush(dep2);
        List<Department> expected = List.of(dep3, dep2, dep1);
        List<Department> actual = victim.findAllByOrderByDepartmentNameDesc();
        assertEquals(expected, actual);
    }

}