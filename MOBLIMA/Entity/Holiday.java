package MOBLIMA.Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {
    private LocalDate date;

    public Holiday(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateString() {
        return this.date.format(Constants.dateFormatShort);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("The holiday is on %S", this.getDateString());
    }

}
