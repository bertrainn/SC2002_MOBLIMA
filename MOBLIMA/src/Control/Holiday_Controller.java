package Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entity.Holiday;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Controller for the holiday.
 */
public class Holiday_Controller {
    
    /**
     * File name of the Holiday.txt file in the database to access.
     */
    public final static String FILENAME = "MOBLIMA/database/Holiday.txt";
    
    /**
     * Assigning each choices with a value.
     */
    public final static int CHOICE_NAME = 0;
    public final static int CHOICE_DATE = 1;

    public Holiday_Controller() {
    }

    /**
     * Function to read file.
     *
     * @return A new arraylist that has the movie sessions.
     */
    public ArrayList<Holiday> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<Holiday> HolidayList = (ArrayList<Holiday>) ois.readObject();
            ois.close();
            return HolidayList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Holiday>();
    }

    /**
     * Function that adds public holiday.
     *
     * @param name The name of the holiday.
     * @param Date The date of the holiday.
     */ 
    public void addHoliday(String name, LocalDate Date) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Holiday Holiday = new Holiday(name, Date);
        ArrayList<Holiday> Data = new ArrayList<Holiday>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(Holiday);
            oos.writeObject(Data);
            oos.flush();
            oos.close();
        } catch (IOException e) {
        }
    }

    /**
     * Function that replaces the content in the file.
     *
     * @param data     ArrayList that replaces the current data in the File.
     * @param filename Name of the file.
     */
    public void replaceFile(ArrayList<Holiday> data, String filename) {
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
     * Function that updates the dates of the holidays.
     *
     * @param choice The Content to update either name or date of holiday.
     * @param date   Date of the holiday.
     * @param obj    The object that updates the choice.
     */
    public void updateHoliday(int choice, LocalDate Date, Object obj) {
        ArrayList<Holiday> Data = readFile();
        ArrayList<Holiday> UpdateData = new ArrayList<Holiday>();
        Holiday m;

        for (int i = 0; i < Data.size(); i++) {
            m = Data.get(i);
            if (m.getDate().equals(Date)) {
                switch (choice) {
                    case CHOICE_NAME:
                        m.setName((String) obj);
                        break;
                    case CHOICE_DATE:
                        m.setDate((LocalDate) obj);
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
     * Function that deletes and remove the holiday.
     *
     * @param date The date of the holiday to be removed.
     */
    public void deleteHoliday(LocalDate Date) {
        ArrayList<Holiday> Data = readFile();
        ArrayList<Holiday> UpdateData = new ArrayList<Holiday>();
        Holiday a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getDate().equals(Date))) {
                UpdateData.add(a);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }

    /**
     * Function that checks whether the date or day is a holiday.
     *
     * @param value The date of the holiday.
     * @return true if it is holiday otherwise false.
     */
    public boolean isHoliday(LocalDate value) {
        ArrayList<Holiday> Data = readFile();
        for (int i = 0; i < Data.size(); i++) {
            if (Data.get(i).getDate().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
