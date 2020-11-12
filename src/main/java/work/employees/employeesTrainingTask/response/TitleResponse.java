package work.employees.employeesTrainingTask.response;

import java.util.Date;
import java.util.Objects;

public class TitleResponse {

    private String title;
    private Date fromDate;
    private Date toDate;

    public TitleResponse(String title, Date fromDate, Date toDate) {
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
        TitleResponse that = (TitleResponse) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fromDate, toDate);
    }
}
