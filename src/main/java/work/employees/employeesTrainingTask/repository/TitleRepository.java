package work.employees.employeesTrainingTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import work.employees.employeesTrainingTask.domain.Title;

import java.util.List;

@Repository
public interface TitleRepository extends CrudRepository<Title, String> {

    @Query(value = "SELECT DISTINCT title FROM titles ORDER BY title ASC", nativeQuery = true)
    List<String> getAllTitles();

}
