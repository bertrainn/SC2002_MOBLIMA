package MOBLIMA;

public class Seat {

    private int SeatID;
    private Ticket Ticket;
    private Constants.Status SeatStatus;

    /**
    * Constructor for the Seats class
    */
    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.Ticket = null;
        this.SeatStatus = Constants.Status.Vacant;
    }
    
    
    /**
     * This method returns the seat ID of the seat
     * 
     * @return the seatID of the seat 
     */
    public int getSeatID() {
        return SeatID;
    }
    
    /**
     * This method sets a new ticket for the seats of the movie
     * 
     * @param Ticket is the new Ticket for the seats
     */
    public void setTicket(Ticket Ticket) {
        this.Ticket = Ticket;
    }

    /**
     * This method returns the ticket of the movie
     * 
     * @return the ticket of the movie
     */
    public Ticket getTicket() {
        return this.Ticket;
    }

    /**
     * This method sets a new title for the movie
     * 
     * @param title is the new title for the movie
     */
    public void setSeatStatus(Constants.Status s) {
        this.SeatStatus = s;
    }

    /**
     * Returns seat status.
     */
    public Constants.Status getSeatStatus() {
        return this.SeatStatus;
    }
}
