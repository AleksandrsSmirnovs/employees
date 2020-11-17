package work.employees.employeesTrainingTask.domain.embeddableId;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class TitleId implements Serializable {

    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "from_date")
    private Date fromDate;

    public TitleId() {
    }

    public TitleId(Integer employeeNumber, String title, Date fromDate) {
        this.employeeNumber = employeeNumber;
        this.title = title;
        this.fromDate = fromDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleId titleId = (TitleId) o;
        return Objects.equals(employeeNumber, titleId.employeeNumber) &&
                Objects.equals(title, titleId.title) &&
                Objects.equals(fromDate, titleId.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, title, fromDate);
    }

    @Override
    public String toString() {
        return "TitleId{" +
                "employeeNumber=" + employeeNumber +
                ", title='" + title + '\'' +
                ", fromDate=" + fromDate +
                '}';
    }
}
