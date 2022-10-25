package MOBLIMA.Entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {
    private String name;
    private LocalDate date;

    public Holiday(String name, LocalDate date) {
        this.name = name;
        this.date = date;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
        return String.format("%S is on %S", this.name, this.getDateString());
    }

}
