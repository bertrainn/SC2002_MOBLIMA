package MOBLIMA.Control;

import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Cinema;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

public class Cineplex_Controller {
    public final static String FILENAME = "MOBLIMA/Data/Cineplex.txt";
    public final static int CHOICE_NAME = 0;
    public final static int CHOICE_CODE = 1;
    public final static int CHOICE_CINEMALIST = 2;

    public Cineplex_Controller() {
    }

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Cineplex>();
    }

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

    public void updateCineplex(int choice, String Name, Object obj) {
        ArrayList<Cineplex> Data = readFile();
        ArrayList<Cineplex> UpdateData = new ArrayList<Cineplex>();
        Cineplex m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getName().equals(Name)) {
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

    public Cineplex getCineplexByCode(String cineplexCode) {
        ArrayList<Cineplex> cineplexList = CineplexController.readFile();
        for (Cineplex c : cineplexList) {
            if (c.getCineplexCode().equals(cineplexCode)) {
                return c;
            }
        }
        return null;
    }

    public boolean CineplexExists(String Name) {
        ArrayList<Cineplex> Data = readFile();

        for (Cineplex c : Data) {
            if (c.getName().equals(Name)) {
                return true;
            }
        }
        return false;
    }

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
