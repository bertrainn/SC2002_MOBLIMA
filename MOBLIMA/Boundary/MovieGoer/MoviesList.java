package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Control.Movie_Controller;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;

import java.util.ArrayList;

public class MoviesList extends BaseMenu {
	
	private boolean topFive = false;
	private Movie_Controller mc = new Movie_Controller();
	private ArrayList<Movie> movieList = mc.readFile();

	@Override
	public void load() {
		showMenu();
	}
	
	private void showMenu() {
		printHeader("View Movies");
		printMenu("Choose from one of the following options:",
				  "1. Search for a movie",
				  "2. List all movies",
				  "3. List the top 5 movies",
				  "4. Back");
		
		int choice = userInput(1, 4);
		
		switch(choice) {
			case 1:
				search();
				break;
			case 2:
				showAllMovies();
				topFive = false;
				break;
			case 3: 
				topFive = true;
				break;
			case 4: 
				back();
				break;
		}
	}
	
	private void search() {
		String searchInput = getStringInput("Enter the movie title: ");
		ArrayList<Movie> searchResults = getMovieByTitle(searchInput.toUpperCase());
		
		if (searchResults.isEmpty()) {
			getStringInput("No movies found, press any key to go back");
			showMenu();
		}
		
		else {
			int i = 0;
			
			printMenu(searchResults.size() + "results found: ");
			for (Movie movie : searchResults)
				printMenu(++i + "." + movie.getTitle());
			printMenu(++i + ". Back");
			
			int choice = userInput(1, i);
			
			if (choice == i)
				load();
			else
				//show the movie details, delete load() when done
				load();
		}
	}
	
	private void showAllMovies() {
		ArrayList<Movie> movies = null;
		
		if (topFive) {}
		else
			movies = movieList;
		
		printHeader("Movies");
		
		if(movies.isEmpty()) {
			printMenu("There are no movies");
			showMenu();
		}
		
		int i = 0; 
		
		for (Movie m : movies) {
			if (m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS)) continue;
			printMenu(++i + ". " + m.getTitle());
		}
		
		printMenu(++i + ". Back");
		
		int choice = userInput(1, i);
		
		if (choice == i)
			load();
		else {
			Movie m = movies.get(choice-1);
			//show movie details
		}
		
	}
	
	private ArrayList<Movie> getMovieByTitle(String searchInput) {
		ArrayList<Movie> searchResults = new ArrayList<>();
		
		for (Movie m : movieList) {
			String title = m.getTitle().toUpperCase();
			if (title.contains(searchInput)) {
				searchResults.add(m);
			}
		}
		
		return searchResults;
	}

}
