package Boundary.Admin;

import Boundary.BaseMenu;
import Control.Holiday_Controller;
import Control.SystemSettings_Controller;
import Control.TicketPrice_Controller;
import Control.Admin_Controller;
import Entity.Holiday;
import Entity.Admin;
import Entity.Constants;

import java.util.ArrayList;

import static Control.UserInput_Controller.*;

import java.time.LocalDate;

public class editSettings extends BaseMenu {

	private Holiday_Controller hol_Control = new Holiday_Controller();
	private Admin_Controller admin_Control = new Admin_Controller();
	private SystemSettings_Controller systemsetting_Controller = new SystemSettings_Controller();
	private TicketPrice_Controller ticketPrice_control = new TicketPrice_Controller();

	@Override
	public void load() {
		showMenu();
	}

	public void showMenu() {
		printHeader("Edit Settings");
		printMenu("Choose from one of the following options:",
				"1. Add Holiday",
				"2. Delete Holiday",
				"3. Update Holiday",
				"4. List All Holidays",
				"5. Change Ticket Type Pricing",
				"6. Change Movie Type Pricing",
				"7. Change Cinema Type Pricing",
				"8. Change Top 5 Ranking Option",
				"9. Add Admin",
				"10. Delete Admin",
				"11. Back");

		int choice = userInput(1, 11);

		switch (choice) {
			case 1:
				add_Holiday();
				break;
			case 2:
				deleteHolidays();
				break;
			case 3:
				updateHolidays();
				break;
			case 4:
				list_Holidays();
				break;
			case 5:
				changeTicketPrice();
				break;
			case 6:
				changeMoviePrice();
				break;
			case 7:
				changeCinemaPrice();
				break;
			case 8:
				changeTop5();
				break;
			case 9:
				addNewAdmin();
				break;
			case 10:
				deleteAdmin();
				break;
			case 11:
				back();
				break;
		}
	}

	// Holidays

	public void add_Holiday() {
		String name;
		LocalDate date;
		boolean flag = false;
		int choice = 0;

		date = getDateInput_NoYear("Enter holiday date (e.g. 10 Aug): ");

		if (hol_Control.isHoliday(date)) {
			flag = true;
			System.out.println(
					"Holiday already exist, press 0 to return to settings menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				add_Holiday();
			}
		}

		name = getStringInput_Sentence("Enter holiday name: ");

		hol_Control.addHoliday(name, date);
		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	public void deleteHolidays() {
		ArrayList<Holiday> Hol_list = this.hol_Control.readFile();
		Holiday holiday_temp;
		int choice;
		int i = 0;

		if (Hol_list.isEmpty()) {
			printMenu("There are no holidays in the system.");
			load();
		}
		for (Holiday h : Hol_list) {
			printMenu(++i + ". " + h.getDateString() + ": " + h.getName());
		}

		printMenu(++i + ". Back");

		printMenu("Enter the id of holiday to remove from the system: ");

		choice = userInput(1, i);

		if (choice == i) {
			load();
		}

		holiday_temp = Hol_list.get(choice - 1);
		hol_Control.deleteHoliday(holiday_temp.getDate());

		System.out.println("Deletion success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		load();
	}

	public void updateHolidays() {
		ArrayList<Holiday> Hol_list = this.hol_Control.readFile();
		Holiday holiday_temp;
		int choice;
		int i = 0;

		if (Hol_list.isEmpty()) {
			printMenu("There are no holidays in the system.");
			showMenu();
		}
		for (Holiday h : Hol_list) {
			printMenu(++i + ". " + h.getDateString() + ": " + h.getName());
		}

		printMenu(++i + ". Back");

		printMenu("Enter the id of holiday to update in the system: ");

		choice = userInput(1, i);

		if (choice == i) {
			load();
		}

		holiday_temp = Hol_list.get(choice - 1);

		printMenu("Choose from one of the following options:",
				"1. Edit Name",
				"2. Edit Date",
				"3. Back");

		choice = userInput(1, 3);

		switch (choice) {
			case 1:
				String name = getStringInput_Sentence("Enter holiday name: ");
				hol_Control.updateHoliday(Holiday_Controller.CHOICE_NAME, holiday_temp.getDate(), name);
				break;
			case 2:
				LocalDate date = getDateInput("Enter holiday date (e.g. 10 Aug): ");
				hol_Control.updateHoliday(Holiday_Controller.CHOICE_DATE, holiday_temp.getDate(), date);
				break;
			case 3:
				load();
				break;
		}

		System.out.println("Update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		load();
	}

	public void list_Holidays() {
		ArrayList<Holiday> Hol_list = this.hol_Control.readFile();
		int choice;
		printMenu("Holidays");

		if (Hol_list.isEmpty()) {
			printMenu("There are no holidays in the system.");
			showMenu();
		}

		int i = 0;

		for (Holiday h : Hol_list) {
			printMenu(++i + ". " + h.getDateString() + ": " + h.getName());
		}

		printMenu(++i + ". Back");

		choice = userInput(1, i);
		if (choice == i) {
			load();
		}
	}

	// Edit pricing

	public void changeTicketPrice() {
		int i = 0;
		int choice;
		double newPrice;
		System.out.println("These are the ticket types and their corresponding prices.");
		for (Constants.TICKET_TYPE ticket : Constants.TICKET_TYPE.values()) {
			System.out.format("%d. %s: $%.2f", ++i, ticket.toString(), this.ticketPrice_control.getPrice(ticket));
			System.out.println();
		}

		printMenu(++i + ". Back");

		printMenu("Enter the ID of the ticket type you want to change");

		choice = userInput(1, i);

		newPrice = getDoubleInput("Enter the new price: ");

		switch (choice) {
			case 1:
				this.ticketPrice_control.updateTicketPriceItem(Constants.TICKET_TYPE.MON_TO_THU, newPrice);
				break;
			case 2:
				this.ticketPrice_control.updateTicketPriceItem(Constants.TICKET_TYPE.FRI_WEEKEND_PH, newPrice);
				break;
			case 3:
				this.ticketPrice_control.updateTicketPriceItem(Constants.TICKET_TYPE.SENIOR, newPrice);
				break;
			case 4:
				this.ticketPrice_control.updateTicketPriceItem(Constants.TICKET_TYPE.STUDENT, newPrice);
				break;
			case 5:
				load();
				break;
		}
		System.out.println("Price update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	public void changeMoviePrice() {
		int i = 0;
		int choice;
		double newPrice;
		System.out.println("These are the Movie types and their corresponding prices.");
		for (Constants.MOVIE_TYPE movie : Constants.MOVIE_TYPE.values()) {
			System.out.format("%d. %s: $%.2f", ++i, movie.toString(), this.ticketPrice_control.getPrice(movie));
			System.out.println();
		}

		printMenu(++i + ". Back");

		printMenu("Enter the ID of the movie type you want to change");

		choice = userInput(1, i);

		newPrice = getDoubleInput("Enter the new price: ");

		switch (choice) {
			case 1:
				this.ticketPrice_control.updateTicketPriceItem(Constants.MOVIE_TYPE.TWO_D, newPrice);
				break;
			case 2:
				this.ticketPrice_control.updateTicketPriceItem(Constants.MOVIE_TYPE.THREE_D, newPrice);
				break;
			case 3:
				this.ticketPrice_control.updateTicketPriceItem(Constants.MOVIE_TYPE.BLOCKBUSTER, newPrice);
				break;
			case 4:
				load();
				break;
		}
		System.out.println("Price update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	public void changeCinemaPrice() {
		int i = 0;
		int choice;
		double newPrice;
		System.out.println("These are the Cinema types and their corresponding prices.");
		for (Constants.CINEMA_TYPE Cinema : Constants.CINEMA_TYPE.values()) {
			System.out.format("%d. %s: $%.2f", ++i, Cinema.toString(), this.ticketPrice_control.getPrice(Cinema));
			System.out.println();
		}

		printMenu(++i + ". Back");

		printMenu("Enter the ID of the cinema type you want to change");

		choice = userInput(1, i);

		newPrice = getDoubleInput("Enter the new price: ");

		switch (choice) {
			case 1:
				this.ticketPrice_control.updateTicketPriceItem(Constants.CINEMA_TYPE.STANDARD, newPrice);
				break;
			case 2:
				this.ticketPrice_control.updateTicketPriceItem(Constants.CINEMA_TYPE.PLATIUM, newPrice);
				break;
			case 3:
				load();
				break;
		}
		System.out.println("Price update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	// Top 5
	public void changeTop5() {

		String Top5OrderBy = systemsetting_Controller.readSystemSettings().get(0);

		String newOrderBy = Top5OrderBy;
		ArrayList<String> update = new ArrayList<String>();

		System.out.println("Top 5 for Movie Goers are currently ordered by " + Top5OrderBy);

		printMenu("Choose from one of the following options:",
				"1. Change to by review",
				"2. Change to by ticket sales",
				"3. Change to let the user decide",
				"4. Back");

		int choice = userInput(1, 4);

		switch (choice) {
			case 1:
				newOrderBy = "review";
				update.add(newOrderBy);
				break;
			case 2:
				newOrderBy = "sales";
				update.add(newOrderBy);
				break;
			case 3:
				newOrderBy = "both";
				update.add(newOrderBy);
				break;
			default:
				break;
		}

		if (!update.isEmpty()) {
			systemsetting_Controller.saveSystemSettings(update);
		}

		load();
	}

	// Admin Functions
	public void addNewAdmin() {
		int choice = -1;
		String name;
		String password;
		Boolean flag = false;

		name = getStringInput("Enter admin's username: ");

		if (admin_Control.AdminExists(name)) {
			flag = true;
			System.out.println(
					"Username taken, press 0 to return to settings menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				addNewAdmin();
			}
		}

		password = getStringInput("Enter admin's password: ");
		admin_Control.addAdmin(name, password);
		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	public void deleteAdmin() {
		ArrayList<Admin> admin_list = admin_Control.readFile();
		Admin temp;
		int i = 0;
		int choice;

		if (admin_list.size() <= 1) {
			System.out
					.println("There is only 1 admin account left in the system, you're unable to delete this account");
			load();
		}

		for (Admin a : admin_list) {
			printMenu(++i + ". " + a.getUsername());
		}

		printMenu(++i + ". Back");

		printMenu("Enter the id of admin to remove from the system: ");

		choice = userInput(1, i);

		if (choice == i) {
			load();
		}

		temp = admin_list.get(choice - 1);
		admin_Control.deleteAdmin(temp.getUsername());

		System.out.println("Deletion success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}
}