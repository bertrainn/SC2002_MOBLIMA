package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Entity.MovieGoer;

public class MovieGoerMainMenu extends BaseMenu {

	@Override
	public void load() {
		//MovieGoer m = new MovieGoer();
		printHeader("Welcome, User");
		printMenu("Choose from one of the following options:",
				"1. Search for a movie",
				"2. Show all movies",
				"3. Go back");
		
		int choice = userInput();
		
		switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
		}
	}

}
