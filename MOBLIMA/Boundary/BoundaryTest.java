package MOBLIMA.Boundary;

import java.util.ArrayList;

import MOBLIMA.Control.MovieGoer_Controller;
import MOBLIMA.Entity.MovieGoer;

public class BoundaryTest {
	
	public static MovieGoer_Controller mgc = new MovieGoer_Controller();
	public static ArrayList<MovieGoer> movieGoerList = mgc.readFile();
	
	public static MovieGoer customer;
	
	
	public static void main(String[] args) {	
		new MainMenu().load();
	}

}