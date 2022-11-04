package Entity;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * The entity class to contain cinema informtaion
 */
public class Cinema implements Serializable {

    /**
     * Cinema code. is a concatenation of cinplexCode + the ID of the cinema that is generated at the time of creation of the Cinema. 
     * Format = A00, A = Cineplex Code and 00 is the ID of the cinema. 
     */
    private String cinemaCode;

    /**
     * Determine if cinema is 'Platinum'.
     */
    private Constants.CINEMA_TYPE cinemaType;

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
     * @param isPlat        Whether Cinema is 'Platinum'
     * @param SeatPlan      Seating layout of the Cinema
     * @param MovieSessions Movie sessions shown in the Cinema
     */
    public Cinema(String cinemaCode, Constants.CINEMA_TYPE cinemaType, SeatLayout SeatPlan,
            ArrayList<MovieSession> MovieSessions) {
        this.cinemaCode = cinemaCode;
        this.cinemaType = cinemaType;
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

    public Constants.CINEMA_TYPE getCinemaType() {
        return cinemaType;
    }

    public void setCinemaType(Constants.CINEMA_TYPE cinemaType) {
        this.cinemaType = cinemaType;
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
