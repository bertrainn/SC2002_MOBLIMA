package MOBLIMA.Boundary;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.MovieGoer.MovieGoerLogin;
import MOBLIMA.Boundary.MovieGoer.MovieGoerRegistration;

public class MainMenu extends BaseMenu {

	@Override
	public void load() {
		printHeader("Welcome to MOBLIMA");
		printMenu("Please make a selection: ",
				  "1. Movie Goer Login",
				  "2. Movie Goer Registration",
				  "3. Admin Login",
				  "4. Exit");
		
		int choice = userInput();
		
		switch(choice) {
			case 1:
				navigate(this, new MovieGoerLogin());
				break;
			case 2:
				navigate(this, new MovieGoerRegistration());
				break;
			case 3:
				break;
			case 4:
				System.out.println("Exitting application...");
				System.exit(1);
				break;
			default:
				System.out.println("Invalid selection, please try again.");
		}
	}

}
