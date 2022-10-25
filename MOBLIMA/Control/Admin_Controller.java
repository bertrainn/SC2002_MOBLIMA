package MOBLIMA.Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import MOBLIMA.Entity.Admin;
import java.util.ArrayList;

public class Admin_Controller {

    public final static String FILENAME = "MOBLIMA/Data/Admin.txt";
    public final static int CHOICE_USERNAME = 0;
    public final static int CHOICE_PASSWORD = 1;

    public Admin_Controller() {
    }

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

    public void updateAdmin(int choice, String Username, Object obj) {
        ArrayList<Admin> Data = readFile();
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin m;

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
                    default:
                        break;
                }
            }
            UpdateData.add(m);
        }
        replaceFile(UpdateData, FILENAME);
    }

    public void deleteAdmin(String Username) {
        ArrayList<Admin> Data = readFile();
        ArrayList<Admin> UpdateData = new ArrayList<Admin>();
        Admin a;

        for (int i = 0; i < Data.size(); i++) {
            a = Data.get(i);
            if (!(a.getUsername() == Username)) {
                UpdateData.add(a);
            }
        }
        replaceFile(UpdateData, FILENAME);
    }
}
