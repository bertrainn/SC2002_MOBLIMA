package MOBLIMA.Boundary;

import static MOBLIMA.Boundary.MenuMethods.*;

public class AdminMainMenu {

	public void showMenu() {
		printHeader("Admin");
		printMenu("Choose from one of the following options:",
				"1. Create/Update/Remove Movie Listing",
				"2. Create/Update/Remove cinema showtimes and movies to be shown",
				"3. Configure system settings",
				"4. Go back", "");
	}

}