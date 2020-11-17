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
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.response.SimpleEmployeeResponse;
import work.employees.employeesTrainingTask.service.TitleService;
import work.employees.employeesTrainingTask.utils.TestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleSimpleEmployeeResponseList;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleTitleNameList;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TitleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimpleDateFormat dateFormatter;

    @MockBean
    private TitleService service;

    @Test
    public void shouldFindAllTitles() throws Exception {
        when(service.getAllTitles(any())).thenReturn(sampleTitleNameList());
        mockMvc.perform(get("/titles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").value("Title1"))
                .andExpect(jsonPath("$[1]").value("Title2"))
                .andExpect(jsonPath("$[2]").value("Title3"));
        verify(service, times(1)).getAllTitles(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldFindEmployeesByTitle() throws Exception {
        when(service.getEmployeesByTitle(any(),any(),any(),any(),any(),any(),any())).thenReturn(sampleSimpleEmployeeResponseList());
        mockMvc.perform(get("/titles/testTitle/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
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
        verify(service, times(1)).getEmployeesByTitle(any(),any(),any(),any(),any(),any(),any());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void shouldReturnExceptionJsonWhenNoEmployeesFoundByTitle() throws Exception {
        when(service.getEmployeesByTitle(any(),any(),any(),any(),any(),any(),any())).thenThrow(new ItemNotFoundException("message"));
        mockMvc.perform(get("/titles/testTitle/employees"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("message"));
        verify(service, times(1)).getEmployeesByTitle(any(),any(),any(),any(),any(),any(),any());
        verifyNoMoreInteractions(service);
    }

}