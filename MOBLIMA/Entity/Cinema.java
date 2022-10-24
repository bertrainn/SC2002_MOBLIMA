package MOBLIMA.Entity;

import java.util.ArrayList;

/**
 * The entity class to contain cinema informtaion
 */
public class Cinema {

    /**
     * Name of cinema.
     */
    private String cinemaName;
    private boolean isPlat;
    private ArrayList<Seat> seatList;
    private int col, row;
    private String cinemaCode;
    private String cineplex;

    /**
     * Constructor for the Cinema class
     * 
     * @param name Name of the Cinema
     */
    public Cinema(String cinemaName, boolean isPlat, ArrayList<Seat> SeatList, int col, int row, String cinemaCode,
            String cineplex) {
        this.cinemaName = cinemaName;
        this.isPlat = isPlat;
        this.seatList = SeatList;
        this.row = row;
        this.col = col;
        this.cinemaCode = cinemaCode;
        this.cineplex = cineplex;
    }

    /**
     * This method sets a new name for the cinema
     * 
     * @param cinemaName is the new name for the cinema
     */
    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    /**
     * This method returns the name of the cinema
     * 
     * @return the name of the cinema
     */
    public String getCinemaName() {
        return cinemaName;
    }

    public boolean isPlat() {
        return this.isPlat;
    }

    public void setisPlat(boolean isPlat) {
        this.isPlat = isPlat;
    }

    public ArrayList<Seat> getseatList() {
        return this.seatList;
    }

    public void setseatList(ArrayList<Seat> seatList) {
        this.seatList = seatList;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getcinemaCode() {
        return this.cinemaCode;
    }

    public void setcinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode;
    }

    public String getCinplex() {
        return this.cineplex;
    }

    public void setcineplex(String cineplex) {
        this.cineplex = cineplex;
    }
}
