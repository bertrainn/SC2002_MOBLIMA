package Control;

import Entity.Movie;
import Entity.Review_Ratings;
import Entity.Constants;

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
    public final static String FILENAME = "database/Movie.txt";

    public final static int CHOICE_ID = 0;
    public final static int CHOICE_TITLE = 1;
    public final static int CHOICE_DESC = 2;
    public final static int CHOICE_AGERATING = 3;
    public final static int CHOICE_ACTOR = 4;
    public final static int CHOICE_DIRECTOR = 5;
    public final static int CHOICE_GENRE = 6;
    public final static int CHOICE_REVIEW = 7;
    public final static int CHOICE_OPENING = 8;
    public final static int CHOICE_CLOSING = 9;
    public final static int CHOICE_SHOWING = 10;
    public final static int CHOICE_DURATION = 11;

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
        Movie movie = new Movie(getLastID() + 1, Title, Description, Age, ActorList, DirectorList, GenreList,
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
                    case CHOICE_ID:
                        m.setId((int) obj);
                        break;
                    case CHOICE_TITLE:
                        m.setTitle((String) obj);
                        break;
                    case CHOICE_DESC:
                        m.setDescription((String) obj);
                        break;
                    case CHOICE_AGERATING:
                        m.setAgeRating((Constants.AGE_CLASSIFICATION) obj);
                        break;
                    case CHOICE_ACTOR:
                        m.setActor((ArrayList<String>) obj);
                        break;
                    case CHOICE_DIRECTOR:
                        m.setDirector((ArrayList<String>) obj);
                        break;
                    case CHOICE_GENRE:
                        m.setGenre((ArrayList<String>) obj);
                        break;
                    case CHOICE_REVIEW:
                        m.setReview((ArrayList<Review_Ratings>) obj);
                        break;
                    case CHOICE_OPENING:
                        m.setOpeningDate((LocalDate) obj);
                        break;
                    case CHOICE_CLOSING:
                        m.setClosingDate((LocalDate) obj);
                        break;
                    case CHOICE_SHOWING:
                        m.setShowingStatus();
                        break;
                    case CHOICE_DURATION:
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

    public ArrayList<Movie> getShowingMovies() {
        ArrayList<Movie> Data = readFile();
        ArrayList<Movie> Output = new ArrayList<Movie>();

        for (Movie m : Data) {
            if (!m.getShowingStatus().equals(Constants.SHOWING_STATUS.EOS)) {
                Output.add(m);
            }
        }

        return Output;
    }

    public boolean MovieExists(String Title) {
        ArrayList<Movie> Data = readFile();

        for (Movie m : Data) {
            if (m.getTitle().equals(Title)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Movie> getMoviesByTitle(String Title) {
        ArrayList<Movie> Data = readFile();
        ArrayList<Movie> searchResults = new ArrayList<>();

        for (Movie m : Data) {
            String title = m.getTitle().toUpperCase();
            if (title.contains(Title)) {
                searchResults.add(m);
            }
        }

        return searchResults;
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