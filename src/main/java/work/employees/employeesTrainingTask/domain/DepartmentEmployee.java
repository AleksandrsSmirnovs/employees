package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee implements Serializable {

    @Id
    @Column(name = "dept_no", columnDefinition = "char")
    private String departmentNumber;

    @Id
    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public DepartmentEmployee() {
    }

    public DepartmentEmployee(String departmentNumber, Integer employeeNumber, Date fromDate, Date toDate) {
        this.departmentNumber = departmentNumber;
        this.employeeNumber = employeeNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEmployee that = (DepartmentEmployee) o;
        return Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(employeeNumber, that.employeeNumber) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, employeeNumber, fromDate, toDate);
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

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        DepartmentEmployee that = (DepartmentEmployee) o;
//        return Objects.equals(departmentEmployeeId, that.departmentEmployeeId) &&
//                Objects.equals(employee, that.employee) &&
//                Objects.equals(department, that.department) &&
//                Objects.equals(fromDate, that.fromDate) &&
//                Objects.equals(toDate, that.toDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(departmentEmployeeId, employee, department, fromDate, toDate);
//    }
}
