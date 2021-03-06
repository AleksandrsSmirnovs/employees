package work.employees.employeesTrainingTask.request;

import java.util.Date;
import java.util.Objects;

public class CreateEmployeeDepartmentRequest {

    private String departmentNumber;
    private String departmentName;
    private Date fromDate;
    private Date toDate;

    public CreateEmployeeDepartmentRequest() {
    }

    public CreateEmployeeDepartmentRequest(String departmentNumber, String departmentName, Date fromDate, Date toDate) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
        this.fromDate = fromDate;
        this.toDate = toDate;
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
        CreateEmployeeDepartmentRequest that = (CreateEmployeeDepartmentRequest) o;
        return Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, departmentName, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "CreateEmployeeDepartmentRequest{" +
                "departmentNumber='" + departmentNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
