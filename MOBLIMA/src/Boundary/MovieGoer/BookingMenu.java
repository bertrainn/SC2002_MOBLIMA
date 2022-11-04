package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;

import Boundary.BaseMenu;
import Control.Cineplex_Controller;
import Control.Cinema_Controller;
import Control.MovieSession_Controller;
import Entity.Cineplex;
import Entity.Movie;
import Entity.MovieGoer;
import Entity.MovieSession;
import Entity.Seat;
import Entity.SeatLayout;

public class BookingMenu extends BaseMenu {

	private Cineplex_Controller cpc = new Cineplex_Controller();
	private Cinema_Controller cc = new Cinema_Controller(cpc);
	private MovieSession_Controller msc = new MovieSession_Controller(cc);
	private Cineplex cp;
	private MovieGoer cust;

	public BookingMenu(MovieGoer m, Cineplex cp) {
		cust = m;
		this.cp = cp;
	}

	@Override
	public void load() {
		showMenu();
	}

	private void showMenu() {
		ArrayList<MovieSession> allMovieSessions = msc.readFile();

		printHeader("Movies showing at " + cp.getName());

		if (allMovieSessions.isEmpty()) {
			printMenu("There are no movies showing at this cineplex, enter any number to go back.");
			userInput(1, 9);
			back();
		}

		else {
			int i = 0;
			int flag = 0;
			ArrayList<String> movieNames = new ArrayList<>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : allMovieSessions) {
				String movieName = ms.getShownMovie().getTitle();
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
				showSessions(m);
			}
		}
	}

	private void showSessions(Movie m) {
		int i = 0;
		ArrayList<MovieSession> sessions = msc.readFileByValues(MovieSession_Controller.CHOICE_MOVIE, m.getId());

		printMenuWithoutSpace("Showtimes for " + m.getTitle() + ":");
		for (MovieSession ms : sessions) {
			printMenuWithoutSpace(++i + ". " + ms.getShowDateTime());
		}
		printMenu(++i + ". Back");

		int choice = userInput(1, i);

		if (choice == i)
			load();
		else {
			MovieSession ms = sessions.get(choice - 1);
			printMenu("How many tickets would you like to purchase?");
			int maxSeats = ms.getSeatPlan().getCol() * ms.getSeatPlan().getRow();
			int noOfSeats = userInput(1, maxSeats);
			showSeatingPlan(ms, noOfSeats);
		}

	}

	private void showSeatingPlan(MovieSession ms, int noOfSeats) {
		int flag = 0;
		ArrayList<Seat> chosenSeats = new ArrayList<Seat>();
		SeatLayout seatPlan = ms.getSeatPlan();
		int total = seatPlan.getCol() * seatPlan.getRow();
		seatPlan.printLayout();

		for (int i = 0; i < noOfSeats; i++) {
			printMenuWithoutSpace("Choice for seat " + (i + 1));
			int choice = userInput(0, total - 1);
			chosenSeats.add(new Seat(choice));
		}

		for (Seat i : chosenSeats) {
			if (seatPlan.isSeatAssign(i.getSeatID())) {
				flag = 1;
				break;
			}
		}

		if (flag == 1) {
			printMenu("One or more of the seats you have chosen is/are occupied, enter any number to try again.");
			userInput(0, 9);
			showSeatingPlan(ms, noOfSeats);
		} else {
			navigate(this, new BookingConfirmationMenu(cust, ms, chosenSeats, cp));
		}
	}

}
