package ru.vsu.cs.krasnoperov.course2.pi.gr11;

public class Boards {

    private int sizeOfSeaBattle;

    private String[][] boardA = new String[sizeOfSeaBattle][sizeOfSeaBattle];
    private String[][] boardB = new String[sizeOfSeaBattle][sizeOfSeaBattle];

    private int numPowerBoatPlay = 0;
    private int numCruiserPlay = 0;
    private int numMinaPlay = 0;
    private int numDestroyerPlay = 0;
    private int numSubmarinePlay = 0;
    private int numBattleshipPlay = 0;
    private int numAircraftPlay = 0;

    private int numPowerBoatComp = 0;
    private int numCruiserComp = 0;
    private int numMinaComp = 0;
    private int numDestroyerComp = 0;
    private int numSubmarineComp = 0;
    private int numBattleshipComp = 0;
    private int numAircraftComp = 0;

    public Boards(int size) {
        sizeOfSeaBattle = size + 1;

        boardA = new String[sizeOfSeaBattle][sizeOfSeaBattle];
        boardB = new String[sizeOfSeaBattle][sizeOfSeaBattle];
        initBoardA();
        initBoardB();
    }

    public void initBoardA() {
        boardA[0][0] = " ";
        for (int i = 1; i <= sizeOfSeaBattle; i++){
            boardA[i][0] = Integer.toString(i);;
        }

        for (int i = 1; i <= sizeOfSeaBattle; i++){
            boardA[0][i] = Integer.toString(i);;
        }

        for (int i = 0; i < sizeOfSeaBattle; i++) {
            for (int j = 0; i < sizeOfSeaBattle; j++) {
                boardA[i][j] = "*";
            }
        }
    }

    public void initBoardB() {
        boardA[0][0] = " ";
        for (int i = 1; i <= sizeOfSeaBattle; i++){
            boardB[i][0] = Integer.toString(i);;
        }

        for (int i = 1; i <= sizeOfSeaBattle; i++){
            boardB[0][i] = Integer.toString(i);;
        }

        for (int i = 1; i < sizeOfSeaBattle; i++) {
            for (int j = 1; i < sizeOfSeaBattle; j++) {
                boardB[i][j] = "~";
            }
        }
    }
}
