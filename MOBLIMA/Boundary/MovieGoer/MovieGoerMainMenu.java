package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Control.UserInput_Controller.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Entity.MovieGoer;

public class MovieGoerMainMenu extends BaseMenu {

	private MovieGoer cust;

	public MovieGoerMainMenu(MovieGoer m) {
		cust = m;
	}

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
