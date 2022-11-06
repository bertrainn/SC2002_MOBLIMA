package Boundary.Admin;

import static Control.UserInput_Controller.*;

import Boundary.BaseMenu;
import Entity.Admin;
import Boundary.MainMenu;

/**
 * This shows the Admin's Main menu.
 */
public class AdminMainMenu extends BaseMenu {

	/**
	 * The Admin User that is able to access the Admin main menu.
	 */
	private Admin User;

	/**
     	 * Creates a new Admin Main Menu with the given Admin user.
     	 * @param User This AdminMainMenu's User.
     	 */
	public AdminMainMenu(Admin user) {
		this.User = user;
	}

	/**
         * Loads the Booking Confirmation Menu.
         */
	@Override
	public void load() {
		printHeader("Welcome to Admin main menu, " + User.getUsername());
		printMenu("Choose from one of the following options:",
				"1. Movie Options",
				"2. Cinemplex & Session Settings",
				"3. Edit System Settings",
				"4. Logout");

		int choice = userInput(1, 4);

		switch (choice) {
			case 1:
				navigate(this, new editMovie());
				break;
			case 2:
				navigate(this, new editCineplex());
				break;
			case 3:
				navigate(this, new editSettings());
				break;
			case 4:
				navigate(this, new MainMenu());
				break;
		}

	}

}
