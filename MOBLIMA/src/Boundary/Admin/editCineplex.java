package Boundary.Admin;

import Boundary.BaseMenu;

import Control.Cineplex_Controller;
import Control.Cinema_Controller;
import Control.MovieSession_Controller;
import Control.Movie_Controller;

import Entity.Cineplex;
import Entity.Constants;
import Entity.Cinema;
import Entity.SeatLayout;
import Entity.MovieSession;
import Entity.Movie;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * This allows the admin to access to the editCineplex menu.
 */
public class editCineplex extends BaseMenu {

	/**
	 * Intialising the controllers for the editCineplex menu.
	 */
	private Cineplex_Controller cineplex_Controller = new Cineplex_Controller();
	private Cinema_Controller cinema_Controller = new Cinema_Controller(cineplex_Controller);
	private MovieSession_Controller movie_session_Controller = new MovieSession_Controller(cinema_Controller);
	private Movie_Controller movie_controller = new Movie_Controller();
	private editMovie editMovies = new editMovie();

	/**
	 * Loads the Edit Cineplex Menu.
	 */
	@Override
	public void load() {
		showMenu();
	}

	/**
	 * Shows the content of the Menu that will be loaded into the edit cineplex
	 * menu.
	 */
	public void showMenu() {
		printHeader("Cineplex & Session Options");
		printMenu("Choose from one of the following options:",

				"1. Add Cineplex",
				"2. Delete Cineplex",
				"3. Add Cinemas",
				"4. Delete Cinemas",
				"5. Add Session",
				"6. Edit Session",
				"7. Delete Session",
				"8. Back");

		int choice = userInput(1, 8);

		switch (choice) {
			case 1:
				addCineplex();
				break;
			case 2:
				deleteCineplex();
				break;
			case 3:
				addCinema();
				break;
			case 4:
				deleteCinema();
				break;
			case 5:
				addSession();
				break;
			case 6:
				editSession();
				break;
			case 7:
				deleteSession();
				break;
			case 8:
				back();
				break;
		}
	}

	/**
	 * This method list all the cineplexes that are currently in the system, showing
	 * their name, code and which cinemas are attached to them
	 */
	public void ListCineplex() {
		ArrayList<Cineplex> cineplexList = cineplex_Controller.readFile();
		ArrayList<Cinema> cinemaList;
		int i = 0;

		if (cineplexList.isEmpty()) {
			System.out.println("There are no cineplexes in the system");
			System.out.println("Returning to cineplex menu");
			load();
		}

		System.out.printf("%-3s %-5s %-20s %-15s", "No.", "Code", "Cineplex Name", "Cinema Codes");
		System.out.println();
		for (Cineplex c : cineplexList) {
			cinemaList = c.getCinemaList();
			System.out.printf("%-3d %-5s %-20s ", ++i, c.getCineplexCode(), c.getName());
			for (Cinema cinema : cinemaList) {
				System.out.printf("%-3s ", cinema.getcinemaCode());
			}
			System.out.println();
		}
	}

	/**
	 * This method prints a list of all the sessions that belong to a certain
	 * cinema. It shows which movie are showing and the session date & time
	 * 
	 * @param cinemaCode the code of the cinema that you want to show all the
	 *                   sessions of
	 */
	public void ListCinemaSessions(String cinemaCode) {
		Cinema cinema = cinema_Controller.getCinemaByCode(cinemaCode);
		ArrayList<MovieSession> sessionList = cinema.getMovieSessions();
		int i = 0;
		System.out.printf("%-3s %-25s %-15s", "No.", "Movie", "Session date & timing");
		System.out.println();

		for (MovieSession ms : sessionList) {
			System.out.printf("%-3d %-25s %-15s", i++, reduceStringLength(ms.getShownMovie().getTitle(), 25),
					ms.getShowDateTime());
			System.out.println();
		}
	}

	/**
	 * Function shows the list of all the movie sessions.
	 */
	public void ListAllSessions() {
		ArrayList<MovieSession> sessions = movie_session_Controller.readFile();
		int i = 0;
		System.out.printf("%-3s %-15s %-25s %-15s", "No.", "Cinema Code ", "Movie", "Session date & timing");
		System.out.println();

		for (MovieSession ms : sessions) {
			System.out.printf("%-3d %-15s %-25s %-15s", i++, ms.getCinemaCode(),
					reduceStringLength(ms.getShownMovie().getTitle(), 25),
					ms.getShowDateTime());
			System.out.println();
		}
	}

	/**
	 * This method will add a new cineplex to the system.
	 * It will prompt the user for the name and code of the new cineplex and how
	 * many cinemas (min 3) are in the new cineplex
	 * it will then create the new cineplex with the associated cinema objects and
	 * add them to the serialisation file through the controller
	 */
	public void addCineplex() {
		// function to auto add 3 cinema
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
		cineplex_Controller.addCineplex(cineplexName, cineplexCode, cinemaList);
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

			cinemaList.add(new Cinema(cinemaCode, cinemaType, seatPlan, sessionList));
		}
		cineplex_Controller.updateCineplex(Cineplex_Controller.CHOICE_CINEMALIST, cineplexCode, cinemaList);

		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * Function delete the cineplex.
	 */
	public void deleteCineplex() {
		String choice;
		ListCineplex();

		choice = getStringInput("Enter the code of the cineplex you want to delete:");

		if (!cineplex_Controller.CineplexCodeExist(choice)) {
			System.out.println("Invalid choice, returning to settings menu...");
		} else {
			cineplex_Controller.deleteCineplex(choice);
			System.out.println("Addition success, returning to settings menu...");
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * This function adds a cinema to an existing cineplex.
	 */
	public void addCinema() {
		ListCineplex();
		Cineplex cineplex = null;
		String Cineplexchoice;
		String cinemaCode;
		Constants.CINEMA_TYPE cinemaType = Constants.CINEMA_TYPE.STANDARD;
		ArrayList<MovieSession> sessions = new ArrayList<MovieSession>();

		Cineplexchoice = getStringInput("Enter the code of the cineplex you want to add a cinema to:");

		if (!cineplex_Controller.CineplexCodeExist(Cineplexchoice)) {
			System.out.println("Invalid choice, returning to settings menu...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			load();
		} else {
			cineplex = cineplex_Controller.getCineplexByCode(Cineplexchoice);
		}

		String cinemaCode_int = String.format("%02d", cinema_Controller.getLastCinemaID(Cineplexchoice) + 1);
		cinemaCode = cineplex.getCineplexCode() + cinemaCode_int;
		int row = getIntInput_Min("Enter the number of rows this cinema has: ", 0);
		int col = getIntInput_Min("Enter the number of columns this cinema has: ", 0);
		SeatLayout seatPlan = new SeatLayout(row, col);

		printMenu("Select the type of Cinema (Number): ",
				"1. Platinum",
				"2. Standard");

		int cinemaTypeChoice = userInput(1, 2);

		switch (cinemaTypeChoice) {
			case 1:
				cinemaType = Constants.CINEMA_TYPE.PLATIUM;
				break;
			case 2:
				cinemaType = Constants.CINEMA_TYPE.STANDARD;
				break;
		}

		cinema_Controller.createCinema(Cineplexchoice, cinemaCode, cinemaType, seatPlan, sessions);

		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * This method deletes an exisiting cinema from it's associated cineplex.
	 */
	public void deleteCinema() {
		String choice;
		Cineplex cineplex = null;
		ListCineplex();

		choice = getStringInput("Enter the code of the cineplex that the cinema belongs to:");

		if (!cineplex_Controller.CineplexCodeExist(choice)) {
			System.out.println("Invalid choice, returning to settings menu...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			load();
		} else {
			cineplex = cineplex_Controller.getCineplexByCode(choice);
		}

		if (cineplex.getCinemaList().size() <= 3) {
			System.out.println("This cineplex has 3 cinemas, unable to delete anymore, returning to settings menu...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			load();
		}

		choice = getStringInput("Enter the code of the cinema you want to delete:");

		if (cinema_Controller.doesCinemaExist(choice)) {
			cinema_Controller.deleteCinema(choice);
			System.out.println("Deletion success, returning to settings menu...");
		} else {
			System.out.println("Invalid choice, returning to settings menu...");
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * This method adds a new movie session to a cinema.
	 */
	public void addSession() {
		ArrayList<Movie> movieList = movie_controller.getShowingMovies();
		String CinemaCode;
		Movie shownMovie;
		LocalDateTime showingDateTime;
		Constants.MOVIE_TYPE movieType = Constants.MOVIE_TYPE.TWO_D;

		ListCineplex();

		CinemaCode = getStringInput("Enter the cinema code you want to add the session to: ");

		if (cinema_Controller.doesCinemaExist(CinemaCode) == false) {

			System.out.println("Invalid cinema code, returning to settings menu...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			load();
		}

		int i = 0;
		System.out.printf("%-3s %-4s %-28s %-15s", "No.", "ID", "Name", "Showing Status");
		System.out.println();
		for (Movie m : movieList) {
			System.out.printf("%-3d %-4d %-28s %-15s", ++i, m.getId(), reduceStringLength(m.getTitle(), 25),
					m.getShowingStatus().toString());
			System.out.println();
		}

		printMenu(++i + ". Back");
		System.out.println("Select the No. of the movie you want to show: ");
		int choice = userInput(1, i);

		if (choice == i) {
			load();
		}

		shownMovie = movieList.get(choice - 1);

		showingDateTime = getDateTimeInput("Enter the date and time of the session (e.g 10 Nov 2022, 10:30am): ");

		if (!movie_session_Controller.checkIfValidTime(showingDateTime, CinemaCode, shownMovie)) {
			System.out.println("Invalid session time, returning to settings menu...");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			load();
		}

		printMenu("Enter the movie session type (number):",
				"1. 2D",
				"2. 3D",
				"3. Blockbuster");

		choice = userInput(1, 3);

		switch (choice) {
			case 1:
				movieType = Constants.MOVIE_TYPE.TWO_D;
				break;
			case 2:
				movieType = Constants.MOVIE_TYPE.THREE_D;
				break;
			case 3:
				movieType = Constants.MOVIE_TYPE.BLOCKBUSTER;
				break;
		}

		movie_session_Controller.createSession(CinemaCode, shownMovie, showingDateTime, movieType);

		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * This method edits the details of the movie session.
	 */
	public void editSession() {
		ArrayList<MovieSession> sessions = movie_session_Controller.readFile();
		MovieSession movieSession;
		int choice;
		ListAllSessions();

		System.out.println("Enter the No. of the sessions to edit: ");
		choice = userInput(0, sessions.size() - 1);

		movieSession = sessions.get(choice);

		printMenu("Select which session attribute you want to edit: ",
				"1. Movie Shown",
				"2. Date and Timing of Session",
				"3. Back");

		int editChoice = userInput(1, 3);

		switch (editChoice) {
			case 1:
				ArrayList<Movie> movieList = movie_controller.getShowingMovies();

				int i = 0;
				System.out.printf("%-3s %-4s %-28s %-15s", "No.", "ID", "Name", "Showing Status");
				System.out.println();
				for (Movie m : movieList) {
					System.out.printf("%-3d %-4d %-28s %-15s", ++i, m.getId(), reduceStringLength(m.getTitle(), 25),
							m.getShowingStatus().toString());
					System.out.println();
				}

				printMenu(++i + ". Back");
				System.out.println("Select the No. of the movie you want to show: ");
				choice = userInput(1, i);

				if (choice == i) {
					load();
				}

				Movie newShowingMovie = movieList.get(choice - 1);

				if (movie_session_Controller.checkIfValidTime(movieSession.getShowDateTime_NonString(),
						movieSession.getCinemaCode(), newShowingMovie)) {
					movieSession.setShownMovie(newShowingMovie);
				} else {
					System.out.println("Invalid movie, returning to settings menu...");
					load();
				}
				break;
			case 2:
				LocalDateTime newShowingTime = getDateTimeInput(
						"Enter the new showing time for the movie (e.g 10 Nov 2022, 10:30am): ");
				if (movie_session_Controller.checkIfValidTime(newShowingTime, movieSession.getCinemaCode(),
						movieSession.getShownMovie())) {
					movieSession.setShowDateTime(newShowingTime);
				} else {
					System.out.println("Invalid showing time, returning to settings menu...");
					load();
				}
				break;
			case 3:
				load();
				break;
		}
		movie_session_Controller.updateSession(movieSession);
		System.out.println("Update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
	 * This method deletes a movie session.
	 */
	public void deleteSession() {
		ArrayList<MovieSession> sessions = movie_session_Controller.readFile();
		MovieSession movieSession;
		int choice;
		ListAllSessions();

		System.out.println("Enter the No. of the sessions to delete: ");
		choice = userInput(0, sessions.size() - 1);

		movieSession = sessions.get(choice);

		movie_session_Controller.deleteByID(movieSession.getSessionId());

		System.out.println("Deletion success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}
}
