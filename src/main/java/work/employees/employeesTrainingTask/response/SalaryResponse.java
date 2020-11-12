package work.employees.employeesTrainingTask.response;

import java.util.Date;
import java.util.Objects;

public class SalaryResponse {

    private Integer salary;
    private Date fromDate;
    private Date toDate;

    public SalaryResponse(Integer salary, Date fromDate, Date toDate) {
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryResponse that = (SalaryResponse) o;
        return Objects.equals(salary, that.salary) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, fromDate, toDate);
    }
}
