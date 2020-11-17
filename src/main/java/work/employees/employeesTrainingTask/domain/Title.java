package work.employees.employeesTrainingTask.domain;

import work.employees.employeesTrainingTask.domain.embeddableId.TitleId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "titles")
public class Title implements Serializable {

    @EmbeddedId
    private TitleId titleId;

    @Column(name = "to_date")
    private Date toDate;

    public Title() {
    }

    public Title(TitleId titleId, Date toDate) {
        this.titleId = titleId;
        this.toDate = toDate;
    }

    public TitleId getTitleId() {
        return titleId;
    }

    public void setTitleId(TitleId titleId) {
        this.titleId = titleId;
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
        Title title = (Title) o;
        return Objects.equals(titleId, title.titleId) &&
                Objects.equals(toDate, title.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleId, toDate);
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", toDate=" + toDate +
                '}';
    }
}
