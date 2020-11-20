package work.employees.employeesTrainingTask.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import work.employees.employeesTrainingTask.service.DepartmentService;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @Test
    public void shouldFindAllDepartments() throws Exception {
        when(service.getAllDepartments(any())).thenReturn(sampleSimpleDepartmentResponseList());
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].departmentNumber").value("d001"))
                .andExpect(jsonPath("$[0].departmentName").value("testDep1"))
                .andExpect(jsonPath("$[1].departmentNumber").value("d002"))
                .andExpect(jsonPath("$[1].departmentName").value("testDep2"));
        verify(service, times(1)).getAllDepartments(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindEmployeesByDepartmentName() throws Exception {
        when(service.getEmployeesByDepartmentName(any(), any(), any(), any(), any(), any(), any())).thenReturn(sampleSimpleEmployeeResponseList());
        mockMvc.perform(get("/departments/testDepartment/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeNumber").value(123))
                .andExpect(jsonPath("$[0].birthDate").value("1981-01-01"))
                .andExpect(jsonPath("$[0].firstName").value("Name1"))
                .andExpect(jsonPath("$[0].lastName").value("LastName1"))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[0].hireDate").value("2001-01-01"))
                .andExpect(jsonPath("$[1].employeeNumber").value(321))
                .andExpect(jsonPath("$[1].birthDate").value("1982-02-02"))
                .andExpect(jsonPath("$[1].firstName").value("Name2"))
                .andExpect(jsonPath("$[1].lastName").value("LastName2"))
                .andExpect(jsonPath("$[1].gender").value("F"))
                .andExpect(jsonPath("$[1].hireDate").value("2002-02-02"));
        verify(service, times(1)).getEmployeesByDepartmentName(any(), any(), any(), any(), any(), any(), any());
        verifyNoMoreInteractions(service);
    }


}