package MOBLIMA.Entity;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Represents an instance of a holiday used in the system.
 */

public class Holiday implements Serializable {
    /**
     * Name of holiday.
     */
    private String name;
    
    /**
     * Date of holiday.
     */
    private LocalDate date;

     /**
     * Constructor for the Holiday class.
     * 
     * @param name   name of holiday
     * @param date   date of holiday
     */
    public Holiday(String name, LocalDate date) {
        this.name = name;
        this.date = date;

    }

    /**
     * This method returns the name of holiday
     * 
     * @return the name of holiday
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method sets the name of holiday
     * 
     * @param id is the new name of holiday
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the date of holiday
     * 
     * @return the date of holiday
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * This method returns the date in string format
     * 
     * @return the date in string format
     */
    public String getDateString() {
        return this.date.format(Constants.holidayFormat);
    }

    /**
     * This method sets the date of holiday
     * 
     * @param id is the new date of holiday
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%S is on %S", this.name, this.getDateString());
    }

}
