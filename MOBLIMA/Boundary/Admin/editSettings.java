
package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Entity.User.*;

public class editSettings {

	    public void showMenu() {
		printHeader("Edit Movie");
		printMenu("Choose from one of the following options:",
				"1. Change username",
				"2. Change password", "");
	}
	
		int choice = userInput(1, 2);

			switch(choice) {
				case 1:
					System.out.println("Username is %s", getUsername());
	                user = getStringInput("Please enter new username: ");
                    setUsername(user);
					break;
				case 2:
	                pw = getStringInput("Please enter new password: ");
                    setUsername(pw);
					break;
			
}