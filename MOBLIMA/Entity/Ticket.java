package MOBLIMA.Entity;

import java.io.Serializable;

public class Ticket implements Serializable {
    private double price;
    private Constants.TICKET_TYPE TicketType;
    private Constants.MOVIE_TYPE MovieType;
    private Seat seat;

    public Ticket(Constants.TICKET_TYPE TicketType, Constants.MOVIE_TYPE MovieType, Seat seat) {
        this.TicketType = TicketType;
        this.MovieType = MovieType;
        this.seat = seat;
        calculatePrice();
    }

    /**
     * This method calculates the price of the tickets based on the TicketType and
     * MovieType
     */
    public void calculatePrice() {
        if (this.TicketType == Constants.TICKET_TYPE.SENIOR) {
            this.price = 5.00;
            return;
        }
        if (this.TicketType == Constants.TICKET_TYPE.STUDENT) {
            this.price = 7.00;
            return;
        }

        if (this.TicketType == Constants.TICKET_TYPE.MON_TO_THU_OPENING) {
            switch (this.MovieType) {
                case TWO_D:
                    this.price = 10.50;
                    return;

                default:
                    this.price = 14.50;
                    return;

            }
        } else if (this.TicketType == Constants.TICKET_TYPE.MON_TO_THU) {
            switch (this.MovieType) {
                case TWO_D:
                    this.price = 10.00;
                    return;

                default:
                    this.price = 14.00;
                    return;
            }
        } else {
            switch (this.MovieType) {
                case TWO_D:
                    this.price = 14.50;
                    return;

                default:
                    this.price = 15.50;
                    return;
            }
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
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
