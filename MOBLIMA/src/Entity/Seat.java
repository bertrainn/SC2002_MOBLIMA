package Entity;

import java.io.Serializable;

/**
 * Represents an instance of a seat.
 */

public class Seat implements Serializable {

    /**
     * Identification number for seat.
     */
    private int SeatID;

    /**
     * Whether seat is occupied.
     */
    private boolean IsOccupied;

    /**
     * Whether seat is a couple seat.
     */
    private boolean IsCoupleSeat = false;

    /**
     * The ID of the seat this seat is a couple with, -1 if not couple seat.
     */
    int pairID = -1;

    /**
     * Constructor for the Seats class
     */
    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.IsOccupied = false;
    }

    /**
     * Constructor for couple seat Seat class
     */
    public Seat(int SeatID, boolean IsCoupleSeat, int pairID) {
        this.SeatID = SeatID;
        this.IsOccupied = false;
        this.IsCoupleSeat = IsCoupleSeat;
        this.pairID = pairID;
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

    /**
     * This method returns the pair ID of the seat
     * 
     * @return the pairID of the seat
     */
    public int getPairID() {
        return pairID;
    }

    /**
     * This method changes the pairID of a seat
     * 
     * @param pairID the new pairID of the seat
     */
    public void setPairID(int pairID) {
        this.pairID = pairID;
    }

    /**
     * This method returns whether seat is occupied
     * 
     * @return whether seat is occupied
     */
    public boolean isIsOccupied() {
        return IsOccupied;
    }

    /**
     * This method changes whether seat is occupied
     * 
     * @param isOccupied returns whether seat is occupied
     */
    public void setIsOccupied(boolean isOccupied) {
        IsOccupied = isOccupied;
    }

    /**
     * This method returns if the seat is a couple seat or is not.
     * 
     * @return true if the seat is a couple seat, false otherwise
     */
    public boolean isIsCoupleSeat() {
        return IsCoupleSeat;
    }

    /**
     * This method sets whether the seat is a couple seat
     * 
     * @param isCoupleSeat is the new type of seat
     */
    public void setIsCoupleSeat(boolean isCoupleSeat) {
        IsCoupleSeat = isCoupleSeat;
    }

    /**
     * This method lets a seat to be occupied
     */
    public void book() {
        this.IsOccupied = true;
    }

    /**
     * This method lets a seat to be unoccupied
     */
    public void unbook() {
        this.IsOccupied = false;
    }

}
