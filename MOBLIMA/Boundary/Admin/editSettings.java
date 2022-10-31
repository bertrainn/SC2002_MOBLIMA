/**
 * TODO: Implement
 * Holiday
 * Ticket price
 * Edit account details
 */

package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;

public class editSettings extends BaseMenu {
	@Override
	public void load() {
		// TODO Auto-generated method stub
		printHeader("Edit Settings");
		printMenu("Choose from one of the following options:",
				"1. Add Holiday",
				"2. Delete Holiday",
				"3. List All Holidays",
				"4. Change Movie Type Pricing",
				"5. Change Cinema Type Pricing",
				"6. Back");

		int choice = userInput(1, 6);

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
				navigate(this, new AdminMainMenu());
				break;
		}
	}

}