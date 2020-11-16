package work.employees.employeesTrainingTask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.service.EmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleDateFormat dateFormatter;

    @MockBean
    private EmployeeService service;

    @Test
    public void shouldFindAllEmployees() throws Exception {
        when(service.getAllEmployees(any(),any(),any(),any(),any(),any())).thenReturn(sampleEmployeeList());
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].employeeNumber").value(123))
                .andExpect(jsonPath("$[0].birthDate").value("1981-01-01"))
                .andExpect(jsonPath("$[0].firstName").value("Name1"))
                .andExpect(jsonPath("$[0].lastName").value("LastName1"))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].hireDate").value("2001-01-01" ))
                .andExpect(jsonPath("$[1].employeeNumber").value(234))
                .andExpect(jsonPath("$[1].birthDate").value("1982-02-02"))
                .andExpect(jsonPath("$[1].firstName").value("Name2"))
                .andExpect(jsonPath("$[1].lastName").value("LastName2"))
                .andExpect(jsonPath("$[1].gender").value("F"))
                .andExpect(jsonPath("$[1].hireDate").value("2002-02-02" ))
                .andExpect(jsonPath("$[2].employeeNumber").value(345))
                .andExpect(jsonPath("$[2].birthDate").value("1983-03-03"))
                .andExpect(jsonPath("$[2].firstName").value("Name3"))
                .andExpect(jsonPath("$[2].lastName").value("LastName3"))
                .andExpect(jsonPath("$[2].gender").value("M"))
                .andExpect(jsonPath("$[2].hireDate").value("2003-03-03" ));
        verify(service, times(1)).getAllEmployees(any(),any(),any(),any(),any(),any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindEmployeeByEmployeeNumber() throws Exception {
        when(service.getEmployeeById(any())).thenReturn(sampleEmployeeResponse());
        mockMvc.perform(get("/employees/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeNumber").value(123))
                .andExpect(jsonPath("$.birthDate").value("1981-01-01"))
                .andExpect(jsonPath("$.firstName").value("Name1"))
                .andExpect(jsonPath("$.lastName").value("LastName1"))
                .andExpect(jsonPath("$.gender").value("M"))
                .andExpect(jsonPath("$.hireDate").value("2001-01-01" ))
                .andExpect(jsonPath("$.departments", hasSize(2)))
                .andExpect(jsonPath("$.departments").isArray())
                .andExpect(jsonPath("$.departments[0].departmentNumber").value("d001"))
                .andExpect(jsonPath("$.departments[0].departmentName").value("testDep1"))
                .andExpect(jsonPath("$.departments[1].departmentNumber").value("d002"))
                .andExpect(jsonPath("$.departments[1].departmentName").value("testDep2"))
                .andExpect(jsonPath("$.managedDepartments", hasSize(1)))
                .andExpect(jsonPath("$.managedDepartments").isArray())
                .andExpect(jsonPath("$.managedDepartments[0].departmentNumber").value("d001"))
                .andExpect(jsonPath("$.managedDepartments[0].departmentName").value("testDep1"))
                .andExpect(jsonPath("$.salaries").isArray())
                .andExpect(jsonPath("$.salaries", hasSize(1)))
                .andExpect(jsonPath("$.salaries[0].salary").value(12345))
                .andExpect(jsonPath("$.salaries[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.salaries[0].toDate").value("2003-03-03"))
                .andExpect(jsonPath("$.titles").isArray())
                .andExpect(jsonPath("$.titles", hasSize(1)))
                .andExpect(jsonPath("$.titles[0].title").value("TestTitle"))
                .andExpect(jsonPath("$.titles[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.titles[0].toDate").value("2003-03-03"));
        verify(service, times(1)).getEmployeeById(any());
        verifyNoMoreInteractions(service);
    }

    private EmployeeResponse sampleEmployeeResponse() throws ParseException {
        return new EmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01" ),
                List.of(new DepartmentResponse("d001", "testDep1"), new DepartmentResponse("d002", "testDep2")),
                List.of(new DepartmentResponse("d001", "testDep1")),
                List.of(new SalaryResponse(12345, dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))),
                List.of(new TitleResponse("TestTitle", dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))));
    }

    private List<SimpleEmployeeResponse> sampleEmployeeList() throws ParseException {
        return List.of(
                new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01" )),
                new SimpleEmployeeResponse(234, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02" )),
                new SimpleEmployeeResponse(345, dateFormatter.parse("1983-03-03"), "Name3", "LastName3", 'M', dateFormatter.parse("2003-03-03" ))
        );
    }
}