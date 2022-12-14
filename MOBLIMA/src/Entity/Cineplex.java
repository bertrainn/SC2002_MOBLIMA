package Entity;

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
     * Unique Code of the Cineplex
     */
    private String CineplexCode;

    /**
     * List of Cinemas.
     */
    private ArrayList<Cinema> CinemaList;

    /**
     * Constructor for the Cinema class.
     * 
     * @param Name       Name of Cineplex
     * @param CineplexCode Code of the Cineplex
     * @param CinemaList List of Cinemas
     */
    public Cineplex(String Name, String CineplexCode, ArrayList<Cinema> CinemaList) {
        this.Name = Name;
        this.CineplexCode = CineplexCode;
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
     * @param name is the new name of Cineplex.
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * This method returns the name of Cineplex.
     * 
     * @return the code of Cineplex.
     */
    public String getCineplexCode() {
        return this.CineplexCode;
    }

    /**
     * This method sets the name of Cineplex.
     * 
     * @param CineplexCode is the new code of Cineplex.
     */
    public void setCineplexCode(String CineplexCode) {
        this.CineplexCode = CineplexCode;
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
     * @param CinemaList is the new list of Cinemas.
     */
    public void setCinema(ArrayList<Cinema> CinemaList) {
        this.CinemaList = CinemaList;
    }

    /**
     * This method adds a new cinema to the Cinemaist
     * 
     * @param Cinema is the new cinema to be added to the cineplex.
     */
    public void addCinema(Cinema Cinema) {
        this.CinemaList.add(Cinema);
    }

    /**
     * This method removes a cinema from the CinemaList
     * 
     * @param Cinema is the cinema to remove.
     */
    public void removeCinema(Cinema Cinema) {
        this.CinemaList.remove(Cinema);
    }

}
