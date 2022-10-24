package MOBLIMA.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private String Name;
    private ArrayList<Cinema> CinemaList;

    public Cineplex(String Name, ArrayList<Cinema> CinemaList) {
        this.Name = Name;
        this.CinemaList = CinemaList;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<Cinema> getCinemaList() {
        return this.CinemaList;
    }

    public void setCinema(ArrayList<Cinema> CinemaList) {
        this.CinemaList = CinemaList;
    }

    public void addCinema(Cinema Cinema) {
        this.CinemaList.add(Cinema);
    }

    public void removeCinema(Cinema Cinema) {
        this.CinemaList.remove(Cinema);
    }

    @Override
    public String toString() {
        return "Cineplex [Name=" + Name + ", CinemaList=" + CinemaList + "]";
    }

}
