package MOBLIMA.Control;

import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.Review_Ratings;
import MOBLIMA.Entity.Constants;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;
import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDate;

public class Movie_Controller {
    public final static String FILENAME = "MOBLIMA/Data/Movie.txt";

    public final static int ID_CHOICE = 0;
    public final static int TITLE_CHOICE = 1;
    public final static int DESC_CHOICE = 2;
    public final static int AGERATING_CHOICE = 3;
    public final static int ACTOR_CHOICE = 4;
    public final static int DIRECTOR_CHOICE = 5;
    public final static int GENRE_CHOICE = 6;
    public final static int REVIEW_CHOICE = 7;
    public final static int OPENING_CHOICE = 8;
    public final static int CLOSING_CHOICE = 9;
    public final static int SHOWING_CHOICE = 10;
    public final static int DURATION_CHOICE = 11;

    public Movie_Controller() {
    }

    public ArrayList<Movie> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<Movie> MovieList = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return MovieList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Movie>();
    }

    public void addMovie(String Title, String Description, Constants.AGE_CLASSIFICATION Age,
            ArrayList<String> DirectorList, ArrayList<String> ActorList, ArrayList<String> GenreList,
            ArrayList<Review_Ratings> reviewList, LocalDate OpeningDate, LocalDate ClosingDate, Duration Duration) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Movie movie = new Movie(getLastID() + 1, Title, Description, Age, DirectorList, ActorList, GenreList,
                reviewList,
                OpeningDate,
                ClosingDate, Duration);

        ArrayList<Movie> Data = new ArrayList<Movie>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(movie);
            oos.writeObject(Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public void replaceFile(ArrayList<Movie> data, String filename) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File temp = new File(filename);
        if (temp.exists()) {
            temp.delete();
            try {
                fos = new FileOutputStream(FILENAME);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(data);
                oos.flush();
                oos.close();
            } catch (IOException e) {
            }
        }

    }

    public void updateMovie(int choice, int id, Object obj) {
        ArrayList<Movie> Data = readFile();
        ArrayList<Movie> UpdateData = new ArrayList<Movie>();
        Movie m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getId() == id) {
                switch (choice) {
                    case ID_CHOICE:
                        m.setId((int) obj);
                        break;
                    case TITLE_CHOICE:
                        m.setTitle((String) obj);
                        break;
                    case DESC_CHOICE:
                        m.setDescription((String) obj);
                        break;
                    case AGERATING_CHOICE:
                        m.setAgeRating((Constants.AGE_CLASSIFICATION) obj);
                        break;
                    case ACTOR_CHOICE:
                        m.setActor((ArrayList<String>) obj);
                        break;
                    case DIRECTOR_CHOICE:
                        m.setDirector((ArrayList<String>) obj);
                        break;
                    case GENRE_CHOICE:
                        m.setGenre((ArrayList<String>) obj);
                        break;
                    case REVIEW_CHOICE:
                        m.setReview((ArrayList<Review_Ratings>) obj);
                        break;
                    case OPENING_CHOICE:
                        m.setOpeningDate((LocalDate) obj);
                        break;
                    case CLOSING_CHOICE:
                        m.setClosingDate((LocalDate) obj);
                        break;
                    case SHOWING_CHOICE:
                        m.setShowingStatus();
                        break;
                    case DURATION_CHOICE:
                        m.setDuration((Duration) obj);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }
        replaceFile(UpdateData, FILENAME);
    }

    public void deleteMovie(int id) {
        ArrayList<Movie> Data = readFile();
        ArrayList<Movie> UpdateData = new ArrayList<Movie>();
        Movie m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (!(m.getId() == id)) {
                UpdateData.add(m);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }

    public int getLastID() {
        int lastID = -1;
        int MovieID;
        ArrayList<Movie> Data = readFile();
        for (int i = 0; i < Data.size(); i++) {
            MovieID = Data.get(i).getId();
            if (MovieID > lastID) {
                lastID = MovieID;
            }
        }
        return lastID;
    }

}