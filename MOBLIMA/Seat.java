package MOBLIMA;

public class Seat {

    private int SeatID;
    private Ticket Ticket;
    private Constants.Status SeatStatus;

    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.Ticket = null;
        this.SeatStatus = Constants.Status.Vacant;
    }

    public int getSeatID() {
        return SeatID;
    }

    public void setTicket(Ticket Ticket) {
        this.Ticket = Ticket;
    }

    public Ticket getTicket() {
        return this.Ticket;
    }

    public void setSeatStatus(Constants.Status s) {
        this.SeatStatus = s;
    }

    public Constants.Status getSeatStatus() {
        return this.SeatStatus;
    }
}
