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
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleEmployee;


@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeRepositoryTest {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository victim;

    @Autowired
    private DepartmentRepository departmentRepository;


    @Test
    public void shouldFindMaxEmployeeNumber() throws ParseException {
        Employee employee1 = sampleEmployee();
        Employee employee2 = sampleEmployee();
        Employee employee3 = sampleEmployee();
        employee1.setEmployeeNumber(111);
        employee2.setEmployeeNumber(222);
        employee3.setEmployeeNumber(333);
        entityManager.persistAndFlush(employee1);
        entityManager.persistAndFlush(employee2);
        entityManager.persistAndFlush(employee3);
        assertEquals(333, victim.getMaxId());
    }

    @Test
    public void shouldReturnEmployeeById() throws ParseException {
        Employee employee = sampleEmployee();
        entityManager.persistAndFlush(employee);
        Employee found = victim.findById(employee.getEmployeeNumber()).orElse(new Employee());
        assertEquals(employee, found);
    }


    @Test
    public void shouldGetEmployeesByTitleWithoutExtraParams() throws ParseException {
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
        List<Employee> expected = List.of(employee2, employee3);
        List<Employee> actual = victim.getEmployeesByTitle("TestTitle3","%","9999-01-01","0000-01-01",PageRequest.of(0, 10, Sort.by(new ArrayList<>())));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetEmployeesByTitleWithParams() throws ParseException {
        Employee employee1 = sampleEmployee();
        Employee employee2 = sampleEmployee();
        Employee employee3 = sampleEmployee();
        Employee employee4 = sampleEmployee();
        Employee employee5 = sampleEmployee();
        employee1.setGender('F');
        employee2.setGender('M');
        employee3.setGender('F');
        employee4.setGender('F');
        employee5.setGender('M');
        employee1.setHireDate(dateFormatter.parse("1990-01-01"));
        employee2.setHireDate(dateFormatter.parse("1992-01-01"));
        employee3.setHireDate(dateFormatter.parse("1994-01-01"));
        employee4.setHireDate(dateFormatter.parse("1996-01-01"));
        employee5.setHireDate(dateFormatter.parse("1998-01-01"));
        employee1.setEmployeeNumber(1);
        employee2.setEmployeeNumber(2);
        employee3.setEmployeeNumber(3);
        employee4.setEmployeeNumber(4);
        employee5.setEmployeeNumber(5);
        employee1.setTitles(List.of(
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2053-03-03")),
                new Title(new TitleId(employee1.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2006-06-06")))
        );
        employee2.setTitles(List.of(
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2043-03-03")),
                new Title(new TitleId(employee2.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        employee3.setTitles(List.of(
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2033-03-03")),
                new Title(new TitleId(employee3.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        employee4.setTitles(List.of(
                new Title(new TitleId(employee4.getEmployeeNumber(), "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2033-03-03")),
                new Title(new TitleId(employee4.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        employee5.setTitles(List.of(
                new Title(new TitleId(employee5.getEmployeeNumber(), "TestTitle2", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2033-03-03")),
                new Title(new TitleId(employee5.getEmployeeNumber(), "TestTitle3", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2026-06-06")))
        );
        entityManager.persistAndFlush(employee1);
        entityManager.persistAndFlush(employee2);
        entityManager.persistAndFlush(employee3);
        entityManager.persistAndFlush(employee4);
        entityManager.persistAndFlush(employee5);
        List<Employee> expected = List.of(employee3);
        List<Employee> actual = victim.getEmployeesByTitle("TestTitle1","F","1995-01-01","1991-01-01", PageRequest.of(0, 10, Sort.by(new ArrayList<>())));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllWithParams() throws ParseException {
        Employee employee1 = sampleEmployee();
        Employee employee2 = sampleEmployee();
        Employee employee3 = sampleEmployee();
        Employee employee4 = sampleEmployee();
        Employee employee5 = sampleEmployee();
        employee1.setGender('F');
        employee2.setGender('F');
        employee3.setGender('M');
        employee4.setGender('F');
        employee5.setGender('F');
        employee1.setHireDate(dateFormatter.parse("1990-01-01"));
        employee2.setHireDate(dateFormatter.parse("1992-01-01"));
        employee3.setHireDate(dateFormatter.parse("1994-01-01"));
        employee4.setHireDate(dateFormatter.parse("1996-01-01"));
        employee5.setHireDate(dateFormatter.parse("1998-01-01"));
        employee1.setEmployeeNumber(1);
        employee2.setEmployeeNumber(2);
        employee3.setEmployeeNumber(3);
        employee4.setEmployeeNumber(4);
        employee5.setEmployeeNumber(5);
        entityManager.persistAndFlush(employee1);
        entityManager.persistAndFlush(employee2);
        entityManager.persistAndFlush(employee3);
        entityManager.persistAndFlush(employee4);
        entityManager.persistAndFlush(employee5);
        List<Employee> expected = List.of(employee2, employee4);
        List<Employee> actual = victim.findAllWithParams("F","1997-01-01","1991-01-01", PageRequest.of(0, 10, Sort.by(new ArrayList<>())));
        assertEquals(expected, actual);
    }








}