package MOBLIMA.Entity;

/**
 * Represents an instance of an administrator in the system.
 */

public class Admin extends User {
    
    private String Username;
    /**
     * Username of administrator.
     */
    
    private String Password;
    /**
     * Password of administrator.
     */
    
    /**
     * Constructor for the Table class, with additional parameters.
     * 
     * @param Username      name of administrator
     * @param Password      password for administrator
     */
   
    public Admin(String Username, String Password) {
        super(Username, Password);
    }
}
