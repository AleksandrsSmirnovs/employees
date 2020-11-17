package work.employees.employeesTrainingTask.domain.embeddableId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class SalaryId implements Serializable {

    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Column(name = "from_date")
    private Date fromDate;

    public SalaryId() {
    }

    public SalaryId(Integer employeeNumber, Date fromDate) {
        this.employeeNumber = employeeNumber;
        this.fromDate = fromDate;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryId salaryId = (SalaryId) o;
        return Objects.equals(employeeNumber, salaryId.employeeNumber) &&
                Objects.equals(fromDate, salaryId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, fromDate);
    }

    @Override
    public String toString() {
        return "SalaryId{" +
                "employeeNumber=" + employeeNumber +
                ", fromDate=" + fromDate +
                '}';
    }
}
