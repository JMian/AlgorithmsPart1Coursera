/* *****************************************************************************
 *  Name: JMian
 *  Date: 26 August 2018
 *  Description: PercolationStats.java, Assignment 1,  Algorithms Part 1 Coursera
 *  Time Elapsed in sec (N , T)
 *  0.542 (N = 200, T = 100)
 *  2.134 (N = 400, T = 100)
 *  0.855 (N = 200, T = 200)
 *  3.795 (N = 400, T = 200)
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLow95;
    private final double confidenceHigh95;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Both grid length and trials "
                                               + "must be positive values");
        // an array consists of doubles to record the fraction in each trial
        double[] fractions = new double[trials];
        for (int i = 0; i < trials; i++) {   // iterate through each trial
            Percolation percoTrial = new Percolation(n);
            // permutate the position index of all the grid cells and store into
            // an array to be used later
            int gridSize = n * n;
            int[] permutedNum = StdRandom.permutation(gridSize);
            for (int j = 0; j < gridSize; j++) {
                // +1 because in Percolation.open(row, col) we programmed
                // the way where row and column index from 1
                int row = permutedNum[j] / n + 1;
                int col = permutedNum[j] % n + 1;
                percoTrial.open(row, col);
                if (percoTrial.percolates()) {
                    fractions[i] = (double) percoTrial.numberOfOpenSites() / (double) (n*n);
                    break;
                }
            }
        }
        this.mean = StdStats.mean(fractions);
        this.stddev = StdStats.stddev(fractions);
        double confidence95 = 1.96 * stddev / Math.sqrt(trials);
        this.confidenceLow95 = mean - confidence95;
        this.confidenceHigh95 = mean + confidence95;
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLow95;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHigh95;
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length != 2) {   // check if there are only two command-line arguments
            System.out.println("Proper Usage: java program gridLength trials");
        }
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch timeRecord = new Stopwatch();
        PercolationStats percoRun = new PercolationStats(n, trials);
        double timeElapsed = timeRecord.elapsedTime();
        System.out.println("sample mean: " + percoRun.mean());
        System.out.println("sample standard deviation: " + percoRun.stddev());
        System.out.println("95% confidence interval: " + percoRun.confidenceLo()
                                   + ", " + percoRun.confidenceHi());
        System.out.println("time elapsed " + timeElapsed + "s for N = " + n
                                   + " and Trials = " + trials);
    }
}
