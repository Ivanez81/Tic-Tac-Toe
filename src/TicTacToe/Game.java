package TicTacToe;

import java.util.Random;

public class Game {
    public static Random rnd = new Random();
    public static char[][] map;
    public static int size = 3;
    public static final int DOTS_TO_WIN = 3;
    public static char human_selection = 'X';
    public static char ai_selection = 'O';
    public static int score, result;

    public static void printMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + "-");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isMapFull(char[][] checkMap) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (checkMap[i][j] == '\u0000') return false;
            }
        }
        return true;
    }

    public static void aiTurn(char ai_selection) {
        int x, y;
        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!isCellValid(x, y, map));
        System.out.println("Компьютер сделал свой ход в точку " + (y + 1) + " " + (x + 1));
        map[x][y] = ai_selection;
//        minMax(map, ai_selection);
    }

//    public static int checkScore(char[][] newMap, char turn) {
//        if (checkWin(newMap, human_selection)) {
//            return score = -10;
//        }
//        else if (checkWin(newMap, ai_selection)) {
//            return score = 10;
//        }
//        else if (isMapFull(newMap)) {
//            return score = 0;
//        }
//        return score = 0;
//    }
//
//    public static void minMax(char[][] newMap, char turn) {
//        if (isMapFull(newMap)) {
//            System.out.println(score);
//            return;
//        }
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if(isCellValid(i, j, newMap)) {
//                    checkScore(newMap,turn);
//                    if(turn == ai_selection) {
//                        minMax(newMap, human_selection);
//                    } else {
//                        minMax(newMap, ai_selection);
//                    }
//                }
//            }
//
//        }
//    }

    public static boolean isCellValid (int x, int y, char[][] checkMap) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        if (checkMap[x][y] == '\u0000') return true;
        return false;
    }

    // Проверка победы
    public static boolean checkWin(char[][] checkMap, char symb) {
        if (checkLine(checkMap, symb,0, 0, 0)) return true;
        if (checkColumn(checkMap, symb,0,0,0)) return true;
        for (int i = 0; i <= size-DOTS_TO_WIN; i++) {
            for (int j = 0; j <= size-DOTS_TO_WIN; j++) {
                if (checkMainDiag(checkMap, symb, 0, i, j)) return true;
            }
        }
        for (int i = 0; i <= size-DOTS_TO_WIN; i++) {
            for (int j = size-1; j >= size-(size-DOTS_TO_WIN)-1; j--) {
                if (checkSecDiag(checkMap, symb, 0, i, j)) return true;
            }
        }
        return false;
    }

    // проверка линий
    public static boolean checkLine(char[][] checkMap, char symb, int index, int move_i, int move_j) {
        if (index == DOTS_TO_WIN-1) {
            return true;
        } else {
            for (int i = move_i; i < size; i++) {
                for (int j = move_j; j < size-1; j++) {
                    if (checkMap[i][j] == symb && checkMap[i][j+1] == symb ) {
                        move_i = i;
                        move_j = j+1;
                        return checkLine(checkMap, symb, index+1, move_i, move_j);
                    }
                    index = 0;
                }
            }
            return false;
        }
    }

    // проверка вертикалей
    public static boolean checkColumn(char[][] checkMap, char symb, int index, int move_i, int move_j) {
        if (index == DOTS_TO_WIN-1) {
            return true;
        } else {
            for (int i = move_i; i < size-1; i++) {
                for (int j = move_j; j < size; j++) {
                    if (checkMap[i][j] == symb && checkMap[i+1][j] == symb ) {
                        move_i = i+1;
                        move_j = j;
                        return checkColumn(checkMap, symb, index+1, move_i, move_j);
                    }
                    index = 0;
                }
            }
            return false;
        }
    }

    // проверка Главной диагонали
    public static boolean checkMainDiag(char[][] checkMap, char symb, int index, int move_i, int move_j) {
        if (index == DOTS_TO_WIN-1) {
            return true;
        } else {
            for (int i = move_i; i < size-1; i++) {
                for (int j = move_j; j < size-1; j++) {
                    if (checkMap[i][j] == symb && checkMap[i+1][j+1] == symb) {
                        move_i = i+1;
                        move_j = j+1;
                        return checkMainDiag(checkMap, symb, index+1, move_i, move_j);
                    }
                    index = 0;
                }
            }
            return false;
        }
    }

    // Проверка Побочной диагонали
    public static boolean checkSecDiag(char[][] checkMap, char symb, int index, int move_i, int move_j) {
        if (index == DOTS_TO_WIN-1) {
            return true;
        } else {
            for (int i = move_i; i < size-1; i++) {
                for (int j = move_j; j > 0; j--) {
                    if (checkMap[i][j] == symb && checkMap[i+1][j-1] == symb ) {
                        move_i = i+1;
                        move_j = j-1;
                        return checkSecDiag(checkMap, symb,index+1, move_i, move_j);
                    }
                    index = 0;
                }
            }
            return false;
        }
    }

}
