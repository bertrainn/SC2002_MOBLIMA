package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;

import Boundary.BaseMenu;
import Entity.Movie;
import Entity.MovieGoer;

public class MovieDetailsView extends BaseMenu {

	private Movie m;
	private MovieGoer cust;

	public MovieDetailsView(Movie m, MovieGoer mg) {
		this.m = m;
		cust = mg;
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
		String genreString = listToString(genreList);
		String rating = m.getOverallRating();
		int flag = 0;

		if (rating != "N/A") {
			rating = rating + " stars";
		}
		
		String tit = reduceStringLength(m.getTitle(), 35);

		printHeader("Movie Details: " + tit);
		
		if (tit.length() != m.getTitle().length())
			printMenu("Title:", m.getTitle());

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
		switch (choice) {
			case 1:
				navigate(this, new ReviewView(m, cust, true));
				break;
			case 2:
				navigate(this, new ReviewView(m, cust, false));
				break;
			case 3:
				back();
				break;
		}
	}

}
