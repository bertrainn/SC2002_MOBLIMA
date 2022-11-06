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

/**
 * This shows the menu where the MovieGoer can register an account.
 */
public class MovieGoerRegistration extends BaseMenu {

	/**
	 * Intialising the controller for the MovieGoer's Registration menu.
	 */
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	
	/**
	 * The Moviesession that was chosen by the moviegoer.
	 */
	private MovieSession ms;
	
	/**
	 * The Movie that was chosen by the moviegoer.
	 */
	private Movie m;
	
	/**
	 * The Seats that was chosen by the moviegoer.
	 */
	private ArrayList<Seat> chosenSeats;
	
	/**
	 * The Seats that was chosen by the moviegoer.
	 */
	private Cineplex cp;

	//Default constructor
	public MovieGoerRegistration() {
		
	}
	
	/**
	 * Creates a New MovieGoerRegistration Menu to redirect
	 * to review page after registration.
	 * @param m The MovieGoer's selected movie.
	 */
	public MovieGoerRegistration(Movie m) {
		this.m = m;
	}

	/**
	 * Creates a New MovieGoerRegistration Menu to redirect
	 * to booking page after registration.
	 * @param ms The MovieGoer's selected movie session.
	 * @param chosenSeats The MovieGoer's selected seats.
	 * @param cp The MovieGoer's selected cineplex.
	 */
	public MovieGoerRegistration(MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
		this.ms = ms;
		this.chosenSeats = chosenSeats;
		this.cp = cp;
	}

	/**
         * Loads the MovieGoer Registration Menu.
         */
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

			MovieGoer mg = new MovieGoer(username, pw, name, email, num, PostedReviewsList, BookingList);
			if (this.getPrevMenu() instanceof BookingConfirmationMenu)
				navigate(this, new BookingConfirmationMenu(mg, ms, chosenSeats, cp));
			else if (this.getPrevMenu() instanceof ReviewView)
				navigate(this, new ReviewView(m, mg, false));
			else
				navigate(this, new MovieGoerMainMenu(mg));
		}
	}
}
