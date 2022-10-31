package MOBLIMA.Entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    private TicketPrice TicketPrice;
    private Constants.TICKET_TYPE TicketType;
    private Constants.MOVIE_TYPE MovieType;
    private Seat seat;

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

    public void setTicketPrice(TicketPrice ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public void setTicketType(Constants.TICKET_TYPE ticketType) {
        this.TicketType = ticketType;
    }

    public Constants.TICKET_TYPE getTicketType() {
        return this.TicketType;
    }

    public void setMovieType(Constants.MOVIE_TYPE movieType) {
        this.MovieType = movieType;
    }

    public Constants.MOVIE_TYPE getMovieType() {
        return this.MovieType;
    }

    public void setSeat(Seat s) {
        this.seat = s;
    }

    public Seat getSeat() {
        return this.seat;
    }
}
