package work.employees.employeesTrainingTask.service.utils;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSorter {

    public Sort.Direction getSortDirection(String direction) {
        return direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
//
//    public List<Sort.Order> getOrders(String[] sort) {
//        List<Sort.Order> orders = new ArrayList<>();
//        if (sort[0].contains(",")) {
//            for (String sortOrder : sort) {
//                String[] _sort = sortOrder.split(",");
//                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
//            }
//        } else {
//            orders.add(new Sort.Order(Sort.Direction.ASC, sort[0]));
//        }
//        return orders;
//    }
//
//    public List<Sort.Order> getOrders(String[] sort) {
//        List<Sort.Order> orders = new ArrayList<>();
//        if (sort.length > 0) {
//            for (String param : sort) {
//                if (param.contains(",")) {
//                    String[] sortSplit = param.split(",");
//                    orders.add(new Sort.Order(getSortDirection(sortSplit[1]), sortSplit[0]));
//                } else {
//                    orders.add(new Sort.Order(Sort.Direction.ASC, param));
//                }
//            }
//        }
//        return orders;
//    }

    public List<Sort.Order> getOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String param : sort) {
            if (!param.contains(",")) {
                param = param + ", ";
            }
            String[] sortSplit = param.split(",");
            orders.add(new Sort.Order(getSortDirection(sortSplit[1]), sortSplit[0]));
        }
        return orders;
    }
}
