package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.Cineplex_Controller;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Control.Movie_Controller;
import MOBLIMA.Control.SystemSettings_Controller;
import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Movie;

import java.util.ArrayList;
import java.util.Collections;

public class MoviesList extends BaseMenu {
	
	private boolean topFive = false;
	private Movie_Controller mc = new Movie_Controller();
	private Cineplex_Controller cc = new Cineplex_Controller();
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private SystemSettings_Controller ssc = new SystemSettings_Controller();
	private String orderBy = ssc.readSystemSettings().get(0);
	
	@Override
	public void load() {
		showMenu();
	}
	
	private void showMenu() {
		printHeader("View Movies");
		printMenu("Choose from one of the following options:",
				  "1. Make a booking",
				  "2. Search for a movie",
				  "3. List all movies",
				  "4. List the top 5 movies",
				  "5. Back");
		
		int choice = userInput(1, 5);
		
		switch(choice) {
			case 1: 
				allShowtimes();
				break;
			case 2:
				search();
				break;
			case 3:
				showAllMovies();
				topFive = false;
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
	
	private void allShowtimes() {
		ArrayList<Cineplex> cineplexList = cc.readFile();
		int i=0;
		
		printMenuWithoutSpace("Choose from one of the following cineplexes:");
		for (Cineplex cp : cineplexList) {
			printMenuWithoutSpace(++i + ". " + cp.getName());
		}
		printMenu(++i + ". Back");
		
		int choice = userInput(1, i);
		
		if (choice == i) load();
		else {
			Cineplex cp = cineplexList.get(choice-1);
			navigate(this, new BookingMenu(cp));
		}
		
		
	}
	
	private void search() {
		String searchInput = getStringInput("Enter the movie title: ");
		ArrayList<Movie> searchResults = getMovieByTitle(searchInput.toUpperCase());
		
		if (searchResults.isEmpty()) {
			printMenuWithoutSpace("No movies found, enter any number to go back");
			userInput(0, 9);
			showMenu();
		}
		
		else {
			int i = 0;
			
			printMenu(searchResults.size() + " result(s) found: ");
			for (Movie movie : searchResults)
				printMenuWithoutSpace(++i + ". " + movie.getTitle());
			printMenu(++i + ". Back");
			
			int choice = userInput(1, i);
			
			if (choice == i)
				load();
			else {
				Movie m = searchResults.get(choice-1);
				navigate(this, new MovieDetailsView(m));
			}
			
		}
	}
	
	private void showAllMovies() {
		ArrayList<Movie> movieList = mc.readFile();
		ArrayList<Movie> movies = null;
		
		if (topFive) {
			printHeader("Top 5 Movies (" + orderBy + ")");
			movies = getTop5Movies(orderBy);
		}
		else {
			printHeader("All Movies");
			movies = movieList;
		}
		
		
		
		if(movies.isEmpty()) {
			printMenu("There are no movies");
			showMenu();
		}
		
		int i = 0; 
		
		for (Movie m : movies) {
			if (m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS)) continue;
			printMenuWithoutSpace(++i + ". " + m.getTitle());
		}
		
		printMenu(++i + ". Back");
		
		int choice = userInput(1, i);
		
		if (choice == i)
			load();
		else {
			Movie m = movies.get(choice-1);
			navigate(this, new MovieDetailsView(m));
		}
		
	}
	
	private ArrayList<Movie> getMovieByTitle(String searchInput) {
		ArrayList<Movie> movieList = mc.readFile();
		ArrayList<Movie> searchResults = new ArrayList<>();
		
		for (Movie m : movieList) {
			String title = m.getTitle().toUpperCase();
			if (title.contains(searchInput)) {
				searchResults.add(m);
			}
		}
		
		return searchResults;
	}
	
	private ArrayList<Movie> getTop5Movies(String orderBy) {
		ArrayList<Movie> movieList = mc.readFile();
		ArrayList<Movie> top5 = new ArrayList<>();
		for (Movie m : movieList)
			if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS)) top5.add(m);
		if (orderBy == "review") {
			Collections.sort(top5, (o1 ,o2) -> compareRating(o1, o2));
		}
		else {
			//sort by ticket sales (TBD)
			Collections.sort(top5, (o1 ,o2) -> compareRating(o1, o2));
		}
		
		while (top5.size() > 5)
			top5.remove(5);
		
		return top5;
	}
	
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

}