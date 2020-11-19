package work.employees.employeesTrainingTask.domain;

import work.employees.employeesTrainingTask.domain.embeddableId.DepartmentEmployeeId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee {

    @EmbeddedId
    private DepartmentEmployeeId departmentEmployeeId;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    private Department department;

    public DepartmentEmployee() {
    }

    public DepartmentEmployee(Date fromDate, Date toDate, Employee employee, Department department) {
        this.departmentEmployeeId = new DepartmentEmployeeId(department.getDepartmentNumber(), employee.getEmployeeNumber());
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
        this.department = department;
        if (employee.getDepartments() == null) {
            employee.setDepartments(new ArrayList<>());
        }
        employee.getDepartments().add(this);
        if (department.getEmployees() == null) {
            department.setEmployees(new ArrayList<>());
        }
        department.getEmployees().add(this);
    }

    public DepartmentEmployeeId getDepartmentEmployeeId() {
        return departmentEmployeeId;
    }

    public void setDepartmentEmployeeId(DepartmentEmployeeId departmentEmployeeId) {
        this.departmentEmployeeId = departmentEmployeeId;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEmployee that = (DepartmentEmployee) o;
        return Objects.equals(departmentEmployeeId, that.departmentEmployeeId) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentEmployeeId, fromDate, toDate, employee, department);
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "departmentEmployeeId=" + departmentEmployeeId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", employee=" + employee +
                ", department=" + department +
                '}';
    }
}
