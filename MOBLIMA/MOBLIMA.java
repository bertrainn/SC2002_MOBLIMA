package MOBLIMA;

import MOBLIMA.Control.*;
import MOBLIMA.Entity.*;
import MOBLIMA.Entity.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

/**
 * TODO:
 * - Implement a controller which combines the multiple entity classes into one;
 * - Booking Controller - Combines Ticket, Movie, Cinema Class Etc
 * 
 */
public class MOBLIMA {
    public static void main(String[] args) {
        String title = "Top Gun";
        String description = "After thirty years, Maverick is still pushing the envelope as a top naval aviator, but  must confront ghosts of his past when he leads TOP GUN’s elite graduates on a mission that demands the ultimate  sacrifice  from those chosen to fly it.";
        Constants.AGE_CLASSIFICATION AgeRating = Constants.AGE_CLASSIFICATION.PG13;

        ArrayList<String> actors = new ArrayList<String>();
        actors.add("Tom Cruise");
        actors.add("Jennifer Connelly");

        ArrayList<String> director = new ArrayList<String>();
        director.add("Joseph Kosinski");

        ArrayList<String> genre = new ArrayList<String>();
        genre.add("Action");
        genre.add("Adventure");

        ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();

        Review_Ratings review_1 = new Review_Ratings(5,
                "Brings Maverick’s story full circle in a satisfying manner that adds depth and dimension to its predecessor, but still tells a story that’s all its own.");
        reviews.add(review_1);

        Review_Ratings review_2 = new Review_Ratings(4,
                "It helps that the filmmaking is pretty much impeccable, with director Joseph Kosinski providing the kind of clear, streamlined action sequences that make blockbuster spectacle feel fun instead of mandatory.");
        reviews.add(review_2);

        Review_Ratings review_3 = new Review_Ratings(5,
                "Somehow Cruise's foray back into the danger zone will be remembered more than the original, setting a new standard in the era of reboots.");
        reviews.add(review_3);

        LocalDate releasedate = LocalDate.of(2022, 5, 27);
        LocalDate closingdate = releasedate.plusDays(30);
        Duration current = Duration.ofMinutes(131);

        Movie_Controller mc = new Movie_Controller();
        ArrayList<Movie> movies = mc.readFile();

        mc.addMovie(title, description, AgeRating, actors, director, genre, reviews, releasedate,
                closingdate,
                current);

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).getTitle());
        }
    }
}
