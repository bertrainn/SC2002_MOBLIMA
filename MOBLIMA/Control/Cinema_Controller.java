package MOBLIMA.Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

import MOBLIMA.Entity.Cinema;
import MOBLIMA.Entity.SeatLayout;
import MOBLIMA.Entity.MovieSession;
import MOBLIMA.Entity.Cineplex;

public class Cinema_Controller {
    private Cineplex_Controller CineplexController;
    public String FILENAME;

    public final static int CHOICE_CODE = 0;
    public final static int CHOICE_NAME = 1;
    public final static int CHOICE_ISPLAT = 2;
    public final static int CHOICE_SEATPLAN = 3;
    public final static int CHOICE_MOVIESESS = 4;

    public Cinema_Controller(Cineplex_Controller CineplexController) {
        this.CineplexController = CineplexController;
        FILENAME = CineplexController.FILENAME;
    }

    public void createCinema(String cineplexName, String cinemaCode, String CinemaName, boolean isplat, SeatLayout sl,
            ArrayList<MovieSession> sessions) {

        ArrayList<Cineplex> Data = CineplexController.readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        Cineplex c;
        Cinema newCinema = new Cinema(cinemaCode, CinemaName, isplat, sl, sessions);

        for (int i = 0; i < Data.size(); i++) {
            c = Data.get(i);
            if (c.getName().equals(cineplexName)) {
                ArrayList<Cinema> cinemas = c.getCinemaList();
                cinemas.add(newCinema);
            }
            UpdateData.add(c);
        }
        this.CineplexController.replaceFile(UpdateData, this.FILENAME);
    }

    public ArrayList<Cinema> getCinema() {
        ArrayList<Cinema> OutputList = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexList = CineplexController.readFile();
        Cineplex c;
        Cinema cinema;
        for (int i = 0; i < cineplexList.size(); i++) {
            c = cineplexList.get(i);
            ArrayList<Cinema> temp = c.getCinemaList();
            for (int j = 0; j < temp.size(); j++) {
                cinema = temp.get(j);
                OutputList.add(cinema);
            }
        }
        return OutputList;
    }

    public ArrayList<Cinema> getCinemaByCineplex(String cineplexName) {
        ArrayList<Cinema> OutputList = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexList = CineplexController.readFile();
        Cineplex c;
        Cinema cinema;
        for (int i = 0; i < cineplexList.size(); i++) {
            c = cineplexList.get(i);
            if (c.getName().equals(cineplexName)) {
                ArrayList<Cinema> temp = c.getCinemaList();
                for (int j = 0; j < temp.size(); j++) {
                    cinema = temp.get(j);
                    OutputList.add(cinema);
                }
            }
        }
        return OutputList;
    }

    public ArrayList<Cinema> getCinemaByCode(String cineplexName) {
        ArrayList<Cinema> OutputList = new ArrayList<Cinema>();
        ArrayList<Cineplex> cineplexList = CineplexController.readFile();
        Cineplex c;
        Cinema cinema;
        for (int i = 0; i < cineplexList.size(); i++) {
            c = cineplexList.get(i);
            if (c.getName().equals(cineplexName)) {
                ArrayList<Cinema> temp = c.getCinemaList();
                for (int j = 0; j < temp.size(); j++) {
                    cinema = temp.get(j);
                    OutputList.add(cinema);
                }
            }
        }
        return OutputList;
    }

    public void updateCinema(int choice, String cinemaCode, Object obj) {
        ArrayList<Cineplex> Data = CineplexController.readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        ArrayList<Cinema> temp;
        ArrayList<Cinema> UpdatedList = new ArrayList<Cinema>();
        Cineplex cineplex;
        Cinema cinema;

        for (int i = 0; i < Data.size(); i++) {
            cineplex = Data.get(i);
            temp = cineplex.getCinemaList();

            for (int j = 0; j < temp.size(); j++) {
                cinema = temp.get(j);
                if (cinema.getcinemaCode().equals(cinemaCode)) {
                    switch (choice) {
                        case CHOICE_CODE:
                            cinema.setcinemaCode((String) obj);
                            break;
                        case CHOICE_NAME:
                            cinema.setCinemaName((String) obj);
                            break;
                        case CHOICE_ISPLAT:
                            cinema.setisPlat((boolean) obj);
                            break;
                        case CHOICE_SEATPLAN:
                            cinema.setSeatPlan((SeatLayout) obj);
                            break;
                        case CHOICE_MOVIESESS:
                            cinema.setMovieSessions((ArrayList<MovieSession>) obj);
                            break;
                        default:
                            break;
                    }
                }
                UpdatedList.add(cinema);
            }
            cineplex.setCinema(UpdatedList);
            UpdateData.add(cineplex);
        }
        this.CineplexController.replaceFile(UpdateData, FILENAME);
    }

    public void deleteCinema(String cinemaCode) {
        ArrayList<Cineplex> Data = CineplexController.readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        ArrayList<Cinema> temp;
        ArrayList<Cinema> UpdatedList = new ArrayList<Cinema>();
        Cineplex cineplex;
        Cinema cinema;

        for (int i = 0; i < Data.size(); i++) {
            cineplex = Data.get(i);
            temp = cineplex.getCinemaList();

            if (temp.size() > 3) {
                for (int j = 0; j < temp.size(); j++) {
                    cinema = temp.get(j);
                    if (!cinema.getcinemaCode().equals(cinemaCode)) {
                        UpdatedList.add(cinema);
                    }
                }
                cineplex.setCinema(UpdatedList);
            }
            UpdateData.add(cineplex);
        }
        this.CineplexController.replaceFile(UpdateData, FILENAME);
    }
}
