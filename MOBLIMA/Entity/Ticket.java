package MOBLIMA.Entity;

import java.io.Serializable;

/**
 * Represents an instance of a ticket used in the system.
 */

public class Ticket implements Serializable {
    
    /**
     * Price of ticket.
     */
    private TicketPrice TicketPrice;
    
    /**
     * Type of ticket.
     */
    private Constants.TICKET_TYPE TicketType;
    
    /**
     * Type of movie.
     */
    private Constants.MOVIE_TYPE MovieType;
    
    /**
     * Seating position.
     */
    private Seat seat;

    
    /**
     * Constructor for the Ticket class, with additional parameters.
     * 
     * @param TicketPrice   price of ticket
     * @param TicketType    type of ticket
     * @param MovieType     type of movie
     * @param Seat          seat position
     */
    public Ticket(Constants.TICKET_TYPE TicketType, Constants.MOVIE_TYPE MovieType, Seat seat) {
        this.TicketType = TicketType;
        this.MovieType = MovieType;
        this.seat = seat;
        this.TicketPrice = new TicketPrice();
    }

    /**
     * This method calculates the price of the tickets based on the TicketType and
     * MovieType
     */
    public TicketPrice getTicketPrice() {
        return TicketPrice;
    }

    /**
     * This method sets the price of the tickets based on the TicketType and MovieType
     * 
     * @param id is the new price of the tickets based on the TicketType and MovieType
     */
    public void setTicketPrice(TicketPrice ticketPrice) {
        TicketPrice = ticketPrice;
    }

    /**
     * This method sets the type of the ticket
     * 
     * @param id is the new type of the ticket
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
     * @param id is the new type of movie
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
     * This method sets the seat of interest
     * 
     * @param id is the new seat
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
