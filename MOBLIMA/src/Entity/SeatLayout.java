package Entity;

import static Control.UserInput_Controller.*;

import java.io.Serializable;

/**
 * Represents an instance of a movie used in the system.
 */

public class SeatLayout implements Serializable {

    /**
     * Seating layout.
     */
    private Seat[][] layout;

    /**
     * Row and column of seating layout.
     */
    private int row, col;

    /**
     * Constructor for the SeatLayout class.
     * 
     * @param row row of seating layout
     * @param col column of seating layout
     */
    public SeatLayout(int row, int col) {
        this.row = row;
        this.col = col;
        layout = new Seat[row][col];

        for (int i = 0; i < this.row-1; i++) {
            for (int j = 0; j < this.col; j++) {
                layout[i][j] = new Seat(i * this.col + j);
            }
        }
        
        boolean IsCoupleSeat = true;
        int last = this.row-1;
        int pairID;
        if (this.col % 2 != 0) {
        	layout[last][0] = new Seat(last * this.col + 0);
        	if (this.col/2 % 2 == 0) {
        		for (int k = 1; k<this.col; k++) {
        			if (k == this.col/2 - 1 || k == this.col/2) {
        				layout[last][k] = new Seat(last * this.col + k);
            		}
        			else {
	            		if (k % 2 != 0)
	            			pairID = last * this.col + (k+1);
	            		else pairID = last * this.col + (k-1);
	            		layout[last][k] = new Seat(last * this.col + k, IsCoupleSeat, pairID);
            		}
            	}
        	}
        	else {
	        	for (int k = 1; k<this.col; k++) {
	        		if (k % 2 != 0)
	        			pairID = last * this.col + (k+1);
	        		else pairID = last * this.col + (k-1);
	        		layout[last][k] = new Seat(last * this.col + k, IsCoupleSeat, pairID);
	        	}
        	}
        }
        
        else {
        	if (this.col/2 % 2 != 0) {
        		for (int k = 0; k < this.col; k++) {
        			if (k == this.col/2 - 1 || k == this.col/2) {
        				layout[last][k] = new Seat(last * this.col + k);
        			}
        			else {
	    	        	if (k % 2 == 0)
	    	        		pairID = last * this.col + (k+1);
	    	        	else pairID = last * this.col + (k-1);
	    	        	layout[last][k] = new Seat(last * this.col + k, IsCoupleSeat, pairID);
    	        	}
    	        }
        	}
        	else {
		        for (int k = 0; k < this.col; k++) {
		        	if (k % 2 == 0)
		        		pairID = last * this.col + (k+1);
		        	else pairID = last * this.col + (k-1);
		        	layout[last][k] = new Seat(last * this.col + k, IsCoupleSeat, pairID);
		        }
        	}
        }
        
    }

    /**
     * This method prints the layout of the cinema
     */
    public void printLayout() {
        System.out.print("\nSelect Your Seats\n");
        System.out.println("([ ] Available [X] Sold)");
        System.out.println();

        int space;
        if (row > 10)
            space = 27 + (col / 2);
        else if (row > 5)
            space = 17 + (col / 2);
        else
            space = (13 + col / 2);

        System.out.print(generateSpaces(space) + "|      Screen       |\n");
        for (int i = 0; i < this.row; i++) {
            String s = "Seats " + (i * this.col) + " - " + ((i + 1) * this.col - 1);
            System.out.printf(s + generateSpaces(17 - s.length()));
            for (int j = 0; j < this.col; j++) {
            	if (layout[i][j].isIsCoupleSeat()) {
            		if (layout[i][j].getSeatID() < layout[i][j].getPairID()) {
            			if (layout[i][j].isIsOccupied())
            				System.out.print("[X ");
            			else
            				System.out.print("[  ");
            		}
            		else {
            			if (layout[i][j].isIsOccupied())
            				System.out.print(" X]");
            			else
            				System.out.print("  ]");
            		}	
            	}
            	else {
	                if (layout[i][j].isIsOccupied())
	                    System.out.print("[X]");
	                else
	                    System.out.print("[ ]");
            	}
                if (j == this.col / 2 - 1)
                    System.out.print("  ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * This method returns the seating layout
     * 
     * @return the seating layout
     */
    public Seat[][] getLayout() {
        return layout;
    }

    /**
     * This method sets the seating layout
     * 
     * @param id is the new seating layout
     */
    public void setLayout(Seat[][] layout) {
        this.layout = layout;
    }

    /**
     * This method returns the row
     * 
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * This method sets the row
     * 
     * @param id is the new row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * This method returns the column
     * 
     * @return the column
     */
    public int getCol() {
        return col;
    }

    /**
     * This method sets the column
     * 
     * @param id is the new column
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * This method assigns an id to a particular seat
     */
    public void assignSeats(int id) {
        int index = id / col;
        layout[index][id - col * index].book();
    }

    /**
     * This method unassigns an id from a particular seat
     */
    public void unassignSeats(int id) {
        int index = id / col;
        layout[index][id - col * index].unbook();
    }

    /**
     * This method returns if a seat has been assigned to a id
     */
    public boolean isSeatAssign(int id) {
        int index = id / col;
        return layout[index][id - col * index].isIsOccupied();
    }
    
    
    /** 
     * @param id
     * @return int
     */
    public int isCoupleSeat(int id) {
    	int index = id / col;
    	return layout[index][id - col * index].getPairID();
    }
}
