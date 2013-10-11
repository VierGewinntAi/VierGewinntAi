/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viergewinntai;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rusinda
 */
public class ArtificialIntelligence {

    private final int ALPHABETADEPTH = 3;
    private int[][] field;
    private int[][] processingField;
    private int heuristicVals[][] = {{3, 4, 5, 5, 4, 3}, {4, 6, 8, 8, 6, 4}, {5, 8, 11, 11, 8, 5}, {7, 10, 13, 13, 10, 7}, {5, 8, 11, 11, 8, 5}, {4, 6, 8, 8, 6, 4}, {3, 4, 5, 5, 4, 3}};

    public void startAi() {

//        randomMove();
        startAlphaBetaMove();
    }

    private void startAlphaBetaMove() {
        field = VierGewinntAi.mainGameEngine.getPlayingField();
        processingField = VierGewinntAi.cloneArray((field));

        AlphaBetaTree alphaBetaTree = new AlphaBetaTree(field, ALPHABETADEPTH, heuristicVals);




    }

    private int max(LinkedList<AlphaBetaTree.Node> nodes) {
        int returnVal = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).value > returnVal) {
                returnVal = nodes.get(i).value;
            }
        }

        return returnVal;
    }

    private int min(LinkedList<AlphaBetaTree.Node> nodes) {
        int returnVal = 0;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).value < returnVal) {
                returnVal = nodes.get(i).value;
            }
        }

        return returnVal;
    }

    private void randomMove() {
        int i;
        Random r = new Random();
        while (!VierGewinntAi.mainGameEngine.checkMove((i = r.nextInt(7))));
        VierGewinntAi.mainGameEngine.tryMove(i);

    }

    public int evaluate(int[][] field, int player) {

        int[][] evals = {{0, 0}, {0, 0}, {0, 0}, {0, 0}};

        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 6; row++) {
                if (field[column][row] == 0) {
                    break;
                }

                int currColumn;
                int currRow;

                /*
                 * #####################################
                 * ############# 1 out of 4 ############
                 * #####################################
                 */
                {
                    //up
                    if (row < 3 && field[column][row + 1] == 0) {
                        evals[0][field[column][row] - 1]++;
                        System.out.println(column + " " + row + " 1o4 up");
                    }
                    //left
                    if (column > 2) {
                        currColumn = column - 1;
                        currRow = row;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn--;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 left");
                        }
                    }
                    //right
                    if (column < 4) {
                        currColumn = column + 1;
                        currRow = row;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn++;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 right");
                        }
                    }
                    //up-left
                    if (column > 2 && row < 3) {
                        currColumn = column - 1;
                        currRow = row + 1;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn--;
                            currRow++;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 up-left");
                        }
                    }
                    //up-right
                    if (column < 4 && row < 3) {
                        currColumn = column + 1;
                        currRow = row + 1;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn++;
                            currRow++;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 up-right");
                        }
                    }
                    //down-left
                    if (column > 2 && row > 2) {
                        currColumn = column - 1;
                        currRow = row + 1;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn--;
                            currRow--;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 up-left");
                        }
                    }
                    //down-right
                    if (column < 4 && row > 2) {
                        currColumn = column + 1;
                        currRow = row + 1;
                        int i = 0;
                        while (i < 3 && field[currColumn][currRow] == 0) {
                            i++;
                            currColumn++;
                            currRow--;
                        }
                        if (i == 3) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 up-right");
                        }
                    }
                    //00x0
                    if (column > 1 && column < 6) {
                        if (field[column - 2][row] == 0 && field[column - 1][row] == 0 && field[column + 1][row] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 00x0");
                        }
                    }
                    //0x00
                    if (column > 0 && column < 5) {
                        if (field[column - 1][row] == 0 && field[column + 1][row] == 0 && field[column + 2][row] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 0x00");
                        }
                    }
                    //00x0 diagonal left up
                    if (column > 1 && column < 6 && row > 0 && row < 4) {
                        if (field[column - 2][row + 2] == 0 && field[column - 1][row + 1] == 0 && field[column + 1][row - 1] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 00x0 diagleftup");
                        }
                    }
                    //0x00 diagonal left up
                    if (column > 0 && column < 5 && row > 1 && row < 5) {
                        if (field[column - 1][row + 1] == 0 && field[column + 1][row - 1] == 0 && field[column + 2][row - 2] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 0x00diagleftup");
                        }
                    }
                    //00x0 diagonal right up
                    if (column > 1 && column < 6 && row > 1 && row < 5) {
                        if (field[column - 2][row - 2] == 0 && field[column - 1][row - 1] == 0 && field[column + 1][row + 1] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 00x0diagrightup");
                        }
                    }
                    //0x00 diagonal right up
                    if (column > 0 && column < 5 && row > 0 && row < 4) {
                        if (field[column - 1][row - 1] == 0 && field[column + 1][row + 1] == 0 && field[column + 2][row + 2] == 0) {
                            evals[0][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 1o4 0x00diagrightup");
                        }
                    }
                }

                /*
                 * #####################################
                 * ############# 2 out of 4 ############
                 * #####################################
                 */
                {
                    //up
                    if (row < 3 && field[column][row + 1] == field[column][row] && field[column][row + 2] == 0) {
                        evals[1][field[column][row] - 1]++;
                        System.out.println(column + " " + row + " 2o4 up");
                    }

                    //left
                    if (column > 2) {
                        currColumn = column - 1;
                        currRow = row;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 1 && i >= 2){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 left");
                        }
                    }
                    //right
                    if(column < 4){
                        currColumn = column + 1;
                        currRow = row;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && currColumn - column !=3) {
                                    addPiece++;
                                    currColumn++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if (i == 2 && addPiece == 1) {
                            evals[1][field[column][row] - 1]++;
                            System.out.println(column + " " + row + " 2o4 right");
                        }
                    }
                    //0xx0
                    if(column > 0 && column<6){
                        if(field[column+1][row] == field[column][row] && field[column-1][row] == 0 && field[column+2][row] == 0){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 0xx0");
                        }
                    }
                    //up-left
                    if(column > 2 && row < 3){
                        currColumn = column - 1;
                        currRow = row + 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && currColumn>=0 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                                currRow++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn--;
                                    currRow++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 1 && i >= 2){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 up-left");
                        }
                    }
                    //up-right
                    if(column < 4 && row < 3){
                        currColumn = column + 1;
                        currRow = row + 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && currColumn>=0 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                                currRow++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn++;
                                    currRow++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 1 && i >= 2){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 up-left");
                        }
                    }
                    //down-left
                    if(column > 2 && row > 2){
                        currColumn = column - 1;
                        currRow = row - 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && currColumn>=0 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                                currRow--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && column - currColumn != 3) {
                                    addPiece++;
                                    currColumn--;
                                    currRow--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 1 && i >= 2){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 down-left");
                        }
                    }
                    //down-right
                    if(column < 4 && row > 2){
                        currColumn = column + 1;
                        currRow = row - 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 2 && addPiece < 2 && currColumn>=0 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                                currRow--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && currColumn - column != 3) {
                                    addPiece++;
                                    currColumn++;
                                    currRow--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 1 && i >= 2){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 down-left");
                        }
                    }
                    //0xx0 diagonal left up
                    if(column > 0 && column < 6 && row > 0 && row < 5){
                        if(field[column -1][row+1] == field[column][row] && field[column-2][row+2] == 0 && field[column+1][row-1] == 0){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 0xx0 diagonalleftup");
                        }
                    }
                    //0xx0 diagonal right up
                    if(column > 0 && column < 6 && row > 0 && row < 5){
                        if(field[column +1][row+1] == field[column][row] && field[column+2][row+2] == 0 && field[column-1][row-1] == 0){
                            evals[1][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 2o4 0xx0 diagonalrightup");
                        }
                    }
                }
                
                /*
                 * #####################################
                 * ############# 3 out of 4 ############
                 * #####################################
                 */
                {
                    //up
                    if(row < 3){
                        if(field[column][row+1] == field[column][row] && field[column][row+2] == field[column][row] && field[column][row+3] == 0){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 3o4 up");
                        }
                    }
                    //left
                    if (column > 2) {
                        currColumn = column - 1;
                        currRow = row;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 3o4 left");
                        }
                    }
                    //right
                    if (column < 4) {
                        currColumn = column + 1;
                        currRow = row;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && currColumn - column != 3) {
                                    addPiece++;
                                    currColumn++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 3o4 left");
                        }
                    }
                    //up-left
                    if(column > 2 && row < 3){
                        currColumn = column - 1;
                        currRow = row + 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && currColumn>=0 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                                currRow++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn--;
                                    currRow++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 304 up-left");
                        }
                    }
                    //up-right
                    if(column < 4 && row < 3){
                        currColumn = column + 1;
                        currRow = row + 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && currColumn>=0 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                                currRow++;
                            } else {
                                if (field[currColumn][currRow] == field[column][row]) {
                                    addPiece++;
                                    currColumn++;
                                    currRow++;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 304 up-left");
                        }
                    }
                    //down-left
                    if(column > 2 && row > 2){
                        currColumn = column - 1;
                        currRow = row - 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && currColumn>=0 && column - currColumn < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn--;
                                currRow--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && column - currColumn != 3) {
                                    addPiece++;
                                    currColumn--;
                                    currRow--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 304 down-left");
                        }
                    }
                    //down-right
                    if(column < 4 && row > 2){
                        currColumn = column + 1;
                        currRow = row - 1;
                        int i = 0;
                        int addPiece = 0;
                        while (i <= 1 && addPiece < 3 && currColumn>=0 && currColumn - column < 4) {
                            if (field[currColumn][currRow] == 0) {
                                i++;
                                currColumn++;
                                currRow--;
                            } else {
                                if (field[currColumn][currRow] == field[column][row] && currColumn - column != 3) {
                                    addPiece++;
                                    currColumn++;
                                    currRow--;
                                } else {
                                    break;
                                }
                            }
                        }
                        if(addPiece == 2 && i >= 1){
                            evals[2][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 304 down-left");
                        }
                    }
                }
                
                /*
                 * #####################################
                 * ############# 4 out of 4 ############
                 * #####################################
                 */
                {
                    //up
                    if(row < 3){
                        int i = 0;
                        currRow = row+1;
                        currColumn = column;
                        while(field[currColumn][currRow] == field[column][row]){
                            i++;
                            currRow++;
                        }
                        if(i>=3){
                            evals[3][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 404 up");
                        }
                    }
                    
                    //up-right
                    if(row <3 && column < 4){
                        int i = 0;
                        currRow = row+1;
                        currColumn = column+1;
                        while(field[currColumn][currRow] == field[column][row]){
                            i++;
                            currRow++;
                            currColumn++;
                        }
                        if(i>=3){
                            evals[3][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 404 up-right");
                        }
                    }
                    
                    //right
                    if(column < 4){
                        int i = 0;
                        currRow = row;
                        currColumn = column+1;
                        while(field[currColumn][currRow] == field[column][row]){
                            i++;
                            currColumn++;
                        }
                        if(i>=3){
                            evals[3][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 404 right");
                        }
                    }
                    
                    //down-right
                    if(row <3 && column < 4){
                        int i = 0;
                        currRow = row+1;
                        currColumn = column-1;
                        while(field[currColumn][currRow] == field[column][row]){
                            i++;
                            currRow--;
                            currColumn++;
                        }
                        if(i>=3){
                            evals[3][field[column][row]-1]++;
                            System.out.println(column + " " + row + " 404 down-right");
                        }
                    }
                }

////                int currPlayer = field[column][row];
//                int amount;
//
//                int currColumn;
//                int currRow;
//
//                amount = 1;
//                currColumn = column;
//                currRow = row - 1;
//                while (currRow >= 0 && currRow < 6 && field[currColumn][currRow] == field[column][row]) {
//                    amount++;
//                    currRow--;
//                }
//                currColumn = column;
//                currRow = row + 1;
//                while (currRow >= 0 && currRow < 6 && field[currColumn][currRow] == field[column][row]) {
//                    amount++;
//                    currRow++;
//                }
//                if (amount >= 4) {
//                    evals[3][field[column][row] - 1]++;
//                } else {
//                    currRow = row;
//                    while (row < 6 && field[currColumn][currRow] == field[column][row]) {
//                        currRow++;
//                    }
//                    if(field[currColumn][currRow] !=0){
//                        break;
//                    } else {
//                        if ((6 - currRow + amount) >= 4){
//                            evals[amount-1][field[column][row]-1]++;
//                        }
//                                
//                    }
//                    
//                }
            }
        }

        for(int i= 0; i< 4; i++){
            System.out.println(Arrays.toString(evals[i]));
        }
        return 0;
    }
}
