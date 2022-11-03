package MOBLIMA;

import MOBLIMA.Entity.User;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Control.*;
import MOBLIMA.Entity.Cinema;
import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Constants;
import MOBLIMA.Entity.Holiday;
import MOBLIMA.Control.Holiday_Controller;
import static MOBLIMA.Boundary.MenuMethods.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Duration;

public class MOBLIMA {

    public static void main(String[] args) {

        Cineplex_Controller cpc = new Cineplex_Controller();

        ArrayList<Cineplex> data = cpc.readFile();
        ArrayList<Cinema> Cinemadata;

        int i = 0;

        System.out.printf("%-3s %-20s %-15s", "No.", "Cineplex Name", "Cinema Codes");
        System.out.println();
        for (Cineplex c : data) {
            Cinemadata = c.getCinemaList();
            System.out.printf("%-3d %-20s ", ++i, c.getName());
            for (Cinema cinema : Cinemadata) {
                System.out.printf("%-3s ", cinema.getcinemaCode());
            }
            System.out.println();
        }

        // Cinema_Controller cc = new Cinema_Controller(cpc);
        // MovieSession_Controller msc = new MovieSession_Controller(cc);
        // Movie_Controller mc = new Movie_Controller();
        // ArrayList<Movie> movies = mc.readFile();
        //
        // cpc.addCineplex("Great World City", cc.getCinema());
        //
        // ArrayList<MovieSession> sessions1 = new ArrayList<MovieSession>();
        // ArrayList<MovieSession> sessions2 = new ArrayList<MovieSession>();
        // ArrayList<MovieSession> sessions3 = new ArrayList<MovieSession>();
        // SeatLayout ssl = new SeatLayout(20, 20);
        // SeatLayout psl = new SeatLayout(10, 10);
        //
        // cc.createCinema("Great World City", "A1", "A1",
        // Constants.CINEMA_TYPE.STANDARD, ssl, sessions1);
        // cc.createCinema("Great World City", "A2", "A2",
        // Constants.CINEMA_TYPE.STANDARD, ssl, sessions2);
        // cc.createCinema("Great World City", "A3", "A3",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions3);
        //
        // ArrayList<Cinema> cinemaList = cc.getCinemaByCineplex("Great World City");
        // cpc.updateCineplex(cpc.CHOICE_CINEMALIST, "Great World City", cinemaList);
        //
        // LocalDate date = LocalDate.of(2022, 11, 28);
        // LocalTime time = LocalTime.of(12, 00);
        // LocalDateTime showtime = LocalDateTime.of(date, time);
        // LocalDate date1 = LocalDate.of(2022, 11, 24);
        // LocalTime time1 = LocalTime.of(11, 00);
        // LocalDateTime showtime1 = LocalDateTime.of(date1, time1);
        // LocalDate date2 = LocalDate.of(2022, 11, 19);
        // LocalTime time2 = LocalTime.of(15, 00);
        // LocalDateTime showtime2 = LocalDateTime.of(date2, time2);
        //
        // msc.createSession("A1", movies.get(0), showtime1,
        // Constants.MOVIE_TYPE.TWO_D);
        // msc.createSession("A1", movies.get(1), showtime1,
        // Constants.MOVIE_TYPE.THREE_D);
        // msc.createSession("A1", movies.get(2), showtime1,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        //
        // msc.createSession("A2", movies.get(0), showtime2,
        // Constants.MOVIE_TYPE.THREE_D);
        // msc.createSession("A2", movies.get(1), showtime2,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        // msc.createSession("A2", movies.get(2), showtime2,
        // Constants.MOVIE_TYPE.TWO_D);
        //
        // msc.createSession("A3", movies.get(0), showtime,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        // msc.createSession("A3", movies.get(1), showtime, Constants.MOVIE_TYPE.TWO_D);
        // msc.createSession("A3", movies.get(2), showtime,
        // Constants.MOVIE_TYPE.THREE_D);

        // String name = getStringInput("Enter admin's username: ");
        // String password = getStringInput("Enter admin's password: ");
        // System.out.println(name);
        // System.out.println(password);
        //
        // Admin_Controller admin_Controller = new Admin_Controller();
        // admin_Controller.addAdmin(name, password);
        // admin_Control.addAdmin(name, password);

        // System.out.println(date);

        // controller.addHoliday("Chirstmas", LocalDate.of(2022, 12, 25));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 12));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 13));

        // Movie_Controller mc = new Movie_Controller();
        // ArrayList<Movie> movies = mc.readFile();

        // Movie m = movies.get(2);
        // System.out.println(m.getGenre());

        // String title = "Black Adam";
        // String Description = "Pee pee poo poo";
        // Constants.AGE_CLASSIFICATION age = Constants.AGE_CLASSIFICATION.PG13;
        // ArrayList<String> actorList = new ArrayList<String>();
        // actorList.add("Rock");
        // actorList.add("Big Rock");
        // actorList.add("Bigger Rock");
        //
        // ArrayList<String> directorList = new ArrayList<String>();
        // directorList.add("Pebble");
        //
        // ArrayList<String> genres = new ArrayList<String>();
        // genres.add("Action");
        // genres.add("Superhero");
        //
        // ArrayList<Review_Ratings> reviews = new ArrayList<Review_Ratings>();
        //
        // LocalDate releasedate = LocalDate.of(2022, 10, 20);
        // LocalDate closingdate = releasedate.plusDays(30);
        // Duration current = Duration.ofMinutes(125);
        //
        // mc.addMovie(title, Description, age, directorList, actorList, genres,
        // reviews, releasedate, closingdate,
        // current);
        // for (Movie m : movies) {
        // System.out.println(m.getTitle());
        // }

    }
}
