package MOBLIMA.Entity;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * The entity class to contain cinema informtaion
 */
public class Cinema implements Serializable {

    /**
     * Name of cinema.
     */
    private String cinemaCode;
    private String cinemaName;
    private boolean isPlat;
    private SeatLayout SeatPlan;
    private ArrayList<MovieSession> MovieSessions;

    /**
     * Constructor for the Cinema class
     * 
     * @param name Name of the Cinema
     */
    public Cinema(String cinemaCode, String cinemaName, boolean isPlat, SeatLayout SeatPlan,
            ArrayList<MovieSession> MovieSessions) {
        this.cinemaCode = cinemaCode;
        this.cinemaName = cinemaName;
        this.isPlat = isPlat;
        this.SeatPlan = SeatPlan;
        this.MovieSessions = MovieSessions;
    }

    public String getcinemaCode() {
        return this.cinemaCode;
    }

    public void setcinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    /**
     * This method sets a new name for the cinema
     * 
     * @param cinemaName is the new name for the cinema
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * This method returns the name of the cinema
     * 
     * @return the name of the cinema
     */
    public String getCinemaName() {
        return cinemaName;
    }

    public boolean getisPlat() {
        return this.isPlat;
    }

    public void setisPlat(boolean isPlat) {
        this.isPlat = isPlat;
    }

    public SeatLayout getSeatPlan() {
        return SeatPlan;
    }

    public void setSeatPlan(SeatLayout seatPlan) {
        SeatPlan = seatPlan;
    }

    public ArrayList<MovieSession> getMovieSessions() {
        return this.MovieSessions;
    }

    public void setMovieSessions(ArrayList<MovieSession> MovieSessions) {
        this.MovieSessions = MovieSessions;
    }
}
