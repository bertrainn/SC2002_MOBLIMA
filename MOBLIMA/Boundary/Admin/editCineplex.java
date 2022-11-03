package MOBLIMA.Boundary.Admin;

import MOBLIMA.Boundary.BaseMenu;

import MOBLIMA.Control.Cineplex_Controller;
import MOBLIMA.Control.Cinema_Controller;
import MOBLIMA.Control.MovieSession_Controller;

import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Cinema;

import static MOBLIMA.Control.UserInput_Controller.*;

import java.util.ArrayList;

public class editCineplex extends BaseMenu {
	private Cineplex_Controller cineplex_Controller = new Cineplex_Controller();
	private Cinema_Controller cinema_Controller = new Cinema_Controller(cineplex_Controller);
	private MovieSession_Controller movie_session_Controller = new MovieSession_Controller(cinema_Controller);

	@Override
	public void load() {
		showMenu();
	}

	public void showMenu() {
		printHeader("Cineplex Options");
		printMenu("Choose from one of the following options:",

				"1. Add Cineplex",
				"2. Delete Cineplex",
				"3. List Cinemas",
				"4. Add Cinemas",
				"5. Delete Cinemas",
				"6. Add Session",
				"7. Delete Session",
				"8. Cineplex Ordered By Sales",
				"9. Back");

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
				back();
				break;
		}
	}

	public void ListCineplex() {
		// show a count of how many cinemas is in which cineplex, show the id as well.
		ArrayList<Cineplex> cineplexList = cineplex_Controller.readFile();
		ArrayList<Cinema> cinemaList;
		int i = 0;

		if (cineplexList.isEmpty()) {
			System.out.println("There are no cineplexes in the system");
			System.out.println("Returning to cineplex menu");
			load();
		}

		System.out.printf("%-3s %-20s %-15s", "No.", "Cineplex Name", "Cinema Codes");
		System.out.println();
		for (Cineplex c : cineplexList) {
			cinemaList = c.getCinemaList();
			System.out.printf("%-3d %-20s ", ++i, c.getName());
			for (Cinema cinema : cinemaList) {
				System.out.printf("%-3s ", cinema.getcinemaCode());
			}
			System.out.println();
		}
	}

	public void addCineplex() {
		// auto build 3 Cinemas
		String cineplexName = getStringInput_Sentence("Enter the name of the new cineplex: ");


	}

}
