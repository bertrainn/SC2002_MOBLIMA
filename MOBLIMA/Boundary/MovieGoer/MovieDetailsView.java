package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Entity.Movie;

public class MovieDetailsView extends BaseMenu {

	private Movie m;
	
	public MovieDetailsView(Movie m) {
		this.m = m;
	}
	
	@Override
	public void load() {
		showDetails();
	}
	
	private void showDetails() {
		ArrayList<String> actorList = m.getActors();
		ArrayList<String> directorList = m.getDirectors();
		ArrayList<String> genreList = m.getGenre();
		
		String actorsString = listToString(actorList);
		String directorsString = listToString(directorList);
		String genreString = "Action, Adventure, Fantasy"; //listToString(genreList);
		String rating = m.getOverallRating();
		
		if (rating != "N/A") {
			rating = rating + "/5 Stars";
		}
		
		printHeader("Movie Details: " + m.getTitle());
		
		printMenu("Cast:", actorsString, "",
				  "Directors:", directorsString, "",
				  "Synopsis:", m.getDescription(), "",
				  "Genre:", genreString, "",
				  "Movie duration:", Long.toString(m.getDuration().toMinutes()) + " mins", "",
				  "Opening date:", m.getOpeningDate().toString(), "",
				  "Closing date:", m.getClosingDate().toString(), "",
				  "Overall rating:", rating);
		
		printMenu("1. See reviews",
				  "2. Leave a review",
				  "3. Back");
		
		int choice = userInput(1, 3);
		switch(choice) {
			case 1:
				navigate(this, new ReviewView(m, true));
				break;
			case 2:
				navigate(this, new ReviewView(m, false));
				break;
			case 3:
				back();
				break;
		}
	}
	

}
