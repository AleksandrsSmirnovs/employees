package work.employees.employeesTrainingTask.service.utils;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.domain.Sort.*;

@Component
public class DataSorter {

    public Direction getSortDirection(String direction) {
        return direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
    }

    public List<Order> getOrders(String[] sort) {
        List<Order> orders = new ArrayList<>();
        if(sort.length == 2 && (sort[1].equalsIgnoreCase("desc") || sort[1].equalsIgnoreCase("asc"))) {
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        } else {
            for (String param : sort) {
                if (param.contains(",")) {
                    String[] sortSplit = param.split(",");
                    orders.add(new Order(getSortDirection(sortSplit[1]), sortSplit[0]));
                } else {
                    orders.add(new Order(Direction.ASC, param));
                }
            }
        }
        return orders;
    }
}
