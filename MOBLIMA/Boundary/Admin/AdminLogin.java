package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.MovieGoer;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;

public class AdminLogin extends BaseMenu{

	@Override
	public void load() {
		String user, pw;
		BaseMenu next = new AdminLogin();
		int flag = 0, choice = 1, i = 0;
		
		printHeader("Login");
		
		user = getStringInput("Enter your username: ");
		pw = getStringInput("Enter your password: ");
		
		for (Admin ad : BoundaryTest.adminList) {
			if (user.equals(ad.getUsername())) {
				flag = 1;
				if (pw.equals(ad.getPassword())) {
					next = new AdminMainMenu();
					BoundaryTest.customer = BoundaryTest.adminList.get(i);
					System.out.println("Logging in...");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				else {
					System.out.println("Incorrect password, press 0 to return to main menu, press any other number to try again.");
					choice = userInput(0, 9);
					break;
				}
			}
			i++;
		}
		
		if (flag == 0) {
			System.out.println("Username does not exist, press 0 to return to main menu, press any other number to try again.");
			choice = userInput(0, 9);
		}
		
		if (choice == 0)
			navigate(this, new MainMenu());
		
		else {
			navigate(this, next);
		}
	}
}