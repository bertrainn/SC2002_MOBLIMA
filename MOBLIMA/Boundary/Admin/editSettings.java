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
				"1. Search for a movie",
				"2. List all movies",
				"3. List the top 5 movies",
				"4. Back");
	}

}