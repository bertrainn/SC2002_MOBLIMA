package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;

public class AdminMainMenu extends BaseMenu  {

	public void showMenu() {
		printHeader("Admin");
		printMenu("Choose from one of the following options:",
				"1. Create/Update/Remove Movie Listing",
				"2. Create/Update/Remove cinema showtimes and movies to be shown",
				"3. Configure system settings",
				"4. Logout", "");
	}
	
		int choice = userInput(1, 4);

			switch(choice) {
				case 1:
					editMovie();
					break;
				case 2:
					editCinema();
					break;
				case 3:
					editSettings()
					break;
				case 4:
					BoundaryTest.customer = null;
					navigate(this, new MainMenu());
					break;
			}

}
