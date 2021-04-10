package com.teko.codingtest.models;

import javafx.util.Pair;

import java.util.ArrayList;

public class Cinema {
    private int rows;
    private int columns;
    private int min_distance;
    private int[][] seats;
    private int countParties;
    public Cinema () {
        rows = 0;
        columns = 0;
        min_distance = 0;
        seats = new int[rows][columns];
        countParties = 1;
    }

    public Cinema(int rows, int columns, int min_distance) {
        this.columns = columns;
        this.rows = rows;
        this.min_distance = min_distance;
        this.seats = new int[rows][columns];
        countParties = 1;
    }

    /*
        Hàm đánh dấu các vị trí bị loại bỏ khi lựa chọn vị trí (x, y)
     */
    public void tick(int x, int y) {
        x--;
        y--;
        int n = min_distance * 2 + 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                if (j - 1 >= n / 2 - i + 1 && i + n / 2 >= j && i <= j + n / 2 && j - n / 2 <= n - i + 1) {
                    int tmpx = x-min_distance + i;
                    int tmpy = y-min_distance+j;
                    if (tmpx >= 0 && tmpx < rows & tmpy >= 0 & tmpy < columns && seats[tmpx][tmpy] != countParties)
                        seats[tmpx][tmpy]=-1;
                }

        }
        seats[x+1][y+1] = countParties;
    }

    /*
    * TODO
    * Hàm lựa chọn các vị trí cho 1 nhóm
    * */
    public void chooseSlot(ArrayList<Pair<Integer, Integer>> l) {

    }

    /*
    * Hàm lấy danh sách các vị trí có thể chọn
    * Kết quả trả về dạng key-value tương ứng với vị trí (key, value) có thể lựa chọn
    * */
    public ArrayList<Pair<Integer, Integer>> getAvailableSlot() {
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seats[i][j] == 0) {
                    res.add(new Pair<>(i, j));
                }
            }
        }
        return res;
    }

    /*
    * Hàm kiếm tra 1 trạng thái của 1 vị trí có thể được chọn hay không
    * */
    public int check(int x, int y) {
        if (seats[x][y] > 0 )
            return 1; // vi tri (x, y) da co nguoi dat
        else if (seats[x][y] < 0)
            return -1; // vi tri (x, y) vi pham khoang cach
        return 0; // vi tri (x, y) ok
    }

    public void displayMahattan() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (seats[i][j] >= 0)
                    System.out.print(seats[i][j] + " \t");
                else System.out.print("x \t");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getMin_distance() {
        return min_distance;
    }

    public void setMin_distance(int min_distance) {
        this.min_distance = min_distance;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }

    public int getCountParties() {
        return countParties;
    }

    public void setCountParties(int countParties) {
        this.countParties = countParties;
    }
}
