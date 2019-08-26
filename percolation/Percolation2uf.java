/* *****************************************************************************
 *  Name: JMian
 *  Date: 26 August 2018
 *  Description: Percolation2uf.java, Assignment 1, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation2uf {
    private static final boolean BLOCKED = false;
    private static final boolean OPEN = true;
    private boolean[][] grid;   // a grid consists of boolean data types
    // make block and open sites more clearly
    private final int n;   // the length of the grid
    // the WeightedQuickUnionUF data type for the whole grid
    private final WeightedQuickUnionUF ufTop;
    private final WeightedQuickUnionUF ufBottom;
    private int openSites;   // to keep track the number of open sites
    private boolean percolateFlag;

    // creates n-by-n-grid, with all sites initially blocked
    public Percolation2uf(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("grid length n should be at least 1");
        grid = new boolean[n][n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = BLOCKED;   // all sites initially blocked
            }
        }
        // ufTop and ufBottom +1 to account for the virtual top and virtual bottom
        // let both virtual Top and virtual Bottom be at the 0 index position
        ufTop = new WeightedQuickUnionUF(n * n + 1);
        ufBottom = new WeightedQuickUnionUF(n * n + 1);
        openSites = 0;   // initially there is no open site
        percolateFlag = false;   // the system initially does not percolate
    }

    // to validate if the input arguments row and col are within their presribed ranges
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("row and column lengths must be "
                                                       + "positive and not larger than grid length n ");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int currentSite = (row - 1) * n + col;
        // only if the current site is BLOCKED and has never been touched before
        if (grid[row - 1][col - 1] == BLOCKED) {
            grid[row - 1][col - 1] = OPEN;
            openSites++;

            // if the site is at the top row, connect it to the virtual top
            if (row == 1)
                ufTop.union(0, col);
            // if the site is at the bottom row, connect it to the virtual bottom
            if (row == n)
                ufBottom.union(0, (n - 1) * n + col);
            if (row > 1 && grid[row - 2][col - 1] == OPEN) {  // connects upper site
                ufTop.union(currentSite, (row - 2) * n + col);
                ufBottom.union(currentSite, (row - 2) * n + col);
            }
            if (row < n && grid[row][col - 1] == OPEN) {  // connects lower site
                ufTop.union(currentSite, row * n + col);
                ufBottom.union(currentSite, row * n + col);
            }
            if (col > 1 && grid[row - 1][col-2] == OPEN) {  // connects left site
                ufTop.union(currentSite, currentSite - 1);
                ufBottom.union(currentSite, currentSite - 1);
            }
            if (col < n && grid[row - 1][col] == OPEN) {  // connects right site
                ufTop.union(currentSite, currentSite + 1);
                ufBottom.union(currentSite, currentSite + 1);
            }
        }
        // the system percolates when the currentSite is connected to both
        // virtual top and virtual bottom
        if (ufTop.connected(0, currentSite) && ufBottom.connected(0, currentSite))
            percolateFlag = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row-1][col-1] == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        // check if the site is currently open and is connected to
        // the virtual top (any open sites at the top row are connected to virtual top)
        return (isOpen(row, col) && ufTop.connected(0, (row - 1) * n + col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolateFlag;
    }

    // test client (optional)
    public static void main(String[] args) {
        int testN = 3;
        Percolation2uf percoRun = new Percolation2uf(testN);
        // starts from 1 because we assummed row and column indexed from 1 in our program
        for (int i = 1; i <= testN; i++) {
            for (int j = 1; j <= testN; j++) {
                percoRun.open(i, j);
                if (percoRun.percolates()) {
                    // should print out n*(n-1) + 1
                    System.out.println(percoRun.numberOfOpenSites());
                    break;
                }
            }
        }
    }
}
