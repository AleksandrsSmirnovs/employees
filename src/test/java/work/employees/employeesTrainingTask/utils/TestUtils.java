package work.employees.employeesTrainingTask.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class TestUtils {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Employee sampleEmployee() throws ParseException {
        return new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                sampleDepartmentList(),
                sampleManagedDepartmentList(),
                sampleSalaryList(),
                sampleTitleList());
    }

    public static CreateEmployeeRequest sampleCreateEmployeeRequest() throws ParseException {
        return new CreateEmployeeRequest(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                sampleDepartmentList(),
                sampleManagedDepartmentList(),
                sampleSalaryList(),
                sampleTitleList());
    }

    public static EmployeeDeleteResponse sampleEmployeeDeleteResponse() throws ParseException {
        return new EmployeeDeleteResponse("Employee deleted successfully", sampleSimpleEmployeeResponse());
    }

    public static EmployeeResponse sampleEmployeeResponse() throws ParseException {
        return new EmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                List.of(new DepartmentResponse("d001", "testDep1"), new DepartmentResponse("d002", "testDep2")),
                List.of(new DepartmentResponse("d001", "testDep1")),
                List.of(new SalaryResponse(12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"))),
                List.of(new TitleResponse("TestTitle", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"))));
    }

    public static List<Employee> sampleEmployeeList() throws ParseException {
        return List.of(
                new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                        sampleDepartmentList(),
                        sampleManagedDepartmentList(),
                        sampleSalaryList(),
                        sampleTitleList()),
                new Employee(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"),
                        sampleDepartmentList(),
                        sampleManagedDepartmentList(),
                        sampleSalaryList(),
                        sampleTitleList())
        );
    }

    public static List<Department> sampleDepartmentList() throws ParseException {
        return List.of(
                new Department("d001", "testDep1", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d002", "testDep2", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d003", "testDep3", sampleShortEmployeeList(), sampleShortEmployeeList())
        );
    }

    public static Department sampleDepartment() throws ParseException {
        return new Department("d001", "testDep1", sampleShortEmployeeList(), sampleShortEmployeeList());
    }

    public static List<Department> sampleManagedDepartmentList() throws ParseException {
        return List.of(
                new Department("d001", "testDep1", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d002", "testDep2", sampleShortEmployeeList(), sampleShortEmployeeList())
        );
    }

    public static List<Salary> sampleSalaryList() throws ParseException {
        return List.of(
                new Salary(123, 12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new Salary(321, 54321, dateFormatter.parse("2004-04-04"), dateFormatter.parse("2006-06-06"))
        );
    }

    public static Salary sampleSalary() throws ParseException {
        return new Salary(123, 12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
    }

    public static SalaryResponse sampleSalaryResponse() throws ParseException {
        return new SalaryResponse(12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
    }

    public static List<Title> sampleTitleList() throws ParseException {
        return List.of(
                new Title(123, "TestTitle1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new Title(321, "TestTitle2", dateFormatter.parse("2004-04-04"), dateFormatter.parse("2006-06-06"))
        );
    }

    public static Title sampleTitle() throws ParseException {
        return new Title(123, "TestTitle1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
    }

    public static List<Employee> sampleShortEmployeeList() throws ParseException {
        return List.of(
                new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"), null, null, null, null),
                new Employee(234, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"), null, null, null, null),
                new Employee(345, dateFormatter.parse("1983-03-03"), "Name3", "LastName3", 'M', dateFormatter.parse("2003-03-03"), null, null, null, null)
        );
    }


    public static List<SimpleEmployeeResponse> sampleSimpleEmployeeResponseList() throws ParseException {
        return List.of(
                new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")),
                new SimpleEmployeeResponse(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"))
        );
    }

    public static SimpleEmployeeResponse sampleSimpleEmployeeResponse() throws ParseException {
        return new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"));
    }


    public static List<DepartmentResponse> sampleDepartmentResponseList() throws ParseException {
        return List.of(
                new DepartmentResponse("d001", "testDep1"),
                new DepartmentResponse("d002", "testDep2"),
                new DepartmentResponse("d003", "testDep3")
        );
    }

    public static DepartmentResponse sampleDepartmentResponse() {
        return new DepartmentResponse("d001", "testDep1");
    }

    public static List<String> sampleTitleNameList() throws ParseException {
        return List.of("Title1", "Title2", "Title3");
    }

    public static EmployeeResponse sampleWrongEmployee() throws ParseException {
        return new EmployeeResponse(null, null, null, "LastName1", 'M', dateFormatter.parse("2001-01-01" ),
                List.of(new DepartmentResponse("d001", "testDep1"), new DepartmentResponse("d002", "testDep2")),
                List.of(new DepartmentResponse("d001", "testDep1")),
                List.of(new SalaryResponse(12345, dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))),
                List.of(new TitleResponse("TestTitle", dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))));
    }

    public static String sampleJsonString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
