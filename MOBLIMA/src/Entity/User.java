package Entity;

import java.io.Serializable;

/**
 * Represents an instance of a user used in the system.
 */

public class User implements Serializable {

    /**
     * Username of user.
     */
    private String username;

    /**
     * Password of user.
     */
    private String password;

    /**
     * The account type of the user, use of constant value found in the
     * Constants.java file
     * ADMIN = admin user account
     * MOVIEGOER = moviegoer user account
     */

    private Constants.ACCOUNT_TYPE accountType;

    /**
     * Constructor for the user class.
     * The type of user will be determined by at the time of account creation.
     * 
     * @param username username of user
     * @param password password of user
     */
    public User(String username, String password, Constants.ACCOUNT_TYPE AccountType) {
        this.username = username;
        this.password = password;
        this.accountType = AccountType;
    }

    /**
     * This method returns the username of user
     * 
     * @return the username of user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This method sets the username of user
     * 
     * @param id is the new username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the password of user
     * 
     * @return the password of user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * This method sets the password of user
     * 
     * @param id is the new password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns the account type of the user
     * 
     * @return ACCOUNT_TYPE is the account type of the user
     */
    public Constants.ACCOUNT_TYPE getAccountType() {
        return this.accountType;
    }

    /**
     * This method sets the account type of user
     * 
     * @param accountType is the new account type of user
     */
    public void setAccountType(Constants.ACCOUNT_TYPE accountType) {
        this.accountType = accountType;
    }
}
