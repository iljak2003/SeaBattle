package ru.vsu.cs.krasnoperov.course2.pi.gr11;

public class Player {
    private final Boards brds;

    public Player(int size) {
        this.brds = new Boards(size);
    }

    public boolean tryPlace(String letter, String dir, int x, int y) {
        Coordinate crd = new Coordinate(x, y);
        Ship ship = new Ship(letter, dir, crd);
        if (!brds.isValidLocation(crd, ship)) return false;
        brds.placeShip(crd, ship);
        return true;
    }

    public boolean isValidAttack(Coordinate attack) {
        return brds.isValidAttack(attack);
    }

    public void attackComp(Computer comp, Coordinate attack) {
        String res = brds.attackOpponent(attack, comp.getBoardA());
        printAttackResult(res);
    }

    public boolean verifyPlayerW(Computer comp) {
        return !Boards.hasRemainingShips(comp.getBoardA());
    }

    public String[][] getBoardA() {
        return brds.getBoardA();
    }

    public void printOwn() {
        brds.printBoardA();
    }

    public void printShots() {
        brds.printBoardB();
    }

    private void printAttackResult(String res) {
        if ("H".equals(res)) {
            System.out.println("Hit!");
        } else if ("M".equals(res)) {
            System.out.println("You hit a mine! BOOM 3x3 on your board.");
        } else {
            System.out.println("Miss.");
        }
    }
}
