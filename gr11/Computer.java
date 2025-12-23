package ru.vsu.cs.krasnoperov.course2.pi.gr11;

import java.util.ArrayList;
import java.util.Random;

public final class Computer {
    private final int size;
    private Boards brds;

    private final Random rnd = new Random();

    public Computer(int size) {
        this.size = size;
        this.brds = new Boards(size);
    }

    public void compSetUp() {
        ArrayList<String> fleet = Ship.sizeOfFleet(size);

        for (String letter : fleet){
            String direction = randomDirection(letter);
            Coordinate crd = randomCoordinate();
            Ship ship = new Ship(letter, direction, crd);

            while (!brds.isValidLocation(crd, ship)) {
                direction = randomDirection(letter);
                crd = randomCoordinate();
                ship = new Ship(letter, direction, crd);
            }
            brds.placeShip(crd, ship);
        }
        System.out.println("Computer fleet placed");
    }

    public Coordinate randomValidAttack(){
        Coordinate c = randomCoordinate();
        while (!brds.isValidAttack(c)){
            c = randomCoordinate();
        }
        return c;
    }

    public String attackPlayer(Player p, Coordinate attack) {
        String res = brds.attackOpponent(attack, p.getBoardA());
        printAttackResult(res);
        return res;
    }

    public boolean verifyCompW(Player p){

        return !Boards.hasRemainingShips(p.getBoardA());
    }

    private String randomDirection(String letter) {
        if (Ship.sizeByLetter(letter) <= 1) return "r";

        int dirNum = rnd.nextInt(4); // 0..3
        if (dirNum == 0) return "u";
        if (dirNum == 1) return "d";
        if (dirNum == 2) return "r";
        return "l";
    }

    private Coordinate randomCoordinate() {
        int randX = rnd.nextInt(size);
        int randY = rnd.nextInt(size);
        return new Coordinate(randX, randY);
    }

    private void printAttackResult(String res){
        if ("H".equals(res)) System.out.println("Computer: hit!");
        else if ("M".equals(res)) System.out.println("Computer hit a mine! BOOM 3x3 on computer board.");
        else System.out.println("Computer: miss.");
    }

    public String[][] getBoardA() {
        return brds.getBoardA();
    }
}
