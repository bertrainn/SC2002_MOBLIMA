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
				"1. Search for a movie",
				"2. Show all movies",
				"3. Logout");
		
		int choice = userInput();
		
		switch(choice) {
			case 1:
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
