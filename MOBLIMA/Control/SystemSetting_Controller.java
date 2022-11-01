package MOBLIMA.Control;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SystemSettings_Controller {
    public final static String FILENAME = "Data/SystemSettings.txt";

    public ArrayList<String> read() throws IOException {
        ArrayList<String> data = new ArrayList<String>();

        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
        Scanner scanner = new Scanner(new FileInputStream(FILENAME));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    public void write(List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(FILENAME));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    public ArrayList<String> readSystemSettings() throws IOException {

        ArrayList<String> stringArray = this.read();
        ArrayList<String> alr = new ArrayList<String>();
        for (int i = 0; i < stringArray.size(); i++) {
            String st = stringArray.get(i);
            alr.add(st);
        }
        return alr;
    }

    public void saveSystemSettings(ArrayList<String> al) throws IOException {
        ArrayList<String> alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            alw.add(al.get(i));
        }
        write(alw);
    }
}
