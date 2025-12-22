package ru.vsu.cs.krasnoperov.course2.pi.gr11;

import java.util.ArrayList;

public class Ship {

    private int size;
    private final String letter;
    private final String direction;
    private final Coordinate coord;

    public Ship(String letter, String dir, Coordinate coord) {
        this.letter = letter;
        this.direction = dir;
        this.coord = coord;

        size = 1;
        if ("A".equals(letter)) {
            size = 5;
        } else if ("B".equals(letter)) {
            size = 4;
        } else if ("D".equals(letter) || "S".equals(letter)) {
            size = 3;
        } else if ("C".equals(letter)) {
            size = 2;
        }

    }

    public int getSize() {
        return size;
    }

    public String getLetter() {
        return letter;
    }

    public String getDirection() {
        return direction;
    }

    public Coordinate getCoordinate() {
        return coord;
    }

    public static int sizeByLetter(String letter) {
        if ("A".equals(letter)) {
            return 5;
        }
        if ("B".equals(letter)) {
            return 4;
        }
        if ("D".equals(letter) || "S".equals(letter)) {
            return 3;
        }
        if ("C".equals(letter)) {
            return 2;
        }
        return 1;
    }


    public static boolean isAdjacencyExempt(String letter) {
        return "M".equals(letter) || "S".equals(letter);
    }

    public static ArrayList<String> sizeOfFleet(int boardSize) {
        double k = boardSize / 10.0;

        int A = (int) Math.round(1 * k);
        int B = (int) Math.round(1 * k);
        int D = (int) Math.round(2 * k);
        int S = (int) Math.round(2 * k);
        int C = (int) Math.round(3 * k);
        int P = (int) Math.round(4 * k);
        int M = (int) Math.round(2 * k);


        if (sizeByLetter("A") > boardSize) A = 0;
        if (sizeByLetter("B") > boardSize) B = 0;
        if (sizeByLetter("D") > boardSize) D = 0;
        if (sizeByLetter("S") > boardSize) S = 0;
        if (sizeByLetter("C") > boardSize) C = 0;


        ArrayList<String> list = new ArrayList<>();
        add(list, "A", A);
        add(list, "B", B);
        add(list, "D", D);
        add(list, "S", S);
        add(list, "C", C);
        add(list, "P", P);
        add(list, "M", M);

        return list;
    }

    private static void add(ArrayList<String> list, String letter, int count) {
        for (int i = 0; i < count; i++) {
            list.add(letter);
        }
    }
}
