package MOBLIMA.MenuItems;

import static MOBLIMA.MenuItems.MenuMethods.*;

public class MovieGoerMainMenu {

	public void showMenu() {
		printHeader("Movie Goer");
		printMenu("Choose from one of the following options:",
				"1. Search for a movie",
				"2. Show all movies",
				"3. Go back", "");
	}

}
