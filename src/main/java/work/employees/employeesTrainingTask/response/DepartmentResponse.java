package work.employees.employeesTrainingTask.response;

import java.util.Objects;

public class DepartmentResponse {

    private String departmentNumber;
    private String departmentName;

    public DepartmentResponse(String departmentNumber, String departmentName) {
        this.departmentNumber = departmentNumber;
        this.departmentName = departmentName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentResponse that = (DepartmentResponse) o;
        return Objects.equals(departmentNumber, that.departmentNumber) &&
                Objects.equals(departmentName, that.departmentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentNumber, departmentName);
    }

    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "departmentNumber='" + departmentNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
