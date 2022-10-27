package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Entity.MovieGoer;

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
				choice = userInput();
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
			BoundaryTest.mgc.addMovieGoer(username, pw, name, email, num, null, null);
			BoundaryTest.movieGoerList = BoundaryTest.mgc.readFile();
			
			System.out.println("Registration success, logging in now");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			MovieGoer m = new MovieGoer(username, pw, name, email, num, null, null);
			navigate(this, new MovieGoerMainMenu());
		}
	}
}
