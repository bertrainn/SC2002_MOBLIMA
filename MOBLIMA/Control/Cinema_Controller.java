package MOBLIMA.Control;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;

import MOBLIMA.Entity.Cinema;

public class Cinema_Controller {
    private Cineplex_Controller CC;
    private String FILENAME;

    public Cinema_Controller(Cineplex_Controller CC) {
        this.CC = CC;
        FILENAME = CC.FILENAME;
    }

    
}
