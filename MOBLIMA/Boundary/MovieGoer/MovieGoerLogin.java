package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.MovieGoer;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;

public class MovieGoerLogin extends BaseMenu{

	@Override
	public void load() {
		String user, pw;
		BaseMenu next = new MovieGoerLogin();
		int flag = 0, choice = 1, i = 0;
		
		printHeader("Login");
		
		user = getStringInput("Enter your username: ");
		pw = getStringInput("Enter your password: ");
		
		for (MovieGoer mg : BoundaryTest.movieGoerList) {
			if (user.equals(mg.getUsername())) {
				flag = 1;
				if (pw.equals(mg.getPassword())) {
					next = new MovieGoerMainMenu();
					break;
				}
				else {
					System.out.println("Incorrect password, press 0 to return to main menu, press any other number to try again.");
					choice = userInput();
					break;
				}
			}
			i++;
		}
		
		if (flag == 0) {
			System.out.println("Username does not exist, press 0 to return to main menu, press any other number to try again.");
			choice = userInput();
		}
		
		if (choice == 0)
			navigate(this, new MainMenu());
		
		else {
			MovieGoer m = BoundaryTest.movieGoerList.get(i);
			navigate(this, next);
		}
		
	}

}
