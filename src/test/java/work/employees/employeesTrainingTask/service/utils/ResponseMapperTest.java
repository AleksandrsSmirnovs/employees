package work.employees.employeesTrainingTask.service.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;
import work.employees.employeesTrainingTask.response.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseMapperTest {

    private ResponseMapper victim;

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void init() {
        victim = new ResponseMapper();
    }

    @Test
    public void shouldCreateTitleResponse() throws ParseException {
        TitleResponse expected = new TitleResponse("TestTitle1");
        TitleResponse actual = victim.createTitleResponse(sampleTitle());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateSimpleEmployeeResponseFromEmployeeEntity() throws ParseException {
        SimpleEmployeeResponse expected = sampleSimpleEmployeeResponse();
        SimpleEmployeeResponse actual = victim.createSimpleEmployeeResponse(sampleEmployee());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateEmployeeResponseFromEmployeeEntity() throws ParseException {
        EmployeeResponse expected = sampleEmployeeResponse();
        EmployeeResponse actual = victim.createEmployeeResponse(new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                List.of(new Department("d001", "testDep1"), new Department("d002", "testDep2")),
                List.of(new Department("d001", "testDep1")),
                List.of(new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345,  dateFormatter.parse("2003-03-03"))),
                List.of(new Title(new TitleId(123, "TestTitle", dateFormatter.parse("2001-01-01")),  dateFormatter.parse("2003-03-03")))));
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateDepartmentResponseForEmployee() throws ParseException {
        DepartmentResponse expected = sampleDepartmentResponse();
        DepartmentResponse actual = victim.createDepartmentResponseForEmployee(sampleDepartment());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateSalaryResponseForEmployee() throws ParseException {
        SalaryResponse expected = sampleSalaryResponse();
        SalaryResponse actual = victim.createSalaryResponseForEmployee(sampleSalary());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateTitleResponseForEmployee() throws ParseException {
        TitleResponse expected = new TitleResponse("TestTitle1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
        TitleResponse actual = victim.createTitleResponseForEmployee(sampleTitle());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateDepartmentResponse() throws ParseException {
        DepartmentResponse expected = sampleDepartmentResponse();
        DepartmentResponse actual = victim.createDepartmentResponse(sampleDepartment());
        assertEquals(actual, expected);
    }




}