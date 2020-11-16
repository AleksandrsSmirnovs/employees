package work.employees.employeesTrainingTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Title;

import java.util.List;

@Repository
public interface TitleRepository extends PagingAndSortingRepository<Title, String> {

    @Query(value = "SELECT DISTINCT title FROM titles ORDER BY title ASC", nativeQuery = true)
    List<String> getAllTitlesAsc();

    @Query(value = "SELECT DISTINCT title FROM titles ORDER BY title DESC", nativeQuery = true)
    List<String> getAllTitlesDesc();

}
