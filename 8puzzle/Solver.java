/* *****************************************************************************
 *  Name: JMian
 *  Date: 25 September 2019
 *  Description: Solver.java, Assignment4, Algorithms Part 1 Coursera
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayDeque;
import java.util.Deque;

public final class Solver {
    private SearchNode currentNode;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode prevNode;
        private final int manPriority;   // priority calculated using Manhattan distance

        public SearchNode(Board board, SearchNode prevNode) {
            this.board = board;
            this.prevNode = prevNode;
            if (prevNode == null)
                moves = 0;
            else
                moves = prevNode.moves + 1;
            manPriority = board.manhattan() + moves;
        }

        @Override
        public int compareTo(SearchNode that) {
            return (this.manPriority - that.manPriority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("Include a new initial board");
        MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
        pq.insert(new SearchNode(initial, null));
        MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
        pqTwin.insert(new SearchNode(initial.twin(), null));
        while (true) {
            currentNode = pq.delMin();
            if (currentNode.board.isGoal())
                break;
            addNeighborNodes(currentNode, pq);

            SearchNode currentNodeTwin = pqTwin.delMin();
            if (currentNodeTwin.board.isGoal())
                break;
            addNeighborNodes(currentNodeTwin, pqTwin);
        }
    }

    private void addNeighborNodes(SearchNode node, MinPQ<SearchNode> pq) {
        Iterable<Board> neighbors = node.board.neighbors();
        for (Board neighbor : neighbors) {
            if (node.prevNode == null || !neighbor.equals(node.prevNode.board))
                pq.insert(new SearchNode(neighbor, node));
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return currentNode.board.isGoal();
    }

    // min number of moves to solve initial board
    public int moves() {
        if (currentNode.board.isGoal())
            return currentNode.moves;
        else
            return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (currentNode.board.isGoal()) {
            Deque<Board> solution = new ArrayDeque<>();
            SearchNode node = currentNode;
            while (node != null) {
                solution.push(node.board);
                node = node.prevNode;
            }
            return solution;
        }
        return null;
    }

    // test client
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
        tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
                StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println("solution step: " + board);
        }
    }
}
