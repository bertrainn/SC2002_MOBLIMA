package MOBLIMA;

public class Seat {

    private int SeatID;
    private Ticket Ticket;
    private Constants.Status SeatStatus;

    /**
    * Constructor for Seats
    */
    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.Ticket = null;
        this.SeatStatus = Constants.Status.Vacant;
    }
    
    
    /**
     * Returns SeatID.
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
     * Returns ticket number.
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
