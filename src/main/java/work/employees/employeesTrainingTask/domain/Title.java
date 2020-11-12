package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "titles")
public class Title implements Serializable {

    @Id
    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public Title() {
    }

    public Title(Integer employeeNumber, String title, Date fromDate, Date toDate) {
        this.employeeNumber = employeeNumber;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Title title1 = (Title) o;
        return Objects.equals(employeeNumber, title1.employeeNumber) &&
                Objects.equals(title, title1.title) &&
                Objects.equals(fromDate, title1.fromDate) &&
                Objects.equals(toDate, title1.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, title, fromDate, toDate);
    }
}
