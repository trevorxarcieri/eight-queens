class Board {
  private static final int BOARD_DIM = 8;
  private static final String ROW_NUM_GAP = "    ";
  private static final String SQUARE_GAP = "  ";
  private static final String NEW_ROW_GAP = "\n\n";
  private static final String COL_NUM_START = "\n ";

  //Array of row-column pairs that represent the position of each queen on the board, from row 8-1 and column 1-8.
  private int[][] queenLocs;

  public Board(int[][] queenLocs) {
    this.queenLocs = queenLocs;
  }

  public int numQueens() {
    return queenLocs.length;
  }

  public void moveQueen(int queenLocsI, int[] loc) {
    queenLocs[queenLocsI] = loc;
  }

  public int[] queenLoc(int queenLocsI) {
    return queenLocs[queenLocsI];
  }

  public boolean hasConflicts() {
    return numBoardConflicts() != 0;
  }

  public int numBoardConflicts() {
    int conflicts = 0;
    for(int i = 0; i < queenLocs.length; i++) {
      conflicts += numConflicts(i);
    }
    return conflicts;
  }
  
  public int numBoardConflictsAfterMove(int queenLocsI, int[] newLoc) {
    int[] oldLoc = queenLocs[queenLocsI];
    queenLocs[queenLocsI] = newLoc;
    int conflicts = numBoardConflicts();
    queenLocs[queenLocsI] = oldLoc;
    return conflicts;
  }

  private int numConflicts(int queenLocsI) {
    int conflicts = 0;
    int[] curLoc;
    int[] nextLoc;
    for(int i = queenLocsI; i < queenLocs.length - 1; i++) {
      curLoc = queenLocs[queenLocsI];
      nextLoc = queenLocs[i + 1];
      conflicts += numSquareConflicts(curLoc, nextLoc) + numRowConflicts(curLoc, nextLoc) + numColConflicts(curLoc, nextLoc) + numLeftDiagConflicts(curLoc, nextLoc) + numRightDiagConflicts(curLoc, nextLoc);
      // System.out.println(i + ":\nsquare: " + numSquareConflicts(curLoc, nextLoc) + "\nrow: " + numRowConflicts(curLoc, nextLoc) + "\ncol: " + numColConflicts(curLoc, nextLoc) + "\nld: "+ numLeftDiagConflicts(curLoc, nextLoc) + "\nrd: " + numRightDiagConflicts(curLoc, nextLoc));
    }
    return conflicts;
  }

  /*
  Each of the following num conflicts functions evaluates the number of conflicts
  between the current queen location and the next queen location,
  with a maximum number of 1 conflict and a minimum number of 0
  conflicts betwen the queens.
  */
  private int numSquareConflicts(int[] curLoc, int[] nextLoc) {
    if(curLoc[0] == nextLoc[0] && curLoc[1] == nextLoc[1]) {
      return 1;
    }
    return 0;
  }

  private int numRowConflicts(int[] curLoc, int[] nextLoc) {
    if(curLoc[0] == nextLoc[0] && curLoc[1] != nextLoc[1]) {
      return 1;
    }
    return 0;
  }
  
  private int numColConflicts(int[] curLoc, int[] nextLoc) {
    if(curLoc[0] != nextLoc[0] && curLoc[1] == nextLoc[1]) {
      return 1;
    }
    return 0;
  }
  
  private int numLeftDiagConflicts(int[] curLoc, int[] nextLoc) {
    if(curLoc[0] + curLoc[1] == nextLoc[0] + nextLoc[1] && numSquareConflicts(curLoc, nextLoc) != 1) {
      return 1;
    }
    return 0;
  }
  
  private int numRightDiagConflicts(int[] curLoc, int[] nextLoc) {
    if(curLoc[0] + 8 - curLoc[1] == nextLoc[0] + 8 - nextLoc[1] && numSquareConflicts(curLoc, nextLoc) != 1) {
      return 1;
    }
    return 0;
  }

  public String toString() {
    String boardString = "";
    int[][] nonPrintedQueens = queenLocs.clone();
    for(int row = BOARD_DIM; row >= 1; row--) {
      boardString += row + ROW_NUM_GAP;
      for(int col = 1; col <= BOARD_DIM; col++) {
          boolean blank = true;
          for(int i = 0; i < nonPrintedQueens.length; i++) {
            if(row == nonPrintedQueens[i][0] && col == nonPrintedQueens[i][1]) {
              blank = false;
              boardString += "Q" + SQUARE_GAP;
            }
          } 
          if(blank) {
            boardString += "_" + SQUARE_GAP;
          }
      }
      boardString += NEW_ROW_GAP;
    }
    boardString += COL_NUM_START + ROW_NUM_GAP;
    for(int col = 1; col <= BOARD_DIM; col++) {
      boardString += col + SQUARE_GAP;
    }
    return boardString;
  }
}