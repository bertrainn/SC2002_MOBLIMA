package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Entity.Cinema.*;

public class editCinema {

	    System.out.println("Movie code is %s and movie name is %s", getcinemaCode(), getCinemaName());
	
	
		code = getStringInput("Please enter movie code: ");
        setcinemaCode(code);
        
        name = getStringInput("Please enter movie name: ");

	
	    String name = userInput();
        setCinemaName(name);
        

}