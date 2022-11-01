package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;
import java.util.HashMap;

import MOBLIMA.Boundary.BaseMenu;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Entity.Booking;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.Review_Ratings;

public class MovieGoerRegistration extends BaseMenu {

	@Override
	public void load() {
		String username, pw, name, email, num;
		int choice = 1, flag = 0;
		
		printHeader("Registration");
		
		username = getStringInput("Enter a username: ");
		pw = getStringInput("Enter a password: ");
		name = getStringInput("Enter your name: ");
		email = getStringInput("Enter your email address: ");
		num = getStringInput("Enter your phone number: ");
		
		for (MovieGoer mg : BoundaryTest.movieGoerList) {
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
			BoundaryTest.mgc.addMovieGoer(username, pw, name, email, num, PostedReviewsList, BookingList);
			BoundaryTest.movieGoerList = BoundaryTest.mgc.readFile();
			
			System.out.println("Registration success, logging in now...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			BoundaryTest.customer = new MovieGoer(username, pw, name, email, num, PostedReviewsList, BookingList);
			navigate(this, new MovieGoerMainMenu());
		}
	}
}
