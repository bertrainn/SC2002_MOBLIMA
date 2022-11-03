package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Control.UserInput_Controller.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.Seat;
//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;

public class MovieGoerLogin extends BaseMenu {

	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private MovieSession ms;
	private ArrayList<Seat> chosenSeats;
	private Cineplex cp;

	public MovieGoerLogin() {
	}

	public MovieGoerLogin(MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
		this.ms = ms;
		this.chosenSeats = chosenSeats;
		this.cp = cp;
	}

	@Override
	public void load() {
		ArrayList<MovieGoer> movieGoerList = mgc.readFile();
		String user, pw;
		BaseMenu next = new MovieGoerLogin();
		int choice = 1, i = 0;

		printHeader("Login");

		user = getStringInput("Enter your username: ");
		pw = getStringInput("Enter your password: ");
		
		if (!mgc.MovieGoerExist(user)) {
			printMenu(
					"Username does not exist, press 0 to return to main menu, press any other number to try again.");
			choice = userInput(0, 9);
		}
		else {
			MovieGoer cust = mgc.getMovieGoerByUsername(user);
			if (cust.getPassword().equals(pw)) {
				if (this.getPrevMenu() instanceof BookingConfirmationMenu)
					next = new BookingConfirmationMenu(cust, ms, chosenSeats, cp);
				else
					next = new MovieGoerMainMenu(cust);
				System.out.println("Logging in...");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println(
						"Incorrect password, press 0 to return to main menu, press any other number to try again.");
				choice = userInput(0, 9);
			}
		}

		if (choice == 0)
			navigate(this, new MainMenu());

		else {
			navigate(this, next);
		}
	}
}
