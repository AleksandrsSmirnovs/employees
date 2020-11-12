package work.employees.employeesTrainingTask.response;

import work.employees.employeesTrainingTask.domain.Department;
import work.employees.employeesTrainingTask.domain.Salary;
import work.employees.employeesTrainingTask.domain.Title;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EmployeeResponse {

    private Integer employeeNumber;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Character gender;
    private Date hireDate;
    private List<DepartmentResponse> departments;
    private List<DepartmentResponse> managedDepartments;
    private List<SalaryResponse> salaries;
    private List<TitleResponse> titles;

    public EmployeeResponse(Integer employeeNumber, Date birthDate, String firstName, String lastName, Character gender, Date hireDate, List<DepartmentResponse> departments, List<DepartmentResponse> managedDepartments, List<SalaryResponse> salaries, List<TitleResponse> titles) {
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

    public List<DepartmentResponse> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentResponse> departments) {
        this.departments = departments;
    }

    public List<DepartmentResponse> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<DepartmentResponse> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    public List<SalaryResponse> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<SalaryResponse> salaries) {
        this.salaries = salaries;
    }

    public List<TitleResponse> getTitles() {
        return titles;
    }

    public void setTitles(List<TitleResponse> titles) {
        this.titles = titles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeResponse that = (EmployeeResponse) o;
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
}
