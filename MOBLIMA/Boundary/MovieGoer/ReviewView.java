package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Boundary.BoundaryTest;
import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Control.Movie_Controller;
import MOBLIMA.Control.Review_Controller;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.Review_Ratings;

public class ReviewView extends BaseMenu {

	private Movie m;
	private MovieGoer cust;
	private boolean view;
	private Movie_Controller mc = new Movie_Controller();
	private MovieGoer_Controller mgc = new MovieGoer_Controller();
	private Review_Controller rc = new Review_Controller(mc, mgc);

	public ReviewView(Movie m, MovieGoer mg, boolean view) {
		this.m = m;
		cust = mg;
		this.view = view;
	}

	@Override
	public void load() {
		if (view == true)
			viewReviews();
		else
			leaveReview();
	}

	public void viewReviews() {
		printHeader("Reviews for " + m.getTitle());
		ArrayList<Review_Ratings> reviewList = m.getReviewList();
		if (reviewList.isEmpty()) {
			printMenu("There are no reviews for this movie. Enter any number to go back.");
			userInput(0, 9);
			back();
		} else {
			int i = 0;
			for (Review_Ratings r : reviewList) {
				printMenu("Rating: " + (int) r.getRating() + " stars", "Review:", r.getReview());
			}
			printMenu("Enter any number to go back");
			userInput(0, 9);
			back();
		}

	}

	private void leaveReview() {
		printHeader("Leave a Review for " + m.getTitle());

		if (cust == null) {
			printMenu("You must be logged in to leave a review, choose one of the following options:",
					"1. Login",
					"2. Register");
			int choice = userInput(1, 2);
			switch (choice) {
				case 1:
					navigate(this, new MovieGoerLogin());
					break;
				case 2:
					navigate(this, new MovieGoerRegistration());
					break;
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
				back();
			} else {
				String review = getStringInput_Sentence("Enter your review of the movie: ");
				printMenuWithoutSpace("Rate the movie from 0 to 5 stars:");
				double rating = userInput(0, 5);
				rc.createReview(m, cust.getUsername(), rating, review);
				m.addReview(new Review_Ratings(rating, review));
				cust.addReview(m, new Review_Ratings(rating, review));
				printMenu("Review successfully posted, enter any number to go back.");
				userInput(0, 9);
				back();
			}
		}
	}

}
