package work.employees.employeesTrainingTask.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DepartmentEmployeeId implements Serializable {

    @Column(name = "dept_no", columnDefinition = "char")
    private String departmentNumber;

    @Column(name = "emp_no")
    private Integer employeeNumber;

    private DepartmentEmployeeId() {

    }

    public DepartmentEmployeeId(String departmentNumber, Integer employeeNumber) {
        this.departmentNumber = departmentNumber;
        this.employeeNumber = employeeNumber;
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
        DepartmentEmployeeId that = (DepartmentEmployeeId) o;
        return Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(employeeNumber, that.employeeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, employeeNumber);
    }
}
