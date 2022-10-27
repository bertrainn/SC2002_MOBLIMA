package MOBLIMA.Boundary;

import java.util.Scanner;

import static MOBLIMA.Boundary.MenuMethods.*;

public class AdminMain {

	public static void main(String[] args) {
		AdminMainMenu amm = new AdminMainMenu();
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			amm.showMenu();
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					//editMovie();
					break;
				case 2:
					//editCinema();
					break;
				case 3:
					//configSetting();
					break;
				case 4:
					//goBack();
					break;
			}
		} while (choice < 4);
		sc.close();
	}

}