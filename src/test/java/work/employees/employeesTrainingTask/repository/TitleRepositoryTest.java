package work.employees.employeesTrainingTask.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
class TitleRepositoryTest {

    @Autowired
    private TitleRepository victim;

    @Test
    public void shouldReturnDistinctTitlesAsc() {
        List<String> expected = List.of("Title1", "Title3");
        List<String> actual = victim.getAllTitlesAsc();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnDistinctTitlesDesc() {
        List<String> expected = List.of("Title3", "Title1");
        List<String> actual = victim.getAllTitlesDesc();
        assertEquals(expected, actual);
    }

}