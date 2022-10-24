package MOBLIMA;

import MOBLIMA.Entity.*;
import java.time.LocalDate;
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

        LocalDate releasedate = LocalDate.now();

        Duration current = Duration.ofMinutes(84);

        Movie movie = new Movie(title, description, actors, director, genre, reviews, releasedate, current);

        String text = movie.getReleaseDate().format(Constants.bookingFormat);
        System.out.println(movie.getOverallRating());
        System.out.println(movie.getShowingStatus());
        System.out.println(movie.getAgeRating());

        Seat s = new Seat(0, 5, 5);

        Ticket t = new Ticket(Constants.TicketType.MON_TO_THU, Constants.MovieType.ULTIMA_TWO_D, s);

        System.out.println(t.getPrice());
        System.out.println(text);
    }
}
