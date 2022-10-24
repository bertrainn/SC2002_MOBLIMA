package MOBLIMA.Entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Booking implements Serializable{
    private String TID;
    private LocalDateTime Timestamp;
    private double TotalAmount;
    private Cinema cinema;
    private Movie movie;
    private ArrayList<Ticket> TicketList = new ArrayList<Ticket>();
    private MovieGoer user;

    public Booking(String TID, LocalDateTime Timestamp, double TotalAmount, Cinema cinema, Movie movie,
            ArrayList<Ticket> ticketList, MovieGoer user) {
        this.TID = TID;
        this.Timestamp = Timestamp;
        this.TotalAmount = TotalAmount;
        this.cinema = cinema;
        this.movie = movie;
        this.TicketList = ticketList;
        this.user = user;
    }

    public String getTID() {
        return this.TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public LocalDateTime getTimeStamp() {
        return this.Timestamp;
    }

    public void setTimeStamp(LocalDateTime Timestamp) {
        this.Timestamp = Timestamp;
    }

    public double getTotalAmount() {
        return this.TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Ticket> getTicketList() {
        return this.TicketList;
    }

    public void setTicketList(ArrayList<Ticket> TicketList) {
        this.TicketList = TicketList;
    }

    public void addTicket(Ticket Ticket) {
        if (!this.TicketList.contains(Ticket)) {
            this.TicketList.add(Ticket);
            System.out.println("This ticket has been successfully added.");
            return;
        } else {
            System.out.println("This ticket already exists.");
        }
    }

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

    public MovieGoer getMovieGoer() {
        return this.user;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.user = movieGoer;
    }
}
