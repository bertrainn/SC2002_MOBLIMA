package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Entity.Movie.*;

public class editMovie {
	
	movie mov;

	System.out.println("Movie title is %s and movie id is %s", mov.getTitle(), mov.getId());
	
	String title = getStringInput("Please enter movie title: ");
        mov.setTitle(title);
        
        String des = getStringInput("Please enter movie description: ");
        mov.setDescriptoin(des);
        
	String age = getStringInput("Please enter movie age rating: ");
        mov.setAgeRating(age);
	
	ArrayList actor = getStringInput("Please enter movie actors: ");
        mov.setActor(actor);
	
	ArrayList genre = getStringInput("Please enter movie genres: ");
        mov.setGenre(genre);
	
	ArrayList rev = getStringInput("Please enter movie reviews: ");
        mov.setReview(rev);	
}
