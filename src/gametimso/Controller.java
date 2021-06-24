/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametimso;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mypc
 */
public final class Controller {

    private int row, col;
    private int[][] matrix;

    public Controller(int row, int col) {
        this.row = row;
        this.col = col;
        System.out.println(row + " " + col);
        createMatrix();
        showMatrix();
    }

    public void createMatrix() {
        matrix = new int[row][col];
        Random rand = new Random();
        int numCount = row * col;
        int max = 1;
        int arr[] = new int[numCount + 1];
        for (int i = 0; i < numCount + 1; i++) {
            arr[i] = 0;
        }
        ArrayList<Point> listPoint = new ArrayList<Point>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                listPoint.add(new Point(i, j));
            }
        }
        int i = 0;
        do {
            int index = rand.nextInt(numCount) + 1;
            if (arr[index] < max) {
                arr[index]++;
                int size = listPoint.size();
                int pointIndex = rand.nextInt(size);
                matrix[listPoint.get(pointIndex).x][listPoint.get(pointIndex).y] = index;
                listPoint.remove(pointIndex);
                i++;
            }
        } while (i < row * col);
    }

    public void showMatrix() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }
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

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

}
