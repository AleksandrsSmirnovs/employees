package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dept_manager")
public class DepartmentManager {

    @EmbeddedId
    private DepartmentManagerId departmentManagerId;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public DepartmentManager() {
    }

    public DepartmentManager(DepartmentManagerId departmentManagerId, Date fromDate, Date toDate) {
        this.departmentManagerId = departmentManagerId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public DepartmentManagerId getDepartmentManagerId() {
        return departmentManagerId;
    }

    public void setDepartmentManagerId(DepartmentManagerId departmentManagerId) {
        this.departmentManagerId = departmentManagerId;
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
        DepartmentManager that = (DepartmentManager) o;
        return Objects.equals(departmentManagerId, that.departmentManagerId) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentManagerId, fromDate, toDate);
    }
}
