package MOBLIMA.Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuMethods {
	
	static Scanner sc = new Scanner(System.in);

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
			System.out.println(s);
		}
		System.out.println();
	}

	public static int userInput(int i, int j) {
		int choice;
		
		System.out.print("Enter choice: ");
		
		try {
			choice = sc.nextInt();
		} catch (InputMismatchException ex) {
			System.out.println("Invalid selection, please try again.");
			sc.nextLine();
			return userInput(i, j);
		}
		
		if (choice < i || choice > j) {
			System.out.println("Invalid selection, please try again.");
			return userInput(i, j);
		}

		return choice;
	}
	
	public static String getStringInput(String message) {
		System.out.print(message);
		String input = sc.next();
		return input;
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

}
