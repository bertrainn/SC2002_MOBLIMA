package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;
import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Entity.Movie;

import MOBLIMA.Control.Movie_Controller;

public class editMovie extends BaseMenu {

	@Override
	public void load() {
		showMenu();
	}

	public void showMenu() {
		printHeader("Movie Options");
		printMenu("Choose from one of the following options:",
				"1. Add Movie",
				"2. Delete Movie",
				"3. Update Movie",
				"4. List All Holidays",
				"5. List Top 5 Movies By Sales",
				"6. List Top 5 Movies By Review Ratings",
				"7. Back");

		int choice = userInput(1, 7);

		switch (choice) {
			case 1:
				AddMovie();
				break;
			case 2:
				DeleteMovie();
				break;
			case 3:
				UpdateMovie();
				break;
			case 4:
				ListMovies();
				break;
			case 5:
				ListMoviesBySales();
				break;
			case 6:
				ListMoviesByRating();
				break;
			case 7:
				back();
				break;
		}
	}

	public void AddMovie() {

	}

	public void DeleteMovie() {
		// don't need to remove the movie from the database: just change it to EOS;
	}

	public void UpdateMovie() {

	}

	public void ListMovies() {
		//show movie + showing status
	}

	public void ListMoviesBySales() {

	}

	public void ListMoviesByRating() {

	}
}
