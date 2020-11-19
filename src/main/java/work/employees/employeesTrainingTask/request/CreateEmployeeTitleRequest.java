package work.employees.employeesTrainingTask.request;

import java.util.Date;
import java.util.Objects;

public class CreateEmployeeTitleRequest {

    private String title;
    private Date fromDate;
    private Date toDate;

    public CreateEmployeeTitleRequest() {
    }

    public CreateEmployeeTitleRequest(String title, Date fromDate, Date toDate) {
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        CreateEmployeeTitleRequest that = (CreateEmployeeTitleRequest) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fromDate, toDate);
    }

    @Override
    public String toString() {
        return "CreateEmployeeTitleRequest{" +
                "title='" + title + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
