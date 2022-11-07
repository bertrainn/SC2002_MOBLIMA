package Boundary;

import static Control.UserInput_Controller.*;

import Boundary.Admin.AdminMainMenu;
import Boundary.MovieGoer.BookingConfirmationMenu;
import Boundary.MovieGoer.MovieGoerMainMenu;
import Boundary.MovieGoer.ReviewView;
import Control.Admin_Controller;
import Control.MovieGoer_Controller;
import Entity.User;
import Entity.MovieSession;
import Entity.Seat;
import Entity.Cineplex;
import Entity.Movie;

import java.util.ArrayList;

/**
 * This class helps to log in both admin and moviegoer .
 */
public class LogIn extends BaseMenu {

    /**
	 * Intialising the controllers for the confirmed booking menu.
	 */
    private Admin_Controller adminController = new Admin_Controller();
    private MovieGoer_Controller movieGoerController = new MovieGoer_Controller();
    
    /**
	 * The MovieSession selected at the login Page.
	 */
    private MovieSession ms;
    
    /**
	 * The Movie selected at the login Page.
	 */
    private Movie m;
    
    /**
	 * The seats chosen at the login Page.
	 */
    private ArrayList<Seat> chosenSeats;
    
    /**
	 * The cineplex chosen at the login Page.
	 */
    private Cineplex cp;

    //Default constructor
    public LogIn() {

    }
    
    /**
     * Creates a new Login Menu that redirect back to the review page after login..
     *
     * @param m This Login Menu's Movie.
     */
    public LogIn(Movie m) {
    	this.m = m;
    }

    /**
     * Creates a new Login Menu that redirect back to the booking page after login..
     *
     * @param ms          This Login Menu's MovieSession.
     * @param chosenSeats This Login Menu's ChosenSeats.
     * @param cp          This Login Menu's Cineplex.
     */
    public LogIn(MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
        this.ms = ms;
        this.chosenSeats = chosenSeats;
        this.cp = cp;
    }

    /**
     * Loads the Login Menu.
     */
    @Override
    public void load() {
        String user, pw;
        User temp;
        BaseMenu next;
        if (m != null)
        	next = new LogIn(m);
        else if (ms != null)
        	next = new LogIn(ms, chosenSeats, cp);
        else next = new LogIn();
        int choice = 1;

        printHeader("Login");

        user = getStringInput("Enter your username: ");
        pw = getStringInput("Enter your password: ");

        if (!(movieGoerController.MovieGoerExist(user) || adminController.AdminExists(user))) {
            printMenu(
                    "Username does not exist, press 0 to return to main menu, press any other number to try again.");
            choice = userInput(0, 9);
        } else {
            if (adminController.getAdminByUsername(user) != null) {
                temp = adminController.getAdminByUsername(user);
            } else {
                temp = movieGoerController.getMovieGoerByUsername(user);
            }
            if (!temp.getPassword().equals(pw)) {
                System.out.println(
                        "Incorrect password, press 0 to return to main menu, press any other number to try again.");
                choice = userInput(0, 9);
            } else {
                switch (temp.getAccountType()) {
                    case ADMIN:
                        next = new AdminMainMenu(adminController.getAdminByUsername(user));
                        break;
                    case MOVIEGOER:
                        if (ms != null)
                            next = new BookingConfirmationMenu(movieGoerController.getMovieGoerByUsername(user), ms,
                                    chosenSeats, cp);
                        else if (m != null)
                        	next = new ReviewView(m, movieGoerController.getMovieGoerByUsername(user), false);
                        else
                            next = new MovieGoerMainMenu(movieGoerController.getMovieGoerByUsername(user));
                        break;
                }
                System.out.println("Logging in...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (choice == 0)
            navigate(this, new MainMenu());
        else {
        	navigate(this, next);
        }
    }
}
