package MOBLIMA;

import java.util.ArrayList;

public class Movie_Controller {
    private ArrayList<Movie> movieList;
    private int movieCount;

    public Movie_Controller() {
        this.movieList = new ArrayList<Movie>();
        this.movieCount = 0;
    }

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

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public int getMovieCount() {
        return movieCount;
    }
}
