package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import Boundary.BaseMenu;
import Boundary.MainMenu;
import Entity.MovieGoer;

/**
 * This shows the main menu of the MovieGoer.
 */
public class MovieGoerMainMenu extends BaseMenu {

	/**
	 * The Menu's MovieGoer.
	 */
	private MovieGoer cust;

	/**
         * Creates a new Main Menu for the MovieGoer with the given parameters.
         * @param m This MovieGoerMainMenu's MovieGoer.
         */
	public MovieGoerMainMenu(MovieGoer m) {
		cust = m;
	}

	/**
         * Loads the MovieGoer's Main Menu.
         */
	@Override
	public void load() {
		printHeader("Welcome to customer main menu, " + cust.getName());
		printMenu("Choose from one of the following options:",
				"1. View Movies",
				"2. View Booking History",
				"3. Logout");

		int choice = userInput(1, 3);

		switch (choice) {
			case 1:
				navigate(this, new MoviesList(cust));
				break;
			case 2:
				navigate(this, new BookingHistory(cust));
				break;
			case 3:
				cust = null;
				navigate(this, new MainMenu());
				break;
		}
	}

}
