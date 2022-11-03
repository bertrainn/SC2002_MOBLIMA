package MOBLIMA.Entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Represents an instance of a booking used in the system.
 */

public class Booking implements Serializable {

    /**
     * Transaction ID of the Booking
     * Format of TID = XXXYYYYMMDDhhmmm (Y: Year, M: Month, D: Day, h: Hour, m:
     * Minute, XXX: Cinema Code)
     */
    private String TID;
    
    /**
     * The total amount payable.
     */
    private double TotalAmount;
    
    /**
     * The booked Cinema.
     */
    private Cinema cinema;
    
    /**
     * The booked movie.
     */
    private Movie movie;
    
    /**
     * The list of tickets booked.
     */
    private ArrayList<Ticket> TicketList = new ArrayList<Ticket>();
    
    /**
     * The name of movie goer.
     */
    private MovieGoer user;
    
    private Cineplex cineplex;

    /**
     * Constructor for the Booking class, with additional parameters.
     * 
     * @param TotalAmount     total amount for booking
     * @param cinema          cinema for booking
     * @param movie           movie for booking
     * @param ticketList      list of tickets
     * @param user            name of movie goer / user
     */
    
    public Booking(double TotalAmount, Cinema cinema, Movie movie,
            ArrayList<Ticket> ticketList, MovieGoer user, Cineplex cineplex) {
        String TimeStamp = LocalDateTime.now().format(Constants.bookingFormat);
        this.TID = cinema.getcinemaCode() + TimeStamp;
        this.TotalAmount = TotalAmount;
        this.cinema = cinema;
        this.movie = movie;
        this.TicketList = ticketList;
        this.user = user;
        this.cineplex = cineplex;
    }

    /**
     * This method returns the transaction ID
     * 
     * @return the transaction id
     */
    public String getTID() {
        return this.TID;
    }

    /**
     * This method sets the transaction id
     * 
     * @param id is the new transaction id
     */
    public void setTID(String TID) {
        this.TID = TID;
    }

    /**
     * This method returns the total amount for booking
     * 
     * @return the total amount
     */
    public double getTotalAmount() {
        return this.TotalAmount;
    }

    /**
     * This method sets the total amount for booking
     * 
     * @param id is the new total amount
     */
    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    /**
     * This method returns the cinema for booking
     * 
     * @return the cinema
     */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
     * This method sets the cinema for booking
     * 
     * @param id is the new cinema
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    
    public Cineplex getCineplex() {
    	return cineplex;
    }
    
    public void setCineplex(Cineplex cineplex) {
    	this.cineplex = cineplex;
    }

    /**
     * This method returns the movie for booking
     * 
     * @return the movie
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * This method sets the movie for booking
     * 
     * @param id is the new movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * This method returns the list of tickets
     * 
     * @return the list of tickets
     */
    public ArrayList<Ticket> getTicketList() {
        return this.TicketList;
    }

    /**
     * This method sets the list of tickets
     * 
     * @param id is the new list of tickets
     */
    public void setTicketList(ArrayList<Ticket> TicketList) {
        this.TicketList = TicketList;
    }

    /**
     * This method adds a new ticket to the ticketList
     * Firstly it does a search on the current ticketList to find if they exist in
     * the list
     * If they do, the method will terminate.
     * Else, the ticket is added into ticketList.
     * 
     * @param Ticket is the new ticket to be added.
     */
    public void addTicket(Ticket Ticket) {
        if (!this.TicketList.contains(Ticket)) {
            this.TicketList.add(Ticket);
            System.out.println("This ticket has been successfully added.");
            return;
        } else {
            System.out.println("This ticket already exists.");
        }
    }

    /**
     * This method removes a ticket from the ticketList
     * Firstly it does a search on the current ticketList to find if they exist in
     * the list
     * If they do, they will be removed from the ticketList.
     * Else, the method terminates.
     * 
     * @param Ticket is the ticket to remove.
     */
    public void removeTicket(Ticket Ticket) {
        if (this.TicketList.contains(Ticket)) {
            this.TicketList.remove(Ticket);
            System.out.println("This ticket has been successfully removed.");
            return;
        } else {
            System.out.println("This ticket does not exist.");
            return;
        }
    }

    /**
     * This method returns the name of movie goer
     * 
     * @return the movie goer name
     */
    public MovieGoer getMovieGoer() {
        return this.user;
    }

    /**
     * This method sets the name of movie goer
     * 
     * @param id is the new movie goer name
     */
    public void setMovieGoer(MovieGoer movieGoer) {
        this.user = movieGoer;
    }
}
