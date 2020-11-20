package work.employees.employeesTrainingTask.service.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.response.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseMapperTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ResponseMapper victim;


    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void init() {
        victim = new ResponseMapper(departmentRepository);
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
    public void shouldCreateDepartmentResponse() {
        DepartmentResponse expected = sampleDepartmentResponse();
        DepartmentResponse actual = victim.createDepartmentResponse(sampleDepartment());
        assertEquals(actual, expected);
    }

    @Test
    public void shouldCreateEmployeeFromCreateRequest() throws ParseException {
        when(departmentRepository.save(any())).thenReturn(new Department());
        Employee employee = victim.createEmployeeFromCreateRequest(sampleCreateEmployeeRequest());
        assertThat(employee.getEmployeeNumber()).isEqualTo(123);
        assertThat(employee.getBirthDate()).isEqualTo(dateFormatter.parse("1981-01-01"));
        assertThat(employee.getFirstName()).isEqualTo("Name1");
        assertThat(employee.getLastName()).isEqualTo("LastName1");
        assertThat(employee.getGender()).isEqualTo('M');
        assertThat(employee.getHireDate()).isEqualTo(dateFormatter.parse("2001-01-01"));
        assertThat(employee.getDepartments().size()).isEqualTo(3);
        assertThat(employee.getDepartments()).contains(new DepartmentEmployee(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), employee, new Department("d001", "testDep1")));
        assertThat(employee.getDepartments()).contains(new DepartmentEmployee(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), employee, new Department("d002", "testDep2")));
        assertThat(employee.getDepartments()).contains(new DepartmentEmployee(dateFormatter.parse("2005-05-05"), dateFormatter.parse("2007-07-07"), employee, new Department("d003", "testDep3")));
        assertThat(employee.getManagedDepartments().size()).isEqualTo(2);
        assertThat(employee.getManagedDepartments()).contains(new DepartmentManager(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), employee, new Department("d001", "testDep1")));
        assertThat(employee.getManagedDepartments()).contains(new DepartmentManager(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), employee, new Department("d002", "testDep2")));
        assertThat(employee.getSalaries().size()).isEqualTo(2);
        assertThat(employee.getSalaries()).contains(new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03")));
        assertThat(employee.getSalaries()).contains(new Salary(new SalaryId(123, dateFormatter.parse("2003-03-03")), 54321, dateFormatter.parse("2005-05-05")));
        assertThat(employee.getTitles().size()).isEqualTo(2);
        assertThat(employee.getTitles()).contains(new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")));
        assertThat(employee.getTitles()).contains(new Title(new TitleId(123, "TestTitle2", dateFormatter.parse("2003-03-03")), dateFormatter.parse("2005-05-05")));
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

    @Test
    public void shouldCreateDepartmentManagerResponseListForEmployee() throws ParseException {
        List<DepartmentResponse> expected = List.of(
                new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))
        );
        List<DepartmentResponse> actual = victim.createDepartmentManagerResponseListForEmployee(sampleDepartmentManagerList());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateDepartmentResponseListForEmployee() throws ParseException {
        List<DepartmentResponse> expected = List.of(
                new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))
        );
        List<DepartmentResponse> actual = victim.createDepartmentResponseListForEmployee(sampleDepartmentEmployeeList());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateEmployeeResponse() throws ParseException {
        EmployeeResponse expected = sampleEmployeeResponse();
        EmployeeResponse actual = victim.createEmployeeResponse(new Employee(
                123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                List.of(
                        new DepartmentEmployee(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")), new Department("d001", "testDep1")),
                        new DepartmentEmployee(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")), new Department("d001", "testDep1"))
                ),
                List.of(
                        new DepartmentManager(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")), new Department("d001", "testDep1")),
                        new DepartmentManager(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")), new Department("d001", "testDep1"))
                ),
                List.of(
                        new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03")),
                        new Salary(new SalaryId(123, dateFormatter.parse("2003-03-03")), 54321, dateFormatter.parse("2005-05-05"))
                ),
                List.of(
                        new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                        new Title(new TitleId(123, "TestTitle2", dateFormatter.parse("2003-03-03")), dateFormatter.parse("2005-05-05"))
                )
        ));
    }


}