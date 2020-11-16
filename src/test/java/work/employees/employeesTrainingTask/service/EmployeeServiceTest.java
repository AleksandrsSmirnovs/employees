package work.employees.employeesTrainingTask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Employee;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.response.responseMapper.ResponseMapper;
import work.employees.employeesTrainingTask.service.utils.DataSorter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private SimpleDateFormat dateFormatter;

    @Mock
    private EmployeeRepository repository;

    @Mock
    private ResponseMapper mapper;

    @Mock
    private DataSorter sorter;

    @InjectMocks
    private EmployeeService victim;

    @Before
    public void init() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }

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
        when(mapper.createResponseFromEmployeeEntity(any())).thenReturn(sampleEmployeeResponse());
        victim.getEmployeeById(123);
        verify(repository, times(1)).findById(123);
        verify(mapper, times(1)).createResponseFromEmployeeEntity(any());
        verifyNoMoreInteractions(repository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    public void shouldThrowExceptionWhenCannotFindEmployeeById() throws ParseException {
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
    public void shouldThrowExceptionWhenTryingToDeleteEmployeeThatDoesNotExist() throws ParseException {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> victim.deleteEmployee(123)).isInstanceOf(ItemNotFoundException.class).hasMessage("Employee with id 123 not found");
        verify(repository, times(1)).findById(123);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(mapper);
    }

    private Employee sampleEmployee() throws ParseException {
        return new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01"),
                sampleDepartmentList(),
                sampleManagedDepartmentList(),
                sampleSalaryList(),
                sampleTitleList());
    }

    private EmployeeDeleteResponse sampleEmployeeDeleteResponse() throws ParseException {
        return new EmployeeDeleteResponse("Employee deleted successfully", sampleSimpleEmployeeResponse());
    }

    private EmployeeResponse sampleEmployeeResponse() throws ParseException {
        return new EmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01" ),
                List.of(new DepartmentResponse("d001", "testDep1"), new DepartmentResponse("d002", "testDep2")),
                List.of(new DepartmentResponse("d001", "testDep1")),
                List.of(new SalaryResponse(12345, dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))),
                List.of(new TitleResponse("TestTitle", dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" ))));
    }

    private List<Employee> sampleEmployeeList() throws ParseException {
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

    private List<Department> sampleDepartmentList() throws ParseException {
        return List.of(
                new Department("d001", "testDep1", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d002", "testDep2", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d003", "testDep3", sampleShortEmployeeList(), sampleShortEmployeeList())
        );
    }

    private List<Department> sampleManagedDepartmentList() throws ParseException {
        return List.of(
                new Department("d001", "testDep1", sampleShortEmployeeList(), sampleShortEmployeeList()),
                new Department("d002", "testDep2", sampleShortEmployeeList(), sampleShortEmployeeList())
        );
    }

    private List<Salary> sampleSalaryList() throws ParseException {
        return List.of(
                new Salary(123, 12345, dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" )),
                new Salary(321, 54321, dateFormatter.parse("2004-04-04" ), dateFormatter.parse("2006-06-06" ))
        );
    }

    private List<Title> sampleTitleList() throws ParseException {
        return List.of(
                new Title(123, "TestTitle1", dateFormatter.parse("2001-01-01" ), dateFormatter.parse("2003-03-03" )),
                new Title(321, "TestTitle2", dateFormatter.parse("2004-04-04" ), dateFormatter.parse("2006-06-06" ))
        );
    }

    private List<Employee> sampleShortEmployeeList() throws ParseException {
        return List.of(
                new Employee(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01") , null, null, null, null),
                new Employee(234, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02" ), null, null, null, null),
                new Employee(345, dateFormatter.parse("1983-03-03"), "Name3", "LastName3", 'M', dateFormatter.parse("2003-03-03" ), null, null, null, null)
        );
    }


    private List<SimpleEmployeeResponse> sampleSimpleEmployeeResponseList() throws ParseException {
        return List.of(
                new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01" )),
                new SimpleEmployeeResponse(321, dateFormatter.parse("1982-02-02"), "Name2", "LastName2", 'F', dateFormatter.parse("2002-02-02" ))
        );
    }

    private SimpleEmployeeResponse sampleSimpleEmployeeResponse() throws ParseException {
        return new SimpleEmployeeResponse(123, dateFormatter.parse("1981-01-01"), "Name1", "LastName1", 'M', dateFormatter.parse("2001-01-01" ));
    }


}