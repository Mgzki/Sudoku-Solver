import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sudoku {
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
    public static boolean hasRepeats(List<Integer> collection){
        for (int index = 1; index <= 9; index++) {
            if (Collections.frequency(collection, index) > 1)
                return true;
        }
        return false;
    }
    /*
     * Checks if any sudoku conditions have been broken (
     */
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
     */
    public static boolean sudokuCompleted(Board game){
        if (sudokuInvalid(game)) return false;
        if (game.full_Board()) return false;
        return true;
    }

    public static void main(String[] args){
        Board game = new Board();
        game.display();
        /*
        //filled column testing
        game.set_Move(new int[] {0,0}, 1);
        game.set_Move(new int[] {1,0}, 1);
        game.set_Move(new int[] {2,0}, 3);
        game.set_Move(new int[] {3,0}, 4);
        game.set_Move(new int[] {4,0}, 5);
        game.set_Move(new int[] {5,0}, 6);
        game.set_Move(new int[] {6,0}, 7);
        game.set_Move(new int[] {7,0}, 8);
        game.set_Move(new int[] {8,0}, 9);

        //filled row testing
        game.set_Move(new int[] {0,1}, 2);
        game.set_Move(new int[] {0,2}, 2);
        game.set_Move(new int[] {0,3}, 4);
        game.set_Move(new int[] {0,4}, 5);
        game.set_Move(new int[] {0,5}, 6);
        game.set_Move(new int[] {0,6}, 7);
        game.set_Move(new int[] {0,7}, 8);
        game.set_Move(new int[] {0,8}, 9);
         //*/
        //game.display();
        System.out.print(sudokuInvalid(game));
    }
}
