package MOBLIMA;

public class Seat {

    public enum Status {
        Occupied,
        Vacant
    }

    private int SeatID;
    private Ticket Ticket;
    private Status SeatStatus;

    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.Ticket = null;
        this.SeatStatus = Status.Vacant;
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

    public void setSeatStatus(Status s) {
        this.SeatStatus = s;
    }

    public Status getSeatStatus() {
        return this.SeatStatus;
    }
}
