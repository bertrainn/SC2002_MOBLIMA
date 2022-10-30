package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;

//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Entity.Admin;

public class AdminRegistration extends BaseMenu {

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
		
		for (Admin ad : BoundaryTest.adminList) {
			if (username.equals(mg.getUsername())) {
				flag = 1;
				System.out.println("Username taken, press 0 to return to main menu, press any other number to try again.");
				choice = userInput(0, 9);
				break;
			}
		}
		
		if (flag == 1) {
			if (choice == 0)
				navigate(this, new MainMenu());
			else {
				navigate(this, new AdminRegistration());
			}
		}
		
		else {
			BoundaryTest.mgc.addAdmin(username, pw, name, email, num, null, null);
			BoundaryTest.adminList = BoundaryTest.mgc.readFile();
			
			System.out.println("Registration success, logging in now...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			BoundaryTest.customer = new Admin(username, pw, name, email, num, null, null);
			navigate(this, new AdminMainMenu());
		}
	}
}