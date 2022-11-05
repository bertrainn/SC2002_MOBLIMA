package Control;

import Entity.Cineplex;
import Entity.Cinema;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

/**
 * Controller for the Movie Goers.
 */
public class Cineplex_Controller {
    public final static String FILENAME = "MOBLIMA/database/Cineplex.txt";
    
    /**
     * Assigning each choices with a value.
     */
    public final static int CHOICE_NAME = 0;
    public final static int CHOICE_CODE = 1;
    public final static int CHOICE_CINEMALIST = 2;

    public Cineplex_Controller() {
    }

    /**
     * Function to read file. 
     * @return A new arraylist that has the movie sessions.
     * @exception IOException for the file
     * @exception ClassNotFoundException for the file
     */
    public ArrayList<Cineplex> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<Cineplex> CineplexList = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return CineplexList;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Cineplex>();
    }

    /**
     * Function to add in a new cineplex.
     * @param Name This cineplex's Name.
     * @param CineplexCode This cineplex's code.
     * @param CinemaList The cinemas to be added into the cineplex.
     * @exception IOException for the reading the file
     */
    public void addCineplex(String Name, String CineplexCode, ArrayList<Cinema> CinemaList) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Cineplex Cineplex = new Cineplex(Name, CineplexCode, CinemaList);

        ArrayList<Cineplex> Data = new ArrayList<Cineplex>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(Cineplex);
            oos.writeObject(Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    /**
     * Function that replaces the content in the file.
     * @param data ArrayList that replaces the current data in the File.
     * @param filename Name of the file.
     * @exception IOException If there is any error within the file.
     */
    public void replaceFile(ArrayList<Cineplex> data, String filename) {
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

    /**
     * Function that updates the cineplex's details.
     * @param choice The choice of detail to change.
     * @param Code The Cineplex's Code.
     * @param obj The object that will update the choice of detail.
     */
    public void updateCineplex(int choice, String Code, Object obj) {
        ArrayList<Cineplex> Data = readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        Cineplex m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getName().equals(Code)) {
                switch (choice) {
                    case CHOICE_NAME:
                        m.setName((String) obj);
                        break;
                    case CHOICE_CODE:
                        m.setCineplexCode((String) obj);
                    case CHOICE_CINEMALIST:
                        m.setCinema((ArrayList<Cinema>) obj);
                        break;
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }
        replaceFile(UpdateData, FILENAME);
    }

    /**
     * Function that will delete the cineplex.
     * @param Code This Ciniplex's code.
     */
    public void deleteCineplex(String Code) {
        ArrayList<Cineplex> Data = readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        Cineplex m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (!(m.getCineplexCode().equals(Code))) {
                UpdateData.add(m);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }

    /**
     * Function that returns the cineplex using the cineplex code if exist
     * @param cineplexCode The Cineplex's code.
     * @return the cineplex if Cineplex exist otherwise null.
     */
    public Cineplex getCineplexByCode(String cineplexCode) {
        ArrayList<Cineplex> cineplexList = readFile();
        for (Cineplex c : cineplexList) {
            if (c.getCineplexCode().equals(cineplexCode)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Function that checks the cineplex existence by name.
     * @param Name The Cineplex's Name.
     * @return true if Cineplex exist otherwise false.
     */
    public boolean CineplexExists(String Name) {
        ArrayList<Cineplex> Data = readFile();

        for (Cineplex c : Data) {
            if (c.getName().equals(Name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that checks the cineplex existence by code.
     * @param Code The Cineplex's Code.
     * @return true if Cineplex exist otherwise false.
     */
    public boolean CineplexCodeExist(String Code) {
        ArrayList<Cineplex> Data = readFile();

        for (Cineplex c : Data) {
            if (c.getCineplexCode().equals(Code)) {
                return true;
            }
        }
        return false;
    }
}
