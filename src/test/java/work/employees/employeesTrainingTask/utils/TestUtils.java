package work.employees.employeesTrainingTask.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import work.employees.employeesTrainingTask.domain.*;
import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;
import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;
import work.employees.employeesTrainingTask.request.CreateEmployeeRequest;
import work.employees.employeesTrainingTask.response.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class TestUtils {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    //Entities
    public static Employee sampleEmployee() throws ParseException {
        return new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"));
    }

    public static Department sampleDepartment() {
        return new Department("d001", "testDep1");
    }

    public static Title sampleTitle() throws ParseException {
        return new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03"));
    }

    public static Salary sampleSalary() throws ParseException {
        return new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03"));
    }

    public static DepartmentEmployee sampleDepartmentEmployee() throws ParseException {
        return new DepartmentEmployee((dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment());
    }

    //Entity Lists
    public static List<Employee> sampleEmployeeList() throws ParseException {
        return List.of(
                new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")),
                new Employee(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"))
        );
    }

    public static List<Department> sampleDepartmentList() throws ParseException {
        return List.of(
                new Department("d001", "testDep1"),
                new Department("d002", "testDep2"),
                new Department("d003", "testDep3")
        );
    }

    public static List<Department> sampleDepartmentListReversed() throws ParseException {
        return List.of(
                new Department("d003", "testDep3"),
                new Department("d002", "testDep2"),
                new Department("d001", "testDep1")
        );
    }

    public static List<Salary> sampleSalaryList() throws ParseException {
        return List.of(
                new Salary(new SalaryId(123, dateFormatter.parse("2001-01-01")), 12345, dateFormatter.parse("2003-03-03")),
                new Salary(new SalaryId(321, dateFormatter.parse("2004-04-04")), 54321, dateFormatter.parse("2006-06-06"))
        );
    }

    public static List<Title> sampleTitleList() throws ParseException {
        return List.of(
                new Title(new TitleId(123, "TestTitle1", dateFormatter.parse("2001-01-01")), dateFormatter.parse("2003-03-03")),
                new Title(new TitleId(321, "TestTitle2", dateFormatter.parse("2004-04-04")), dateFormatter.parse("2006-06-06"))
        );
    }

    public static List<DepartmentEmployee> sampleDepartmentEmployeeList() throws ParseException {
        return List.of(
                new DepartmentEmployee( dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment()),
                new DepartmentEmployee( dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), sampleEmployee(), sampleDepartment())
        );
    }


    public static List<DepartmentManager> sampleDepartmentManagerList() throws ParseException {
        return List.of(
                new DepartmentManager( dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment()),
                new DepartmentManager( dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), sampleEmployee(), sampleDepartment())
        );
    }

    //Responses
    public static EmployeeResponse sampleEmployeeResponse() throws ParseException {
        return new EmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"), sampleDepartmentResponseList(), sampleDepartmentResponseList(), sampleSalaryResponseList(), sampleTitleResponseList());
    }

    public static SimpleEmployeeResponse sampleSimpleEmployeeResponse() throws ParseException {
        return new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"));
    }

    public static EmployeeDeleteResponse sampleEmployeeDeleteResponse() throws ParseException {
        return new EmployeeDeleteResponse("Employee deleted successfully", sampleSimpleEmployeeResponse());
    }

    public static DepartmentResponse sampleDepartmentResponse() {
        return new DepartmentResponse("d001", "testDep1");
    }


    public static SalaryResponse sampleSalaryResponse() throws ParseException {
        return new SalaryResponse(12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
    }

    public static TitleResponse sampleTitleResponse() throws ParseException {
        return new TitleResponse("TestTitle1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"));
    }

    //Response Lists

    public static List<EmployeeResponse> sampleEmployeeResponseList() throws ParseException {
        return List.of(
                new EmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"), sampleDepartmentResponseList(), sampleDepartmentResponseList(), sampleSalaryResponseList(), sampleTitleResponseList()),
                new EmployeeResponse(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"), sampleDepartmentResponseList(), sampleDepartmentResponseList(), sampleSalaryResponseList(), sampleTitleResponseList())
                );
    }

    public static List<DepartmentResponse> sampleDepartmentResponseList() throws ParseException {
        return List.of(
                new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new DepartmentResponse("d002", "testDep2", dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))
                );
    }

    public static List<SalaryResponse> sampleSalaryResponseList() throws ParseException {
        return List.of(
                new SalaryResponse(12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new SalaryResponse(54321, dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))
        );
    }

    public static List<TitleResponse> sampleTitleResponseList() throws ParseException {
        return List.of(
                new TitleResponse("TestTitle1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")),
                new TitleResponse("TestTitle2", dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))
        );
    }

    public static List<SimpleEmployeeResponse> sampleSimpleEmployeeResponseList() throws ParseException {
        return List.of(
                new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01")),
                new SimpleEmployeeResponse(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"))
        );
    }

    public static List<SimpleDepartmentResponse> sampleSimpleDepartmentResponseList() throws ParseException {
        return List.of(
                new SimpleDepartmentResponse("d001", "testDep1"),
                new SimpleDepartmentResponse("d002", "testDep2"),
                new SimpleDepartmentResponse("d001", "testDep1")
        );
    }


    //Other
    public static List<String> sampleTitleNameList() throws ParseException {
        return List.of("Title1", "Title2", "Title3");
    }


    public static EmployeeResponse sampleWrongEmployee() throws ParseException {
        return new EmployeeResponse(null, null, null, "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                List.of(new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03")), new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"))),
                List.of(new DepartmentResponse("d001", "testDep1", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"))),
                List.of(new SalaryResponse(12345, dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"))),
                List.of(new TitleResponse("TestTitle", dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"))));
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

    public static CreateEmployeeRequest sampleCreateEmployeeRequest() throws ParseException {
        return new CreateEmployeeRequest(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                null,
                null,
                sampleSalaryList(),
                sampleTitleList());
    }

//
//    public static void sampleDepartmentEmployeeList() throws ParseException {
//        employee.setDepartments(List.of(
//                new DepartmentEmployee(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), employee, sampleDepartment()),
//                new DepartmentEmployee(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), employee, sampleDepartment()),
//                new DepartmentEmployee(dateFormatter.parse("2005-05-05"), dateFormatter.parse("2007-07-07"), employee, sampleDepartment())
//        ));
//    }
////
//    public static List<DepartmentEmployee> sampleDepartmentEmployeeListReversed() throws ParseException {
//        return List.of(
//                new DepartmentEmployee(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment()),
//                new DepartmentEmployee(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), sampleEmployee(), sampleDepartment()),
//                new DepartmentEmployee(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment())
//        );
//    }
//
//    public static List<DepartmentManager> sampleDepartmentManagerList() throws ParseException {
//        return List.of(
//                new DepartmentManager(dateFormatter.parse("2001-01-01"), dateFormatter.parse("2003-03-03"), sampleEmployee(), sampleDepartment()),
//                new DepartmentManager(dateFormatter.parse("2003-03-03"), dateFormatter.parse("2005-05-05"), sampleEmployee(), sampleDepartment()),
//                new DepartmentManager(dateFormatter.parse("2005-05-05"), dateFormatter.parse("2007-07-07"), sampleEmployee(), sampleDepartment())
//        );
//    }
//

////
//    public static List<Employee> sampleShortEmployeeList() throws ParseException {
//        return List.of(
//                new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"), null, null, null, null),
//                new Employee(234, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02"), null, null, null, null),
//                new Employee(345, dateFormatter.parse("1983-03-03"), "Name3", "LastName3", 'M', dateFormatter.parse("2003-03-03"), null, null, null, null)
//        );
//    }
//
//
//    public static SimpleDepartmentResponse sampleDepartmentResponse() throws ParseException {
//
//        return new SimpleDepartmentResponse("d001", "testDep1");
//    }

}
