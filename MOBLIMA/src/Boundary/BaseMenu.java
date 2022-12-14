package Boundary;

/**
 * This Abstract class is implemented for the login class and main menu class.
 */
public abstract class BaseMenu {

	/**
	 * Creates the Previous Menu which is before the current menu.
	 */
	public BaseMenu previousMenu;

	/**
	 * The purpose of this abstract method is to be able to load the menus after the
	 * selected class.
	 */
	public abstract void load();

	/**
	 * This method is to be called when traversing back to a previous page.
	 * If no previous page, then quit.
	 */
	public void back() {
		if (previousMenu == null)
			System.exit(1);
		else {
			System.out.println();
			previousMenu.load();
		}
	}

	/**
	 * This method is used to navigate to the next menu while saving current menu as
	 * next menu's previous.
	 */
	public void navigate(BaseMenu a, BaseMenu b) {
		b.previousMenu = a;
		System.out.println();
		b.load();
	}

	/**
	 * This method gets the previous menu.
	 */
	public BaseMenu getPrevMenu() {
		return previousMenu;
	}

}
