package work.employees.employeesTrainingTask.service;

import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.domain.Title;
import work.employees.employeesTrainingTask.repository.TitleRepository;
import work.employees.employeesTrainingTask.response.TitleResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleService(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public List<String> getAllTitles() {
        return titleRepository.getAllTitles();
    }

    private TitleResponse createTitleResponse(Title title) {
        return new TitleResponse(title.getTitle());
    }
}
