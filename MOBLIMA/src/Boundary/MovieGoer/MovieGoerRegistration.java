package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;
import java.util.HashMap;

import Boundary.BaseMenu;

//Application class test
import Boundary.MainMenu;
import Control.MovieGoer_Controller;
import Entity.Booking;
import Entity.Cineplex;
import Entity.Movie;
import Entity.MovieGoer;
import Entity.MovieSession;
import Entity.Review_Ratings;
import Entity.Seat;

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
