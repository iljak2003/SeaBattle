package ru.vsu.cs.krasnoperov.course2.pi.gr11;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter board size (>=2): ");
        int size = readInt(sc);
        while (size < 2) {
            System.out.print("Size must be >=2. Enter again: ");
            size = readInt(sc);
        }

        Player player = new Player(size);
        Computer computer = new Computer(size);

        System.out.println("Rule: regular ships must have 1-cell gap (including diagonals). Mines (M) and submarines (S) can be adjacent.\n");


        ArrayList<String> order = Ship.sizeOfFleet(size);
        player.printOwn();
        System.out.println();

        for (String letter : order) {
            placeOne(sc, player, letter, size);
            System.out.println();
            player.printOwn();
            System.out.println();
        }


        System.out.println("\nComputer is placing fleet...\n");
        computer.compSetUp();


        System.out.println("\n--- GAME START ---");

        while (true) {
            System.out.println("\nYour board:");
            player.printOwn();
            System.out.println("\nYour shots:");
            player.printShots();

            Coordinate attack = readAttack(sc, size);
            while (!player.isValidAttack(attack)) {
                System.out.println("Invalid attack (out of bounds or already tried). Try again.");
                attack = readAttack(sc, size);
            }

            player.attackComp(computer, attack);

            if (player.verifyPlayerW(computer)) {
                System.out.println("\nYOU WIN!");
                break;
            }

            Coordinate compAttack = computer.randomValidAttack();
            System.out.println("\nComputer attacks " + compAttack);
            computer.attackPlayer(player, compAttack);

            if (computer.verifyCompW(player)) {
                System.out.println("\nCOMPUTER WINS!");
                break;
            }

            System.out.println("\n-----------------------------");
        }

        System.out.println("\n--- FINAL ---");
        System.out.println("Player board:");
        player.printOwn();
        System.out.println("\nPlayer shots:");
        player.printShots();
        System.out.println("\nComputer board (for check):");
        printBoard(computer.getBoardA());
    }

    private static void placeOne(Scanner sc, Player player, String letter, int size) {
        int shipSize = Ship.sizeByLetter(letter);

        while (true) {
            System.out.println("Place " + letter + " (size " + shipSize + ")");
            String dir = "r";

            if (shipSize > 1) {
                System.out.print("Direction [u/d/l/r]: ");
                dir = sc.next().toLowerCase();
            }

            System.out.print("Start X (0.." + (size - 1) + "): ");
            int x = readInt(sc);
            System.out.print("Start Y (0.." + (size - 1) + "): ");
            int y = readInt(sc);

            if (player.tryPlace(letter, dir, x, y)) return;

            System.out.println("Incorrect location (overlap / out of bounds / too close). Try again.\n");
        }
    }

    private static Coordinate readAttack(Scanner sc, int size) {
        System.out.print("Attack X (0.." + (size - 1) + "): ");
        int x = readInt(sc);
        System.out.print("Attack Y (0.." + (size - 1) + "): ");
        int y = readInt(sc);
        return new Coordinate(x, y);
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Enter a number: ");
        }
        return sc.nextInt();
    }

    private static void printBoard(String[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                System.out.print(board[y][x] + " ");
            }
            System.out.println();
        }
    }
}
