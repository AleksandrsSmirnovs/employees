package work.employees.employeesTrainingTask.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.DepartmentEmployeeId;
import work.employees.employeesTrainingTask.domain.embeddableId.DepartmentManagerId;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleEmployee;


@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private EmployeeRepository victim;

//    @Test
//    public void shouldNotThrowExceptionWhenSavingEmployee() throws ParseException {
//        assertThatCode(() -> victim.save(new Employee(123,
//                dateFormatter.parse("1981-01-01"),
//                "name",
//                "lastName",
//                'M',
//                dateFormatter.parse("2001-01-01"),
//                List.of(new Department("d001", "testDep1", List.of(
//                        new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"), null, null, null, null),
//                        new Employee(234, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"), null, null, null, null),
//                        new Employee(345, dateFormatter.parse("1983-03-03"), "Name3", "LastName3", 'M', dateFormatter.parse("2003-03-03"), null, null, null, null)))),
//                List.of(new Department(new DepartmentManagerId("d001", 123)),
//                List.of(new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03")),
//                        new Salary(new SalaryId(123, dateFormatter.parse("2003-03-03")), 54321, dateFormatter.parse("2005-05-05"))),
//                List.of(new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03"))))))
//                .doesNotThrowAnyException();
//    }

    @Test
    public void shouldFindMaxEmployeeNumber() {
        assertEquals(victim.getMaxId(), 333);
    }



//    @Test
//    public void shouldFindEmployeesByTitleWithoutParams() throws ParseException {
//        List<Employee> actual = victim.getEmployeesByTitle("Title3", "%", "9999-01-01", "0000-01-01", PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "emp_no")));
//        List<Employee> expected = List.of(
//                new Employee(222, valueOf("1982-02-02 00:00:00.0"), "Name2", "LastName2", 'F', valueOf("2002-02-02 00:00:00.0"),
//                        List.of(new Department("d002", "TestDept2")), new ArrayList<>(),
//                        List.of(new Salary(222, 34567, valueOf("2001-02-03 00:00:00.0"), valueOf("2009-09-09 00:00:00.0"))),
//                        List.of(new Title(222, "Title3", valueOf("2001-02-03 00:00:00.0"), valueOf("2023-06-07 00:00:00.0")))),
//                new Employee(333, valueOf("1983-03-03 00:00:00.0"), "Name3", "LastName3", 'M', valueOf("2003-03-03 00:00:00.0"),
//                        List.of(new Department("d003", "TestDept3")), new ArrayList<>(),
//                        List.of(new Salary(333, 45678, valueOf("2001-02-03 00:00:00.0"), valueOf("2004-05-06 00:00:00.0"))),
//                        List.of(new Title(333, "Title3", valueOf("2001-02-03 00:00:00.0"), valueOf("2025-06-07 00:00:00.0"))))
//        );
//        assertThat(actual).contains(new Employee(222, valueOf("1982-02-02 00:00:00.0"), "Name2", "LastName2", 'F', valueOf("2002-02-02 00:00:00.0"),
//                List.of(new Department("d002", "TestDept2")), new ArrayList<>(),
//                List.of(new Salary(222, 34567, valueOf("2001-02-03 00:00:00.0"), valueOf("2009-09-09 00:00:00.0"))),
//                List.of(new Title(222, "Title3", valueOf("2001-02-03 00:00:00.0"), valueOf("2023-06-07 00:00:00.0")))));
//        assertThat(actual).contains(new Employee(333, valueOf("1983-03-03 00:00:00.0"), "Name3", "LastName3", 'M', valueOf("2003-03-03 00:00:00.0"),
//                List.of(new Department("d003", "TestDept3")), new ArrayList<>(),
//                List.of(new Salary(333, 45678, valueOf("2001-02-03 00:00:00.0"), valueOf("2004-05-06 00:00:00.0"))),
//                List.of(new Title(333, "Title3", valueOf("2001-02-03 00:00:00.0"), valueOf("2025-06-07 00:00:00.0")))));
//    }


}