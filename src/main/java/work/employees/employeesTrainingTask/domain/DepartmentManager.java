package work.employees.employeesTrainingTask.domain;

import work.employees.employeesTrainingTask.domain.embeddableId.DepartmentManagerId;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    private Department department;

    public DepartmentManager() {
    }

    public DepartmentManager(String departmentNumber, Integer employeeNumber, Date fromDate, Date toDate) {
        this.departmentManagerId = new DepartmentManagerId(departmentNumber, employeeNumber);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public DepartmentManager(Date fromDate, Date toDate, Employee employee, Department department) {
        this.departmentManagerId = new DepartmentManagerId(department.getDepartmentNumber(), employee.getEmployeeNumber());
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employee = employee;
        this.department = department;
        if (employee.getManagedDepartments() == null) {
            employee.setManagedDepartments(new ArrayList<>());
        }
        employee.getManagedDepartments().add(this);
        if (department.getDepartmentManagers() == null) {
            department.setDepartmentManagers(new ArrayList<>());
        }
        department.getDepartmentManagers().add(this);
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
        DepartmentManager that = (DepartmentManager) o;
        return Objects.equals(departmentManagerId, that.departmentManagerId) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentManagerId, fromDate, toDate, employee, department);
    }

    @Override
    public String toString() {
        return "DepartmentManager{" +
                "departmentManagerId=" + departmentManagerId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", employee=" + employee +
                ", department=" + department +
                '}';
    }
}
