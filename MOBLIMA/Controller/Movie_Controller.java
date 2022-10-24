package MOBLIMA.Controller;

import MOBLIMA.Entity.Movie;
import java.util.ArrayList;

/**
 * This class is used to store all the movies that each Cineplex is showing
 */

public class Movie_Controller {
    private ArrayList<Movie> movieList;

    /**
     * Constructor for the Movie Controller class
     */
    public Movie_Controller() {
        this.movieList = new ArrayList<Movie>();
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

    public boolean doesMovieExist(String Title) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().compareToIgnoreCase(Title) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method adds a new movie to the movie list of the cinema
     * 
     * @param movie is the new movie to be added to the movie list
     */
    public void addMovie(Movie movie) {
        this.movieList.add(movie);
        System.out.println("This movie has been successfully added.");
    }

    public void removeMovie(Movie movie) {
        this.movieList.remove(movie);
        System.out.println("This movie has been successfully removed.");
    }

    /**
     * This method returns the movie count of the cinema
     * 
     * @return the movie count of the cinema
     */
    public int getMovieCount() {
        return movieList.size();
    }
}
