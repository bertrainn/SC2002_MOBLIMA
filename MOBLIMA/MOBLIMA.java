package MOBLIMA;

import MOBLIMA.Entity.User;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Control.*;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Holiday;
import MOBLIMA.Control.Holiday_Controller;
import static MOBLIMA.Boundary.MenuMethods.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

public class MOBLIMA {

    public static void main(String[] args) {

//        String name = getStringInput("Enter admin's username: ");
//        String password = getStringInput("Enter admin's password: ");
//        System.out.println(name);
//        System.out.println(password);
//
//        Admin_Controller admin_Controller = new Admin_Controller();
//        admin_Controller.addAdmin(name, password);
        // admin_Control.addAdmin(name, password);

        // System.out.println(date);

        // controller.addHoliday("Chirstmas", LocalDate.of(2022, 12, 25));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 12));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 13));

         Movie_Controller mc = new Movie_Controller();
         ArrayList<Movie> movies = mc.readFile();
         
         Movie m = movies.get(2);
         System.out.println(m.getGenre());

//         String title = "Black Adam";
//         String Description = "Pee pee poo poo";
//         Constants.AGE_CLASSIFICATION age = Constants.AGE_CLASSIFICATION.PG13;
//         ArrayList<String> actorList = new ArrayList<String>();
//         actorList.add("Rock");
//         actorList.add("Big Rock");
//         actorList.add("Bigger Rock");
//
//         ArrayList<String> directorList = new ArrayList<String>();
//         directorList.add("Pebble");
//
//         ArrayList<String> genres = new ArrayList<String>();
//         genres.add("Action");
//         genres.add("Superhero");
//
//         ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();
//
//         LocalDate releasedate = LocalDate.of(2022, 10, 20);
//         LocalDate closingdate = releasedate.plusDays(30);
//         Duration current = Duration.ofMinutes(125);
//
//         mc.addMovie(title, Description, age, directorList, actorList, genres,
//         reviews, releasedate, closingdate,
//         current);
        // for (Movie m : movies) {
        // System.out.println(m.getTitle());
        // }

    }
}
