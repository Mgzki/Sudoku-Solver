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

    public static boolean hasRepeats(List<Integer> collection){
        for (int index = 1; index <= 9; index++) {
            //System.out.print(Collections.frequency(collection, index));
            if (Collections.frequency(collection, index) > 1)
                return true;
        }
        System.out.println();
        return false;
    }

    public static void main(String[] args){
        Board game = new Board();
        game.display();
        ///*
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

        columnsInvalid(game);
        rowsInvalid(game);
    }
}
