package MOBLIMA.Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.time.LocalDateTime;

import MOBLIMA.Entity.Cinema;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.SeatLayout;

public class MovieSession_Controller {
    private Cinema_Controller CinemaControl;
    public String FILENAME;

    public final static int CHOICE_MOVIE = 1;
    public final static int CHOICE_DATETIME = 2;

    public MovieSession_Controller(Cinema_Controller CC) {
        this.CinemaControl = CC;
        this.FILENAME = CC.FILENAME;
    }

    public void createSession(String cinemaCode, Movie shownMovie, LocalDateTime startDateTime) {
        SeatLayout seatPlan = CinemaControl.getCinemaByCode(cinemaCode).get(0).getSeatPlan();

        MovieSession newSess = new MovieSession(this.getLastID() + 1, shownMovie, startDateTime, seatPlan);

        ArrayList<Cinema> Data = CinemaControl.getCinema();
        ArrayList<MovieSession> temp = new ArrayList<MovieSession>();
        Cinema c;

        for (int i = 0; i < Data.size(); i++) {
            c = Data.get(i);
            if (c.getcinemaCode().equals(cinemaCode)) {
                temp = c.getMovieSessions();
                temp.add(newSess);
                c.setMovieSessions(temp);
                this.CinemaControl.updateCinema(CinemaControl.CHOICE_MOVIESESS, cinemaCode, temp);
                temp.clear();
                break;
            }
        }
    }

    public ArrayList<MovieSession> readFile() {
        ArrayList<Cinema> allCinemas = this.CinemaControl.getCinema();
        ArrayList<MovieSession> allMovieSessions = new ArrayList<MovieSession>();
        ArrayList<MovieSession> temp;
        Cinema c = null;

        for (int i = 0; i < allCinemas.size(); i++) {
            c = allCinemas.get(i);
            temp = c.getMovieSessions();

            for (int j = 0; j < temp.size(); j++) {
                allMovieSessions.add(temp.get(j));
            }
        }

        return allMovieSessions;
    }

    public ArrayList<MovieSession> readFileByValues(int choice, Object obj) {
        ArrayList<MovieSession> allMovieSessions = readFile();
        ArrayList<MovieSession> Output = new ArrayList<MovieSession>();
        MovieSession ms = null;

        for (int i = 0; i < allMovieSessions.size(); i++) {
            ms = allMovieSessions.get(i);
            switch (choice) {
                case CHOICE_MOVIE:
                    if (ms.getShownMovie().getId() == (int) obj) {
                        Output.add(ms);
                    }
                    break;
                case CHOICE_DATETIME:
                    if (ms.getShowDateTime().equals((LocalDateTime) obj)) {
                        Output.add(ms);
                    }
                    break;
                default:
                    break;
            }
        }
        return Output;
    }

    public void deleteByID(int id) {

    }

    public void deleteByMovie(int Movieid) {

    }

    public int getLastID() {
        int lastID = -1;
        int MovieSessionID;
        ArrayList<MovieSession> Data = readFile();
        for (int i = 0; i < Data.size(); i++) {
            MovieSessionID = Data.get(i).getSessionId();
            if (MovieSessionID > lastID) {
                lastID = MovieSessionID;
            }
        }
        return lastID;
    }
}
