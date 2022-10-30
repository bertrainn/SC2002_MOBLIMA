package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Entity.Movie;

public class editMovie {

	public static void main(String[] args) {
		Movie mov = new Movie();
		System.out.println("Movie title is %s and movie id is %s", mov.getTitle(), mov.getId());
		
		int choice;
		Scanner sc = new Scanner(System.in);
		do {

			choice = sc.nextInt();

			switch (choice) {
				case 1:
					String title = getStringInput("Please enter movie title: ");
        				mov.setTitle(title);
					break;
				case 2:
					String des = getStringInput("Please enter movie description: ");
        				mov.setDescriptoin(des);
					break;
				case 3:
					String age = getStringInput("Please enter movie age rating: ");
        				mov.setAgeRating(age);
					break;
				case 4:
					ArrayList actor = getStringInput("Please enter movie actors: ");
        				mov.setActor(actor);
					break;
				case 5:
					ArrayList genre = getStringInput("Please enter movie genres: ");
        				mov.setGenre(genre);
					break
				case 6:
					ArrayList rev = getStringInput("Please enter movie reviews: ");
       					mov.setReview(rev);	
					break;

			}
		} while (choice < 4);
		sc.close();
	}

}
