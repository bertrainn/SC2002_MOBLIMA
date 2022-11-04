package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Boundary.BaseMenu;
import Control.Booking_Controller;
import Entity.Booking;
import Entity.MovieGoer;
import Entity.Ticket;

public class BookingHistory extends BaseMenu {
	
	private MovieGoer cust;
	private Booking_Controller bc = new Booking_Controller();
	
	public BookingHistory(MovieGoer m) {
		cust = m;
	}

	@Override
	public void load() {
		printHeader("Booking History for " + cust.getName());
		
		int i = 0;
		ArrayList<Booking> bookings = bc.retrieveByUser(cust.getUsername());
		
		if (bookings.isEmpty()) {
			printMenu("You do not have any bookings. Enter any number to go back.");
			userInput(0,9);
			navigate(this, new MovieGoerMainMenu(cust));
		}
		
		for (Booking b : bookings) {
			int indent = String.valueOf(++i).length() + 2;
			
			int year = Integer.parseInt(b.getTID().substring(3, 7));
			int month = Integer.parseInt(b.getTID().substring(7, 9)); 
			int day = Integer.parseInt(b.getTID().substring(9, 11));
			int hour = Integer.parseInt(b.getTID().substring(11, 13));
			int min = Integer.parseInt(b.getTID().substring(13, 15));
			
			LocalDate date = LocalDate.of(year, month, day);
			LocalTime time = LocalTime.of(hour, min);
			
			ArrayList<Ticket> tixList = b.getTicketList();
			String seats = "";
			for (int j=0; j<tixList.size()-1; j++) {
				seats = seats + tixList.get(j).getSeat().getSeatID() + ", ";
			}
			seats = seats + tixList.get(tixList.size()-1).getSeat().getSeatID();
			String tit = reduceStringLength(b.getMovie().getTitle(), 50);
			
			printMenu(i + ". " + "Booking date: " + date + " " + time,
					generateSpaces(indent) + "Movie: " + tit,
					generateSpaces(indent) + "Cineplex: " + b.getCineplex().getName(),
					generateSpaces(indent) + "Cinema Hall: " + b.getCinema().getcinemaCode(),
					generateSpaces(indent) + "No. of tickets: " + tixList.size(),
					generateSpaces(indent) + "Seats: " + seats,
					generateSpaces(indent) + "Amount paid: $" + df.format(b.getTotalAmount()));
		}
		
		printMenu("Enter any number to go to main menu.");
		userInput(0,9);
		navigate(this, new MovieGoerMainMenu(cust));
	}

}
