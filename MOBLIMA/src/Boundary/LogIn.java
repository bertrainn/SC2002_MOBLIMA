package Boundary;

import static Control.UserInput_Controller.*;

import Boundary.Admin.AdminMainMenu;
import Boundary.MovieGoer.BookingConfirmationMenu;
import Boundary.MovieGoer.MovieGoerMainMenu;
import Control.Admin_Controller;
import Control.MovieGoer_Controller;
import Entity.Admin;
import Entity.MovieGoer;
import Entity.User;
import Entity.MovieSession;
import Entity.Seat;
import Entity.Cineplex;
import Entity.Constants;

import java.util.ArrayList;

public class LogIn extends BaseMenu {

    private Admin_Controller adminController = new Admin_Controller();
    private MovieGoer_Controller movieGoerController = new MovieGoer_Controller();
    private MovieSession ms;
    private ArrayList<Seat> chosenSeats;
    private Cineplex cp;

    public LogIn() {

    }

    public LogIn(MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
        this.ms = ms;
        this.chosenSeats = chosenSeats;
        this.cp = cp;
    }

    @Override
    public void load() {
        // TODO Auto-generated method stub
        ArrayList<User> userList = new ArrayList<User>();
        String user, pw;
        User temp;
        BaseMenu next = new LogIn();
        int choice = 1, i = 0;

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
                        if (this.getPrevMenu() instanceof BookingConfirmationMenu)
                            next = new BookingConfirmationMenu(movieGoerController.getMovieGoerByUsername(user), ms,
                                    chosenSeats, cp);
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
