package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;
import MOBLIMA.Entity.Cinema;
import MOBLIMA.Boundary.BaseMenu;

public class editCineplex extends BaseMenu {

	@Override
	public void load() {
		showMenu();
	}

	public void showMenu() {
		printHeader("Cinplex Options");
		printMenu("Choose from one of the following options:",
				"1. List Cineplex",
				"2. Add Cineplex",
				"3. Delete Cineplex",
				"4. List Cinemas",
				"5. Add Cinemas",
				"6. Delete Cinemas",
				"7. Add Session",
				"8. Delete Session",
				"9. Cineplex Ordered By Sales",
				"10. Back");

		int choice = userInput(1, 10);

		switch (choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				load();
				break;
		}
	}

	public void ListCineplex() {
		// show a count of how many cinemas is in which cineplex, show the id as well.
	}

	public void addCineplex() {
		// auto build 3 Cinemas
	}

}
