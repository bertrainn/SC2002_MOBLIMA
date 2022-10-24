package MOBLIMA.Controller;

import MOBLIMA.Entity.Movie;
import java.util.ArrayList;

/**
 * This class is used to store all the movies that each Cineplex is showing
 */

public class Movie_Controller {
    private ArrayList<Movie> movieList;
    private int movieCount;

    /**
     * Constructor for the Movie Controller class
     */
    public Movie_Controller() {
        this.movieList = new ArrayList<Movie>();
        this.movieCount = 0;
    }

    /**
     * This method adds a new movie to the movie list of the cinema
     * 
     * @param movie is the new movie to be added to the movie list
     */
    public void addMovie(Movie movie) {
        String title = movie.getTitle();

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().toLowerCase() == title.toLowerCase()) {
                System.out.println("Movie exist in the system.");
                return;
            }
        }
        movieList.add(movie);
        movieCount++;
        System.out.println("Movie successfully added.");
    }

    /**
     * This method returns the movie list of the cinema
     * 
     * @return the movie list of the cinema
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    /**
     * This method returns the movie count of the cinema
     * 
     * @return the movie count of the cinema
     */
    public int getMovieCount() {
        return movieCount;
    }
}
