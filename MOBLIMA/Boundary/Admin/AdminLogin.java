package MOBLIMA.Boundary.Admin;

import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import static MOBLIMA.Boundary.MenuMethods.*;
import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.MainMenu;
import MOBLIMA.Control.Admin_Controller;
import MOBLIMA.Entity.Admin;

import MOBLIMA.Boundary.BoundaryTest;

public class AdminLogin extends BaseMenu {

	@Override
	public void load() {
		Admin_Controller adminController = new Admin_Controller();
		ArrayList<Admin> adminList = adminController.readFile();
		BaseMenu next = new AdminLogin();
		boolean login_flag = false;
		int choice = 1, i = 0;

		printHeader("Admin Login");

		// String user = getStringInput("Enter your admin username: ");
		// String password = getStringInput("Enter your admin password: ");

		String user = "admin1";
		String password = "password";

		for (Admin admin : adminList) {
			if (user.equals(admin.getUsername())) {
				login_flag = true;

				if (password.equals(admin.getPassword())) {
					next = new AdminMainMenu();
					BoundaryTest.adminUser = admin;
					System.out.println("Logging in...");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				} else {
					System.out.println(
							"Incorrect password, press 0 to return to main menu, press any other number to try again.");
					choice = userInput(0, 9);
					break;
				}
			}
		}

		if (login_flag == false) {
			System.out.println(
					"Username does not exist, press 0 to return to main menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (choice == 0) {
			navigate(this, new MainMenu());
		} else {
			navigate(this, next);
		}
	}
}