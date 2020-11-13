package work.employees.employeesTrainingTask.response;

import work.employees.employeesTrainingTask.domain.Employee;

import java.util.Objects;

public class EmployeeDeleteResponse {

    private String message;
    private SimpleEmployeeResponse employee;

    public EmployeeDeleteResponse() {
    }

    public EmployeeDeleteResponse(String message, SimpleEmployeeResponse employee) {
        this.message = message;
        this.employee = employee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SimpleEmployeeResponse getEmployee() {
        return employee;
    }

    public void setEmployee(SimpleEmployeeResponse employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDeleteResponse that = (EmployeeDeleteResponse) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, employee);
    }
}
