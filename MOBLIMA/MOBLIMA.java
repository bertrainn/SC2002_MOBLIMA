package MOBLIMA;

import MOBLIMA.Entity.*;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Controller.*;
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

        String title = "MOBLIMA";
        String description = "Test";
        Constants.AGE_CLASSIFICATION AgeRating = Constants.AGE_CLASSIFICATION.PG13;

        ArrayList<String> actors = new ArrayList<String>();
        actors.add("jim");

        ArrayList<String> director = new ArrayList<String>();
        director.add("jim");

        ArrayList<String> genre = new ArrayList<String>();
        genre.add("yes");

        ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();

        Review_Ratings test = new Review_Ratings(5.0, "Pretty Good");
        reviews.add(test);

        Review_Ratings test2 = new Review_Ratings(4.0, "Pretty okay");
        reviews.add(test2);

        LocalDate releasedate = LocalDate.of(2022, 10, 25);
        LocalDate closingdate = LocalDate.of(2022, 11, 30);
        Duration current = Duration.ofMinutes(84);

        Movie movie = new Movie(title, description, AgeRating, actors, director, genre, reviews, releasedate,
                closingdate,
                current);
        // System.out.println(movie.getAgeRating());
        String text = movie.getOpeningDate().atStartOfDay().format(Constants.bookingFormat);
        // System.out.println(movie.getOverallRating());
        // System.out.println(movie.getShowingStatus());
        // System.out.println(movie.getAgeRating());

        Seat s = new Seat(0, 5, 5);

        Ticket t = new Ticket(Constants.TICKET_TYPE.MON_TO_THU, Constants.MOVIE_TYPE.ULTIMA_TWO_D, s);

        // System.out.println(t.getPrice());
        // System.out.println(text);
        Movie_Controller mc = new Movie_Controller();
        ArrayList<Movie> movies = mc.readFile();

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).getTitle());
        }
    }
}
