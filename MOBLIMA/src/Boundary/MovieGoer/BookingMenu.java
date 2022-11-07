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
import Entity.Constants;
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
	 * The Movie that was chosen by the moviegoer
	 */
	private Movie mov;

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
     * Creates a new Booking Menu with the given parameters.
 *
     * @param m  This BookingMenu's MovieGoer.
     * @param mov This BookingMenu's Movie.
     */
	public BookingMenu(MovieGoer m, Movie mov) {
		cust = m;
		this.mov = mov;
	}

	/**
         * Loads the Booking Menu.
         */
	@Override
	public void load() {
		if (mov == null) {
			if (cp != null)
				showMenu();
			else
				showMenuByMovie();
		}
		else {
			ArrayList<MovieSession> movSess = msc.readFile();
			ArrayList<MovieSession> curMs = new ArrayList<MovieSession>();
			for (MovieSession ms : movSess) {
				if (ms.getShownMovie().getShowingStatus() != Constants.SHOWING_STATUS.CS && ms.getShownMovie().getTitle().equals(mov.getTitle()))
					curMs.add(ms);
			}
			if (curMs.isEmpty()) {
				printMenu("There are no sessions for this movie, enter any number to go back.");
				userInput(0, 9);
				navigate(this, new MovieDetailsView(mov, cust));
			}
			showMovies(curMs);
		}
	}

	/**
         * Shows the Booking Confirmation Menu that will be loaded into the load method.
         */
	private void showMenu() {
		ArrayList<MovieSession> curMs = new ArrayList<MovieSession>();
		ArrayList<MovieSession> movSes = msc.readFile();
		ArrayList<MovieSession> allMovieSessions = new ArrayList<MovieSession>();
		ArrayList<Cinema> cList = cp.getCinemaList();
		
		for (MovieSession ms : movSes) {
			if (ms.getShownMovie().getShowingStatus() != Constants.SHOWING_STATUS.CS)
				allMovieSessions.add(ms);
		}

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
			ArrayList<Movie> movieNames = new ArrayList<Movie>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : curMs) {
				flag = 0;
				String movieName = ms.getShownMovie().getTitle();
				for (Movie s : movieNames) {
					if (s.getTitle().equals(movieName)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					continue;
				movieNames.add(ms.getShownMovie());
				printMenuWithoutSpace(++i + ". " + reduceStringLength(movieName, 60));
			}
			printMenu(++i + ". Back");

			int choice = userInput(1, i);

			if (choice == i)
				back();
			else {
				Movie m = movieNames.get(choice - 1);
				showSessions(m);
			}
		}
	}
	
	/**
     * Shows the Booking Confirmation Menu by Movie that will be loaded into the load method.
     */
	private void showMenuByMovie() {
		ArrayList<MovieSession> curMs = new ArrayList<MovieSession>();
		ArrayList<MovieSession> movSes = msc.readFile();
		ArrayList<MovieSession> allMovieSessions = new ArrayList<MovieSession>();
		
		for (MovieSession ms : movSes) {
			if (ms.getShownMovie().getShowingStatus() != Constants.SHOWING_STATUS.CS)
				allMovieSessions.add(ms);
		}
		
		printHeader("Movies currently showing");
		
		if (allMovieSessions.isEmpty()) {
			printMenu("There are no movies showing currently, enter any number to go back.");
			userInput(1, 9);
			back();
		}
	
		else {
			int i = 0;
			int flag = 0;
			ArrayList<Movie> movieNames = new ArrayList<Movie>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : allMovieSessions) {
				flag = 0;
				String movieName = ms.getShownMovie().getTitle();
				for (Movie s : movieNames) {
					if (s.getTitle().equals(movieName)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1)
					continue;
				movieNames.add(ms.getShownMovie());
				printMenuWithoutSpace(++i + ". " + reduceStringLength(movieName, 60));
			}
			printMenu(++i + ". Back");
	
			int choice = userInput(1, i);
	
			if (choice == i)
				back();
			else {
				Movie m = movieNames.get(choice - 1);
				for (MovieSession ms2 : allMovieSessions) {
					if (ms2.getShownMovie().getTitle().equals(m.getTitle()))
						curMs.add(ms2);
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
		ArrayList<Integer> avail = new ArrayList<Integer>();
		String movieName = reduceStringLength(curMs.get(0).getShownMovie().getTitle(), 60);
		printHeader("Movie sessions for " + movieName);
		if (curMs.isEmpty()) {
			printMenu("There are no sessions for this movie, enter any number to go back.");
			userInput(0, 9);
			navigate(this, new MovieDetailsView(mov, cust));
		}
		printMenuWithoutSpace("Choose one of the following sessions:");
		for (MovieSession ms : curMs) {
			String availability;
			SeatLayout availSeats = ms.getSeatPlan();
			int seatCount = 0, coupleCount = 0, total = availSeats.getCol()*availSeats.getRow();
			
			for (int j=0; j<total-1; j++) {
				if (!availSeats.isSeatAssign(j))
					seatCount++;
				if (availSeats.isCoupleSeat(j) != -1 && !availSeats.isSeatAssign(j))
					coupleCount++;
			}
			seatCount = seatCount - (coupleCount/2);
			if (seatCount == 0) {
				availability = "Sold out";
			}
			else {
				availability = String.valueOf(seatCount) + " seat(s) available";
			}
			avail.add(seatCount);
			
			String cineplexCode = ms.getCinemaCode().substring(0, 1);
			Cineplex cinp = cpc.getCineplexByCode(cineplexCode);
			String type = ms.getMovieType().toString();
			if (type.length()>11)
				type = type.substring(0, 11);
			printMenuWithoutSpace(++i + ". " + reduceStringLength(cinp.getName(), 14) + generateSpaces(15 - cinp.getName().length()) 
				+ ms.getShowDateTime() + generateSpaces(25 - ms.getShowDateTime().toString().length()) + type 
				+ generateSpaces(15 - type.length()) + availability);
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
			int noOfSeats = userInput(1, avail.get(choice-1));
			showSeatingPlan(ms, noOfSeats);
		}
	}

	/**
         * Shows the movie session that the MovieGoer will like to book and prompt
	 * the moviegoer for the amount of tickets.
         */
	private void showSessions(Movie m) {
		int i = 0;
		ArrayList<Integer> avail = new ArrayList<Integer>();
		ArrayList<MovieSession> sessions = msc.readFileByValues(MovieSession_Controller.CHOICE_MOVIE, m.getId());
		ArrayList<MovieSession> movSes = new ArrayList<MovieSession>();
		
		String movieName = reduceStringLength(m.getTitle(), 40);

		printMenuWithoutSpace("Showtimes for " + movieName + ":");
		for (MovieSession ms : sessions) {
			if (!ms.getCinemaCode().substring(0, 1).equals(this.cp.getCineplexCode()))
				continue;
			String availability;
			SeatLayout availSeats = ms.getSeatPlan();
			int seatCount = 0, coupleCount = 0, total = availSeats.getCol()*availSeats.getRow();
			
			for (int j=0; j<total-1; j++) {
				if (!availSeats.isSeatAssign(j))
					seatCount++;
				if (availSeats.isCoupleSeat(j) != -1 && !availSeats.isSeatAssign(j))
					coupleCount++;
			}
			seatCount = seatCount - (coupleCount/2);
			if (seatCount == 0) {
				availability = "Sold out";
			}
			else {
				availability = String.valueOf(seatCount) + " seat(s) available";
			}
			avail.add(seatCount);
			
			String type = ms.getMovieType().toString();
			if (type.length()>11)
				type = type.substring(0, 11);
			
			movSes.add(ms);
			
			printMenuWithoutSpace(++i + ". " + ms.getShowDateTime() + 
					generateSpaces(25 - ms.getShowDateTime().toString().length()) 
				+ type + generateSpaces(15 - type.length()) + availability);
		}
		printMenu(++i + ". Back");

		int choice = userInput(1, i);

		if (choice == i)
			load();
		else {
			MovieSession ms = movSes.get(choice - 1);
			printMenu("How many tickets would you like to purchase? (Enter 1 to buy 1 couple seat)");
			int noOfSeats = userInput(1, avail.get(choice-1));
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
