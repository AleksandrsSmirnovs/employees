package work.employees.employeesTrainingTask.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import work.employees.employeesTrainingTask.exception.ItemAlreadyExistsException;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.service.utils.ResponseMapper;
import work.employees.employeesTrainingTask.service.utils.DataSorter;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private ResponseMapper mapper;

    @Mock
    private DataSorter sorter;

    @InjectMocks
    private EmployeeService victim;


    @Test
    public void shouldFindAllEmployeesWhenNoParametersPassed() throws ParseException {
        when(repository.findAllWithParams(any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getAllEmployees(0, 5, new String[]{}, null, null, null);
        verify(repository, times(1)).findAllWithParams("%", "9999-01-01", "0000-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldFindAllEmployeesWhenAllParametersPassed() throws ParseException {
        when(repository.findAllWithParams(any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getAllEmployees(0, 5, new String[]{}, 'M', "2001-01-01", "1999-01-01");
        verify(repository, times(1)).findAllWithParams("M", "2001-01-01", "1999-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldFindEmployeeById() throws ParseException {
        when(repository.findById(any())).thenReturn(Optional.of(sampleEmployee()));
        when(mapper.createEmployeeResponse(any())).thenReturn(sampleEmployeeResponse());
        victim.getEmployeeById(123);
        verify(repository, times(1)).findById(123);
        verify(mapper, times(1)).createEmployeeResponse(any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldThrowExceptionWhenCannotFindEmployeeById() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> victim.getEmployeeById(123))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("Employee with id 123 not found");
        verify(repository, times(1)).findById(123);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    public void shouldDeleteEmployeeAndReturnResponse() throws ParseException {
        when(repository.findById(any())).thenReturn(Optional.of(sampleEmployee()));
        when(mapper.createEmployeeDeleteResponse(any(), any())).thenReturn(sampleEmployeeDeleteResponse());
        victim.deleteEmployee(123);
        verify(repository, times(1)).findById(123);
        verify(repository, times(1)).deleteById(123);
        verify(mapper, times(1)).createEmployeeDeleteResponse(any(), any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToDeleteEmployeeThatDoesNotExist() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> victim.deleteEmployee(123)).isInstanceOf(ItemNotFoundException.class).hasMessage("Employee with id 123 not found");
        verify(repository, times(1)).findById(123);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    @Test
    public void shouldSaveEmployee() throws ParseException {
        when(repository.save(any())).thenReturn(sampleEmployee());
        when(mapper.createEmployeeFromCreateRequest(any())).thenReturn(sampleEmployee());
        when(mapper.createEmployeeResponse(any())).thenReturn(sampleEmployeeResponse());
        EmployeeResponse actual = victim.saveEmployee(sampleCreateEmployeeRequest());
        EmployeeResponse expected = sampleEmployeeResponse();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToSaveEmployeeWithExistingId() {
        when(repository.getMaxId()).thenReturn(123);
        assertThatThrownBy(() -> victim.saveEmployee(sampleCreateEmployeeRequest()))
                .isInstanceOf(ItemAlreadyExistsException.class)
                .hasMessage("Item with id 123 already exists");
    }
}