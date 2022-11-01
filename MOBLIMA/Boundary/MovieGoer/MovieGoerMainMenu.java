package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Entity.MovieGoer;

public class MovieGoerMainMenu extends BaseMenu {

	@Override
	public void load() {
		MovieGoer cust = BoundaryTest.customer;
		printHeader("Welcome to customer main menu, " + cust.getName());
		printMenu("Choose from one of the following options:",
				"1. View Movies",
				"2. View Booking History",
				"3. Logout");

		int choice = userInput(1, 3);

		switch (choice) {
			case 1:
				navigate(this, new MoviesList());
				break;
			case 2:
				break;
			case 3:
				BoundaryTest.customer = null;
				navigate(this, new MainMenu());
				break;
		}
	}

}
