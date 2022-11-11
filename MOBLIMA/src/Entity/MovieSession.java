package Entity;

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

    /**
     * The type of movie being shown
     */
    private Constants.MOVIE_TYPE movieType;

    /**
     * The unique identifier of the cinema that the session is being shown in
     */
    private String CinemaCode;

    /**
     * Constructor for the MovieSeesion class, with additional parameters.
     * 
     * @param sessionId     identification number of session
     * @param shownMovie    movie to be shown
     * @param LocalDateTime date and time
     * @param seatPlan      seating plan
     * @param movieType     the type of movie being shown
     * @param CinemaCode    the code of the cinema that the session is being shown
     *                      at
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
     * @param sessionId is the new identification number of session
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
     * @param shownMovie is the new movie to be shown
     */
    public void setShownMovie(Movie shownMovie) {
        this.shownMovie = shownMovie;
    }

    /**
     * This method returns the date and time of the movie as a string
     * 
     * @return the date and time of the movie as a string in the format "dd/MM/yyyy"
     */
    public String getShowDateTime() {
        return showDateTime.format(Constants.datetimeFormat);
    }

    /**
     * This method returns the movie session date time as a LocalDateTime object
     * 
     * @return showDateTime value of as a LocalDateTime object
     */
    public LocalDateTime getShowDateTime_NonString() {
        return showDateTime;
    }

    /**
     * This method sets the date and time
     * 
     * @param showDateTime is the new date and time
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
     * @param seatPlan is the new seating plan
     */
    public void setSeatPlan(SeatLayout seatPlan) {
        this.seatPlan = seatPlan;
    }

    /**
     * This method gets the type of the movie being shown at the session
     * 
     * @return the type of the movie being shown
     */
    public Constants.MOVIE_TYPE getMovieType() {
        return movieType;
    }

    /**
     * This method sets the type of the movie being shown at the session
     * 
     * @param type is the new type of the movie being shown at the session
     */
    public void setMovieType(Constants.MOVIE_TYPE type) {
        this.movieType = type;
    }

    /**
     * This method returns the unique identifier of the cinema that the session is
     * being shown at
     * 
     * @return the cinema code of the cinema that the session is being shown at.
     */
    public String getCinemaCode() {
        return CinemaCode;
    }

    /**
     * This method sets the session to be shown at a different cinema
     * 
     * @param CinemaCode is the code of the new cinema the session is to be shown at
     */
    public void setCinemaCode(String CinemaCode) {
        this.CinemaCode = CinemaCode;
    }

}
