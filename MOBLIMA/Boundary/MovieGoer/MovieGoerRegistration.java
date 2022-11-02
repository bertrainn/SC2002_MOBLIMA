package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;
import java.util.HashMap;

import MOBLIMA.Boundary.BaseMenu;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.Booking;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.Review_Ratings;

public class MovieGoerRegistration extends BaseMenu {
	
	private MovieGoer_Controller mgc = new MovieGoer_Controller();

	@Override
	public void load() {
		ArrayList<MovieGoer> movieGoerList = mgc.readFile();
		String username, pw, name, email, num;
		int choice = 1, flag = 0;
		
		printHeader("Registration");
		
		username = getStringInput("Enter a username: ");
		pw = getStringInput("Enter a password: ");
		name = getStringInput("Enter your name: ");
		email = getStringInput("Enter your email address: ");
		num = getStringInput("Enter your phone number: ");
		
		for (MovieGoer mg : movieGoerList) {
			if (username.equals(mg.getUsername())) {
				flag = 1;
				System.out.println("Username taken, press 0 to return to main menu, press any other number to try again.");
				choice = userInput(0, 9);
				break;
			}
		}
		
		if (flag == 1) {
			if (choice == 0)
				navigate(this, new MainMenu());
			else {
				navigate(this, new MovieGoerRegistration());
			}
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
			navigate(this, new MovieGoerMainMenu(m));
		}
	}
}
