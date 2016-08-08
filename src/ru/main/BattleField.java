package ru.main;

/**
 * Created by sadmin on 13.12.2015.
 */
public class BattleField {
    Cell[][] cell;
    int xSizeField = 10;
    int ySizeField = 10;

    public void init() {
        cell = new Cell[xSizeField][ySizeField];
        for (int i = 0; i < xSizeField; i++) {
            for (int j = 0; j < ySizeField; j++) {
                cell[i][j] = new Cell();
                cell[i][j].fill(i, j);
            }
        }
    }

    public void initialOut() {
        for (int i = 0; i < xSizeField; i++) {
            for (int j = 0; j < ySizeField; j++) {
                System.out.print(" " + cell[j][i].symbol);
            }
            System.out.println();
        }
    }
}
