package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;

import Boundary.BaseMenu;
import Control.Cineplex_Controller;
import Control.Cinema_Controller;
import Control.MovieSession_Controller;
import Control.Movie_Controller;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.Movie;
import Entity.MovieGoer;
import Entity.MovieSession;
import Entity.Seat;
import Entity.SeatLayout;

/**
 * This shows the booking menu.
 */
public class BookingMenu extends BaseMenu {

	/**
	 * Intialising the controllers for the booking menu.
	 */
	private Cineplex_Controller cpc = new Cineplex_Controller();
	private Cinema_Controller cc = new Cinema_Controller(cpc);
	private MovieSession_Controller msc = new MovieSession_Controller(cc);
	private Movie_Controller mc = new Movie_Controller();
	
	/**
	 * The Cineplex that was chosen by the moviegoer.
	 */
	private Cineplex cp;
	
	/**
	 * The MovieGoer who booked the tickets.
	 */
	private MovieGoer cust;

	/**
         * Creates a new Booking Menu with the given parameters.
	 *
         * @param m  This BookingMenu's MovieGoer.
	 * @param cp This BookingMenu's cineplex.
         */
	public BookingMenu(MovieGoer m, Cineplex cp) {
		cust = m;
		this.cp = cp;
	}

	/**
         * Loads the Booking Menu.
         */
	@Override
	public void load() {
		if (cp != null)
			showMenu();
		else 
			showMenuByMovie();
	}

	/**
         * Shows the Booking Confirmation Menu that will be loaded into the load method.
         */
	private void showMenu() {
		ArrayList<MovieSession> curMs = new ArrayList<MovieSession>();
		ArrayList<MovieSession> allMovieSessions = msc.readFile();
		ArrayList<Cinema> cList = cp.getCinemaList();

		printHeader("Movies showing at " + cp.getName());

		for (MovieSession ms : allMovieSessions) {
			for (Cinema c : cList) {
				if(ms.getCinemaCode().equals(c.getcinemaCode())) {
					curMs.add(ms);
				}
			}
		}
		
		if (curMs.isEmpty()) {
			printMenu("There are no movies showing at this cineplex, enter any number to go back.");
			userInput(1, 9);
			back();
		}

		else {
			int i = 0;
			int flag = 0;
			ArrayList<String> movieNames = new ArrayList<>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : curMs) {
				String movieName = reduceStringLength(ms.getShownMovie().getTitle(), 60);
				for (String s : movieNames) {
					if (s.equals(movieName)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					continue;
				movieNames.add(movieName);
				printMenuWithoutSpace(++i + ". " + movieName);
			}
			printMenu(++i + ". Back");

			int choice = userInput(1, i);

			if (choice == i)
				back();
			else {
				MovieSession ms = curMs.get(choice - 1);
				Movie m = ms.getShownMovie();
				showSessions(m);
			}
		}
	}
	
	/**
     * Shows the Booking Confirmation Menu by Movie that will be loaded into the load method.
     */
	private void showMenuByMovie() {
		ArrayList<MovieSession> curMs = new ArrayList<MovieSession>();
		ArrayList<MovieSession> allMovieSessions = msc.readFile();
	
		printHeader("Movies currently showing");
		
		if (allMovieSessions.isEmpty()) {
			printMenu("There are no movies showing currently, enter any number to go back.");
			userInput(1, 9);
			back();
		}
	
		else {
			int i = 0;
			int flag = 0;
			ArrayList<String> movieNames = new ArrayList<>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : allMovieSessions) {
				String movieName = reduceStringLength(ms.getShownMovie().getTitle(), 60);
				for (String s : movieNames) {
					if (s.equals(movieName)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					continue;
				movieNames.add(movieName);
				printMenuWithoutSpace(++i + ". " + movieName);
			}
			printMenu(++i + ". Back");
	
			int choice = userInput(1, i);
	
			if (choice == i)
				back();
			else {
				MovieSession ms = allMovieSessions.get(choice - 1);
				Movie m = ms.getShownMovie();
				for (MovieSession ms2 : allMovieSessions) {
					if (ms2.getShownMovie().getTitle().equals(m.getTitle()))
						curMs.add(ms);
				}
				
				showMovies(curMs);
			}
		}
	}
	
	/**
     * Show sessions by movies
     * @param curMs The movie sessions for that movie
     */
	private void showMovies(ArrayList<MovieSession> curMs) {
		int i = 0;
		String movieName = reduceStringLength(curMs.get(0).getShownMovie().getTitle(), 60);
		printHeader("Movie sessions for " + movieName);
		printMenuWithoutSpace("Choose one of the following sessions:");
		for (MovieSession ms : curMs) {
			String cineplexCode = ms.getCinemaCode().substring(0, 1);
			Cineplex cinp = cpc.getCineplexByCode(cineplexCode);
			printMenuWithoutSpace(++i + ". " + cinp.getName() + generateSpaces(20 - cinp.getName().length()) + ms.getShowDateTime());
		}
		printMenu(++i + ". Back");
		
		int choice = userInput(1, i);
		
		if (choice == i)
			back();
		else {
			MovieSession ms = curMs.get(choice - 1);
			String cineplexCode = ms.getCinemaCode().substring(0, 1);
			cp = cpc.getCineplexByCode(cineplexCode);
			printMenu("How many tickets would you like to purchase? (Enter 1 to buy 1 couple seat)");
			int maxSeats = ms.getSeatPlan().getCol() * ms.getSeatPlan().getRow();
			int noOfSeats = userInput(1, maxSeats);
			showSeatingPlan(ms, noOfSeats);
		}
	}

	/**
         * Shows the movie session that the MovieGoer will like to book and prompt
	 * the moviegoer for the amount of tickets.
         */
	private void showSessions(Movie m) {
		int i = 0;
		ArrayList<MovieSession> sessions = msc.readFileByValues(MovieSession_Controller.CHOICE_MOVIE, m.getId());
		
		String movieName = reduceStringLength(m.getTitle(), 40);

		printMenuWithoutSpace("Showtimes for " + movieName + ":");
		for (MovieSession ms : sessions) {
			printMenuWithoutSpace(++i + ". " + ms.getShowDateTime());
		}
		printMenu(++i + ". Back");

		int choice = userInput(1, i);

		if (choice == i)
			load();
		else {
			MovieSession ms = sessions.get(choice - 1);
			printMenu("How many tickets would you like to purchase? (Enter 1 to buy 1 couple seat)");
			int maxSeats = ms.getSeatPlan().getCol() * ms.getSeatPlan().getRow();
			int noOfSeats = userInput(1, maxSeats);
			showSeatingPlan(ms, noOfSeats);
		}

	}
	
	/**
         * Shows the seating plan for the Moviegoer to decide 
	 * which seats he would liket to choose.
         */
	private void showSeatingPlan(MovieSession ms, int noOfSeats) {
		int flag = 0, count = 0;
		ArrayList<Seat> chosenSeats = new ArrayList<Seat>();
		SeatLayout seatPlan = ms.getSeatPlan();
		int total = seatPlan.getCol() * seatPlan.getRow();
		seatPlan.printLayout();

		for (int i = 0; i < noOfSeats; i++) {
			printMenuWithoutSpace("Choice for seat " + (i + 1));
			int choice = userInput(0, total - 1);
			if (seatPlan.isCoupleSeat(choice) == -1)
				chosenSeats.add(new Seat(choice));
			else {
				int pairID = seatPlan.isCoupleSeat(choice);
				chosenSeats.add(new Seat(choice, true, pairID));
				chosenSeats.add(new Seat(pairID, true, choice));
			}
		}
		
		for (int i=0; i<chosenSeats.size(); i++) {
			if (seatPlan.isSeatAssign(chosenSeats.get(i).getSeatID()))
				flag = 1;
			for (int j=i+1; j<chosenSeats.size(); j++) {
				if (chosenSeats.get(i).getSeatID() == chosenSeats.get(j).getSeatID()) {
					flag = 2;
					if (chosenSeats.get(i).isIsCoupleSeat())
						flag = 3;
					break;
				}
			}
			if (flag != 0)
				break;
		}

		if (flag == 1) {
			printMenu("One or more of the seats you have chosen is/are occupied, enter any number to try again.");
			userInput(0, 9);
			showSeatingPlan(ms, noOfSeats);
		} else if (flag == 2) {
			printMenu("Two or more of the seats you have chosen are duplicates, enter any number to try again.");
			userInput(0, 9);
			showSeatingPlan(ms, noOfSeats);
		} else if (flag == 3) {
			printMenu("Please only enter one seat number per couple seat, enter any number to try again.");
			userInput(0, 9);
			showSeatingPlan(ms, noOfSeats);
		}
		else {
			navigate(this, new BookingConfirmationMenu(cust, ms, chosenSeats, cp));
		}
	}

}
