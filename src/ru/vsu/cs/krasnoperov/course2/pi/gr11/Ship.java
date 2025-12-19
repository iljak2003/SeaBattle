package ru.vsu.cs.krasnoperov.course2.pi.gr11;

/*
Авианосец (Aircraft) - 'А', размер 5
Линкор (Battleship) - 'B', размер 4
Подводная лодка (Submarine) - 'S', размер 3
Эсминец (Destroyer) - 'D', размер 3
Мина (Mina) - 'M', размер 1
Крейсер (Cruiser) - 'C', размер 2
Катер (PowerBoat) - 'P', размер 1
 */

public class Ship {

    private int size;
    private char letter;
    private char direction;
    private Coordinate coord;

    public Ship(char letter, char dir, Coordinate coord){
        switch (letter){
            case 'A':
                size = 5;
                break;
            case 'B':
                size = 4;
                break;
            case 'S':
                size = 3;
                break;
            case 'D':
                size = 3;
                break;
            case 'M':
                size = 1;
                break;
            case 'C':
                size = 2;
                break;
            case 'P':
                size = 1;
                break;
        }
    }

    public  int getSize(){
        return size;
    }

    public char getLetter(){
        return letter;
    }

    public char getDirection(){
        return direction;
    }

    public Coordinate getCoordinate(){
        return coord;
    }
}
