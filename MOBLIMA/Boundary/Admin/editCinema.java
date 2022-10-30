package MOBLIMA.Boundary.Admin;

import static MOBLIMA.Entity.Cinema.*;

public class editCinema {
        
        name = getStringInput("Please enter movie name: ")
	
	public static void main(String[] args) {
		Cinema cin = new Cinema();
		System.out.println("Movie code is %s and movie name is %s", getcinemaCode(), getCinemaName());
		
		int choice;
		Scanner sc = new Scanner(System.in);
		do {

			choice = sc.nextInt();

			switch (choice) {
				case 1:
					code = getStringInput("Please enter movie code: ");
        				setcinemaCode(code);
					break;
				case 2:
					String name = userInput();
       					setCinemaName(name);
					break;
				
			}
		} while (choice < 3);
		sc.close();
	}
	
        

}
