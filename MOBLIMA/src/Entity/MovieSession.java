package MOBLIMA.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an instance of a movie session used in the system.
 */

public class MovieSession implements Serializable {

    /**
     * Identification number of session.
     */
    private int sessionId;

    /**
     * Movie to be shown.
     */
    private Movie shownMovie;

    /**
     * Date and time.
     */
    private LocalDateTime showDateTime;

    /**
     * Seating plan.
     */
    private SeatLayout seatPlan;

    private Constants.MOVIE_TYPE movieType;

    private String CinemaCode;

    /**
     * Constructor for the MovieSeesion class, with additional parameters.
     * 
     * @param sessionId     identification number of session
     * @param shownMovie    movie to be shown
     * @param LocalDateTime date and time
     * @param seatPlan      seating plan
     */
    public MovieSession(int sessionId, Movie shownMovie, LocalDateTime showDateTime, SeatLayout seatPlan,
            Constants.MOVIE_TYPE movieType, String CinemaCode) {
        this.sessionId = sessionId;
        this.shownMovie = shownMovie;
        this.showDateTime = showDateTime;
        this.seatPlan = seatPlan;
        this.movieType = movieType;
        this.CinemaCode = CinemaCode;
    }

    /**
     * This method returns the identification number of session
     * 
     * @return the identification number of session
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * This method sets the identification number of session
     * 
     * @param id is the new identification number of session
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * This method returns the movie to be shown
     * 
     * @return the movie to be shown
     */
    public Movie getShownMovie() {
        return shownMovie;
    }

    /**
     * This method sets the movie to be shown
     * 
     * @param id is the new movie to be shown
     */
    public void setShownMovie(Movie shownMovie) {
        this.shownMovie = shownMovie;
    }

    /**
     * This method returns the date and time
     * 
     * @return the date and time
     */
    public String getShowDateTime() {
        return showDateTime.format(Constants.datetimeFormat);
    }

    public LocalDateTime getShowDateTime_NonString() {
        return showDateTime;
    }

    /**
     * This method sets the date and time
     * 
     * @param id is the new date and time
     */
    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    /**
     * This method returns the seating plan
     * 
     * @return the seating plan
     */
    public SeatLayout getSeatPlan() {
        return seatPlan;
    }

    /**
     * This method sets the seating plan
     * 
     * @param id is the new seating plan
     */
    public void setSeatPlan(SeatLayout seatPlan) {
        this.seatPlan = seatPlan;
    }

    public Constants.MOVIE_TYPE getMovieType() {
        return movieType;
    }

    public void setMovieType(Constants.MOVIE_TYPE type) {
        this.movieType = type;
    }

    public String getCinemaCode() {
        return CinemaCode;
    }

    public void setCinemaCode(String CinemaCode) {
        this.CinemaCode = CinemaCode;
    }

}
