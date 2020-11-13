package work.employees.employeesTrainingTask.request;

import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CreateEmployeeRequest {

    private Integer employeeNumber;

    @NotNull
    private Date birthDate;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Character gender;

    @NotNull
    private Date hireDate;

    private List<Department> departments;

    private List<Department> managedDepartments;

    private List<Salary> salaries;

    private List<Title> titles;

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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Department> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<Department> managedDepartments) {
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
        CreateEmployeeRequest that = (CreateEmployeeRequest) o;
        return Objects.equals(employeeNumber, that.employeeNumber) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(hireDate, that.hireDate) &&
                Objects.equals(departments, that.departments) &&
                Objects.equals(managedDepartments, that.managedDepartments) &&
                Objects.equals(salaries, that.salaries) &&
                Objects.equals(titles, that.titles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, birthDate, firstName, lastName, gender, hireDate, departments, managedDepartments, salaries, titles);
    }

    @Override
    public String toString() {
        return "CreateEmployeeRequest{" +
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
