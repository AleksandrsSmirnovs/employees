package work.employees.employeesTrainingTask.service.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
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

    @Test
    public void shouldCreateEmployeeFromCreateRequest() throws ParseException {
        Employee expected = new Employee(
                123,
                dateFormatter.parse("1981-01-01"),
                "Name1",
                "LastName1",
                'M',
                dateFormatter.parse("2001-01-01"),
                null,
                null,
                List.of(
                        new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03")),
                        new Salary(new SalaryId(321, dateFormatter.parse("2004-04-04")), 54321, dateFormatter.parse("2006-06-06"))
                ),
                List.of(
                        new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                        new Title(new TitleId(321, "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2006-06-06"))
                ));
        Employee actual = victim.createEmployeeFromCreateRequest(sampleCreateEmployeeRequest());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateEmployeeDeleteResponse() throws ParseException {
        EmployeeDeleteResponse expected = new EmployeeDeleteResponse(
                "message",
                new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"))
        );
        EmployeeDeleteResponse actual = victim.createEmployeeDeleteResponse("message", sampleEmployee());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateSimpleDepartmentResponse() {
        SimpleDepartmentResponse expected = new SimpleDepartmentResponse("d001", "testDep1");
        SimpleDepartmentResponse actual = victim.createSimpleDepartmentResponse(new Department("d001", "testDep1", null, null));
        assertEquals(expected, actual);
    }



}