package MOBLIMA.Entity;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * The entity class to contain cinema informtaion
 */
public class Cinema implements Serializable {

    /**
     * Cinema code.
     */
    private String cinemaCode;

    /**
     * Name of cinema.
     */
    private String cinemaName;

    /**
     * Determine if cinema is 'Platinum'.
     */
    private boolean isPlat;

    /**
     * Contains the seating plan.
     */
    private SeatLayout SeatPlan;

    /**
     * The list of movie sessions.
     */
    private ArrayList<MovieSession> MovieSessions;

    /**
     * Constructor for the Cinema class
     * 
     * @param cinemaCode    Code of Cinema
     * @param cinemaName    Name of the Cinema
     * @param isPlat        Whether Cinema is 'Platinum'
     * @param SeatPlan      Seating layout of the Cinema
     * @param MovieSessions Movie sessions shown in the Cinema
     */
    public Cinema(String cinemaCode, String cinemaName, boolean isPlat, SeatLayout SeatPlan,
            ArrayList<MovieSession> MovieSessions) {
        this.cinemaCode = cinemaCode;
        this.cinemaName = cinemaName;
        this.isPlat = isPlat;
        this.SeatPlan = SeatPlan;
        this.MovieSessions = MovieSessions;
    }

    /**
     * This method returns the code for the cinema
     * 
     * @return the code of the cinema
     */
    public String getcinemaCode() {
        return this.cinemaCode;
    }

    /**
     * This method sets the code for the cinema
     * 
     * @param cinemaCode is the new code for the cinema
     */
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

    /**
     * This method returns whether Cinema is 'Platinum'
     * 
     * @return whether Cinema is 'Platinum'
     */
    public boolean getisPlat() {
        return this.isPlat;
    }

    /**
     * This method sets whether Cinema is 'Platinum'
     * 
     * @param isPlat is either True of False which determines if Cinema is
     *               'Platinum'
     */
    public void setisPlat(boolean isPlat) {
        this.isPlat = isPlat;
    }

    /**
     * This method returns the seating plan of the Cinema
     * 
     * @return the seating plan of the Cinema
     */
    public SeatLayout getSeatPlan() {
        return SeatPlan;
    }

    /**
     * This method sets the seating plan of the Cinema
     * 
     * @param seatPlan is the new seating plan for the cinema
     */
    public void setSeatPlan(SeatLayout seatPlan) {
        SeatPlan = seatPlan;
    }

    /**
     * This method returns the movie sessions in the Cinema
     * 
     * @return the movie sessions of the Cinema
     */
    public ArrayList<MovieSession> getMovieSessions() {
        return this.MovieSessions;
    }

    /**
     * This method sets the movie sessions in the Cinema
     * 
     * @param MovieSessions is the new list of movie sessions in the Cinema
     */
    public void setMovieSessions(ArrayList<MovieSession> MovieSessions) {
        this.MovieSessions = MovieSessions;
    }
}
