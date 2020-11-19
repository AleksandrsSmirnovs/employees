package work.employees.employeesTrainingTask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.service.EmployeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    public void shouldFindAllEmployees() throws Exception {
        when(service.getAllEmployees(any(),any(),any(),any(),any(),any())).thenReturn(sampleSimpleEmployeeResponseList());
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeNumber").value(123))
                .andExpect(jsonPath("$[0].birthDate").value("1981-01-01"))
                .andExpect(jsonPath("$[0].firstName").value("Name1"))
                .andExpect(jsonPath("$[0].lastName").value("LastName1"))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].hireDate").value("2001-01-01" ))
                .andExpect(jsonPath("$[1].employeeNumber").value(321))
                .andExpect(jsonPath("$[1].birthDate").value("1982-02-02"))
                .andExpect(jsonPath("$[1].firstName").value("Name2"))
                .andExpect(jsonPath("$[1].lastName").value("LastName2"))
                .andExpect(jsonPath("$[1].gender").value("F"))
                .andExpect(jsonPath("$[1].hireDate").value("2002-02-02" ));
        verify(service, times(1)).getAllEmployees(any(),any(),any(),any(),any(),any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindEmployeeByEmployeeNumber() throws Exception {
        when(service.getEmployeeById(any())).thenReturn(sampleEmployeeResponse());
        mockMvc.perform(get("/employees/123"))
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
                .andExpect(jsonPath("$.managedDepartments", hasSize(2)))
                .andExpect(jsonPath("$.managedDepartments").isArray())
                .andExpect(jsonPath("$.managedDepartments[0].departmentNumber").value("d001"))
                .andExpect(jsonPath("$.managedDepartments[0].departmentName").value("testDep1"))
                .andExpect(jsonPath("$.managedDepartments[1].departmentNumber").value("d002"))
                .andExpect(jsonPath("$.managedDepartments[1].departmentName").value("testDep2"))
                .andExpect(jsonPath("$.salaries").isArray())
                .andExpect(jsonPath("$.salaries", hasSize(2)))
                .andExpect(jsonPath("$.salaries[0].salary").value(12345))
                .andExpect(jsonPath("$.salaries[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.salaries[0].toDate").value("2003-03-03"))
                .andExpect(jsonPath("$.salaries[1].salary").value(54321))
                .andExpect(jsonPath("$.salaries[1].fromDate").value("2003-03-03"))
                .andExpect(jsonPath("$.salaries[1].toDate").value("2005-05-05"))
                .andExpect(jsonPath("$.titles").isArray())
                .andExpect(jsonPath("$.titles", hasSize(2)))
                .andExpect(jsonPath("$.titles[0].title").value("TestTitle1"))
                .andExpect(jsonPath("$.titles[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.titles[0].toDate").value("2003-03-03"))
                .andExpect(jsonPath("$.titles[1].title").value("TestTitle2"))
                .andExpect(jsonPath("$.titles[1].fromDate").value("2003-03-03"))
                .andExpect(jsonPath("$.titles[1].toDate").value("2005-05-05"));
        verify(service, times(1)).getEmployeeById(123);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnEmployeeDeleteResponseWhenDeletingEmployeeById() throws Exception {
        when(service.deleteEmployee(any())).thenReturn(sampleEmployeeDeleteResponse());
        mockMvc.perform(delete("/employees/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee deleted successfully"))
                .andExpect(jsonPath("$.employee.employeeNumber").value(123))
                .andExpect(jsonPath("$.employee.birthDate").value("1981-01-01"))
                .andExpect(jsonPath("$.employee.firstName").value("Name1"))
                .andExpect(jsonPath("$.employee.lastName").value("LastName1"))
                .andExpect(jsonPath("$.employee.gender").value("M"))
                .andExpect(jsonPath("$.employee.hireDate").value("2001-01-01"));
        verify(service, times(1)).deleteEmployee(123);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnEmployeeResponseWhenSavingEmployee() throws Exception {
        when(service.saveEmployee(any())).thenReturn(sampleEmployeeResponse());
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(sampleJsonString(sampleEmployeeResponse()))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", "http://localhost/employees/123"))
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
                .andExpect(jsonPath("$.managedDepartments", hasSize(2)))
                .andExpect(jsonPath("$.managedDepartments").isArray())
                .andExpect(jsonPath("$.managedDepartments[0].departmentNumber").value("d001"))
                .andExpect(jsonPath("$.managedDepartments[0].departmentName").value("testDep1"))
                .andExpect(jsonPath("$.managedDepartments[1].departmentNumber").value("d002"))
                .andExpect(jsonPath("$.managedDepartments[1].departmentName").value("testDep2"))
                .andExpect(jsonPath("$.salaries").isArray())
                .andExpect(jsonPath("$.salaries", hasSize(2)))
                .andExpect(jsonPath("$.salaries[0].salary").value(12345))
                .andExpect(jsonPath("$.salaries[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.salaries[0].toDate").value("2003-03-03"))
                .andExpect(jsonPath("$.salaries[1].salary").value(54321))
                .andExpect(jsonPath("$.salaries[1].fromDate").value("2003-03-03"))
                .andExpect(jsonPath("$.salaries[1].toDate").value("2005-05-05"))
                .andExpect(jsonPath("$.titles").isArray())
                .andExpect(jsonPath("$.titles", hasSize(2)))
                .andExpect(jsonPath("$.titles[0].title").value("TestTitle1"))
                .andExpect(jsonPath("$.titles[0].fromDate").value("2001-01-01"))
                .andExpect(jsonPath("$.titles[0].toDate").value("2003-03-03"))
                .andExpect(jsonPath("$.titles[1].title").value("TestTitle2"))
                .andExpect(jsonPath("$.titles[1].fromDate").value("2003-03-03"))
                .andExpect(jsonPath("$.titles[1].toDate").value("2005-05-05"));
    }

    @Test
    public void shouldNotSaveEmployeeIfDataIsNotCorrect() throws Exception {
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(sampleJsonString(sampleWrongEmployee()))))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(service);
    }
}