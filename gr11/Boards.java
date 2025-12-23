package ru.vsu.cs.krasnoperov.course2.pi.gr11;

import java.util.Objects;

public class Boards {

    private int size;
    private int sizeWithHdr;
    private String[][] boardA;
    private String[][] boardB;

    public Boards(int size) {
        this.size = size;
        this.sizeWithHdr = size + 1;
        boardA = new String[sizeWithHdr][sizeWithHdr];
        boardB = new String[sizeWithHdr][sizeWithHdr];
        init(boardA);
        init(boardB);
    }

    private void init(String[][] b) {
        b[0][0] = " ";
        for (int i = 1; i < sizeWithHdr; i++) {
            b[i][0] = Integer.toString(i - 1);
        }

        for (int i = 1; i < sizeWithHdr; i++) {
            b[0][i] = Integer.toString(i - 1);
        }

        for (int y = 1; y < sizeWithHdr; y++) {
            for (int x = 1; x < sizeWithHdr; x++) {
                b[y][x] = "~";
            }
        }
    }

    public String[][] getBoardA() {
        return boardA;
    }

    public boolean isValidAttack(Coordinate c) {
        int x = c.getX() + 1;
        int y = c.getY() + 1;
        if (!inside(x, y)) {
            return false;
        }
        return Objects.equals(boardB[y][x], "~");
    }

    private int[] dirToDelta(String dir) {
        int dx = 0;
        int dy = 0;

        if ("u".equals(dir)) {
            dy = -1;
        } else if ("d".equals(dir)) {
            dy = 1;
        } else if ("l".equals(dir)) {
            dx = -1;
        } else {
            dx = 1;
        }

        return new int[]{dx, dy};
    }


    public boolean isValidLocation(Coordinate start, Ship s) {
        int x = start.getX() + 1;
        int y = start.getY() + 1;

        if (!inside(x, y)) {
            return false;
        }

        int len = s.getSize();

        int[] delta = dirToDelta(s.getDirection());
        int dx = delta[0];
        int dy = delta[1];

        int endX = x + dx * (len - 1);
        int endY = y + dy * (len - 1);
        if (!inside(endX, endY)) {
            return false;
        }


        for (int i = 0; i < len; i++) {
            int cx = x + dx * i;
            int cy = y + dy * i;
            if (!Objects.equals(boardA[cy][cx], "~")) {
                return false;
            }
        }

        if (!Ship.isAdjacencyExempt(s.getLetter())) {
            for (int i = 0; i < len; i++) {
                int cx = x + dx * i;
                int cy = y + dy * i;
                if (hasRegularShipNear(cx, cy)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasRegularShipNear(int x, int y) {
        for (int yy = y - 1; yy <= y + 1; yy++) {
            for (int xx = x - 1; xx <= x + 1; xx++) {
                if (!inside(xx, yy)) {
                    continue;
                }
                if (isRegularShip(boardA[yy][xx])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRegularShip(String cell) {
        return Objects.equals(cell, "A")
                || Objects.equals(cell, "B")
                || Objects.equals(cell, "D")
                || Objects.equals(cell, "C")
                || Objects.equals(cell, "P");
    }

    public void placeShip(Coordinate start, Ship s) {
        int x = start.getX() + 1;
        int y = start.getY() + 1;

        int len = s.getSize();

        int[] delta = dirToDelta(s.getDirection());
        int dx = delta[0];
        int dy = delta[1];

        for (int i = 0; i < len; i++) {
            int cx = x + dx * i;
            int cy = y + dy * i;
            boardA[cy][cx] = s.getLetter();
        }
    }

    public String attackOpponent(Coordinate c, String[][] opponentBoardA) {
        int x = c.getX() + 1;
        int y = c.getY() + 1;

        String cell = opponentBoardA[y][x];

        if (Objects.equals(cell, "M")) {
            opponentBoardA[y][x] = "X";
            boardB[y][x] = "M";
            minaBoom(c);
            return "M";
        }

        if (!Objects.equals(cell, "~") && !Objects.equals(cell, "0") && !Objects.equals(cell, "X")) {
            opponentBoardA[y][x] = "X";
            boardB[y][x] = "H";
            return "H";
        }

        boardB[y][x] = "0";
        return "0";
    }


    private void minaBoom(Coordinate center) {
        int cx = center.getX() + 1;
        int cy = center.getY() + 1;

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int x = cx + dx;
                int y = cy + dy;
                if (!inside(x, y)) continue;

                String curr = boardA[y][x];
                if (Objects.equals(curr, "~")) boardA[y][x] = "0";
                else if (!Objects.equals(curr, "0") && !Objects.equals(curr, "X")) boardA[y][x] = "X";
            }
        }
    }

    private boolean inside(int x, int y) {
        return x >= 1 && y >= 1 && x < sizeWithHdr && y < sizeWithHdr;
    }

    public void printBoardA() {
        print(boardA);
    }

    public void printBoardB() {
        print(boardB);
    }

    private void print(String[][] b) {
        for (int y = 0; y < sizeWithHdr; y++) {
            for (int x = 0; x < sizeWithHdr; x++) {
                System.out.print(b[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static boolean hasRemainingShips(String[][] boardA) {
        for (int y = 1; y < boardA.length; y++) {
            for (int x = 1; x < boardA[y].length; x++) {
                String cell = boardA[y][x];
                if (Objects.equals(cell, "A")
                        || Objects.equals(cell, "B")
                        || Objects.equals(cell, "D")
                        || Objects.equals(cell, "S")
                        || Objects.equals(cell, "C")
                        || Objects.equals(cell, "P")) {
                    return true;
                }
            }
        }
        return false;
    }
}
