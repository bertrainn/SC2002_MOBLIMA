package MOBLIMA.Control;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import MOBLIMA.Entity.Constants;

public class UserInput_Controller {

	static Scanner sc = new Scanner(System.in);

	public static final DecimalFormat df = new DecimalFormat("0.00");

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

	public static void printMenuWithoutSpace(String... menu) {
		for (String s : menu) {
			System.out.println(s);
		}
	}

	public static String generateSpaces(int size) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++)
			stringBuilder.append(" ");
		return stringBuilder.toString();
	}

	public static int userInput(int i, int j) {
		int choice;

		System.out.print("Enter choice: ");

		try {
			choice = Integer.parseInt(sc.nextLine());
		} catch (InputMismatchException ex) {
			System.out.println("Invalid selection, please try again.");
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

	public static String getStringInput(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		return input;
	}

	public static String getStringInput_Sentence(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		return input;
	}

	public static int getIntInput(String message) {
		System.out.print(message);
		int input = Integer.parseInt(sc.nextLine());
		return input;
	}

	public static int getIntInput_Min(String message, int min) {
		System.out.print(message);
		int input = Integer.parseInt(sc.nextLine());
		while (input < min) {
			System.out.print("Value too low please input again: ");
			input = Integer.parseInt(sc.nextLine());
		}
		return input;
	}

	public static Double getDoubleInput(String message) {
		System.out.print(message);
		Double input = Double.parseDouble(sc.nextLine());
		return input;
	}

	public static LocalDate getDateInput(String message) {
		System.out.print(message);
		String input = "";
		input += sc.nextLine();
		LocalDate parsedDate = LocalDate.parse(input, Constants.dateFormatLong);
		return parsedDate;
	}

	public static LocalDate getDateInput_NoYear(String message) {
		System.out.print(message);
		String input = sc.nextLine();
		// Year is arbitrary
		input += " 2022";
		LocalDate parsedDate = LocalDate.parse(input, Constants.dateFormatLong);
		return parsedDate;
	}

	public static LocalDateTime getDateTimeInput(String message) {
		System.out.print(message);
		String input = "";
		input += sc.nextLine();
		LocalDateTime parsedDate = LocalDateTime.parse(input, Constants.datetimeFormat);
		return parsedDate;
	}

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

	public static String listToString(ArrayList<String> a) {
		String s = "";
		for (int i = 0; i < a.size() - 1; i++)
			s = s + a.get(i) + ", ";
		s = s + a.get(a.size() - 1);
		return s;
	}

}
