/* *****************************************************************************
 *  Name: JMian
 *  Date: 26 August 2018
 *  Description: Percolation.java, Assignment 1, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final boolean BLOCKED = false;
    private static final boolean OPEN = true;
    // a grid transfomed into an array consists of boolean data types
    private boolean[] gridArray;
    // make block and open sites more clearly
    private final int n;   // the length of the grid
    // the WeightedQuickUnionUF data type for the whole grid
    private final WeightedQuickUnionUF ufGrid;
    private int openSites;   // to keep track the number of open sites
    // create two arrays to record if each site
    // is connected to virtual Top and/or is connected to virtual Bottom
    private boolean[] connectTop;
    private boolean[] connectBottom;
    private boolean percolateFlag;

    // creates n-by-n-grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("grid length n should be at least 1");
        this.n = n;
        int gridSize = n * n;
        gridArray = new boolean[gridSize];
        connectTop = new boolean[gridSize];
        connectBottom = new boolean[gridSize];
        for (int i = 0; i < gridSize; i++) {
            gridArray[i] = BLOCKED;   // all sites initially blocked
            connectTop[i] = false;   // initially not connected to vritual top
            connectBottom[i] = false;   // initially not connected to virtual bottom
        }
        // ufTop and ufBottom +1 to account for the virtual top and virtual bottom
        // let both virtual Top and virtual Bottom be at the 0 index position
        ufGrid = new WeightedQuickUnionUF(gridSize);
        openSites = 0;   // initially there is no open site
        percolateFlag = false;   // the system initially does not percolate
    }

    // to validate if the input arguments row and col are within their presribed ranges
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException("row and column lengths must be "
                                                       + "positive and not larger than grid length n ");
    }

    // suppose the grid is transformd into an array and get the current site's index
    private int toArrayIndex(int row, int col) {
        validate(row, col);
        int siteArrayIndex = (row-1) * n + col - 1;
        return siteArrayIndex;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        int currentSite = toArrayIndex(row, col);
        // after union, the root of the current site should be connected to
        // top/bottom if the current site's or the neighbours' root are connected to top/bottom
        boolean rootTop = false;
        boolean rootBottom = false;
        // only if the current site is BLOCKED and has never been touched before
        if (gridArray[currentSite] == BLOCKED) {
            gridArray[currentSite] = OPEN;
            openSites++;
            // check if the upper site's and/or current site's root is connected to top/bottom
            // union after checking to avoid top-/bottom-connected site
            // being unioned to non-top-/bottom-connected site
            if (row > 1 && gridArray[currentSite - n] == OPEN) {   // connects upper site

                if (connectTop[ufGrid.find(currentSite - n)] || connectTop[ufGrid.find(currentSite)])
                    rootTop = true;
                if (connectBottom[ufGrid.find(currentSite - n)] || connectBottom[ufGrid.find(currentSite)])
                    rootBottom = true;
                ufGrid.union(currentSite, currentSite - n);
            }
            if (row < n && gridArray[currentSite + n] == OPEN) {   // connects lower site
                if (connectTop[ufGrid.find(currentSite + n)] || connectTop[ufGrid.find(currentSite)])
                    rootTop = true;
                if (connectBottom[ufGrid.find(currentSite + n)] || connectBottom[ufGrid.find(currentSite)])
                    rootBottom = true;
                ufGrid.union(currentSite, currentSite + n);
            }
            if (col > 1 && gridArray[currentSite - 1] == OPEN) {   // connects left site
                if (connectTop[ufGrid.find(currentSite - 1)] || connectTop[ufGrid.find(currentSite)])
                    rootTop = true;
                if (connectBottom[ufGrid.find(currentSite - 1)] || connectBottom[ufGrid.find(currentSite)])
                    rootBottom = true;
                ufGrid.union(currentSite, currentSite - 1);
            }
            if (col < n && gridArray[currentSite + 1] == OPEN) {   // connects right site
                if (connectTop[ufGrid.find(currentSite + 1)] || connectTop[ufGrid
                        .find(currentSite)])
                    rootTop = true;
                if (connectBottom[ufGrid.find(currentSite + 1)] || connectBottom[ufGrid
                        .find(currentSite)])
                    rootBottom = true;
                ufGrid.union(currentSite, currentSite + 1);
            }
            // if the site is at the top/bottom row, the site's root should
            // connect to the virtual top/bottom
            if (row == 1)
                rootTop = true;
            if (row == n)
                rootBottom = true;
            // after union, the current site's root should /should not
            // connect to the top/bottom following the checking done above
            connectTop[ufGrid.find(currentSite)] = rootTop;
            connectBottom[ufGrid.find(currentSite)] = rootBottom;
            // if the root is connected to both top and bottom the system percolates
            if (connectTop[ufGrid.find(currentSite)] && connectBottom[ufGrid.find(currentSite)])
                percolateFlag = true;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return gridArray[toArrayIndex(row, col)] == OPEN;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        // check if the root of site is currently connected to the virtual top
        return connectTop[ufGrid.find(toArrayIndex(row, col))];
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
        Percolation percoRun = new Percolation(testN);
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
