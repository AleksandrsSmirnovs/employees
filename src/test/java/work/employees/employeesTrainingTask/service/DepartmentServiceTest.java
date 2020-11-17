package work.employees.employeesTrainingTask.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.DepartmentRepository;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.DepartmentResponse;
import work.employees.employeesTrainingTask.service.utils.DataSorter;
import work.employees.employeesTrainingTask.service.utils.ResponseMapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleDepartmentList;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleEmployeeList;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {


    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ResponseMapper mapper;

    @Mock
    private DataSorter sorter;

    @InjectMocks
    private DepartmentService victim;

    @Test
    public void shouldFindAllDepartments() throws ParseException {
        when(departmentRepository.findAll()).thenReturn(sampleDepartmentList());
        when(mapper.createDepartmentResponse(any())).thenReturn(sampleDepartmentResponse());
        List<DepartmentResponse> expected = List.of(
                new DepartmentResponse("d001", "testDep1"),
                new DepartmentResponse("d001", "testDep1"),
                new DepartmentResponse("d001", "testDep1")
        );
        List<DepartmentResponse> actual = victim.getAllDepartments("");
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldFindEmployeesByDepartmentNameWhenNoParamsPassed() throws ParseException {
        when(employeeRepository.getEmployeesByDepartmentName(any(), any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getEmployeesByDepartmentName("testDepartment", 0, 5, new String[]{}, null, null, null);
        verify(employeeRepository, times(1)).getEmployeesByDepartmentName("testDepartment", "%", "9999-01-01", "0000-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(employeeRepository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldFindEmployeesByDepartmentNameWhenAllParametersPassed() throws ParseException {
        when(employeeRepository.getEmployeesByDepartmentName(any(), any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getEmployeesByDepartmentName("testDepartment", 0, 5, new String[]{}, 'M', "2001-01-01", "1999-01-01");
        verify(employeeRepository, times(1)).getEmployeesByDepartmentName("testDepartment", "M", "2001-01-01", "1999-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(employeeRepository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldThrowExceptionWhenNoEmployeesFoundByDepartmentName() {
        when(employeeRepository.getEmployeesByDepartmentName(any(), any(), any(), any(), any())).thenReturn(new ArrayList<>());
        assertThatThrownBy(() -> victim.getEmployeesByDepartmentName("testDepartment", 0, 5, new String[]{}, null, null, null))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("Employees with department name testDepartment not found");
    }
}