package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import Boundary.BaseMenu;
import Control.Booking_Controller;
import Entity.Booking;
import Entity.MovieGoer;
import Entity.MovieSession;
import Entity.Ticket;

/**
 * This shows the booking history menu.
 */
public class BookingHistory extends BaseMenu {

	/**
	 * The MovieGoer of the booking history.
	 */
	private MovieGoer cust;

	/**
	 * Intialising the controller for the booking history menu.
	 */
	private Booking_Controller bc = new Booking_Controller();

	/**
	 * Constructor for the BookingHistory menu.
	 *
	 * @param m This BookingHistory's MovieGoer.
	 */
	public BookingHistory(MovieGoer m) {
		cust = m;
	}

	/**
	 * Loads the Booking History Menu.
	 */
	@Override
	public void load() {
		printHeader("Booking History for " + cust.getName());

		int i = 0;
		ArrayList<Booking> bookings = bc.retrieveByUser(cust.getUsername());

		if (bookings.isEmpty()) {
			printMenu("You do not have any bookings. Enter any number to go back.");
			userInput(0, 9);
			navigate(this, new MovieGoerMainMenu(cust));
		}

		for (Booking b : bookings) {
			MovieSession movSes = null;
			String dateTimeMov;
			int indent = String.valueOf(++i).length() + 2;

			int year = Integer.parseInt(b.getTID().substring(3, 7));
			int month = Integer.parseInt(b.getTID().substring(7, 9));
			int day = Integer.parseInt(b.getTID().substring(9, 11));
			int hour = Integer.parseInt(b.getTID().substring(11, 13));
			int min = Integer.parseInt(b.getTID().substring(13, 15));

			LocalDate date = LocalDate.of(year, month, day);
			LocalTime time = LocalTime.of(hour, min);
			String dateStr = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
			String timeStr = time.format(DateTimeFormatter.ofPattern("hh:mm a"));

			ArrayList<Ticket> tixList = b.getTicketList();
			Collections.sort(tixList, (o1, o2) -> Integer.compare(o1.getSeat().getSeatID(), o2.getSeat().getSeatID()));
			String seats = "";
			for (int j = 0; j < tixList.size() - 1; j++) {
				seats = seats + tixList.get(j).getSeat().getSeatID() + ", ";
			}
			seats = seats + tixList.get(tixList.size() - 1).getSeat().getSeatID();
			String tit = reduceStringLength(b.getMovie().getTitle(), 50);

			ArrayList<MovieSession> msList = b.getCinema().getMovieSessions();
			for (MovieSession ms : msList) {
				int flag = 0;
				if (ms.getShownMovie().getTitle().equals(b.getMovie().getTitle())) {
					if (!ms.getCinemaCode().equals(b.getCinema().getcinemaCode()))
						flag = 1;
					if (ms.getMovieType() != b.getTicketList().get(0).getMovieType())
						flag = 1;
					if (flag == 0) {
						movSes = ms;
						break;
					}
				}
			}

			if (movSes == null)
				dateTimeMov = "28-Nov-2022";
			else
				dateTimeMov = movSes.getShowDateTime();

			printMenu(i + ". " + "Booking date: " + dateStr + ", " + timeStr,
					generateSpaces(indent) + "Movie date: " + dateTimeMov,
					generateSpaces(indent) + "Movie: " + tit,
					generateSpaces(indent) + "Cineplex: " + b.getCineplex().getName(),
					generateSpaces(indent) + "Cinema Hall: " + b.getCinema().getcinemaCode(),
					generateSpaces(indent) + "No. of tickets: " + tixList.size(),
					generateSpaces(indent) + "Seats: " + seats,
					generateSpaces(indent) + "Amount paid: $" + df.format(b.getTotalAmount()));
		}

		printMenu("Enter any number to go to main menu.");
		userInput(0, 9);
		navigate(this, new MovieGoerMainMenu(cust));
	}
}
