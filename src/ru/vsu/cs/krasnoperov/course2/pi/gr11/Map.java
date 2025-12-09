package ru.vsu.cs.krasnoperov.course2.pi.gr11;

public class Map {

    int W = 10;
    int H = 10;

    char[][] cells;

    public Map() {
        cells = new char[W][H];

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                cells[x][y] = '*';
            }
        }
    }

    public void draw() {

        int coordX = 1;
        int coordY = 1;

        System.out.print(' ');
        System.out.print(' ');

        for (int x = 0; x < W; x++) {
            System.out.print(coordX++);
            System.out.print(' ');
        }
        System.out.println();

        for (int y = 0; y < H; y++) {
            System.out.print(coordY++);
            if (y < 9) {
                System.out.print(' ');
                }
            for (int x = 0; x < W; x++) {
                System.out.print(cells[x][y]);
                System.out.print(' ');
                if (x > 9){
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
