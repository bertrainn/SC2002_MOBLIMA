package MOBLIMA.Boundary;

public abstract class BaseMenu {
	
	//The menu before this menu
	public BaseMenu previousMenu;
	
	//To be called when loading a new menu
	public abstract void load();
	
	//To be called when traversing back to a previous page
	//If no previous page, then quit
	public void back() {
		if (previousMenu == null) 
			System.exit(1);
		else {
			System.out.println();
			previousMenu.load();
			}
	}
	
	//Used to navigate to the next menu while saving current menu as next menu's prev
	public void navigate(BaseMenu a, BaseMenu b) {
		b.previousMenu = a;
		System.out.println();
		b.load();
	}
	
	//Gets the previous menu
	public BaseMenu getPrevMenu() {
		return previousMenu;
	}

}
