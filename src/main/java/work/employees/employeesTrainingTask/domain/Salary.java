package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "salaries")
public class Salary implements Serializable {

    @Id
    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Id
    @Column(name = "salary")
    private Integer salary;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

//    @ManyToOne
//    private Employee employee;

    public Salary() {
    }

    public Salary(Integer employeeNumber, Integer salary, Date fromDate, Date toDate) {
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
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
        Salary salary1 = (Salary) o;
        return Objects.equals(employeeNumber, salary1.employeeNumber) &&
                Objects.equals(salary, salary1.salary) &&
                Objects.equals(fromDate, salary1.fromDate) &&
                Objects.equals(toDate, salary1.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, salary, fromDate, toDate);
    }
}
