package MOBLIMA.Control;

import java.util.ArrayList;
import java.util.HashMap;

import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;

public class Review_Controller {
    private Movie_Controller MovieController;
    public String FILENAME_MOVIE;

    private MovieGoer_Controller MovieGoerController;
    public String FILENAME_MOVIEGOER;

    public Review_Controller(Movie_Controller MovieController, MovieGoer_Controller MovieGoerController) {
        this.MovieController = MovieController;
        this.FILENAME_MOVIE = MovieController.FILENAME;

        this.MovieGoerController = MovieGoerController;
        this.FILENAME_MOVIEGOER = MovieGoerController.FILENAME;
    }

    public Movie_Controller getMovieController() {
        return this.MovieController;
    }

    public void setMovieController(Movie_Controller MovieController) {
        this.MovieController = MovieController;
    }

    public MovieGoer_Controller getMovieGoerController() {
        return this.MovieGoerController;
    }

    public void setMovieGoerController(MovieGoer_Controller MovieGoerController) {
        this.MovieGoerController = MovieGoerController;
    }

    public void createReview(Movie movie, String username, double rating, String Review) {
        int i;
        Review_Ratings review_ratings = new Review_Ratings(rating, Review);

        ArrayList<Movie> MovieData = this.MovieController.readFile();
        ArrayList<Movie> MovieData_Updated = new ArrayList<Movie>();

        ArrayList<MovieGoer> MovieGoerData = this.MovieGoerController.readFile();
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

        this.MovieController.replaceFile(MovieData_Updated, FILENAME_MOVIE);
        this.MovieGoerController.replaceFile(MovieGoerData_Updated, FILENAME_MOVIEGOER);
    }
}
