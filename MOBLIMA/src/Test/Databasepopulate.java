package Test;

import Control.*;
import Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalDate;

public class Databasepopulate {

    public static void main(String[] args) {
        // Movie_Controller mc = new Movie_Controller();
        //
        // String title = "Avatar: The Way of Water";
        // String desc = "Jake Sully lives with his newfound family formed on the planet
        // "
        // + "of Pandora. Once a familiar threat returns to finish what was previously "
        // + "started, Jake must work with Neytiri and the army of the Na'vi race to "
        // + "protect their planet.";
        // Constants.AGE_CLASSIFICATION age = Constants.AGE_CLASSIFICATION.PG13;
        // ArrayList<String> directorList = new ArrayList<String>();
        // directorList.add("James Cameron");
        // ArrayList<String> actor = new ArrayList<String>();
        // actor.add("Michelle Yeoh");
        // actor.add("Zoe Saldana");
        // actor.add("Jamie Flatters");
        // ArrayList<String> genre = new ArrayList<String>();
        // genre.add("Action");
        // genre.add("Adventure");
        // genre.add("Fantasy");
        // ArrayList<Review_Ratings> review = new ArrayList<Review_Ratings>();
        // LocalDate date = LocalDate.of(2022, 12, 16);
        // LocalDate closing = date.plusDays(30);
        // Duration duration = Duration.ofMinutes(190);
        //
        // String title2 = "Morbius";
        // String desc2 = "It's Morbin Time";
        // Constants.AGE_CLASSIFICATION age2 = Constants.AGE_CLASSIFICATION.PG13;
        // ArrayList<String> directorList2 = new ArrayList<String>();
        // directorList2.add("Daniel Espinosa");
        // ArrayList<String> actor2 = new ArrayList<String>();
        // actor2.add("Jared Leto");
        // actor2.add("Matt Smith");
        // actor2.add("Adria Arjona");
        // ArrayList<String> genre2 = new ArrayList<String>();
        // genre2.add("Action");
        // genre2.add("Adventure");
        // genre2.add("Horror");
        // ArrayList<Review_Ratings> review2 = new ArrayList<Review_Ratings>();
        // LocalDate date2 = LocalDate.of(2022, 4, 1);
        // LocalDate closing2 = date.plusDays(30);
        // Duration duration2 = Duration.ofMinutes(104);
        //
        // String title3 = "Black Adam";
        // String desc3 = "Nearly 5,000 years after he was bestowed with the almighty "
        // + "powers of the Egyptian gods--and imprisoned just as quickly--Black Adam is
        // "
        // + "freed from his earthly tomb, ready to unleash his unique form of justice
        // on "
        // + "the modern world.";
        // Constants.AGE_CLASSIFICATION age3 = Constants.AGE_CLASSIFICATION.PG13;
        // ArrayList<String> directorList3 = new ArrayList<String>();
        // directorList3.add("Jaume Collet-Serra");
        // ArrayList<String> actor3 = new ArrayList<String>();
        // actor3.add("Dwayne Johnson");
        // actor3.add("Aldis Hodge");
        // actor3.add("Pierce Brosnan");
        // ArrayList<String> genre3 = new ArrayList<String>();
        // genre3.add("Action");
        // genre3.add("Adventure");
        // genre3.add("Fantasy");
        // ArrayList<Review_Ratings> review3 = new ArrayList<Review_Ratings>();
        // LocalDate date3 = LocalDate.of(2022, 10, 19);
        // LocalDate closing3 = date.plusDays(30);
        // Duration duration3 = Duration.ofMinutes(125);
        //
        // String title4 = "Hell Hole";
        // String desc4 = "This is a tale about love, loss, suffering and karma. A "
        // + "loving mother makes a death pact with a spirit, sacrificing herself to
        // save "
        // + "her son. Years later, the son is bullied in a medical school and dies "
        // + "tragically. Now both mother and son have been reunited as vengeful spirits
        // "
        // + "and are back to exact revenge on those who have wronged them.";
        // Constants.AGE_CLASSIFICATION age4 = Constants.AGE_CLASSIFICATION.NC16;
        // ArrayList<String> directorList4 = new ArrayList<String>();
        // directorList4.add("Sam Loh");
        // ArrayList<String> actor4 = new ArrayList<String>();
        // actor4.add("Justin Cheung");
        // actor4.add("Hedwig Tam");
        // actor4.add("Jennifer Yu");
        // ArrayList<String> genre4 = new ArrayList<String>();
        // genre4.add("Horror");
        // ArrayList<Review_Ratings> review4 = new ArrayList<Review_Ratings>();
        // LocalDate date4 = LocalDate.of(2022, 10, 27);
        // LocalDate closing4 = date.plusDays(30);
        // Duration duration4 = Duration.ofMinutes(92);
        //
        // String title5 = "Ajoomma";
        // String desc5 = "Auntie (Hong Huifang), is a middle-aged Singaporean woman who
        // "
        // + "has dedicated the best years of her life to caring for her family. Now "
        // + "widowed with her grown up son, Sam (Shane Pow) about to fly the roost,
        // Auntie "
        // + "is left to contend with a whole new identity beyond her roles of daughter,
        // "
        // + "wife, and mother. A solo trip to Korea becomes a wild adventure for
        // Auntie, "
        // + "where she meets Kwon-Woo (Kang Hyung Suk), a young tour guide who can’t
        // seem "
        // + "to get his life in order, and Jung Su (Jung Dong-Hwan), an elderly
        // security "
        // + "guard. The trio embark on an unexpected roller coaster ride where hearts "
        // + "flutter and unlikely bonds are formed. Inspired by the director’s mother,
        // "
        // + "AJOOMMA is the story of a woman’s journey of self-discovery, where Auntie
        // "
        // + "learns to embrace her new independent life with renewed confidence and
        // panache.";
        // Constants.AGE_CLASSIFICATION age5 = Constants.AGE_CLASSIFICATION.NC16;
        // ArrayList<String> directorList5 = new ArrayList<String>();
        // directorList5.add("He Shuming");
        // ArrayList<String> actor5 = new ArrayList<String>();
        // actor5.add("Hong Huifang");
        // actor5.add("Jung Dong-Hwan");
        // actor5.add("Kang Hyung Suk");
        // ArrayList<String> genre5 = new ArrayList<String>();
        // genre5.add("Drama");
        // ArrayList<Review_Ratings> review5 = new ArrayList<Review_Ratings>();
        // LocalDate date5 = LocalDate.of(2022, 10, 27);
        // LocalDate closing5 = date.plusDays(30);
        // Duration duration5 = Duration.ofMinutes(90);
        //
        // String title6 = "The Quintessential Quintuplets";
        // String desc6 = "Hired as a private tutor to the five identical quintuplets "
        // + "for the Nakano’s family, high school student Futaro Uesugi has led the "
        // + "quintuplets: Ichika, Nino, Miku, Yotsuba and Itsuki Nakano to a point
        // where "
        // + "they can graduate and pursue their own dreams. At last, it is the final "
        // + "school festival and Futaro resolved to make this an occasion that they
        // will "
        // + "not regret. He searched within himself for his feelings towards the "
        // + "quintuplets and invites them to meet him in the classroom for an important
        // "
        // + " announcement…";
        // Constants.AGE_CLASSIFICATION age6 = Constants.AGE_CLASSIFICATION.PG;
        // ArrayList<String> directorList6 = new ArrayList<String>();
        // directorList6.add("Masato Jinbo");
        // ArrayList<String> actor6 = new ArrayList<String>();
        // actor6.add("Yoshitsugu Matsuoka");
        // actor6.add("Inori Minase");
        // actor6.add("Ayana Taketatsu");
        // ArrayList<String> genre6 = new ArrayList<String>();
        // genre6.add("Anime");
        // ArrayList<Review_Ratings> review6 = new ArrayList<Review_Ratings>();
        // LocalDate date6 = LocalDate.of(2022, 10, 13);
        // LocalDate closing6 = date.plusDays(30);
        // Duration duration6 = Duration.ofMinutes(136);
        //
        // mc.addMovie(title, desc, age, directorList, actor, genre, review,
        // date, closing, duration);
        //
        // mc.addMovie(title2, desc2, age2, directorList2, actor2, genre2, review2,
        // date2, closing2, duration2);
        //
        // mc.addMovie(title3, desc3, age3, directorList3, actor3, genre3, review3,
        // date3, closing3, duration3);
        // mc.addMovie(title4, desc4, age4, directorList4, actor4, genre4, review4,
        // date4, closing4, duration4);
        // mc.addMovie(title5, desc5, age5, directorList5, actor5, genre5, review5,
        // date5, closing5, duration5);
        // mc.addMovie(title6, desc6, age6, directorList6, actor6, genre6, review6,
        // date6, closing6, duration6);

        // Admin_Controller admin_control = new Admin_Controller();
        // admin_control.addAdmin("admin1", "password");
        // admin_control.addAdmin("accounting", "acounnt");
        // admin_control.addAdmin("chief_admin", "password");

        // MovieGoer_Controller mgc = new MovieGoer_Controller();
        // HashMap<Movie, Review_Ratings> reviews = new HashMap<Movie,
        // Review_Ratings>();
        // ArrayList<Booking> bookingList = new ArrayList<Booking>();
        // mgc.addMovieGoer("c", "c", "Ceman", "Ceman@gmail.com", "92312232", reviews,
        // bookingList);

        // mgc.addMovieGoer("jack", "password", "Jack Meoff", "jakmeof@gmail.com",
        // "98232332", reviews,
        // bookingList);

        // mgc.addMovieGoer("hugh", "password", "Hugh Mongus", "Hugh@gmail.com",
        // "91231239", reviews,
        // bookingList);

        // mgc.addMovieGoer("dixie", "password", "Dixie Normus", "Dixie@gmail.com",
        // "96996969", reviews,
        // bookingList);

        // Cineplex_Controller cpc = new Cineplex_Controller();
        // Cinema_Controller cc = new Cinema_Controller(cpc);
        // MovieSession_Controller msc = new MovieSession_Controller(cc);
        // Movie_Controller mc = new Movie_Controller();
        // ArrayList<Movie> movies = mc.readFile();
        // ArrayList<Cinema> cinema_list = new ArrayList<Cinema>();
        // ArrayList<Cinema> cinema_list1 = new ArrayList<Cinema>();
        // ArrayList<Cinema> cinema_list2 = new ArrayList<Cinema>();
        // ArrayList<MovieSession> sessions1 = new ArrayList<MovieSession>();
        // ArrayList<MovieSession> sessions2 = new ArrayList<MovieSession>();
        // ArrayList<MovieSession> sessions3 = new ArrayList<MovieSession>();

        // SeatLayout test1 = new SeatLayout(15, 30);
        // SeatLayout test2 = new SeatLayout(20, 25);
        // SeatLayout psl = new SeatLayout(10, 10);

        // cpc.addCineplex("Great World City", "A", cinema_list);

        // cc.createCinema("A", "A01",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions1);
        // cc.createCinema("A", "A02",
        // Constants.CINEMA_TYPE.STANDARD, test2, sessions1);
        // cc.createCinema("A", "A03",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions1);
        // cc.createCinema("A", "A04",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions1);
        // cc.createCinema("A", "A05",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions1);

        // cpc.addCineplex("JEM", "J", cinema_list1);

        // cc.createCinema("J", "J01",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions2);
        // cc.createCinema("J", "J02",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions2);
        // cc.createCinema("J", "J03",
        // Constants.CINEMA_TYPE.STANDARD, test2, sessions2);
        // cc.createCinema("J", "J04",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions2);
        // cc.createCinema("J", "J05",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions2);

        // cpc.addCineplex("Plaza Singapura", "P", cinema_list2);
        // cc.createCinema("P", "P01",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions3);
        // cc.createCinema("P", "P02",
        // Constants.CINEMA_TYPE.STANDARD, test1, sessions3);
        // cc.createCinema("P", "P03",
        // Constants.CINEMA_TYPE.STANDARD, test2, sessions3);
        // cc.createCinema("P", "P04",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions3);
        // cc.createCinema("P", "P05",
        // Constants.CINEMA_TYPE.PLATIUM, psl, sessions3);

        // ArrayList<Cinema> cinemaList = cc.getCinemaByCineplexCode("A");
        // cpc.updateCineplex(cpc.CHOICE_CINEMALIST, "A", cinemaList);

        // ArrayList<Cinema> cinemaList1 = cc.getCinemaByCineplexCode("J");
        // cpc.updateCineplex(cpc.CHOICE_CINEMALIST, "J", cinemaList1);

        // ArrayList<Cinema> cinemaList2 = cc.getCinemaByCineplexCode("P");
        // cpc.updateCineplex(cpc.CHOICE_CINEMALIST, "P", cinemaList2);
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
        // msc.createSession("A01", movies.get(0), showtime1,
        // Constants.MOVIE_TYPE.TWO_D);
        // msc.createSession("A01", movies.get(1), showtime1,
        // Constants.MOVIE_TYPE.THREE_D);
        // msc.createSession("A01", movies.get(2), showtime1,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        //
        // msc.createSession("A02", movies.get(0), showtime2,
        // Constants.MOVIE_TYPE.THREE_D);
        // msc.createSession("A02", movies.get(1), showtime2,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        // msc.createSession("A02", movies.get(2), showtime2,
        // Constants.MOVIE_TYPE.TWO_D);
        //
        // msc.createSession("A03", movies.get(0), showtime,
        // Constants.MOVIE_TYPE.BLOCKBUSTER);
        // msc.createSession("A03", movies.get(1), showtime,
        // Constants.MOVIE_TYPE.TWO_D);
        // msc.createSession("A03", movies.get(2), showtime,
        // Constants.MOVIE_TYPE.THREE_D);

        // String name = getStringInput("Enter admin's username: ");
        // String password = getStringInput("Enter admin's password: ");
        // System.out.println(name);
        // System.out.println(password);
        //

        // System.out.println(date);

        // Holiday_Controller controller = new Holiday_Controller();
        // controller.addHoliday("Test holiday", LocalDate.of(2022, 11, 24));
        // controller.addHoliday("Chirstmas", LocalDate.of(2022, 12, 25));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 12));
        // controller.addHoliday("Chinese New Year", LocalDate.of(2022, 2, 13));
        //
        // Movie_Controller mc = new Movie_Controller();
        //
        // String title = "Long movie title test this is a movie with a very long "
        // + "title to test the movie list page to see if it will cut the title "
        // + "short if the movie title is too long.";
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

        // ArrayList<Movie> movies = mc.readFile();

        // for (Movie mo : movies) {
        // System.out.println(mo.getTitle());
        // }
    }

}
