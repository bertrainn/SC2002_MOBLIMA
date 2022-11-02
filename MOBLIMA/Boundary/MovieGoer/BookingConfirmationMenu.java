package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.Booking_Controller;
import MOBLIMA.Control.Cinema_Controller;
import MOBLIMA.Control.Cineplex_Controller;
import MOBLIMA.Control.MovieSession_Controller;
import MOBLIMA.Control.Holiday_Controller;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.Booking;
import MOBLIMA.Entity.Cinema;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.Seat;
import MOBLIMA.Entity.SeatLayout;
import MOBLIMA.Entity.Ticket;

public class BookingConfirmationMenu extends BaseMenu {
	
	private MovieGoer cust;
	private MovieSession ms;
	private ArrayList<Seat> chosenSeats;
	private Cineplex_Controller cpc = new Cineplex_Controller();
	private Cinema_Controller cc = new Cinema_Controller(cpc);
	private MovieSession_Controller msc = new MovieSession_Controller(cc);
	private Holiday_Controller hc = new Holiday_Controller();
	private Booking_Controller bc = new Booking_Controller();
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	
	public BookingConfirmationMenu(MovieGoer cust, MovieSession ms, ArrayList<Seat> chosenSeats) {
		this.cust = cust;
		this.ms = ms;
		this.chosenSeats = chosenSeats;
	}
	
	@Override
	public void load() {
		printHeader("Booking Confirmation");
		if (cust == null) {
			printMenu("You must be logged in to continue",
					  "1. Login",
					  "2. Register",
					  "3. Return to main menu");
			int choice = userInput(1,3);
			switch(choice) {
				case 1:
					navigate(this, new MovieGoerLogin(ms, chosenSeats));
					break;
				case 2:
					navigate(this, new MovieGoerRegistration(ms, chosenSeats));
			}
		}
		else {
			showConfirmation();
		}
	}
	
	private void showConfirmation() {
		ArrayList<Ticket> tixList = new ArrayList<Ticket>();
		int i = 1;
		for (Seat s : chosenSeats)
			tixList.add(createTicket(ms, s, i));
		
		Double totalPrice = 0.0;
		for (Ticket t : tixList)
			totalPrice += t.getTicketPrice();
		totalPrice = Math.round(totalPrice*100.0)/100.0;
		printMenu("The total price is : $" + totalPrice,
				  "1. Confirm booking",
				  "2. Cancel");
		int choice = userInput(1, 2);
		if (choice == 2)
			navigate(this, new MovieGoerMainMenu(cust));
		else {
			bc.addBooking(totalPrice, cc.getCinemaByCode(ms.getCinemaCode()), ms.getShownMovie(), tixList, cust);
			
			SeatLayout sl = ms.getSeatPlan();
			for (Seat s : chosenSeats) {
				sl.assignSeats(s.getSeatID());
			}
			ms.setSeatPlan(sl);
			msc.updateSession(ms);
			
			ArrayList<Booking> bList = cust.getBookingList();
			bList.add(new Booking(totalPrice, cc.getCinemaByCode(ms.getCinemaCode()), ms.getShownMovie(), tixList, cust));
			mgc.updateMovieGoer(mgc.CHOICE_BOOKING, cust.getUsername(), bList);
			
			printMenuWithoutSpace("Booking Confirmed Successfully, enter any number to return to main menu");
			userInput(0,9);
			navigate(this, new MovieGoerMainMenu(cust));
		}
	}
	
	private Ticket createTicket(MovieSession session, Seat seat, int i) {
		Constants.TICKET_TYPE ticketType = Constants.TICKET_TYPE.FRI_WEEKEND_PH;
		Cinema cinema = cc.getCinemaByCode(session.getCinemaCode());
		Constants.CINEMA_TYPE cinemaType = cinema.getCinemaType();
		LocalDateTime dateAndTime = session.getShowDateTime_NonString();
		LocalDate dateOnly = dateAndTime.toLocalDate();
		DayOfWeek day = dateAndTime.getDayOfWeek();
		
		if (hc.isHoliday(dateOnly) == true || hc.isHoliday(dateOnly.plusDays(1)) == true)
			day = DayOfWeek.SATURDAY;
		
		if (day == DayOfWeek.MONDAY  || day == DayOfWeek.TUESDAY || day == DayOfWeek.WEDNESDAY || day == DayOfWeek.THURSDAY) {
			ticketType = Constants.TICKET_TYPE.MON_TO_THU;
			if (session.getMovieType() == Constants.MOVIE_TYPE.TWO_D) {
				printMenu("Ticket for Customer " + (i++),
						  "Are you a student/senior citizen?",
						  "1. Student",
						  "2. Senior Citizen",
						  "3. None");
				int choice = userInput(1, 3);
				
				switch(choice) {
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
