package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.Seat;
//Application class test
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Boundary.MainMenu;

public class MovieGoerLogin extends BaseMenu {
	
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private MovieSession ms;
	private ArrayList<Seat> chosenSeats;
	
	public MovieGoerLogin() {}
	
	public MovieGoerLogin(MovieSession ms, ArrayList<Seat> chosenSeats) {
		this.ms = ms;
		this.chosenSeats = chosenSeats;
	}

	@Override
	public void load() {
		ArrayList<MovieGoer> movieGoerList = mgc.readFile();
		String user, pw;
		BaseMenu next = new MovieGoerLogin();
		int flag = 0, choice = 1, i = 0;

		printHeader("Login");

		user = getStringInput("Enter your username: ");
		pw = getStringInput("Enter your password: ");

		for (MovieGoer mg : movieGoerList) {
			if (user.equals(mg.getUsername())) {
				flag = 1;
				if (pw.equals(mg.getPassword())) {
					MovieGoer m = movieGoerList.get(i);
					if (this.getPrevMenu() instanceof BookingConfirmationMenu)
						next = new BookingConfirmationMenu(m, ms, chosenSeats);
					else
						next = new MovieGoerMainMenu(m);
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
			i++;
		}

		if (flag == 0) {
			System.out.println(
					"Username does not exist, press 0 to return to main menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (choice == 0)
			navigate(this, new MainMenu());

		else {
			navigate(this, next);
		}
	}
}
