package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Boundary.BaseMenu;
import Boundary.LogIn;
import Boundary.MainMenu;
import Control.Booking_Controller;
import Control.Cinema_Controller;
import Control.Cineplex_Controller;
import Control.MovieSession_Controller;
import Control.Holiday_Controller;
import Control.MovieGoer_Controller;
import Entity.Booking;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.Constants;
import Entity.MovieGoer;
import Entity.MovieSession;
import Entity.Seat;
import Entity.SeatLayout;
import Entity.Ticket;
/**
 * This shows the booking confirmation menu
 */
public class BookingConfirmationMenu extends BaseMenu {

	/**
	 * The MovieGoer who booked the tickets.
	 */
	private MovieGoer cust;
	
	/**
	 * The Moviesession that was chosen by the moviegoer.
	 */
	private MovieSession ms;
	
	/**
	 * The Cineplex that was chosen by the moviegoer.
	 */
	private Cineplex cp;
	
	/**
	 * The Seats that was chosen by the moviegoer.
	 */
	private ArrayList<Seat> chosenSeats;
	
	/**
	 * Intialising the controllers for the confirmed booking menu.
	 */
	private Cineplex_Controller cpc = new Cineplex_Controller();
	private Cinema_Controller cc = new Cinema_Controller(cpc);
	private MovieSession_Controller msc = new MovieSession_Controller(cc);
	private Holiday_Controller hc = new Holiday_Controller();
	private Booking_Controller bc = new Booking_Controller();
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	
	/**
         * Creates a new BookingConfirmationMenu with the given parameters.
         * @param cust This BookingConfirmationMenu's MovieGoer.
	 * @param ms This BookingConfirmationMenu's ms.
	 * @param chosenSeats This BookingConfirmationMenu's chosenSeats.
	 * @param cp This BookingConfirmationMenu's cp.
         */
	public BookingConfirmationMenu(MovieGoer cust, MovieSession ms, ArrayList<Seat> chosenSeats, Cineplex cp) {
		this.cust = cust;
		this.ms = ms;
		this.chosenSeats = chosenSeats;
		this.cp = cp;
	}

	/**
         * Loads the Booking Confirmation Menu.
         */
	@Override
	public void load() {
		printHeader("Booking Confirmation");
		if (cust == null) {
			printMenu("You must be logged in to continue",
					"1. Login",
					"2. Register",
					"3. Return to main menu");
			int choice = userInput(1, 3);
			switch (choice) {
				case 1:
					navigate(this, new LogIn(ms, chosenSeats, cp));
					break;
				case 2:
					navigate(this, new MovieGoerRegistration(ms, chosenSeats, cp));
					break;
				case 3:
					navigate(this, new MainMenu());
			}
		} else {
			showConfirmation();
		}
	}

	/**
         * Loads the Booking Confirmation Menu.
         */
	private void showConfirmation() {
		ArrayList<Ticket> tixList = new ArrayList<Ticket>();
		int i = 1;
		for (Seat s : chosenSeats)
			tixList.add(createTicket(ms, s, i));

		Double totalPrice = 0.00;
		for (Ticket t : tixList)
			totalPrice += t.getTicketPrice();
		printMenu("The total price is : $" + df.format(totalPrice),
				"1. Confirm booking",
				"2. Cancel");
		int choice = userInput(1, 2);
		if (choice == 2)
			navigate(this, new MovieGoerMainMenu(cust));
		else {
			bc.addBooking(totalPrice, cc.getCinemaByCode(ms.getCinemaCode()), ms.getShownMovie(), tixList, cust, cp);

			SeatLayout sl = ms.getSeatPlan();
			for (Seat s : chosenSeats) {
				sl.assignSeats(s.getSeatID());
			}
			ms.setSeatPlan(sl);
			msc.updateSession(ms);

			ArrayList<Booking> bList = cust.getBookingList();
			bList.add(
					new Booking(totalPrice, cc.getCinemaByCode(ms.getCinemaCode()), ms.getShownMovie(), tixList, cust,
							cp));
			mgc.updateMovieGoer(MovieGoer_Controller.CHOICE_BOOKING, cust.getUsername(), bList);

			printMenu("Your booking has been confirmed",
					"1. View booking history",
					"2. Main menu");

			choice = userInput(1, 2);
			if (choice == 2)
				navigate(this, new MovieGoerMainMenu(cust));

			else
				navigate(this, new BookingHistory(cust));
		}
	}

	/**
         * Function creates ticket with given parameters.
	 * @param session The ticket's movie session.
	 * @param seat The ticket's seat number.
	 * @param i [ Notsure whats the purpose of i ]
	 * @return a completed ticket with the necessary details.
         */
	private Ticket createTicket(MovieSession session, Seat seat, int i) {
		Constants.TICKET_TYPE ticketType = Constants.TICKET_TYPE.FRI_WEEKEND_PH;
		Cinema cinema = cc.getCinemaByCode(session.getCinemaCode());
		Constants.CINEMA_TYPE cinemaType = cinema.getCinemaType();
		LocalDateTime dateAndTime = session.getShowDateTime_NonString();
		LocalDate dateOnly = dateAndTime.toLocalDate();
		DayOfWeek day = dateAndTime.getDayOfWeek();

		if (hc.isHoliday(dateOnly) == true || hc.isHoliday(dateOnly.plusDays(1)) == true)
			day = DayOfWeek.SATURDAY;

		if (day == DayOfWeek.MONDAY || day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY
				|| day == DayOfWeek.THURSDAY) {
			ticketType = Constants.TICKET_TYPE.MON_TO_THU;
			if (session.getMovieType() == Constants.MOVIE_TYPE.TWO_D) {
				printMenu("Ticket for Customer " + (i++),
						"Are you a student/senior citizen?",
						"1. Student",
						"2. Senior Citizen",
						"3. None");
				int choice = userInput(1, 3);

				switch (choice) {
					case 1:
						ticketType = Constants.TICKET_TYPE.STUDENT;
						break;
					case 2:
						ticketType = Constants.TICKET_TYPE.SENIOR;
						break;
				}
			}
		}
		Ticket tix = new Ticket(ticketType, session.getMovieType(), cinemaType, seat);
		return tix;
	}

}
