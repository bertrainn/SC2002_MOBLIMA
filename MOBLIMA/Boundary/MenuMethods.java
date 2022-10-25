package MOBLIMA.Boundary;

public class MenuMethods {
	
	public static void printHeader(String header) {
		int length = 65;
		for (int i=0; i<length; i++)
			System.out.print("-");
		System.out.println();
		
		int indent = (length - header.length())/2;
		for (int i=0; i<indent; i++)
			System.out.print(" ");
		System.out.print(header);
		System.out.println();
		
		for (int i = 0; i < length; i++) System.out.print("-");
        System.out.println();
	}
	
	public static void printMenu(String... menu) {
		for (String s: menu) {
			System.out.println(s);
		}
	}
	
	public static int userInput() {
		System.out.print("Enter choice: ");
		int choice;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		
		
		return choice;
	}

}
