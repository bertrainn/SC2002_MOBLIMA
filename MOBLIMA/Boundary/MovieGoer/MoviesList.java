package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Control.Movie_Controller;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;

import java.util.ArrayList;
import java.util.Collections;

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
				showAllMovies();
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
				movieDetails(m);
			}
			
		}
	}
	
	private void showAllMovies() {
		ArrayList<Movie> movies = null;
		
		if (topFive) {
			printHeader("Top 5 Movies");
			movies = getTop5Movies();
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
			movieDetails(m);
		}
		
	}
	
	private void movieDetails(Movie m) {
		printHeader("Movie Details: " + m.getTitle());
		printMenu("1. Showtimes",
				  "2. Reviews",
				  "3. Back");
		
		int choice = userInput(1, 3);
		switch(choice) {
			case 1:
				//navigate to showtimes view
				break;
			case 2:
				//navigate to reviews view
				break;
			case 3:
				showAllMovies();
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
	
	private ArrayList<Movie> getTop5Movies() {
		ArrayList<Movie> top5 = new ArrayList<>();
		for (Movie m : movieList)
			if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS)) top5.add(m);
		
		Collections.sort(top5, (o1 ,o2) -> Double.compare(Double.parseDouble(o1.getOverallRating()), Double.parseDouble(o2.getOverallRating())));
		
		while (top5.size() > 5)
			top5.remove(5);
		
		return top5;
	}

}