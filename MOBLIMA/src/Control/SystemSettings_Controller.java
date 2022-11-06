package Control;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Controller for the System Settings.
 */
public class SystemSettings_Controller {
    
    /**
     * File name of the SystemSettings.txt file in the database to acces.
     */
    public final static String FILENAME = "MOBLIMA/database/SystemSettings.txt";

    public SystemSettings_Controller() {

    }
    
    /**
     * Function to read the file.
     */
    public ArrayList<String> read() {
        ArrayList<String> data = new ArrayList<String>();

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

        Scanner scanner = null;

        try {
            scanner = new Scanner(new FileInputStream(FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    /**
     * Function to write out data on the file.
     *
     * @param data Data to be written. 
     */
    public void write(List data) {
        PrintWriter out;

        out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    /**
     * Function to read the system settings.
     *
     * @return ArrayList that have the system settings.
     */
    public ArrayList<String> readSystemSettings() {

        ArrayList<String> stringArray = this.read();
        ArrayList<String> alr = new ArrayList<String>();
        for (int i = 0; i < stringArray.size(); i++) {
            String st = stringArray.get(i);
            alr.add(st);
        }
        return alr;
    }

    /**
     * Function that allows to save the currernt System Settings,
     *
     * @param al The Settings to be saved into the system.
     */
    public void saveSystemSettings(ArrayList<String> al) {
        ArrayList<String> alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            alw.add(al.get(i));
        }
        write(alw);
    }
}
