package Control;

import java.util.ArrayList;
import java.util.HashMap;
import Entity.Review_Ratings;
import Entity.Movie;
import Entity.MovieGoer;

/**
 * Controller for the Reviews.
 */
public class Review_Controller {
    
    private Movie_Controller MovieController;
  
    public String FILENAME_MOVIE;

    private MovieGoer_Controller MovieGoerController;
    public String FILENAME_MOVIEGOER;

    /**
     * Creates a new ReviewController with the given controllers.
     * @param MovieController This Review_Controller's MovieController.
     * @param MovieGoerController This Review_Controller's MovieGoerController.
     */
    public Review_Controller(Movie_Controller MovieController, MovieGoer_Controller MovieGoerController) {
        this.MovieController = MovieController;
        this.FILENAME_MOVIE = MovieController.FILENAME;

        this.MovieGoerController = MovieGoerController;
        this.FILENAME_MOVIEGOER = MovieGoerController.FILENAME;
    }

    /**
     * This method gets the MovieController.
     * @return this MovieController.
     */
    public Movie_Controller getMovieController() {
        return this.MovieController;
    }
 
    /**
     * This method sets the moviecontroller.
     * @param MovieController The moviecontroller to be set.
     */
    public void setMovieController(Movie_Controller MovieController) {
        this.MovieController = MovieController;
    }

    /**
     * This method gets the MovieGoerController.
     * @return this MovieGoerController.
     */
    public MovieGoer_Controller getMovieGoerController() {
        return this.MovieGoerController;
    }

    /**
     * This method sets the moviegoercontroller.
     * @param MovieGoerController The moviegoercontroller to be set.
     */
    public void setMovieGoerController(MovieGoer_Controller MovieGoerController) {
        this.MovieGoerController = MovieGoerController;
    }

    /**
     * This method helps to create a movie review.
     * @param movie The object movie.
     * @param username Name of the User.
     * @param rating The User's Rating.
     * @param Review The content of the User's review.
     */
    public void createReview(Movie movie, String username, double rating, String Review) {
        int i;
        Review_Ratings review_ratings = new Review_Ratings(rating, Review);

        ArrayList<Movie> MovieData = this.MovieController.readFile();
        ArrayList<Movie> MovieData_Updated = new ArrayList<Movie>();

        ArrayList<MovieGoer> MovieGoerData = this.MovieGoerController.readFile();
        ArrayList<MovieGoer> MovieGoerData_Updated = new ArrayList<MovieGoer>();

        for (i = 0; i < MovieData.size(); i++) {
            Movie m = MovieData.get(i);
            if (m.getTitle().equals(movie.getTitle())) {
                ArrayList<Review_Ratings> reviews = m.getReviewList();
                reviews.add(review_ratings);
                m.setReview(reviews);
            }
            MovieData_Updated.add(m);
        }

        for (i = 0; i < MovieGoerData.size(); i++) {
            MovieGoer mg = MovieGoerData.get(i);
            if (mg.getUsername().equals(username)) {
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
