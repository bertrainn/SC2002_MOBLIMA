package Boundary;


import Boundary.MovieGoer.MovieGoerRegistration;
import Boundary.MovieGoer.MoviesList;

import static Control.UserInput_Controller.*;

public class MainMenu extends BaseMenu {

	@Override
	public void load() {
		printHeader("Welcome to MOBLIMA");
		printMenu("Please make a selection: ",
				"1. Now Showing Movies",
				"2. User Login",
				"3. Movie Goer Registration",
				"4. Exit");

		int choice = userInput(1, 4);

		switch (choice) {
			case 1:
				navigate(this, new MoviesList(null));
				break;
			case 2:
				navigate(this, new LogIn());
				break;
			case 3:
				navigate(this, new MovieGoerRegistration());
				break;
			case 4:
				printMenu("Exitting application...");
				System.exit(1);
				break;
		}
	}

}
