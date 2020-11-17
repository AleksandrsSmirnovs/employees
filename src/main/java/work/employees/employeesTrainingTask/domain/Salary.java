package work.employees.employeesTrainingTask.domain;

import work.employees.employeesTrainingTask.domain.embeddableId.SalaryId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "salaries")
public class Salary implements Serializable {

    @EmbeddedId
    private SalaryId salaryId;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "to_date")
    private Date toDate;

    public Salary() {
    }

    public Salary(SalaryId salaryId, Integer salary, Date toDate) {
        this.salaryId = salaryId;
        this.salary = salary;
        this.toDate = toDate;
    }

    public SalaryId getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(SalaryId salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
        return Objects.equals(salaryId, salary1.salaryId) &&
                Objects.equals(salary, salary1.salary) &&
                Objects.equals(toDate, salary1.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryId, salary, toDate);
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", salary=" + salary +
                ", toDate=" + toDate +
                '}';
    }
}
