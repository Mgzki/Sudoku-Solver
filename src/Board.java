public class Board {
    private final int[][] board; // 2D array of ints

    //Constructs the 9 x 9 grid with empty cells denoted by 0
    public Board() {
        board = new int[9][9];
        for (int row = 0; row < 9; row++)
            for (int col = 0; col < 9; col++)
                board[row][col] = 0;
    }
    //Convert an input board into a Board object
    public Board(int[][] inputBoard){
        board = inputBoard;
    }
    //Sets the players move in the selected cell
    public boolean set_Move(int[] move, int number) {
        if (cell_Free(move)) {
            board[move[0]][move[1]] = number;
            return true;
        } else {
            return false;
        }
    }

    public void reset_Move(int[]move){
        board[move[0]][move[1]] = 0;
    }

    //Gets the current value from a position on the board
    public int get_Move(int [] move){
        return board[move[0]][move[1]];
    }

    //Checks if the cell is free
    public boolean cell_Free(int[] move){
        return (board[move[0]][move[1]] == 0);
    }

    /*Checks if there are any available moves
     *Used for tie checking in Tic Tac Toe
     */
    public boolean full_Board(){
        int[] temp = new int[2];
        for (int i = 0; i < 3; i++){
            temp[0] = i;
            for (int j = 0; j < 3; j++){
                temp[1] = j;
                if(cell_Free(temp))
                    return false;
            }
        }return true;
    }

    //Displays the board state
    public void display(){
        // display the board
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                System.out.print(board[row][col]);
                if (col < board.length - 1)
                    System.out.print("|");
            }if (row < board.length - 1) {
                if (row % 3 == 2 && row != 0)
                    System.out.println("\n=====|=====|=====");// helps visually distinguish blocks of 3x3 cells
                else
                    System.out.println("\n-----|-----|-----");
            }else
                System.out.println();
        }System.out.println("#################");
    }

    //add actual checking for if all the digits are in the row
    public boolean check_Row(int row, char player){
        for (int col = 0; col < 9; col++){
            if (board[row][col] != player)
                return false; //cell didn't match
        }return true; // full row
    }

    //add actual checking for if all the digits are in the column
    public boolean check_Col(int col){
        for (int row = 0; row < 9; row++){
            if (board[row][col] == 0)
                return false; //cell didn't match
        }return true; // full col
    }

    public int[][] to2DArray() {
        int[][] copyOfBoard = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                copyOfBoard[i][j] = board[i][j];
        }return copyOfBoard;
    }
}

