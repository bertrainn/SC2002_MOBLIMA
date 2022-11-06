package Entity;

import java.io.Serializable;

import Control.TicketPrice_Controller;

/**
 * Represents an instance of a ticket used in the system.
 */

public class Ticket implements Serializable {

    private TicketPrice_Controller price_Controller = new TicketPrice_Controller();

    /**
     * Price of ticket.
     */
    private double TicketPrice;

    /**
     * Type of ticket.This value can be found in the Constants.java file
     */
    private Constants.TICKET_TYPE TicketType;

    /**
     * Type of movie.This value can be found in the Constants.java file
     */
    private Constants.MOVIE_TYPE MovieType;

    /**
     * Type of Cinema. This value can be found in the Constants.java file
     */
    private Constants.CINEMA_TYPE CinemaType;

    /**
     * Seating position.
     */
    private Seat seat;

    /**
     * Constructor for the Ticket class, with additional parameters.
     * The ticketprice is calulated at the time of creation of the object
     * 
     * @param TicketType type of ticket
     * @param MovieType  type of movie
     * @param CinemaType type of cinema
     * @param Seat       seat position
     */
    public Ticket(Constants.TICKET_TYPE TicketType, Constants.MOVIE_TYPE MovieType, Constants.CINEMA_TYPE CinemaType,
            Seat seat) {
        this.TicketType = TicketType;
        this.MovieType = MovieType;
        this.seat = seat;
        this.TicketPrice = price_Controller.calcPrice(MovieType, TicketType, CinemaType);
    }

    /**
     * This method returns the ticket price that is calulated at the time of
     * creation
     * 
     * @return double
     */
    public double getTicketPrice() {
        return this.TicketPrice;
    }

    /**
     * This method updates the price of the ticket
     * 
     * @param ticketPrice the new ticket price
     */
    public void setTicketPrice(double ticketPrice) {
        this.TicketPrice = ticketPrice;
    }

    /**
     * This method sets the type of the ticket
     * 
     * @param ticketType is the new type of the ticket
     */
    public void setTicketType(Constants.TICKET_TYPE ticketType) {
        this.TicketType = ticketType;
    }

    /**
     * This method returns the type of ticket
     * 
     * @return the type of ticket
     */
    public Constants.TICKET_TYPE getTicketType() {
        return this.TicketType;
    }

    /**
     * This method sets the type of movie
     * 
     * @param movieType is the new type of movie
     */
    public void setMovieType(Constants.MOVIE_TYPE movieType) {
        this.MovieType = movieType;
    }

    /**
     * This method returns the type of movie
     * 
     * @return the type of movie
     */
    public Constants.MOVIE_TYPE getMovieType() {
        return this.MovieType;
    }

    /**
     * This method returns the type of the cinema the movie is shown in
     * 
     * @return CINEMA_TYPE
     */
    public Constants.CINEMA_TYPE getCinemaType() {
        return this.CinemaType;
    }

    /**
     * this method sets a new type of cinema that the movie is shown in
     * 
     * @param cinemaType the new type of cinema
     */
    public void setCinemaType(Constants.CINEMA_TYPE cinemaType) {
        this.CinemaType = cinemaType;
    }

    /**
     * This method sets the seat to a new s of interest
     * 
     * @param s is the new seat
     */
    public void setSeat(Seat s) {
        this.seat = s;
    }

    /**
     * This method returns seat of interest
     * 
     * @return the seat of interest
     */
    public Seat getSeat() {
        return this.seat;
    }
}
