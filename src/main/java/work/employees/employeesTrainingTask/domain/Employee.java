package work.employees.employeesTrainingTask.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToMany(mappedBy = "departmentEmployeeId.employeeNumber", cascade = CascadeType.ALL)
    private List<DepartmentEmployee> departments;

    @OneToMany(mappedBy = "departmentManagerId.employeeNumber", cascade = CascadeType.ALL)
    private List<DepartmentManager> managedDepartments;

    @OneToMany(mappedBy = "salaryId.employeeNumber", cascade = CascadeType.ALL)
    private List<Salary> salaries;

    @OneToMany(mappedBy = "titleId.employeeNumber", cascade = CascadeType.ALL)
    private List<Title> titles;

    public Employee() {
    }

    public Employee(Integer employeeNumber, Date birthDate, String firstName, String lastName, Character gender, Date hireDate) {
        this.employeeNumber = employeeNumber;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Employee(Integer employeeNumber, Date birthDate, String firstName, String lastName, Character gender, Date hireDate, List<DepartmentEmployee> departments, List<DepartmentManager> managedDepartments, List<Salary> salaries, List<Title> titles) {
        this.employeeNumber = employeeNumber;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
        this.departments = departments;
        this.managedDepartments = managedDepartments;
        this.salaries = salaries;
        this.titles = titles;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<DepartmentEmployee> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentEmployee> departments) {
        this.departments = departments;
    }

    public List<DepartmentManager> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<DepartmentManager> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber, employee.employeeNumber) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(salaries, employee.salaries) &&
                Objects.equals(titles, employee.titles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, birthDate, firstName, lastName, gender, hireDate, salaries, titles);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber=" + employeeNumber +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                ", departments=" + departments +
                ", managedDepartments=" + managedDepartments +
                ", salaries=" + salaries +
                ", titles=" + titles +
                '}';
    }
}
