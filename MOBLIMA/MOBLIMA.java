package MOBLIMA;

import MOBLIMA.Entity.User;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Control.Movie_Controller;
import MOBLIMA.Entity.Constants;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

public class MOBLIMA {

    public static void main(String[] args) {
        Movie_Controller mc = new Movie_Controller();
        ArrayList<Movie> movies = mc.readFile();

        String title = "Black Adam";
        String Description = "Pee pee poo poo";
        Constants.AGE_CLASSIFICATION age = Constants.AGE_CLASSIFICATION.PG13;
        ArrayList<String> actorList = new ArrayList<String>();
        actorList.add("Rock");
        actorList.add("Big Rock");
        actorList.add("Bigger Rock");

        ArrayList<String> directorList = new ArrayList<String>();
        directorList.add("Pebble");

        ArrayList<String> genres = new ArrayList<String>();
        genres.add("Action");
        genres.add("Superhero");

        ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();

        LocalDate releasedate = LocalDate.of(2022, 10, 20);
        LocalDate closingdate = releasedate.plusDays(30);
        Duration current = Duration.ofMinutes(125);

        // mc.addMovie(title, Description, age, directorList, actorList, genres,
        // reviews, releasedate, closingdate,
        // current);
        for (Movie m : movies) {
            System.out.println(m.getTitle());
        }
    }
}
