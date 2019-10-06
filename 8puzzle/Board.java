/* *****************************************************************************
 *  Name: JMian
 *  Date: 21 September 2019
 *  Description: Board.java, Assignment4, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import java.util.ArrayList;

public final class Board {
    private final int n;
    private final int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                output.append(tiles[i][j] + " ");
            }
            output.append("\n");
        }
        return output.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0)
                    continue;
                if (tiles[i][j] != i*n +j+1)
                    hamDistance++;
            }
        }
        return hamDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manDistance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0)
                    continue;
                int goalRow = (tiles[i][j]-1) / n;
                int verticalDist = Math.abs(i - goalRow);
                int goalCol = (tiles[i][j]-1) % n;
                int horizontalDist = Math.abs(j - goalCol);
                manDistance += verticalDist + horizontalDist;
            }
        }
        return manDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y)
            return true;
        if (y == null)
            return false;
        if (y.getClass() == this.getClass()) {
            Board that = (Board) y;
            if (that.n == this.n) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (this.tiles[i][j] != that.tiles[i][j])
                            return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private int[] findBlank() {
        int[] blankLocation = new int[2];
        outerloop:   // "outerloop" is the label for the whole for-loop block
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    blankLocation[0] = i;
                    blankLocation[1] = j;
                    break outerloop;
                }
            }
        }
        return blankLocation;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int[] blankLocation = findBlank();
        int blankRow = blankLocation[0];
        int blankCol = blankLocation[1];
        if (blankRow > 0) {   // exchange tile with the upper row in the same column
            Board neightborUp = new Board(tiles);
            neightborUp.swap(blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(neightborUp);
        }
        if (blankRow < n-1) {  // exchange tile with the lower row in the same column
            Board neightborLo = new Board(tiles);
            neightborLo.swap(blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(neightborLo);
        }
        if (blankCol > 0) {  // exchange tile with the left column in the same row
            Board neightborLe = new Board(tiles);
            neightborLe.swap(blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(neightborLe);
        }
        if (blankCol < n - 1) {  // exchange tile with the right column in the same row
            Board neightborRi = new Board(tiles);
            neightborRi.swap(blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(neightborRi);
        }
        return neighbors;
    }

    private void swap(int row1, int col1, int row2, int col2) {
        int temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        Board twinBoard = new Board(tiles);
        if (tiles[0][0] != 0 && tiles[0][1] != 0)   // if BLANK is located not located on first row
            twinBoard.swap(0, 0, 0, 1);
        else    // if BLANK is located located on first row, swap in the second row
            twinBoard.swap(1, 0, 1, 1);
        return twinBoard;
    }

    // unit testing
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        int manDist = initial.manhattan();
        System.out.println("final manDistance: " + manDist);
        System.out.println("initial board\n" + initial);
        Iterable<Board> neighbors = initial.neighbors();
        for (Board neighbor: neighbors)
            System.out.println("neighbor\n" + neighbor);
        Board twinBoard = initial.twin();
        System.out.println("twin board\n" + twinBoard);
    }

}
