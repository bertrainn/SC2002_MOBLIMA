package MOBLIMA.Boundary;

import MOBLIMA.Boundary.MovieGoer.MovieGoerLogin;
import MOBLIMA.Boundary.MovieGoer.MovieGoerRegistration;
import MOBLIMA.Boundary.MovieGoer.MoviesList;

import static MOBLIMA.Control.UserInput_Controller.*;

import MOBLIMA.Boundary.Admin.AdminLogin;

public class MainMenu extends BaseMenu {

	@Override
	public void load() {
		printHeader("Welcome to MOBLIMA");
		printMenu("Please make a selection: ",
				"1. Now Showing Movies",
				"2. Movie Goer Login",
				"3. Movie Goer Registration",
				"4. Admin Login",
				"5. Exit");

		int choice = userInput(1, 5);

		switch (choice) {
			case 1:
				navigate(this, new MoviesList(null));
				break;
			case 2:
				navigate(this, new MovieGoerLogin());
				break;
			case 3:
				navigate(this, new MovieGoerRegistration());
				break;
			case 4:
				navigate(this, new AdminLogin());
				break;
			case 5:
				System.out.println("Exitting application...");
				System.exit(1);
				break;
			default:
				System.out.println("Invalid selection, please try again.");
		}
	}

}
