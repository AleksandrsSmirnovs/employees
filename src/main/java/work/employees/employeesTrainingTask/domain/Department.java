package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", columnDefinition = "char")
    private String departmentNumber;

    @Column(name = "dept_name")
    private String departmentName;

    @OneToMany(mappedBy = "departmentEmployeeId.departmentNumber")
    List<DepartmentEmployee> employees;

    @OneToMany(mappedBy = "departmentManagerId.departmentNumber")
    List<DepartmentManager> departmentManagers;

    public Department() {
    }

    public Department(String departmentNumber, String departmentName) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
    }

    public Department(String departmentNumber, String departmentName, List<DepartmentEmployee> employees, List<DepartmentManager> departmentManagers) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.employees = employees;
        this.departmentManagers = departmentManagers;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<DepartmentEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<DepartmentEmployee> employees) {
        this.employees = employees;
    }

    public List<DepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(List<DepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(employees, that.employees) &&
                Objects.equals(departmentManagers, that.departmentManagers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, departmentName, employees, departmentManagers);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNumber='" + departmentNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", employees=" + employees +
                ", departmentManagers=" + departmentManagers +
                '}';
    }
}
