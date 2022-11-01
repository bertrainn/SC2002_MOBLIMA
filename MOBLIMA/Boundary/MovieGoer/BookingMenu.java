package MOBLIMA.Boundary.MovieGoer;

import static MOBLIMA.Boundary.MenuMethods.*;

import java.util.ArrayList;

import MOBLIMA.Boundary.BaseMenu;
import MOBLIMA.Control.Cineplex_Controller;
import MOBLIMA.Control.Cinema_Controller;
import MOBLIMA.Control.MovieSession_Controller;
import MOBLIMA.Entity.Cineplex;
import MOBLIMA.Entity.Movie;
import MOBLIMA.Entity.MovieSession;

public class BookingMenu extends BaseMenu {
	
	private Cineplex_Controller cpc = new Cineplex_Controller();
	private Cinema_Controller cc = new Cinema_Controller(cpc);
	private MovieSession_Controller msc = new MovieSession_Controller(cc);
	private Cineplex cp;
	
	
	public BookingMenu(Cineplex cp) {
		this.cp = cp;
	}

	@Override
	public void load() {
		showMenu();
	}
	
	private void showMenu() {
		ArrayList<MovieSession> allMovieSessions = msc.readFile();
		
		printHeader("Movies showing at " + cp.getName());
		
		if (allMovieSessions.isEmpty()) {
			printMenu("There are no movies showing at this cineplex, enter any number to go back.");
			userInput(1,9);
			back();
		}
		
		else {
			int i=0;
			int flag = 0;
			ArrayList<String> movieNames = new ArrayList<>();
			printMenuWithoutSpace("Choose one of the following movies:");
			for (MovieSession ms : allMovieSessions) {
				String movieName = ms.getShownMovie().getTitle();
				for (String s : movieNames) {
					if (s.equals(movieName)) {
						flag = 1;
						break;
					}
				}
				if (flag == 1) continue;
				movieNames.add(movieName);
				printMenuWithoutSpace(++i + ". " + movieName);
			}
			printMenu(++i + ". Back");
			
			int choice = userInput(1, i);
			
			if (choice == i) back();
			else {
				MovieSession ms = allMovieSessions.get(choice-1);
				Movie m = ms.getShownMovie();
				showSessions(m);
			}
		}
	}
	
	private void showSessions(Movie m) {
		int i=0;
		ArrayList<MovieSession> sessions = msc.readFileByValues(MovieSession_Controller.CHOICE_MOVIE, m.getId());
		
		printMenuWithoutSpace("Showtimes for " + m.getTitle());
		for (MovieSession ms : sessions) {
			printMenuWithoutSpace(++i + ". " + ms.getShowDateTime());
		}
		printMenu(++i + ". Back");
		
		int choice = userInput(1, i);
		
		if (choice == i) load();
		else {
			MovieSession ms = sessions.get(choice-1);
			showSeatingPlan(ms);
		}
		
	}
	
	
	//TBD
	private void showSeatingPlan(MovieSession ms) {
		
	}

}
