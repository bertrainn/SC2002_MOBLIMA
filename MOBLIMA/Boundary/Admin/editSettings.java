package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.Holiday_Controller;
import MOBLIMA.Control.SystemSettings_Controller;
import MOBLIMA.Entity.TicketPrice;
import MOBLIMA.Entity.Holiday;
import MOBLIMA.Entity.Constants;

import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;

public class editSettings extends BaseMenu {

	private Holiday_Controller hol_Control = new Holiday_Controller();
	private TicketPrice ticketPrice = new TicketPrice();
	private SystemSettings_Controller systemsetting_Controller = new SystemSettings_Controller();

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
				"5. Change Movie Type Pricing",
				"6. Change Cinema Type Pricing",
				"7. Change Top 5 Ranking Option",
				"8. Add Admin",
				"8. Back");

		int choice = userInput(1, 8);

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
				break;
			case 6:
				break;
			case 7:
				changeTop5();
				break;
			case 8:
				back();
				break;
		}
	}

	public void add_Holiday() {
		String name;
		LocalDate date;

		name = getStringInput_Sentence("Enter holiday name: ");
		date = getDateInput_NoYear("Enter holiday date (dd MMM): ");
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
				hol_Control.updateHoliday(hol_Control.CHOICE_NAME, holiday_temp.getDate(), name);
				break;
			case 2:
				LocalDate date = getDateInput("Enter holiday date (dd MMM): ");
				hol_Control.updateHoliday(hol_Control.CHOICE_DATE, holiday_temp.getDate(), date);
				break;
			case 3:
				load();
				break;
		}

		System.out.println("Deletion success, returning to settings menu...");
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

	public void changeTop5() {

		String Top5OrderBy = systemsetting_Controller.readSystemSettings().get(0);

		String newOrderBy = Top5OrderBy;
		ArrayList<String> update = new ArrayList<String>();

		System.out.println("Top 5 for Movie Goers are currently ordered by " + Top5OrderBy);

		printMenu("Choose from one of the following options:",
				"1. Change to by review",
				"2. Change to by Ticket Sales",
				"3. Back");

		int choice = userInput(1, 3);

		switch (choice) {
			case 1:
				newOrderBy = "review";
				update.add(newOrderBy);
				break;
			case 2:
				newOrderBy = "sales";
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
}