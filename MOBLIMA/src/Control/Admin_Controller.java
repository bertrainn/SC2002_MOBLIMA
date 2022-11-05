package Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entity.Admin;
import java.util.ArrayList;

/**
 * Controller for the Admin.
 */
public class Admin_Controller {

    public final static String FILENAME = "MOBLIMA/database/Admin.txt";

    public Admin_Controller() {
    }

    /**
     * Function to read file. 
     * @return A new arraylist that has the admin details.
     * @exception IOException for the file.
     * @exception ClassNotFoundException for the file.
     */
    public ArrayList<Admin> readFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(FILENAME);
            ois = new ObjectInputStream(fis);
            ArrayList<Admin> AdminList = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return AdminList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Admin>();
    }

    /**
     * Function to add in a new movie goer.
     * @param Username This Admin's username.
     * @param Password This Admin's password.
     * @exception IOException for the reading the file
     */
    public void addAdmin(String Username, String Password) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        Admin Admin = new Admin(Username, Password);

        ArrayList<Admin> Data = new ArrayList<Admin>();
        File temp = new File(FILENAME);
        if (temp.exists()) {
            Data = readFile();
        }
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            Data.add(Admin);
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
    public void replaceFile(ArrayList<Admin> data, String filename) {
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
     * Function that will delete the admin account.
     * @param Username This Admin's username.
     */
    public void deleteAdmin(String Username) {
        ArrayList<Admin> Data = readFile();
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername().equals(Username))) {
                UpdateData.add(a);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }

    /**
     * Function that checks for the admin's account.
     * @param Username This Admin's username.
     * @return true if admin exist otherwise false.
     */
    public boolean AdminExists(String Username) {
        ArrayList<Admin> Data = readFile();

        for (Admin a : Data) {
            if (a.getUsername().equals(Username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that will get Admin by this username.
     * @param Username The Admin's username.
     * @return admin account if have otherwise null.
     */
    public Admin getAdminByUsername(String Username) {
        ArrayList<Admin> Data = readFile();

        for (Admin a : Data) {
            if (a.getUsername().equals(Username)) {
                return a;
            }
        }
        return null;
    }
}
