import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sudoku {
    /*
     * Block of alternate sudoku condition checking methods.

    public static boolean rowsInvalid(Board game){
        for (int i = 0; i < 9; i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                row.add(game.to2DArray()[i][j]);
            } if (hasRepeats(row)) return true;
        } return false;
    }

    public static boolean columnsInvalid(Board game){
        for (int i = 0; i < 9; i++){
            List<Integer> col = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                col.add(game.to2DArray()[j][i]);
            } if (hasRepeats(col)) return true;
        } return false;
    }

    public static boolean squareInvalid(Board game, int x, int y){
        int xEnd = x + 3;
        int yEnd = y + 3;
        List<Integer> square = new ArrayList<>();
        for (int i = x; i < xEnd; i++) {
            for (int j = y; j < yEnd; j++)
                square.add(game.to2DArray()[i][j]);
        } if (hasRepeats(square)) return true;
        return false;
    }

    public static boolean rowSquareInvalid(Board game, int row){
        switch (row){
            case 0: //first row of squares
                if (squareInvalid(game,0,0)) return true;
                if (squareInvalid(game,0,3)) return true;
                if (squareInvalid(game,0,6)) return true;
            case 1: //second row of squares
                if (squareInvalid(game,3,0)) return true;
                if (squareInvalid(game,3,3)) return true;
                if (squareInvalid(game,3,6)) return true;
            case 2: //third row of squares
                if (squareInvalid(game,6,0)) return true;
                if (squareInvalid(game,6,3)) return true;
                if (squareInvalid(game,6,6)) return true;
            default: return false;
        }
    }

    public static boolean allSquareInvalid(Board game){
        if (rowSquareInvalid(game, 0)) return true; // first row of squares
        if (rowSquareInvalid(game, 1)) return true; // second row of squares
        if (rowSquareInvalid(game, 2)) return true; // third row of squares
        return false;
    }
    // Checks for repeats within the inputted array of 9 values (row, col, or square)
    public static boolean hasRepeats(List<Integer> collection){
        for (int index = 1; index <= 9; index++) {
            if (Collections.frequency(collection, index) > 1)
                return true;
        }
        return false;
    }

    // Checks if any sudoku conditions have been broken
    public static boolean sudokuInvalid(Board game){
        if (rowsInvalid(game)) return true;
        if (columnsInvalid(game)) return true;
        if (allSquareInvalid(game)) return true;
        else return false;
    }

    /*
     * If either a sudoku condition is broken(invalid) or there are no free moves
     * then the game is incomplete. Once all sudoku conditions are met and there
     * are no free moves, the sudoku is complete.
     *\/

    public static boolean sudokuCompleted(Board game){
        if (sudokuInvalid(game)) return false;
        if (game.full_Board()) return false;
        return true;
    }
    */

    // Returns position of first empty cell if one is found, otherwise return invalid move
    public static int[] findEmpty(Board game){
        int[] move = new int[2];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                move[0] = i;
                move[1] = j;
                if (game.cell_Free(move))
                    return move;
            }
        }return new int[] {-1,-1};
    }

    public static boolean valid(Board game, int num, int[] pos){
        int xEnd = pos[0] / 3;
        int yEnd = pos[1] / 3;
        //Check rows
        for (int i = 0; i < 9; i++){
            if (game.to2DArray()[pos[0]][i] == num && pos[0] != i)
                return false;
        }
        //Check columns
        for (int i = 0; i < 9; i++){
            if (game.to2DArray()[i][pos[1]] == num && pos[1] != i)
                return false;
        }
        //Check Squares
        for (int i = xEnd*3; i < xEnd*3+3; i++) {
            for (int j = yEnd*3; j < yEnd*3+3; j++) {
                if (game.to2DArray()[i][j] == num && !(pos[0] == i) && !(pos[1] == j))
                    return false;
            }
        }return true;
    }
    /*
     * Backtracking algorithm that solves the current board by finding available moves and recursively
     * checking for the validity of that move. If that set of moves does not result in a solved board
     * backtrack(reset the move) and try again.
     */
    public static boolean solve(Board game){
        int[] find = findEmpty(game);
        int[] move;
        if (find[0] == -1)
            return true;
        else {
            move = find;
        }
        for (int i = 1; i < 10; i++){
            if (valid(game,i,move)){
                game.set_Move(move,i);
                if (solve(game)) {
                    return true;
                }game.reset_Move(move);
            }
        }return false;
    }

    public static void main(String[] args){
        int[][] inputBoard =
                {      //1 2 3 4 5 6 7 8 9
                        {0,7,4,0,0,0,6,0,0}, //1
                        {2,8,0,0,0,6,0,7,0}, //2
                        {0,0,0,0,1,0,2,0,4}, //3
                        {5,0,0,3,0,9,0,0,0}, //4
                        {8,0,7,0,0,0,3,0,5}, //5
                        {0,0,0,8,0,5,0,0,6}, //6
                        {4,0,8,0,2,0,0,0,0}, //7
                        {0,5,0,9,0,0,0,6,3}, //8
                        {0,0,9,0,0,0,1,8,0}, //9
                };
        Board game = new Board();
        //If the input board is of valid size, use that to solve. Otherwise use an empty board
        if (inputBoard.length == 9) {
            game = new Board(inputBoard);
        }
        
        game.display(); //Displays the board prior to being solved
        solve(game);
        game.display(); //Displays the board after being solved
    }
}
