package Control;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import Entity.Constants;

/** 
 * Controller for User Input.
 */

public class UserInput_Controller {
	
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * The Decimal Format 
	 */
	
	public static final DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Function to print header.
	 * @param header of userinput .
	 */
	
	public static void printHeader(String header) {
		int length = 65;
		for (int i = 0; i < length; i++)
			System.out.print("-");
		System.out.println();

		int indent = (length - header.length()) / 2;
		for (int i = 0; i < indent; i++)
			System.out.print(" ");
		System.out.print(header);
		System.out.println();

		for (int i = 0; i < length; i++)
			System.out.print("-");
		System.out.println();
	}

	/**
	 * Function to print Menu .
	 * @param menu of userinput .
	 */
	
	public static void printMenu(String... menu) {
		for (String s : menu) {
			String firstHalf;
			while (s.length() > 65) {
				int i = 65;
				while (s.charAt(i) != ' ')
					i--;
				firstHalf = s.substring(0, i);
				s = s.substring(i + 1);
				System.out.println(firstHalf);
			}
			System.out.println(s);
		}
		System.out.println();
	}
	
	/**
	 * Function to print Menu without spacing.
	 * @param menu of userinput.
	 */

	public static void printMenuWithoutSpace(String... menu) {
		for (String s : menu) {
			System.out.println(s);
		}
	}
	
	/**
	 * Function to generate spaces. 
	 * @param size Size of the amount of spaces needed.
	 * @return the string of spaces.
	 */

	public static String generateSpaces(int size) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++)
			stringBuilder.append(" ");
		return stringBuilder.toString();
	}
	
	/**
	 * Function to get the user choice.
	 * Makes sure the choice does not have any error.
	 * Makes sure the choice is with the range of choices.
	 * @param i Value of the lowest choice.
	 * @param j Value of the highest choice.
	 * @return Choice of the user. 
	 * @exception NumberFormatException On input error
	 */

	public static int userInput(int i, int j) {
		int choice;

		System.out.print("Enter choice: ");

		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException ex) {
			System.out.println("Invalid selection, press enter to try again.");
			sc.nextLine();
			return userInput(i, j);
		}

		if (choice < i || choice > j) {
			System.out.println("Invalid selection, please try again.");
			return userInput(i, j);
		}

		System.out.println("");

		return choice;
	}
	
	/**
	 * Function to get the string input.
	 * @param message Message to be printed.
	 * @return the string of the next input.
	 */
	

	public static String getStringInput(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		return input;
	}
	
	/**
	 * Function to get the string input in sentences
	 * @param message Message to be printed.
	 * @return the string of the next input.
	 */
	
	public static String getStringInput_Sentence(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		return input;
	}
	
	/**
	 * Function to get the integer input 
	 * @param message Message to be printed.
	 * @return the integer of the next input.
	 */
	
	public static int getIntInput(String message) {
		System.out.print(message);
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

	/**
	 * Function to get the integer which the integer 
	 * have to be higher than the minimum value.
	 * @param message Message to be printed.
	 * @param min Minimum value that the Input integer have to be higher than.
	 * @return the int of the next input.
	 */
	
	public static int getIntInput_Min(String message, int min) {
		System.out.print(message);
		int input = Integer.parseInt(sc.nextLine());
		while (input < min) {
			System.out.print("Value too low please input again: ");
			input = Integer.parseInt(sc.nextLine());
		}
		return input;
	}
	
	/**
	 * Function to get the double input.
	 * @param message Message to be printed in sentences.
	 * @return the double of the next input.
	 */

	public static Double getDoubleInput(String message) {
		System.out.print(message);
		Double input = Double.parseDouble(sc.nextLine());
		return input;
	}
	
	/**
	 * Function to get the date input. 
	 * @param message Message to be printed.
	 * @return the parsed date.
	 */

	public static LocalDate getDateInput(String message) {
		System.out.print(message);
		String input = "";
		input += sc.nextLine();
		LocalDate parsedDate = LocalDate.parse(input, Constants.dateFormatLong);
		return parsedDate;
	}
	
	/**
	 * Function to get the date input if there is no year.
	 * @param message Message to be printed.
	 * @return the parsed date.
	 */

	public static LocalDate getDateInput_NoYear(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		// Year is arbitrary
		input += " 2022";
		LocalDate parsedDate = LocalDate.parse(input, Constants.dateFormatLong);
		return parsedDate;
	}
	
	/**
	 * Function to get the date and time input.
	 * @param message Message to be printed.
	 * @return the parsed date.
	 */

	public static LocalDateTime getDateTimeInput(String message) {
		System.out.print(message);
		String input = "";
		input += sc.nextLine();
		LocalDateTime parsedDate = LocalDateTime.parse(input, Constants.datetimeFormat);
		return parsedDate;
	}
	/**
	 * Function to check the user and password of the admin account
	 * @return Boolean indicating if the login is successful or a failure.
	 */

	public static boolean adminAuthorise() {
		String _user = "GV";
		String _pw = "123456";
		System.out.print("Enter username: ");
		String user = sc.nextLine();
		System.out.print("Enter password: ");
		String pw = sc.nextLine();

		if (_user == user && _pw == pw)
			return true;
		else
			return false;
	}
	
	/**
	 * Function to change the list to a string.
	 * @param a List of strings to be converted to string.
	 * @return the string of the converted list.
	 */

	public static String listToString(ArrayList<String> a) {
		String s = "";
		for (int i = 0; i < a.size() - 1; i++)
			s = s + a.get(i) + ", ";
		s = s + a.get(a.size() - 1);
		return s;
	}
	
	/**
	 * Function to reduce the length of the string.
	 * @param input String input that will be reduced.
	 * @param maxLen The maximum length of the string allowed.
	 * @return the reduced string
	 */
	
	public static String reduceStringLength(String input, int maxLen) {
		String res = input;
		if (input.length()>maxLen) {
			res = res.substring(0, maxLen-2);
			res = res + "...";
		}
		return res;
	}

}
