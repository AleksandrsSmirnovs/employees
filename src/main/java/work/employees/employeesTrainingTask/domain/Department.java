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

    @OneToMany
    @JoinTable(
            name = "dept_emp",
            joinColumns = @JoinColumn(name = "dept_no"),
            inverseJoinColumns = @JoinColumn(name = "emp_no")
    )
    List<Employee> employees;

    @OneToMany
    @JoinTable(
            name = "dept_manager",
            joinColumns = @JoinColumn(name = "dept_no"),
            inverseJoinColumns = @JoinColumn(name = "emp_no")
    )
    List<Employee> departmentManagers;

    public Department() {
    }

    public Department(String departmentNumber, String departmentName, List<Employee> employees, List<Employee> departmentManagers) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(List<Employee> departmentManagers) {
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
}
