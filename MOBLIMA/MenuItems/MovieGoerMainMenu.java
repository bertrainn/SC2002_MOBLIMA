package MOBLIMA.MenuItems;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieGoerMainMenu {
	
	private ArrayList<String> choices = new ArrayList<>();
	
	private void addChoices() {
		choices.add("Search for movies");
		choices.add("All movies");
		choices.add("Back");
	}
	
	public void showMenu() {
		System.out.println("Booking Page");
		addChoices();
	}
	

}
