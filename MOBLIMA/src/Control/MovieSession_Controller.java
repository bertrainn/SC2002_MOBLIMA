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

    /**
     * Assigning each choices with a value.
     */
    public final static int CHOICE_MOVIE = 1;
    public final static int CHOICE_DATETIME = 2;
    public final static int CHOICE_CINEMACODE = 5;
    
    /**
     * Creates a new MovieSession Controller with the given Cinema Controller.
     * @param CC This MovieSession Controller's Cinema Controller.
     */
    public MovieSession_Controller(Cinema_Controller CC) {
        this.CinemaControl = CC;
        this.FILENAME = CC.FILENAME;
    }

    /**
     * Function that creates movie session.
     * @param cinemaCode The cinema's code.
     * @param shownMovie The movies that are currently showing.
     * @param startDateTime The data and time for the session to start.
     */
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

    /**
     * Function that updates the movie session.
     * @param sess The movie session that is going to be updated.
     */
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

    /**
     * Function to read file. 
     * @return A new arraylist that has the movie sessions.
     */
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
    
    /**
     * Function to read files by its values.
     * @param choice The Choice of what to be read and added into the arraylist.
     * @param obj The Object [ this one idk ]
     * @return A new arraylist that has been read by values.
     */
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

    /**
     * Function that deletes the movie by ID.
     * @param id The session ID.
     */
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

    /**
     * Function that deletes the movie by MovieID.
     * @param movieId The movie's ID.
     */
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

    /**
     * Function that gets the last ID.
     * @returns the last ID on the file.
     */
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
