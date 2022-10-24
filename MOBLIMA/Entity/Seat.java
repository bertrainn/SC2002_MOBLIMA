package MOBLIMA.Entity;

public class Seat {

    private int SeatID;
    private int col, row;
    private Constants.SEAT_STATUS SeatStatus;

    /**
     * Constructor for the Seats class
     */
    public Seat(int SeatID, int col, int row) {
        this.SeatID = SeatID;
        this.col = col;
        this.row = row;
        this.SeatStatus = Constants.SEAT_STATUS.Vacant;
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
     * This method changes the ID of a seat
     * 
     * @param seatID the new ID of the seat
     */
    public void setSeatID(int seatID) {
        this.SeatID = seatID;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    /**
     * This method sets a new title for the movie
     * 
     * @param title is the new title for the movie
     */
    public void setSeatStatus(Constants.SEAT_STATUS s) {
        this.SeatStatus = s;
    }

    /**
     * Returns seat status.
     */
    public Constants.SEAT_STATUS getSeatStatus() {
        return this.SeatStatus;
    }
}
