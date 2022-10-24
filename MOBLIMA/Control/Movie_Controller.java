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

        ArrayList<Movie> DataBase = new ArrayList<Movie>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            DataBase = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            DataBase.add(movie);
            oos.writeObject(DataBase);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public void updateMovie(int index, int id, Object obj) {

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