//Current thoughts:
//Exhaustive search: start all queens at same square, then move them through each of their 64 possibilities on the board, making 64 + 64^2 + 64^3 ... + 64^8 boards (?) to test, checking each until one with 0 conflicts is found.
//Hill-climb: Randomly pick where to put one queen per column. Starting in left column, move queen to lowest-conflict spot, then move right one column, moving its queen to the lowest-conflict spot. Once all columns have been combed through, this should solve the problem. If not, then I will have to double back to the first column after doing the next column until changing the next column does not change the first, then work based off the seconed and so on.

import java.util.Random;

class SolutionFinder {
  //Start each queen at the same square and iterate through each possible configuration of the 8 queens on the board until a solution is found, then print the solution and return.
  public static void exhaustiveSearch() {
    Board b = new Board(new int[][] { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } });
    System.out.println(b);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 64; i++) {
      b.moveQueen(0, new int[] { i / 8 + 1, i % 8 + 1 });
      for (int j = 0; j < 64; j++) {
        b.moveQueen(1, new int[] { j / 8 + 1, j % 8 + 1 });
        for (int k = 0; k < 64; k++) {
          b.moveQueen(2, new int[] { k / 8 + 1, k % 8 + 1 });
          for (int l = 0; l < 64; l++) {
            b.moveQueen(3, new int[] { l / 8 + 1, l % 8 + 1 });
            for (int m = 0; m < 64; m++) {
              b.moveQueen(4, new int[] { m / 8 + 1, m % 8 + 1 });
              for (int n = 0; n < 64; n++) {
                b.moveQueen(5, new int[] { n / 8 + 1, n % 8 + 1 });
                for (int o = 0; o < 64; o++) {
                  b.moveQueen(6, new int[] { o / 8 + 1, o % 8 + 1 });
                  for (int p = 0; p < 64; p++) {
                    b.moveQueen(7, new int[] { p / 8 + 1, p % 8 + 1 });
                    if (b.numBoardConflicts() == 0) {
                      System.out.println(b);
                      System.out.println("Min elapsed: " + ((System.currentTimeMillis() - start) / 60000));
                      return;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  //Find the vertical queen move that will minimize the number of board conflicts, continue until the conflicts are 0 (the print ther board and return), or until the number of conflicts no longer decreases. If the conflicts no longer decrease, randomly generate a new board and repeat.
  public static void hillClimb() {
    Random r = new Random();
    Board b;
    do {
      b = new Board(
          new int[][] { { r.nextInt(1, 9), 1 }, { r.nextInt(1, 9), 2 }, { r.nextInt(1, 9), 3 }, { r.nextInt(1, 9), 4 },
              { r.nextInt(1, 9), 5 }, { r.nextInt(1, 9), 6 }, { r.nextInt(1, 9), 7 }, { r.nextInt(1, 9), 8 } });
      int lastMinConf = Integer.MAX_VALUE;
      while (true) {
        int minC = 1;
        int minR = 1;
        int minQLoc = 0;
        int queenLocsI = 0;
        int minConfs = b.numBoardConflictsAfterMove(queenLocsI, new int[] { minR, minC });
        for (int col = 1; col < 9; col++) {
          queenLocsI = col - 1;
          for (int row = 2; row < 9; row++) {
            int confs = b.numBoardConflictsAfterMove(queenLocsI, new int[] { row, col });
            if (confs <= minConfs) {
              if (confs == minConfs) {
                if (r.nextDouble() < .5) {
                  continue;
                }
              }
              minConfs = confs;
              minR = row;
              minC = col;
              minQLoc = queenLocsI;
            }
          }
        }
        if (minConfs >= lastMinConf) {
          break;
        }
        lastMinConf = minConfs;
        b.moveQueen(minQLoc, new int[] { minR, minC });
      }
    } while (b.numBoardConflicts() != 0);

    //Can comment out the below line for testing run time
    System.out.println(b);
  }
}
