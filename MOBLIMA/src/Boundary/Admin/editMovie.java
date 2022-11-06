package Boundary.Admin;

import Boundary.BaseMenu;
import Entity.Constants;
import Entity.Movie;
import Entity.Booking;
import Control.Movie_Controller;
import Control.Booking_Controller;

import java.util.ArrayList;
import java.time.LocalDate;

import static Control.UserInput_Controller.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This allows the admin to edit the movie.
 */
public class editMovie extends BaseMenu {

	/**
	 * Intialising the controllers for the confirmed booking menu.
	 */
	Movie_Controller movie_controller = new Movie_Controller();
	Booking_Controller booking_controller = new Booking_Controller();

	/**
         * Loads the Edit Movie Menu.
         */
	@Override
	public void load() {
		showMenu();
	}

	/**
         * Shows the content of the Menu that will be loaded into the edit movie menu.
         */
	public void showMenu() {
		printHeader("Movie Options");
		printMenu("Choose from one of the following options:",
				"1. Add Movie",
				"2. Delete Movie",
				"3. Update Movie",
				"4. List Top 5 Movies By Sales",
				"5. List Top 5 Movies By Review Ratings",
				"6. Back");

		int choice = userInput(1, 7);

		switch (choice) {
			case 1:
				AddMovie();
				break;
			case 2:
				StopShowingMovie();
				break;
			case 3:
				UpdateMovie();
				break;
			case 4:
				ListMoviesBySales();
				break;
			case 5:
				ListMoviesByRating();
				break;
			case 6:
				back();
				break;
		}
	}

	/**
         * Function to add movies.
         */
	public void AddMovie() {
		int choice = -1;
		int i;
		int numberofentries;
		String entries;

		String title;
		String description;
		Constants.AGE_CLASSIFICATION age_class = Constants.AGE_CLASSIFICATION.G;
		ArrayList<String> DirectorList = new ArrayList<String>();
		ArrayList<String> ActorList = new ArrayList<String>();
		ArrayList<String> GenreList = new ArrayList<String>();
		LocalDate OpeningDate, ClosingDate;
		Duration duration;

		Boolean flag = false;

		title = getStringInput_Sentence("Enter movie's title: ");

		if (movie_controller.MovieExists(title)) {
			flag = true;
			System.out.println(
					"Movie is already in the database, press 0 to return to settings menu, press any other number to try again.");
			choice = userInput(0, 9);
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				AddMovie();
			}
		}

		description = getStringInput_Sentence("Enter movie description: ");

		printMenu("Select the movie age rating (number):",
				"1. General(G)",
				"2. Parental Guidance (PG)",
				"3. Parental Guidance 13 (PG13)",
				"4. No Children under 16(NC16)",
				"5. Mature 18 (M18)",
				"6. Back");

		int age_choice = userInput(1, 6);
		switch (age_choice) {
			case 1:
				age_class = Constants.AGE_CLASSIFICATION.G;
				break;
			case 2:
				age_class = Constants.AGE_CLASSIFICATION.PG;
				break;
			case 3:
				age_class = Constants.AGE_CLASSIFICATION.PG13;
				break;
			case 4:
				age_class = Constants.AGE_CLASSIFICATION.NC16;
				break;
			case 5:
				age_class = Constants.AGE_CLASSIFICATION.M18;
				break;
			case 6:
				load();
				break;

		}

		numberofentries = getIntInput_Min("Enter the number of directors: ", 1);

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " director: ");
			DirectorList.add(entries);
		}

		numberofentries = getIntInput_Min("Enter the number of actors (at least 3): ", 3);

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " actor: ");
			ActorList.add(entries);
		}

		numberofentries = getIntInput_Min("Enter the number of genres: ", 1);

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " genre: ");
			GenreList.add(entries);
		}

		OpeningDate = getDateInput("Enter movie's opening date (dd MMM yyyy): ");
		ClosingDate = OpeningDate.plusDays(30);

		duration = Duration.ofMinutes(getIntInput_Min("Enter the duration of the movie (in minutes): ", 0));

		movie_controller.addMovie(title, description, age_class, DirectorList, ActorList, GenreList, null, OpeningDate,
				ClosingDate, duration);
		System.out.println("Addition success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();

	}

	/**
         * Function to stop the movie on showing for the moviegoer as 
	 * the movie is no longer showing.
         */
	public void StopShowingMovie() {
		// don't need to remove the movie from the database: just change it to EOS;

		ArrayList<Movie> MovieList = movie_controller.readFile();
		Movie temp;
		this.ListMovies();

		System.out.println("Enter the index of the movie you want to edit: ");
		int movie_choice = userInput(1, MovieList.size());

		temp = MovieList.get(movie_choice - 1);
		movie_controller.updateMovie(Movie_Controller.CHOICE_SHOWING, temp.getId(), Constants.SHOWING_STATUS.EOS);

		System.out.println("Update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	/**
         * Function to update the details of the movie.
         */
	public void UpdateMovie() {

		ArrayList<Movie> MovieList = movie_controller.readFile();
		ArrayList<String> list = new ArrayList<String>();
		Movie temp;
		int numberofentries;
		String entries;
		int i;
		this.ListMovies();
		System.out.println("Enter the index of the movie you want to edit: ");
		int movie_choice = userInput(1, MovieList.size());

		temp = MovieList.get(movie_choice - 1);

		printMenu("Select which movie attribute you want to edit: ",
				"1. Title",
				"2. Description",
				"3. Age Rating",
				"4. Actors List",
				"5. Director List",
				"6. Genre List",
				"7. Opening Date",
				"8. Closing Date",
				"9. Showing Status",
				"10. Duration",
				"11. Back");

		int choice = userInput(1, 12);
		switch (choice) {
			case 1:
				String title = getStringInput("Enter the new title: ");
				movie_controller.updateMovie(Movie_Controller.CHOICE_TITLE, temp.getId(), title);
				break;
			case 2:
				String desc = getStringInput("Enter the new description: ");
				movie_controller.updateMovie(Movie_Controller.CHOICE_DESC, temp.getId(), desc);
				break;
			case 3:
				Constants.AGE_CLASSIFICATION age = temp.getAgeRating();
				printMenu("Select the movie age rating (number):",
						"1. General(G)",
						"2. Parental Guidance (PG)",
						"3. Parental Guidance 13 (PG13)",
						"4. No Children under 16(NC16)",
						"5. Mature 18 (M18)",
						"6. Back");

				int age_choice = userInput(1, 6);
				switch (age_choice) {
					case 1:
						age = Constants.AGE_CLASSIFICATION.G;
						break;
					case 2:
						age = Constants.AGE_CLASSIFICATION.PG;
						break;
					case 3:
						age = Constants.AGE_CLASSIFICATION.PG13;
						break;
					case 4:
						age = Constants.AGE_CLASSIFICATION.NC16;
						break;
					case 5:
						age = Constants.AGE_CLASSIFICATION.M18;
						break;
					case 6:
						back();
						break;

				}
				movie_controller.updateMovie(Movie_Controller.CHOICE_AGERATING, temp.getId(), age);
				break;
			case 4:

				numberofentries = getIntInput_Min("Enter the new number of actors: ", 0);

				for (i = 0; i < numberofentries; i++) {
					entries = getStringInput_Sentence("Enter the no." + (i + 1) + " actor: ");
					list.add(entries);
				}
				movie_controller.updateMovie(Movie_Controller.CHOICE_ACTOR, temp.getId(), list);
				break;
			case 5:
				numberofentries = getIntInput_Min("Enter the new number of director: ", 0);
				for (i = 0; i < numberofentries; i++) {
					entries = getStringInput_Sentence("Enter the no." + (i + 1) + " director: ");
					list.add(entries);
				}
				movie_controller.updateMovie(Movie_Controller.CHOICE_DIRECTOR, temp.getId(), list);
				break;
			case 6:
				numberofentries = getIntInput_Min("Enter the new number of genres: ", 0);
				for (i = 0; i < numberofentries; i++) {
					entries = getStringInput_Sentence("Enter the no." + (i + 1) + " genre: ");
					list.add(entries);
				}
				movie_controller.updateMovie(Movie_Controller.CHOICE_GENRE, temp.getId(), list);
				break;
			case 7:
				LocalDate newOPDate = getDateInput("Enter new opening date (dd MMM yyyy): ");
				movie_controller.updateMovie(Movie_Controller.CHOICE_OPENING, temp.getId(), newOPDate);
				break;
			case 8:
				LocalDate newCLDate = getDateInput("Enter new closing date (dd MMM yyyy): ");
				movie_controller.updateMovie(Movie_Controller.CHOICE_CLOSING, temp.getId(), newCLDate);
				break;
			case 9:

				Constants.SHOWING_STATUS showingStat = temp.getShowingStatus();

				printMenu("Select the movie age rating (number):",
						"1. Coming Soon(CS)",
						"2. Preview (P)",
						"3. Now Showing (NS)",
						"4. End Of Showing (EOS)",
						"5. Back");

				int showingstatchoice = userInput(1, 5);
				switch (showingstatchoice) {
					case 1:
						showingStat = Constants.SHOWING_STATUS.CS;
						break;
					case 2:
						showingStat = Constants.SHOWING_STATUS.P;
						break;
					case 3:
						showingStat = Constants.SHOWING_STATUS.NS;
						break;
					case 4:
						showingStat = Constants.SHOWING_STATUS.EOS;
						break;
					case 5:
						load();
						break;
				}

				movie_controller.updateMovie(Movie_Controller.CHOICE_SHOWING, temp.getId(), showingStat);
				break;
			case 10:
				Duration newDuration = Duration
						.ofMinutes(getIntInput("Enter the new duration of the movie (in minutes): "));
				movie_controller.updateMovie(Movie_Controller.CHOICE_DURATION, temp.getId(), newDuration);
				break;
			case 11:
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

	/**
         * Function to list the movies by their sales.
         */
	public void ListMoviesBySales() {
		ArrayList<Movie> top5 = new ArrayList<Movie>();
		HashMap<Movie, Integer> movieSales = topSales();
		if (movieSales.isEmpty()) {
			System.out.println("There are no sales yet");
			load();
		}
		HashMap<Movie, Integer> sortedSalesList = sortHashMap(movieSales);

		printMenu("Select the movie which you want to list (number):",
				"1. All movies in the system",
				"2. Movies that are showing and going to be shown",
				"3. Back");

		int choice = userInput(1, 3);

		switch (choice) {
			case 1:
				for (Map.Entry<Movie, Integer> sales : sortedSalesList.entrySet()) {
					Movie m = sales.getKey();
					top5.add(m);
				}

				break;
			case 2:
				for (Map.Entry<Movie, Integer> sales : sortedSalesList.entrySet()) {
					Movie m = sales.getKey();
					if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
						top5.add(m);
				}
				break;
			case 3:
				load();
				break;
		}

		while (top5.size() > 5)
			top5.remove(5);

		int i = 0;
		System.out.printf("%-3s %-4s %-25s %-15s", "No.", "ID", "Name", "Number of Sales");
		System.out.println();
		for (Movie m : top5) {
			System.out.printf("%-3d %-4d %-25s %-15d", ++i, m.getId(), reduceStringLength(m.getTitle(), 25),
					movieSales.get(m));
			System.out.println();
		}

	}

	/**
         * Function to list the movies by their rating.
         */
	public void ListMoviesByRating() {
		ArrayList<Movie> top5 = new ArrayList<Movie>();
		ArrayList<Movie> movieList = movie_controller.readFile();

		printMenu("Select the movie which you want to list (number):",
				"1. All movies in the system",
				"2. Movies that are showing and going to be shown",
				"3. Back");

		int choice = userInput(1, 3);

		switch (choice) {
			case 1:
				for (Movie m : movieList)
					top5.add(m);
				Collections.sort(top5, (o1, o2) -> compareRating(o1, o2));
				break;
			case 2:
				for (Movie m : movieList)
					if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
						top5.add(m);
				Collections.sort(top5, (o1, o2) -> compareRating(o1, o2));
				break;
			case 3:
				load();
				break;
		}

		while (top5.size() > 5)
			top5.remove(5);

		int i = 0;
		System.out.printf("%-3s %-4s %-25s %-15s", "No.", "ID", "Name", "Overall Review");
		System.out.println();
		for (Movie m : top5) {
			System.out.printf("%-3d %-4d %-25s %-15.2f", ++i, m.getId(), m.getTitle(), m.getOverallRating());
			System.out.println();
		}

	}

	/**
         * Function that compares the movie ratings.
	 *
	 * @param m1 The first Movie to be compared.
	 * @param m2 The second Movie to be compared.
	 * @return a value which indicates which movie is the better rating.
         */
	private int compareRating(Movie m1, Movie m2) {
		String r1 = m1.getOverallRating();
		String r2 = m2.getOverallRating();
		if (r1 == "N/A")
			r1 = "0";
		if (r2 == "N/A")
			r2 = "0";

		if (Double.parseDouble(r1) == Double.parseDouble(r2))
			return 0;
		else if (Double.parseDouble(r1) < Double.parseDouble(r2))
			return 1;
		else
			return -1;
	}

	/**
         * idk
         */
	private HashMap<Movie, Integer> topSales() {
		ArrayList<Booking> bookingList = booking_controller.readFile();
		HashMap<Movie, Integer> movieSales = new HashMap<Movie, Integer>();

		for (Booking b : bookingList) {
			movieSales.put(b.getMovie(), movieSales.getOrDefault(b.getMovie(), 0) + 1);
		}
		return movieSales;
	}

	/**
         * idk
         */
	private HashMap<Movie, Integer> sortHashMap(HashMap<Movie, Integer> h) {
		LinkedHashMap<Movie, Integer> sortedMap = new LinkedHashMap<>();
		h.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		return sortedMap;
	}

	/**
         * Function to show movies and the movie's showing status.
         */
	public void ListMovies() {
		// show movie + showing status
		ArrayList<Movie> moviesList = movie_controller.readFile();
		int i = 0;
		System.out.printf("%-3s %-4s %-28s %-15s", "No.", "ID", "Name", "Showing Status");
		System.out.println();
		for (Movie m : moviesList) {
			System.out.printf("%-3d %-4d %-28s %-15s", ++i, m.getId(), reduceStringLength(m.getTitle(), 25),
					m.getShowingStatus().toString());
			System.out.println();
		}
	}

	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		editMovie eM = new editMovie();
		eM.AddMovie();
		eM.ListMovies();
	}
}
