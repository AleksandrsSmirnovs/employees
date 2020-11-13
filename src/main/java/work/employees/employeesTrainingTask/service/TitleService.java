package work.employees.employeesTrainingTask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import work.employees.employeesTrainingTask.exception.ItemNotFoundException;
import work.employees.employeesTrainingTask.repository.EmployeeRepository;
import work.employees.employeesTrainingTask.repository.TitleRepository;
import work.employees.employeesTrainingTask.response.*;
import work.employees.employeesTrainingTask.response.responseMapper.ResponseMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TitleService {

    private static final Logger log = LoggerFactory.getLogger(TitleService.class);
    private final TitleRepository titleRepository;
    private final EmployeeRepository employeeRepository;
    private final ResponseMapper mapper;

    public TitleService(TitleRepository titleRepository, EmployeeRepository employeeRepository, ResponseMapper mapper) {
        this.titleRepository = titleRepository;
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<String> getAllTitles(String order) {
        return order.equalsIgnoreCase("desc") ? titleRepository.getAllTitlesDesc() : titleRepository.getAllTitlesAsc();
    }

    public List<SimpleEmployeeResponse> getEmployeesByTitle(String title, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<SimpleEmployeeResponse> responseList = employeeRepository.getEmployeesByTitle(title, paging).stream().map(mapper::createSimpleEmployeeResponse).collect(toList());
        if (responseList.isEmpty()) {
            throw new ItemNotFoundException("Employees with title " + title + " not found");
        }
        return responseList;
    }

}
