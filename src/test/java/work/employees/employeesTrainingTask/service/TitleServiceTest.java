package work.employees.employeesTrainingTask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.repository.TitleRepository;
import work.employees.employeesTrainingTask.service.utils.DataSorter;
import work.employees.employeesTrainingTask.service.utils.ResponseMapper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleEmployeeList;
import static work.employees.employeesTrainingTask.utils.TestUtils.sampleSimpleEmployeeResponseList;

@RunWith(MockitoJUnitRunner.class)
public class TitleServiceTest {

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ResponseMapper mapper;

    @Mock
    private DataSorter sorter;

    @InjectMocks
    private TitleService victim;

    @Test
    public void shouldFindAllTitles() throws ParseException {
        when(titleRepository.getAllTitlesAsc()).thenReturn(List.of("title1", "title2", "title3"));
        List<String> expected = List.of("title1", "title2", "title3");
        List<String> actual = victim.getAllTitles("");
        assertEquals(actual, expected);
        verify(titleRepository,times(1)).getAllTitlesAsc();
        verifyNoMoreInteractions(titleRepository);
    }

    @Test
    public void shouldFindAllTitlesSortedDesc() throws ParseException {
        when(titleRepository.getAllTitlesDesc()).thenReturn(List.of("title3", "title2", "title1"));
        List<String> expected = List.of("title3", "title2", "title1");
        List<String> actual = victim.getAllTitles("desc");
        assertEquals(actual, expected);
        verify(titleRepository,times(1)).getAllTitlesDesc();
        verifyNoMoreInteractions(titleRepository);
    }

    @Test
    public void shouldFindEmployeesByTitleWhenNoParamsPassed() throws ParseException {
        when(employeeRepository.getEmployeesByTitle(any(), any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getEmployeesByTitle("testTitle", 0, 5, new String[]{}, null, null, null);
        verify(employeeRepository, times(1)).getEmployeesByTitle("testTitle", "%", "9999-01-01", "0000-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(employeeRepository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldFindEmployeesByDepartmentNameWhenAllParametersPassed() throws ParseException {
        when(employeeRepository.getEmployeesByTitle(any(), any(), any(), any(), any())).thenReturn(sampleEmployeeList());
        when(sorter.getOrders(any())).thenReturn(List.of(new Sort.Order(Sort.Direction.ASC, "emp_no")));
        when(mapper.createSimpleEmployeeResponse(any())).thenReturn(sampleSimpleEmployeeResponseList().get(0));
        victim.getEmployeesByTitle("testTitle", 0, 5, new String[]{}, 'M', "2001-01-01", "1999-01-01");
        verify(employeeRepository, times(1)).getEmployeesByTitle("testTitle", "M", "2001-01-01", "1999-01-01", PageRequest.of(0, 5, Sort.by(new Sort.Order(Sort.Direction.ASC, "emp_no"))));
        verify(sorter, times(1)).getOrders(any());
        verify(mapper, times(2)).createSimpleEmployeeResponse(any());
        verifyNoMoreInteractions(employeeRepository);
        verifyNoMoreInteractions(sorter);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldThrowExceptionWhenNoEmployeesFoundByDepartmentName() {
        when(employeeRepository.getEmployeesByTitle(any(), any(), any(), any(), any())).thenReturn(new ArrayList<>());
        assertThatThrownBy(() -> victim.getEmployeesByTitle("testTitle", 0, 5, new String[]{}, null, null, null))
                .isInstanceOf(ItemNotFoundException.class)
                .hasMessage("Employees with title testTitle not found");
    }


}