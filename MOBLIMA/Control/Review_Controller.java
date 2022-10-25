package MOBLIMA.Control;

import java.util.ArrayList;
import java.util.HashMap;

import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;

public class Review_Controller {
    private Movie_Controller MC;
    private String FILENAME_MOVIE;

    private MovieGoer_Controller MGC;
    private String FILENAME_MOVIEGOER;

    public Review_Controller(Movie_Controller MC, MovieGoer_Controller MGC) {
        this.MC = MC;
        this.FILENAME_MOVIE = MC.FILENAME;

        this.MGC = MGC;
        this.FILENAME_MOVIEGOER = MGC.FILENAME;
    }

    public Movie_Controller getMC() {
        return this.MC;
    }

    public void setMC(Movie_Controller mC) {
        this.MC = mC;
    }

    public MovieGoer_Controller getMGC() {
        return this.MGC;
    }

    public void setMGC(MovieGoer_Controller MGC) {
        this.MGC = MGC;
    }

    public void createReview(Movie movie, String username, double rating, String Review) {
        int i;
        Review_Ratings review_ratings = new Review_Ratings(rating, Review);

        ArrayList<Movie> MovieData = this.MC.readFile();
        ArrayList<Movie> MovieData_Updated = new ArrayList<Movie>();

        ArrayList<MovieGoer> MovieGoerData = this.MGC.readFile();
        ArrayList<MovieGoer> MovieGoerData_Updated = new ArrayList<MovieGoer>();

        for (i = 0; i < MovieData.size(); i++) {
            Movie m = MovieData.get(i);
            if (m.equals(movie)) {
                ArrayList<Review_Ratings> reviews = m.getReviewList();
                reviews.add(review_ratings);
                m.setReview(reviews);
            }
            MovieData_Updated.add(m);
        }

        for (i = 0; i < MovieGoerData.size(); i++) {
            MovieGoer mg = MovieGoerData.get(i);
            if (mg.getUsername() == username) {
                HashMap<Movie, Review_Ratings> reviews = mg.getPostedReviews();
                reviews.put(movie, review_ratings);
                mg.setPostedReviews(reviews);
            }
            MovieGoerData_Updated.add(mg);
        }

        this.MC.replaceFile(MovieData_Updated, FILENAME_MOVIE);
        this.MGC.replaceFile(MovieGoerData_Updated, FILENAME_MOVIEGOER);
    }
}
