package Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.Duration;

import Entity.Cinema;
import Entity.Constants;
import Entity.Movie;
import Entity.MovieSession;
import Entity.SeatLayout;

public class MovieSession_Controller {
    private Cinema_Controller CinemaControl;
    public String FILENAME;

    public final static int CHOICE_MOVIE = 1;
    public final static int CHOICE_DATETIME = 2;
    public final static int CHOICE_CINEMACODE = 5;

    public MovieSession_Controller(Cinema_Controller CC) {
        this.CinemaControl = CC;
        this.FILENAME = CC.FILENAME;
    }

    public void createSession(String cinemaCode, Movie shownMovie, LocalDateTime startDateTime,
            Constants.MOVIE_TYPE movieType) {
        SeatLayout seatPlan = CinemaControl.getCinemaByCode(cinemaCode).getSeatPlan();

        MovieSession newSess = new MovieSession(this.getLastID() + 1, shownMovie, startDateTime, seatPlan,
                movieType, cinemaCode);

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

    public void updateSession(MovieSession sess) {
        this.deleteByID(sess.getSessionId());
        ArrayList<Cinema> Data = CinemaControl.getCinema();
        ArrayList<MovieSession> temp = new ArrayList<MovieSession>();
        Cinema c;

        for (int i = 0; i < Data.size(); i++) {
            c = Data.get(i);
            if (c.getcinemaCode().equals(sess.getCinemaCode())) {
                temp = c.getMovieSessions();
                temp.add(sess);
                Collections.sort(temp, (o1, o2) -> Integer.compare(o1.getSessionId(), o2.getSessionId()));
                c.setMovieSessions(temp);
                this.CinemaControl.updateCinema(CinemaControl.CHOICE_MOVIESESS, sess.getCinemaCode(), temp);
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
                case CHOICE_CINEMACODE:
                    if (ms.getCinemaCode().equals((String) obj)) {
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
        ArrayList<Cinema> allCinemas = CinemaControl.getCinema();
        ArrayList<MovieSession> allMovieSessions = new ArrayList<MovieSession>();
        ArrayList<MovieSession> updatedList = new ArrayList<MovieSession>();
        Cinema c;
        MovieSession ms;

        for (int i = 0; i < allCinemas.size(); i++) {
            c = allCinemas.get(i);
            allMovieSessions = c.getMovieSessions();
            updatedList.clear();

            for (int j = 0; j < allMovieSessions.size(); j++) {
                ms = allMovieSessions.get(j);
                if (!(ms.getSessionId() == id)) {
                    updatedList.add(ms);
                }
            }
            this.CinemaControl.updateCinema(CinemaControl.CHOICE_MOVIESESS, c.getcinemaCode(), updatedList);
        }
    }

    public void deleteByMovie(int movieId) {
        ArrayList<Cinema> allCinemas = CinemaControl.getCinema();
        ArrayList<MovieSession> allMovieSessions = new ArrayList<MovieSession>();
        ArrayList<MovieSession> updatedList = new ArrayList<MovieSession>();
        Cinema c;
        MovieSession ms;

        for (int i = 0; i < allCinemas.size(); i++) {
            c = allCinemas.get(i);
            allMovieSessions = c.getMovieSessions();
            updatedList.clear();

            for (int j = 0; j < allMovieSessions.size(); j++) {
                ms = allMovieSessions.get(j);
                if (!(ms.getShownMovie().getId() == movieId)) {
                    updatedList.add(ms);
                }
            }
            this.CinemaControl.updateCinema(CinemaControl.CHOICE_MOVIESESS, c.getcinemaCode(), updatedList);
        }
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

    /**
     * This method returns if the entered session time is valid.
     * A movie session is considered valid if the cinema isn't showing a movie at
     * that time or 30 mins after the last movie has ended & it does not overlap
     * with the next sessions time.
     * e.g. 1) if the cinema is showing a movie at 1300 and the movie has a duration
     * of 80 mins. The user inputs 1400 as a session time, the system will return
     * false
     * as there is an overlap.
     * 2) Movie ends at 1300, user inputs the next session to be at 1301, system
     * will return false as we assume that we need time to clean the cinema after
     * the previous screening
     * 
     * 
     * @param showingTime The time and date of the new session
     * @param CinemaCode  The code of the cinema that we want the new screening to
     *                    be in.
     * @param shownMovie  The movie being shown at the session
     * @return true if the session time is valid, false if it is invalid;
     */

    public boolean checkIfValidTime(LocalDateTime showingTime, String CinemaCode, Movie shownMovie) {
        ArrayList<MovieSession> sessionList = this.readFileByValues(CHOICE_CINEMACODE, CinemaCode);
        ArrayList<MovieSession> dateList = new ArrayList<MovieSession>();
        MovieSession currentCheck = null;

        for (MovieSession session : sessionList) {
            if (session.getShowDateTime_NonString().toLocalDate().isEqual(showingTime.toLocalDate())) {
                dateList.add(session);
            }
        }

        for (MovieSession nextsess : dateList) {
            if (currentCheck != null) {

                Long shownMovie_Duration = currentCheck.getShownMovie().getDuration().toMinutes();
                LocalDateTime previousSessionEndingTime = currentCheck.getShowDateTime_NonString()
                        .plusMinutes(shownMovie_Duration + 30);
                LocalDateTime nextSessionStartTime = nextsess.getShowDateTime_NonString();
                LocalDateTime checkSessionFullTime = showingTime.plusMinutes(shownMovie.getDuration().toMinutes());

                if (showingTime.isBefore(previousSessionEndingTime)
                        && checkSessionFullTime.isAfter(nextSessionStartTime)) {
                    return false;
                }

                currentCheck = nextsess;
            }

        }
        return true;
    }
}
