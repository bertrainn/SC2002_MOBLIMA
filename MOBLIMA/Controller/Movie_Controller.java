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
     * This method returns the movie list of the cinema
     * 
     * @return the movie list of the cinema
     */
    public ArrayList<Movie> getMovieList() {
        return this.movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    /**
     * This method adds a new movie to the movie list of the cinema
     * 
     * @param movie is the new movie to be added to the movie list
     */
    public void addMovie(Movie movie) {
        if (this.movieList.contains(movie)) {
            System.out.println("This movie already exists.");
            return;
        } else {
            this.movieList.add(movie);
            System.out.println("This movie has been successfully added.");
        }
    }

    public void removeMovie(Movie movie) {
        if (this.movieList.contains(movie)) {
            this.movieList.remove(movie);
            System.out.println("This movie has been successfully removed.");
        } else {
            System.out.println("This movie does not exist.");
        }
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
