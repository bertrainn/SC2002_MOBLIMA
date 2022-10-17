package MOBLIMA;

import java.time.LocalDate;
import java.time.Duration;
import java.util.ArrayList;

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

        LocalDate releasedate = LocalDate.of(2021, 9, 18);

        Duration current = Duration.ofMinutes(84);

        Movie movie = new Movie(title, description, actors, director, genre, reviews, releasedate, current);

        System.out.println(movie.getOverallRating());
        System.out.println(movie.getShowingStatus());
        System.out.println(movie.getAgeRating());
    }
}
