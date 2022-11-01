/**
 * TODO: Implement
 * Holiday
 * Ticket price
 * Edit account details
 */

package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.Holiday_Controller;
import MOBLIMA.Entity.TicketPrice;
import MOBLIMA.Entity.Holiday;
import MOBLIMA.Entity.Constants;

import java.util.ArrayList;
import java.time.LocalDate;

public class editSettings extends BaseMenu {

	private Holiday_Controller hol_Control = new Holiday_Controller();
	private TicketPrice ticketPrice = new TicketPrice();

	@Override
	public void load() {
		showMenu();
	}

	public void showMenu() {
		// TODO Auto-generated method stub
		printHeader("Edit Settings");
		printMenu("Choose from one of the following options:",
				"1. Add Holiday",
				"2. Delete Holiday",
				"3. Update Holiday",
				"4. List All Holidays",
				"5. Change Movie Type Pricing",
				"6. Change Cinema Type Pricing",
				"7. Back");

		int choice = userInput(1, 7);

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
				back();
				break;
		}
	}

	public void add_Holiday() {
		String name = getStringInput_Sentence("Enter holiday name: ");
		LocalDate date = getDateInput_NoYear("Enter holiday date (dd MMM): ");
		hol_Control.addHoliday(name, date);
		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void deleteHolidays() {
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
}