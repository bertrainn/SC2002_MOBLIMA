package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;

import Boundary.BaseMenu;
import Entity.Movie;
import Entity.MovieGoer;

/**
 * This allows the moviegoer to view the movies details.
 */
public class MovieDetailsView extends BaseMenu {

	/**
	 * The Movie that the MovieGoer wants to view the details of.
	 */
	private Movie m;
	
	/**
	 * The MovieGoer who selected the view Movie details.
	 */
	private MovieGoer cust;

	/**
         * Creates a new View Movie details menu with the given parameters.
         * @param m This MovieDetailsView's movie.
	 * @param mg This MovieDetailsView's Moviegoer
         */
	public MovieDetailsView(Movie m, MovieGoer mg) {
		this.m = m;
		cust = mg;
	}

	/**
         * Loads the Booking Confirmation Menu.
         */
	@Override
	public void load() {
		showDetails();
	}

	/**
         * Shows the Details of the movie that will be loaded into the load method.
         */
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
