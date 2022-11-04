package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Control.UserInput_Controller.*;

import java.util.ArrayList;
import java.util.HashMap;

import MOBLIMA.Boundary.BaseMenu;

//Application class test
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.Booking;
import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Entity.Seat;

public class MovieGoerRegistration extends BaseMenu {

	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private MovieSession ms;
	private ArrayList<Seat> chosenSeats;
	private Cineplex cp;

	public MovieGoerRegistration() {
	}

	public MovieGoerRegistration(MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
		this.ms = ms;
		this.chosenSeats = chosenSeats;
		this.cp = cp;
	}

	@Override
	public void load() {
		ArrayList<MovieGoer> movieGoerList = mgc.readFile();
		String username, pw, name, email, num;

		printHeader("Registration");

		username = getStringInput("Enter a username: ");
		pw = getStringInput("Enter a password: ");
		name = getStringInput("Enter your name: ");
		email = getStringInput("Enter your email address: ");
		num = getStringInput("Enter your phone number: ");
		
		if (mgc.MovieGoerExist(username)) {
			printMenu(
					"Username taken, press 0 to return to main menu, press any other number to try again.");
			int choice = userInput(0, 9);
			if (choice == 0)
				navigate(this, new MainMenu());
			else
				navigate(this, new MovieGoerRegistration());
		}
		else {
			HashMap<Movie, Review_Ratings> PostedReviewsList = new HashMap<Movie, Review_Ratings>();
			ArrayList<Booking> BookingList = new ArrayList<Booking>();
			mgc.addMovieGoer(username, pw, name, email, num, PostedReviewsList, BookingList);
			movieGoerList = mgc.readFile();

			System.out.println("Registration success, logging in now...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			MovieGoer m = new MovieGoer(username, pw, name, email, num, PostedReviewsList, BookingList);
			if (this.getPrevMenu() instanceof BookingConfirmationMenu)
				navigate(this, new BookingConfirmationMenu(m, ms, chosenSeats, cp));
			else
				navigate(this, new MovieGoerMainMenu(m));
		}
	}
}