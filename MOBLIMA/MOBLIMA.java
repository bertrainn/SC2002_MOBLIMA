package MOBLIMA;

import java.time.LocalDateTime;
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

        Review_Ratings test = new Review_Ratings(5.0, "Pretty Good");
        ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();
        reviews.add(test);

        LocalDateTime releasedate = LocalDateTime.now();

        Duration current = Duration.ofMinutes(84);

        Movie movie = new Movie(title, description, actors, director, genre, releasedate, current);

        System.out.println(movie.getShowingStatus());
    }
}
