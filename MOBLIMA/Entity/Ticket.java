package MOBLIMA.Entity;

/**
 * TODO:
 * 
 * - Implement Ticket Class
 * - Funcion that calulates the price of the ticket based on the movie + ticket
 * type
 * - Validation will be done at point of entry for the ticket type: i.e. if
 * student/ elderly they are only allowed 2D movies (non-atmos): smth like
 * please enter movie, at that point take the info for the movie, if the movie
 * is after 6pm on friday and they aren't a student then tickettype =
 * Fri_Weekend_PH
 */
public class Ticket {
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
