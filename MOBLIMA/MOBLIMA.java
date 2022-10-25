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
        String title = "Black Adam";
        String description = "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam. Nearly 5,000 years have passed, and Black Adam has gone from man to myth to legend. Now free, his unique form of justice, born out of rage, is challenged by modern-day heroes who form the Justice Society: Hawkman, Dr. Fate, Atom Smasher and Cyclone.";
        Constants.AGE_CLASSIFICATION AgeRating = Constants.AGE_CLASSIFICATION.PG13;

        ArrayList<String> actors = new ArrayList<String>();
        actors.add("Dwayne Johnson");
        actors.add("Viola Davis");

        ArrayList<String> director = new ArrayList<String>();
        director.add("Jaume Collet-Serra");

        ArrayList<String> genre = new ArrayList<String>();
        genre.add("Action");
        genre.add("Superhero");

        ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();

        LocalDate releasedate = LocalDate.of(2022, 10, 20);
        LocalDate closingdate = releasedate.plusDays(30);
        Duration current = Duration.ofMinutes(125);

        // Movie_Controller mc = new Movie_Controller();
        // ArrayList<Movie> movies = mc.readFile();

        // mc.addMovie(title, description, AgeRating, actors, director, genre, reviews,
        // releasedate,
        // closingdate,
        // current);

        // for (int i = 0; i < movies.size(); i++) {
        // System.out.println(movies.get(i).getId());
        // }

        // SeatLayout sl = new SeatLayout(6, 6);
        // for (int i = 0; i < 6 * 6; i += 2) {
        // sl.assignSeats(i);
        // }
        // sl.printLayout();

        Admin_Controller ac = new Admin_Controller();

        ArrayList<Admin> al = ac.readFile();

        for (int i = 0; i < al.size(); i++) {
            System.out.println(al.get(i).getUsername());
        }
    }
}
