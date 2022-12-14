package Boundary.MovieGoer;

import static Control.UserInput_Controller.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import Boundary.BaseMenu;
import Boundary.LogIn;
import Control.MovieGoer_Controller;
import Control.Movie_Controller;
import Control.Review_Controller;
import Entity.Movie;
import Entity.MovieGoer;
import Entity.Review_Ratings;

/**
 * This shows the review menu.
 */
public class ReviewView extends BaseMenu {

	/**
	 * The Movie of the review.
	 */
	private Movie m;

	/**
	 * The MovieGoer who review the movie.
	 */
	private MovieGoer cust;

	/**
	 * The Indicator on whether the moviegoer wants to view the movie review or
	 * leave a review..
	 */
	private boolean view;

	/**
	 * Intialising the controllers for the confirmed booking menu.
	 */
	private Movie_Controller mc = new Movie_Controller();
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private Review_Controller rc = new Review_Controller(mc, mgc);

	/**
	 * Creates a new View review menu with the given parameters.
	 *
	 * @param m    This Review's Movie.
	 * @param mg   This Review's MovierGoer.
	 * @param view The indicator on whether to view the movie review or leave a
	 *             review.
	 */
	public ReviewView(Movie m, MovieGoer mg, boolean view) {
		this.m = m;
		cust = mg;
		this.view = view;
	}

	/**
	 * Loads the Review Menu.
	 */
	@Override
	public void load() {
		if (view == true)
			viewReviews();
		else
			leaveReview();
	}

	/**
	 * Shows the Reviews of the requested movie.
	 */
	public void viewReviews() {
		String tit = reduceStringLength(m.getTitle(), 35);
		printHeader("Reviews for " + tit);
		ArrayList<Review_Ratings> reviewList = m.getReviewList();
		if (reviewList.isEmpty()) {
			printMenu("There are no reviews for this movie. Enter any number to go back.");
			userInput(0, 9);
			navigate(this, new MovieDetailsView(m, cust));
		} else {
			int i = 0;
			for (Review_Ratings r : reviewList) {
				printMenu("Rating: " + (int) r.getRating() + " stars", "Review:", r.getReview());
			}
			printMenu("Enter any number to go back");
			userInput(0, 9);
			navigate(this, new MovieDetailsView(m, cust));
		}

	}

	/**
	 * Allows the MovieGoer to leave a review on the movie.
	 */
	private void leaveReview() {
		String tit = reduceStringLength(m.getTitle(), 35);
		printHeader("Leave a Review for " + tit);

		if (cust == null) {
			printMenu("You must be logged in to leave a review, choose one of the following options:",
					"1. Login",
					"2. Register",
					"3. Back");
			int choice = userInput(1, 3);
			switch (choice) {
				case 1:
					navigate(this, new LogIn(m));
					break;
				case 2:
					navigate(this, new MovieGoerRegistration(m));
					break;
				case 3:
					navigate(this, new MoviesList(cust));
			}
		} else {
			int flag = 0;
			HashMap<Movie, Review_Ratings> reviewList = cust.getPostedReviews();
			for (Map.Entry<Movie, Review_Ratings> movieReview : reviewList.entrySet()) {
				Movie key = movieReview.getKey();
				if (key.getTitle().equals(m.getTitle())) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				printMenu("You have already left a review for this movie, enter any number to go back.");
				userInput(0, 9);
				navigate(this, new MovieDetailsView(m, cust));
			} else {
				String review = getStringInput_Sentence("Enter your review of the movie: ");
				printMenuWithoutSpace("Rate the movie from 0 to 5 stars:");
				double rating = userInput(0, 5);
				rc.createReview(m, cust.getUsername(), rating, review);
				m.addReview(new Review_Ratings(rating, review));
				cust.addReview(m, new Review_Ratings(rating, review));
				printMenu("Review successfully posted, enter any number to go back.");
				userInput(0, 9);
				navigate(this, new MovieDetailsView(m, cust));
			}
		}
	}
}
