package MOBLIMA.Entity;

import java.io.Serializable;

public class SeatLayout implements Serializable {
    private Seat[][] layout;
    private int row, col;

    public SeatLayout(int row, int col) {
        this.row = row;
        this.col = col;
        layout = new Seat[row][col];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                layout[i][j] = new Seat(i * this.row + j);
            }
        }
    }

    public void printLayout() {
        System.out.print("\nSelect Your Seats\n");
        System.out.println("([ ] Available [X] Sold)");
        System.out.println();

        System.out.print("\t \t|      Screen       |\n");
        for (int i = 0; i < this.row; i++) {
            System.out.printf("Seats %d - %d:\t", i * this.col, ((i + 1) * this.col - 1));
            for (int j = 0; j < this.col; j++) {
                if (layout[i][j].isIsOccupied())
                    System.out.print("[X]");
                else
                    System.out.print("[ ]");

                if (j == this.col / 2 - 1)
                    System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    public Seat[][] getLayout() {
        return layout;
    }

    public void setLayout(Seat[][] layout) {
        this.layout = layout;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void assignSeats(int id) {
        int index = id / row;
        layout[index][id - row * index].book();
    }

    public void unassignSeats(int id) {
        int index = id / row;
        layout[index][id - row * index].unbook();
    }

    public boolean isSeatAssign(int id) {
        int index = id / row;
        return layout[index][id - row * index].isIsOccupied();
    }
}
