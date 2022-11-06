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
    
    private boolean IsCoupleSeat = false;
    
    int pairID = -1;

    /**
     * Constructor for the Seats class
     */
    public Seat(int SeatID) {
        this.SeatID = SeatID;
        this.IsOccupied = false;
    }
    
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
     * @return int
     */
    public int getPairID() {
        return pairID;
    }

    
    /** 
     * @param pairID
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
     * @return boolean
     */
    public boolean isIsCoupleSeat() {
    	return IsCoupleSeat;
    }
    
    
    /** 
     * @param isCoupleSeat
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
