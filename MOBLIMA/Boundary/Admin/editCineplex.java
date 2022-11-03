package MOBLIMA.Boundary.Admin;

import MOBLIMA.Boundary.BaseMenu;

import MOBLIMA.Control.Cineplex_Controller;
import MOBLIMA.Control.Cinema_Controller;
import MOBLIMA.Control.MovieSession_Controller;

import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Cinema;
import MOBLIMA.Entity.SeatLayout;
import MOBLIMA.Entity.MovieSession;

import static MOBLIMA.Control.UserInput_Controller.*;

import java.lang.reflect.Array;
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
				addCineplex();
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
		boolean flag = false;
		int choice = -1;
		String cinemaCode;
		int cinemaTypeChoice;
		int numberofCinemas = 0;
		int row, col;
		Constants.CINEMA_TYPE cinemaType = Constants.CINEMA_TYPE.STANDARD;
		ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
		ArrayList<MovieSession> sessionList = new ArrayList<MovieSession>();

		String cineplexName = getStringInput_Sentence("Enter the name of the new cineplex: ");

		if (cineplex_Controller.CineplexExists(cineplexName)) {
			flag = true;
			System.out.println(
					"Cineplex name is already used, press 0 to return to settings menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				addCineplex();
			}
		}

		String cineplexCode = getStringInput_Sentence("Enter the code of the new cineplex: ");

		if (cineplex_Controller.CineplexCodeExist(cineplexCode)) {
			flag = true;
			System.out.println(
					"Code is already in used, press 0 to return to settings menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				addCineplex();
			}
		}

		numberofCinemas = getIntInput_Min("Please enter the number of cinemas this cineplex has (minimum 3): ", 2);

		for (int i = 0; i < numberofCinemas; i++) {
			String cinemaCode_int = String.format("%02d", i);
			cinemaCode = cineplexCode + cinemaCode_int;
			row = getIntInput_Min("Enter the number of rows this cinema has: ", 0);
			col = getIntInput_Min("Enter the number of columns this cinema has: ", 0);
			SeatLayout seatPlan = new SeatLayout(row, col);

			printMenu("Select the type of Cinema (Number): ",
					"1. Platinum",
					"2. Standard");

			cinemaTypeChoice = userInput(1, 2);

			switch (cinemaTypeChoice) {
				case 1:
					cinemaType = Constants.CINEMA_TYPE.PLATIUM;
					break;
				case 2:
					cinemaType = Constants.CINEMA_TYPE.STANDARD;
					break;
			}
			Cinema newCinema = new Cinema(cinemaCode, cinemaType, seatPlan, sessionList);
			cinemaList.add(newCinema);
		}

		cineplex_Controller.addCineplex(cineplexName, cineplexCode, cinemaList);

		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

}
