package work.employees.employeesTrainingTask.service.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DataSorterTest {

    @Autowired
    private DataSorter victim;

    @Before
    public void init() {
        victim = new DataSorter();
    }

    @Test
    public void shouldGetSortDirectionAscWithEmptyArgument() {
        Sort.Direction expected = Sort.Direction.ASC;
        Sort.Direction actual = victim.getSortDirection("");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSortDirectionAscWithWrongArgument() {
        Sort.Direction expected = Sort.Direction.ASC;
        Sort.Direction actual = victim.getSortDirection("Some wrong argument");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetSortDirectionDescWithRightArgument() {
        Sort.Direction expected = Sort.Direction.DESC;
        Sort.Direction actual = victim.getSortDirection("desc");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetOrders() {
        List<Sort.Order> expected = List.of(
                new Sort.Order(Sort.Direction.ASC, "param1"),
                new Sort.Order(Sort.Direction.DESC, "param2")
        );
        List<Sort.Order> actual = victim.getOrders(new String[]{"param1,asc", "param2,desc"});
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetOrdersWithNoSortDirectionParam() {
        List<Sort.Order> expected = List.of(
                new Sort.Order(Sort.Direction.ASC, "param1"),
                new Sort.Order(Sort.Direction.DESC, "param2")
        );
        List<Sort.Order> actual = victim.getOrders(new String[]{"param1", "param2,desc"});
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetOrdersWithDefaultParametersWhenNoParamsPassed() {
        List<Sort.Order> expected = List.of(
                new Sort.Order(Sort.Direction.ASC, "last_name")
        );
        List<Sort.Order> actual = victim.getOrders(new String[]{"last_name", "asc"});
        assertEquals(expected, actual);
    }
}