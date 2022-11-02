package MOBLIMA.Entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an instance of a Cineplex used in the system.
 */

public class Cineplex implements Serializable {
    
    /**
     * Name of Cineplex.
     */
    private String Name;
    
    /**
     * List of Cinemas.
     */
    private ArrayList<Cinema> CinemaList;

    
    /**
     * Constructor for the Cinema class.
     * 
     * @param Name         Name of Cineplex
     * @param CinemaList   List of Cinemas
     */
    public Cineplex(String Name, ArrayList<Cinema> CinemaList) {
        this.Name = Name;
        this.CinemaList = CinemaList;
    }

    /**
     * This method returns the name of Cineplex.
     * 
     * @return the name of Cineplex.
     */
    public String getName() {
        return this.Name;
    }

    /**
     * This method sets the name of Cineplex.
     * 
     * @param id is the new name of Cineplex.
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * This method returns the list of Cinemas.
     * 
     * @return the list Cinemas.
     */
    public ArrayList<Cinema> getCinemaList() {
        return this.CinemaList;
    }

    /**
     * This method sets the list of Cinemas.
     * 
     * @param id is the new list of Cinemas.
     */
    public void setCinema(ArrayList<Cinema> CinemaList) {
        this.CinemaList = CinemaList;
    }

    /**
     * This method adds a new cinema to the Cinemaist
     * Firstly it does a search on the current CinemaList and if they exist in 
     * the list
     * If they do, the method will terminate.
     * Else, the cinema is added into the CinemaList
     * 
     * @param actor is the new cinema to be added.
     */
    public void addCinema(Cinema Cinema) {
        this.CinemaList.add(Cinema);
    }

    /**
     * This method removes a cinema from the CinemaList
     * Firstly it does a search on the current CinemaList and if they exist in
     * the list
     * If they do, they will be removed from the CinemaList.
     * Else, the method terminates.
     * 
     * @param actor is the cinema to remove.
     */
    public void removeCinema(Cinema Cinema) {
        this.CinemaList.remove(Cinema);
    }

}
