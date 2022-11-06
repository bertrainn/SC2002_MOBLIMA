package Boundary.MovieGoer;

import Boundary.BaseMenu;
import Control.Booking_Controller;
import Control.Cineplex_Controller;
import Control.Movie_Controller;
import Control.SystemSettings_Controller;
import Entity.Booking;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.Constants;
import Entity.Movie;
import Entity.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This shows the movieslist that contains all the movies.
 */
public class MoviesList extends BaseMenu {

	/**
	 * Intialising the controllers for the confirmed booking menu.
	 */
	private Movie_Controller mc = new Movie_Controller();
	private Cineplex_Controller cc = new Cineplex_Controller();
	private SystemSettings_Controller ssc = new SystemSettings_Controller();
	private Booking_Controller bc = new Booking_Controller();

	/**
	 * The MovieGoer who selected the Movies List.
	 */
	private MovieGoer cust;
	
	/**
	 * The indicator on whether the MovieGoer would like 
	 * to see the top5 movies or not.
	 */
	private boolean topFive = false;
	
	/**
	 * 
	 */
	private String orderBy = ssc.readSystemSettings().get(0);

	/**
         * Creates a new MoviesList with the given parameters.
         * @param mg This MoviesList's MovieGoer.
	 */
	public MoviesList(MovieGoer mg) {
		cust = mg;
	}

	/**
         * Loads the Movies List Menu.
         */
	@Override
	public void load() {
		showMenu();
	}

	/**
         * Shows the Details of the movie that will be loaded into the load method.
         */
	private void showMenu() {
		topFive = false;
		orderBy = ssc.readSystemSettings().get(0);
		printHeader("View Movies");
		if (orderBy == "both") {
			printMenu("Choose from one of the following options:",
					"1. Make a booking",
					"2. Search for a movie",
					"3. List all movies",
					"4. List the top 5 movies by reviews",
					"5. List the top 5 movies by sales",
					"6. Back");
	
			int choice = userInput(1, 5);
	
			switch (choice) {
				case 1:
					allShowtimes();
					break;
				case 2:
					search();
					break;
				case 3:
					showAllMovies();
					break;
				case 4:
					orderBy = "review";
					topFive = true;
					showAllMovies();
					break;
				case 5:
					orderBy = "sales";
					topFive = true;
					showAllMovies();
				case 6:
					back();
					break;
			}
		}
		else {
			printMenu("Choose from one of the following options:",
					"1. Make a booking",
					"2. Search for a movie",
					"3. List all movies",
					"4. List the top 5 movies by " + orderBy,
					"5. Back");
	
			int choice = userInput(1, 5);
	
			switch (choice) {
				case 1:
					allShowtimes();
					break;
				case 2:
					search();
					break;
				case 3:
					showAllMovies();
					break;
				case 4:
					topFive = true;
					showAllMovies();
					break;
				case 5:
					back();
					break;
			}
		}
	}

	/**
         * Shows all the showtimes of each cineplex.
         */
	private void allShowtimes() {
		ArrayList<Cineplex> cineplexList = cc.readFile();
		int i = 0;

		printMenuWithoutSpace("Choose from one of the following cineplexes:");
		for (Cineplex cp : cineplexList) {
			printMenuWithoutSpace(++i + ". " + cp.getName());
		}
		printMenu(++i + ". Back");

		int choice = userInput(1, i);

		if (choice == i)
			showMenu();
		else {
			Cineplex cp = cineplexList.get(choice - 1);
			navigate(this, new BookingMenu(cust, cp));
		}

	}

	/**
         * Function allows user to search for any Movies by movie title.
         */
	private void search() {
		String searchInput = getStringInput("Enter the movie title: ");
		ArrayList<Movie> searchResults = mc.getMoviesByTitle(searchInput.toUpperCase());

		if (searchResults.isEmpty()) {
			printMenuWithoutSpace("No movies found, enter any number to go back");
			userInput(0, 9);
			showMenu();
		}

		else {
			int i = 0;

			printMenu(searchResults.size() + " result(s) found: ");
			for (Movie m : searchResults) {
				String tit = reduceStringLength(m.getTitle(), 25);
				printMenuWithoutSpace(++i + ". " + tit + 
						generateSpaces(30 - tit.length()) + m.getShowingStatus().toString());
			}
			printMenu(++i + ". Back");

			int choice = userInput(1, i);

			if (choice == i)
				showMenu();
			else {
				Movie m = searchResults.get(choice - 1);
				navigate(this, new MovieDetailsView(m, cust));
			}

		}
	}

	/**
         * Function show all current movies that is showing and
	 * top 5 movies as well.
         */
	private void showAllMovies() {
		ArrayList<Movie> movieList = mc.readFile();
		ArrayList<Movie> movies = null;

		if (topFive) {
			printHeader("Top 5 Movies (" + orderBy + ")");
			movies = getTop5Movies(orderBy);
		} else {
			printHeader("All Movies");
			movies = movieList;
		}

		if (movies.isEmpty()) {
			printMenu("There are no movies");
			showMenu();
		}

		int i = 0;

		if (!topFive) {
			for (Movie m : movies) {
				String tit = reduceStringLength(m.getTitle(), 25);
				if (m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
					continue;
				printMenuWithoutSpace(++i + ". " +tit + 
					generateSpaces(30 - tit.length()) 
					+ m.getShowingStatus().toString());
			}
		}

		else if (orderBy.equals("sales")) {
			HashMap<Movie, Integer> salesList = topSales(); // fakeTopSales();
			if (salesList.isEmpty()) {
				for (Movie m: movies) {
					String tit = reduceStringLength(m.getTitle(), 25);
					printMenuWithoutSpace(++i + ". " + tit 
						+ generateSpaces(30 - tit.length())
						+ m.getShowingStatus().toString() 
						+ generateSpaces(18 - m.getShowingStatus().toString().length())
						+ 0 + " tickets sold");
				}
			}
			else {
				for (Movie m : movies) {
					Integer sales = 0;
					for (Map.Entry<Movie, Integer> movie : salesList.entrySet()) {
						String movTitle = movie.getKey().getTitle();
						if (movTitle.equals(m.getTitle())) {
							sales = movie.getValue();
							break;
						}
					}
					String tit = reduceStringLength(m.getTitle(), 25);
					if (m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
						continue;
					printMenuWithoutSpace(++i + ". " + tit 
						+ generateSpaces(30 - tit.length())
						+ m.getShowingStatus().toString()
						+ generateSpaces(18 - m.getShowingStatus().toString().length())
						+ sales + " tickets sold");
				}
			}
		}

		else {
			for (Movie m : movies) {
				String tit = reduceStringLength(m.getTitle(), 25);
				if (m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
					continue;
				if (!m.getOverallRating().equals("N/A"))
					printMenuWithoutSpace(++i + ". " + tit 
						+ generateSpaces(30 - tit.length())
						+ m.getShowingStatus().toString() 
						+ generateSpaces(18 - m.getShowingStatus().toString().length()) 
						+ m.getOverallRating() + " stars");
				else
					printMenuWithoutSpace(++i + ". " + tit 
							+ generateSpaces(30 - tit.length())
							+  m.getShowingStatus().toString() 
							+ generateSpaces(18 - m.getShowingStatus().toString().length()) 
							+ m.getOverallRating());
			}
		}

		printMenu(++i + ". Back");

		int choice = userInput(1, i);

		if (choice == i)
			showMenu();
		else {
			Movie m = movies.get(choice - 1);
			navigate(this, new MovieDetailsView(m, cust));
		}

	}

	/**
         * Function that gets the current top 5 movies based on reviews or sales.
	 * @param orderBy The Filter based on how the movies is ranked based on review or sales.
	 * @return a arrayList of top5 movies.
         */
	private ArrayList<Movie> getTop5Movies(String orderBy) {
		ArrayList<Movie> top5 = new ArrayList<>();

		if (orderBy == "review") {
			ArrayList<Movie> movieList = mc.readFile();
			for (Movie m : movieList)
				if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
					top5.add(m);
			Collections.sort(top5, (o1, o2) -> compareRating(o1, o2));
		} else {
			HashMap<Movie, Integer> salesList = topSales(); // fakeTopSales();
			ArrayList<Movie> movieList = mc.readFile();
			if (salesList.isEmpty()) {
				for (Movie m : movieList)
				if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
					top5.add(m);
			}
			else {
				HashMap<Movie, Integer> sortedSalesList = sortHashMap(salesList);
				for (Map.Entry<Movie, Integer> sales : sortedSalesList.entrySet()) {
					Movie m = sales.getKey();
					if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS))
						top5.add(m);
				}
				if (top5.size() < 5) {
					for (Movie m : movieList) {
						int flag = 0;
						for (Movie m2 : top5) {
							if (m.getTitle().equals(m2.getTitle())) {
								flag = 1;
								break;
							}
						}
						if (flag == 1)
							continue;
						top5.add(m);
					}
				}
			}
		}

		while (top5.size() > 5)
			top5.remove(5);

		return top5;
	}

	/**
         * Function that compares the movie ratings.
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

	private HashMap<Movie, Integer> sortHashMap(HashMap<Movie, Integer> h) {
		LinkedHashMap<Movie, Integer> sortedMap = new LinkedHashMap<>();
		h.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

		return sortedMap;
	}

	/**
         * idk
         */
	private HashMap<Movie, Integer> topSales() {
		ArrayList<Booking> bookingList = bc.readFile();
		HashMap<Movie, Integer> movieSales = new HashMap<Movie, Integer>();
		for (Booking b : bookingList) {
			int flag = 0;
			Movie m = b.getMovie();
			int count = b.getTicketList().size();
			for (Map.Entry<Movie, Integer> mov : movieSales.entrySet()) {
				String movTitle = mov.getKey().getTitle();
				if (movTitle.equals(m.getTitle())) {
					flag = 1;
					movieSales.put(mov.getKey(), mov.getValue() + count);
					break;
				}
			}
			if (flag == 0)
				movieSales.put(b.getMovie(), count);
		}
		return movieSales;
	}
	
	/**
         * idk
         */
	private HashMap<Movie, Integer> fakeTopSales() {
		ArrayList<Booking> bookingList = new ArrayList<Booking>();
		HashMap<Movie, Integer> movieSales = new HashMap<Movie, Integer>();
		ArrayList<Movie> moviesList = mc.readFile();

		Cinema c1 = new Cinema(null, null, null, null);

		for (int i = 0; i < 5; i++) {
			for (Movie m : moviesList) {
				if (i % 2 == 0 && m.getTitle().equals("Black Adam"))
					continue;
				if (i == 3 && m.getTitle().equals("Thor: Love and Thunder"))
					continue;

				Booking b1 = new Booking(0, c1, m, null, null, null);
				bookingList.add(b1);
			}
		}

		for (Booking b : bookingList) {
			movieSales.put(b.getMovie(), movieSales.getOrDefault(b.getMovie(), 0) + 1);
		}
		return movieSales;
	}

}
