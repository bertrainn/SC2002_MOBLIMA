package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Boundary.MenuMethods.*;
import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Control.Movie_Controller;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

public class editMovie extends BaseMenu {

	Movie_Controller movie_controller = new Movie_Controller();

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

	public void AddMovie() {

		ArrayList<Movie> movie_list = movie_controller.readFile();
		int choice = -1;
		int i;
		int numberofentries;
		String entries;

		String title;
		String description;
		Constants.AGE_CLASSIFICATION age_class;
		ArrayList<String> DirectorList = new ArrayList<String>();
		ArrayList<String> ActorList = new ArrayList<String>();
		ArrayList<String> GenreList = new ArrayList<String>();
		LocalDate OpeningDate, ClosingDate;
		Duration duration;

		Boolean flag = false;

		title = getStringInput_Sentence("Enter movie's title: ");

		for (Movie movie : movie_list) {
			if (movie.getTitle().equals(title)) {
				flag = true;
				System.out.println(
						"Movie is already in the database, press 0 to return to settings menu, press any other number to try again.");
				choice = userInput(0, 9);
				break;
			}
		}

		if (flag == true) {
			if (choice == 0) {
				load();
			} else {
				AddMovie();
			}
		}

		description = getStringInput_Sentence("Enter movie description: ");
		age_class = Constants.AGE_CLASSIFICATION.valueOf(getStringInput("Enter the age classification of the movie: "));

		numberofentries = getIntInput("Enter the number of directors: ");

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " director: ");
			DirectorList.add(entries);
		}

		numberofentries = getIntInput("Enter the number of actors (at least 3): ");

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " actor: ");
			ActorList.add(entries);
		}

		numberofentries = getIntInput("Enter the number of genres: ");

		for (i = 0; i < numberofentries; i++) {
			entries = getStringInput_Sentence("Enter the no." + (i + 1) + " genre: ");
			GenreList.add(entries);
		}

		OpeningDate = getDateInput("Enter movie's opening date (dd MMM yyyy): ");
		ClosingDate = OpeningDate.plusDays(30);

		duration = Duration.ofMinutes(getIntInput("Enter the duration of the movie (in minutes): "));

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

	public void StopShowingMovie() {
		// don't need to remove the movie from the database: just change it to EOS;

		ArrayList<Movie> MovieList = movie_controller.readFile();
		Movie temp;
		this.ListMovies();

		int movie_choice = getIntInput("Enter the index of the movie you want to stop showing: ");

		temp = MovieList.get(movie_choice);
		movie_controller.updateMovie(movie_controller.CHOICE_SHOWING, temp.getId(), Constants.SHOWING_STATUS.EOS);

		System.out.println("Update success, returning to settings menu...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		load();
	}

	public void UpdateMovie() {

		ArrayList<Movie> MovieList = movie_controller.readFile();
		ArrayList<String> list = new ArrayList<String>();
		Movie temp;
		this.ListMovies();

		int movie_choice = getIntInput("Enter the index of the movie you want to stop showing: ");

		temp = MovieList.get(movie_choice);

		printMenu("Select which movie attribute you want to edit: ",
				"1. Title",
				"2. Description",
				"3. Age Rating",
				"4. Actors List",
				"5. Director List",
				"6. Genre List",
				"7. Reviews",
				"8. Opening Date",
				"9. Closing Date",
				"10. Showing Status",
				"11. Duration",
				"12. Back");

		int choice = userInput(1, 12);
		switch (choice) {
			case 1:
				String title = getStringInput("Enter the new title: ");
				movie_controller.updateMovie(movie_controller.CHOICE_TITLE, temp.getId(), title);
				break;
			case 2:
				String desc = getStringInput("Enter the new description: ");
				movie_controller.updateMovie(movie_controller.CHOICE_DESC, temp.getId(), desc);
				break;
			case 3:
				Constants.AGE_CLASSIFICATION age = Constants.AGE_CLASSIFICATION
						.valueOf(getStringInput("Enter the new age classification: "));
				movie_controller.updateMovie(movie_controller.CHOICE_AGERATING, temp.getId(), age);
				break;
			case 4:
				list = temp.getActors();

				movie_controller.updateMovie(movie_controller.CHOICE_ACTOR, temp.getId(), list);
				break;
			case 5:
				list = temp.getDirectors();
				movie_controller.updateMovie(movie_controller.CHOICE_DIRECTOR, temp.getId(), list);
				break;
			case 6:
				list = temp.getGenre();
				movie_controller.updateMovie(movie_controller.CHOICE_GENRE, temp.getId(), list);
				break;
			case 7:
				ArrayList<Review_Ratings> reviewList = temp.getReviewList();
				movie_controller.updateMovie(movie_controller.CHOICE_REVIEW, temp.getId(), reviewList);
				break;
			case 8:
				LocalDate newOPDate = getDateInput("Enter new opening date (dd MMM yyyy): ");
				movie_controller.updateMovie(movie_controller.CHOICE_OPENING, temp.getId(), newOPDate);
				break;
			case 9:
				LocalDate newCLDate = getDateInput("Enter new closing date (dd MMM yyyy): ");
				movie_controller.updateMovie(movie_controller.CHOICE_CLOSING, temp.getId(), newCLDate);
				break;
			case 10:
				Constants.SHOWING_STATUS ShowingStatus = Constants.SHOWING_STATUS
						.valueOf(getStringInput("Enter the new showing status: "));
				movie_controller.updateMovie(movie_controller.CHOICE_SHOWING, temp.getId(), ShowingStatus);
				break;
			case 11:
				Duration newDuration = Duration
						.ofMinutes(getIntInput("Enter the new duration of the movie (in minutes): "));
				movie_controller.updateMovie(movie_controller.CHOICE_DURATION, temp.getId(), newDuration);
				break;
			case 12:
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

	public void ListMoviesBySales() {

	}

	public void ListMoviesByRating() {

	}

	public void ListMovies() {
		// show movie + showing status
		ArrayList<Movie> moviesList = movie_controller.readFile();
		int i = 0;
		System.out.printf("%-3s %-4s %-25s %-15s", "No.", "ID", "Name", "Showing Status");
		System.out.println();
		for (Movie m : moviesList) {
			System.out.printf("%-3d %-4d %-25s %-15s", ++i, m.getId(), m.getTitle(), m.getShowingStatus().toString());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		editMovie eM = new editMovie();
		eM.AddMovie();
		eM.ListMovies();
	}
}
