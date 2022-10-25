package MOBLIMA.Entity;

import java.io.Serializable;

public class Seat implements Serializable {

    private int SeatID;

    private boolean IsOccupied;

    /**
     * Constructor for the Seats class
     */
    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.IsOccupied = false;
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

    public boolean isIsOccupied() {
        return IsOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        IsOccupied = isOccupied;
    }

    public void book() {
        this.IsOccupied = true;
    }

    public void unbook() {
        this.IsOccupied = false;
    }

}
