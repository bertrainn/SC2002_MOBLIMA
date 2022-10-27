package MOBLIMA.Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import MOBLIMA.Entity.Booking;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieGoer;
import MOBLIMA.Entity.Review_Ratings;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieGoer_Controller {

    public final static String FILENAME = "MOBLIMA/Data/MovieGoer.txt";
    public final static int CHOICE_USERNAME = 0;
    public final static int CHOICE_PASSWORD = 1;
    public final static int CHOICE_NAME = 2;
    public final static int CHOICE_EMAIL = 3;
    public final static int CHOICE_PHONE = 4;
    public final static int CHOICE_REVIEW = 5;
    public final static int CHOICE_BOOKING = 7;

    public MovieGoer_Controller() {
    }

    public ArrayList<MovieGoer> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<MovieGoer> MovieGoerList = (ArrayList<MovieGoer>) ois.readObject();
            ois.close();
            return MovieGoerList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<MovieGoer>();
    }

    public void addMovieGoer(String Username, String Password, String Name, String Email, String Phone,
            HashMap<Movie, Review_Ratings> PostedReviewsList, ArrayList<Booking> BookingList) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        MovieGoer MovieGoer = new MovieGoer(Username, Password, Name, Email, Phone, PostedReviewsList, BookingList);
        ArrayList<MovieGoer> Data = new ArrayList<MovieGoer>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(MovieGoer);
            oos.writeObject(Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    public void replaceFile(ArrayList<MovieGoer> data, String filename) {
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

    public void updateMovieGoer(int choice, String Username, Object obj) {
        ArrayList<MovieGoer> Data = readFile();
        ArrayList<MovieGoer> UpdateData = new ArrayList<MovieGoer>();
        MovieGoer m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getUsername() == Username) {
                switch (choice) {
                    case CHOICE_USERNAME:
                        m.setUsername((String) obj);
                        break;
                    case CHOICE_PASSWORD:
                        m.setPassword((String) obj);
                        break;
                    case CHOICE_NAME:
                        m.setName((String) obj);
                        break;
                    case CHOICE_EMAIL:
                        m.setEmail((String) obj);
                        break;
                    case CHOICE_PHONE:
                        m.setPhone((String) obj);
                        break;
                    case CHOICE_REVIEW:
                        m.setPostedReviews((HashMap<Movie, Review_Ratings>) obj);
                        break;
                    case CHOICE_BOOKING:
                        m.setBookingList((ArrayList<Booking>) obj);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }
        replaceFile(UpdateData, FILENAME);
    }

    public void deleteMovieGoer(String Username) {
        ArrayList<MovieGoer> Data = readFile();
        ArrayList<MovieGoer> UpdateData = new ArrayList<MovieGoer>();
        MovieGoer a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername() == Username)) {
                UpdateData.add(a);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }
}