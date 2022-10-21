package MOBLIMA;

/**
 *  The entity class to contain cinema informtaion
 */
public class Cinema {
    
    /**
     * Name of cinema.
     */
    private String cinemaName;
    
    
    /**
    * Constructor for the Cinema class
    */
    public Cinema (String Name) {
        this.CinemaName = Name;
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

}
