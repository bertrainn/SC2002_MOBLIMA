package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Entity.Admin;

import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;

public class AdminMainMenu extends BaseMenu {

	@Override
	public void load() {
		Admin adminUser = BoundaryTest.adminUser;
		printHeader("Welcome to Admin main menu, " + adminUser.getUsername());
		printMenu("Choose from one of the following options:",
				"1. Movie Options",
				"2. Cinemplex Settings",
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
