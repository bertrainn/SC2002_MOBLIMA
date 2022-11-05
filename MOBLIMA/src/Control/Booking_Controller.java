package Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entity.Booking;
import Entity.Cinema;
import Entity.Cineplex;
import Entity.Movie;
import Entity.Ticket;
import Entity.MovieGoer;
import java.util.ArrayList;

/**
 * Controller for the booking of movies.
 */
public class Booking_Controller {
    public final static String FILENAME = "MOBLIMA/database/Booking.txt";

    public Booking_Controller() {
    }

    /**
     * Function to read file. 
     * @return A new arraylist that has the bookings of movies.
     * @exception IOException for the file
     * @exception ClassNotFoundException for the file
     */
    public ArrayList<Booking> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<Booking> BookingList = (ArrayList<Booking>) ois.readObject();
            ois.close();
            return BookingList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Booking>();
    }

    /**
     * Function to add in a new booking.
     * @param TotalAmount The booking's total amount.
     * @param cinema The booking's cinema.
     * @param movie The booking's movie.
     * @param ticketlist TThe booking's ticketlist.
     * @param user The booking's user.
     * @param cineplex The booking's cineplex.
     * @exception IOException for the reading the file
     */
    public void addBooking(double TotalAmount, Cinema cinema, Movie movie, ArrayList<Ticket> ticketList,
            MovieGoer user, Cineplex cineplex) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Booking Booking = new Booking(TotalAmount, cinema, movie, ticketList, user, cineplex);

        ArrayList<Booking> Data = new ArrayList<Booking>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(Booking);
            oos.writeObject(Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    /**
     * Function to retrieve booking by TID
     * @param TID The booking's TID
     * @return Arraylist that contains the booking linked to the TID
     */
    public ArrayList<Booking> retrieveByTID(String TID) {
        ArrayList<Booking> Data = readFile();
        ArrayList<Booking> ReturnData = new ArrayList<Booking>();
        Booking b;
        for (int i = 0; i < Data.size(); i++) {
            b = Data.get(i);
            if (TID.equals(b.getTID())) {
                ReturnData.add(b);
            }
        }

        return ReturnData;
    }

    /**
     * Function to retrieve booking by User
     * @param Username The username of the booking
     * @return Arraylist that contains the booking linked to the username
     */
    public ArrayList<Booking> retrieveByUser(String Username) {
        ArrayList<Booking> Data = readFile();
        ArrayList<Booking> ReturnData = new ArrayList<Booking>();
        String username;
        Booking b;

        for (int i = 0; i < Data.size(); i++) {
            b = Data.get(i);
            username = b.getMovieGoer().getUsername();

            if (username.equals(Username)) {
                ReturnData.add(b);
            }
        }

        return ReturnData;
    }

    /**
     * Function that will delete the booking.
     * @param TID This booking's TID.
     */
    public void deleteBooking(String TID) {
        ArrayList<Booking> Data = readFile();
        ArrayList<Booking> UpdateData = new ArrayList<Booking>();
        Booking b;

        for (int i = 0; i < Data.size(); i++) {
            b = Data.get(i);
            if (!(b.getTID().equals(TID))) {
                UpdateData.add(b);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }

    /**
     * Function that replaces the content in the file.
     * @param data ArrayList that replaces the current data in the File.
     * @param filename Name of the file.
     * @exception IOException If there is any error within the file.
     */
    public void replaceFile(ArrayList<Booking> data, String filename) {
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
}
