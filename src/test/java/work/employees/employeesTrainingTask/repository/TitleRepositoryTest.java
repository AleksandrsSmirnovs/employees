package work.employees.employeesTrainingTask.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleEmployee;

@DataJpaTest
@RunWith(SpringRunner.class)
class TitleRepositoryTest {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TitleRepository victim;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldReturnDistinctTitlesAsc() throws ParseException {
        Employee employee1 = sampleEmployee();
        Employee employee2 = sampleEmployee();
        Employee employee3 = sampleEmployee();
        employee1.setEmployeeNumber(1);
        employee2.setEmployeeNumber(2);
        employee3.setEmployeeNumber(3);
        employee1.setTitles(List.of(
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2006-06-06")))
        );
        employee2.setTitles(List.of(
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        employee3.setTitles(List.of(
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2033-03-03")),
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        entityManager.persistAndFlush(employee1);
        entityManager.persistAndFlush(employee2);
        entityManager.persistAndFlush(employee3);
        List<String> expected = List.of("TestTitle1", "TestTitle2", "TestTitle3");
        List<String> actual = victim.getAllTitlesAsc();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnDistinctTitlesDesc() throws ParseException {
        Employee employee1 = sampleEmployee();
        Employee employee2 = sampleEmployee();
        Employee employee3 = sampleEmployee();
        employee1.setEmployeeNumber(1);
        employee2.setEmployeeNumber(2);
        employee3.setEmployeeNumber(3);
        employee1.setTitles(List.of(
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2006-06-06")))
        );
        employee2.setTitles(List.of(
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        employee3.setTitles(List.of(
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2033-03-03")),
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        entityManager.persistAndFlush(employee1);
        entityManager.persistAndFlush(employee2);
        entityManager.persistAndFlush(employee3);
        List<String> expected = List.of("TestTitle3", "TestTitle2", "TestTitle1");
        List<String> actual = victim.getAllTitlesDesc();
        assertEquals(expected, actual);
    }



}