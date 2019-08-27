/* *****************************************************************************
 *  Name: JMian
 *  Date: 27 August 2018
 *  Description: WQUFindLargest.java, Week1 Union-Find Quiz02, Algorithms Part 1 Coursera

 Union-find with specific canonical element. Add a method find() to the union-find data
 type so that find(i) returns the largest element in the connected component containing
 i. The operations, union(), connected() and find() should all take logarithmic time or better.
 For example, if one of the connected components is {1,2,6,9}, then the find() method
 should return 9 for each of the four elements in the connected components.
 **************************************************************************** */

public class WQUFindLargest {
  private int[] parent;   // parent[i] = parent of i
  private int[] size;     // size[i] = number of sites in subtree rooted at i
  private int count;      // number of components
  private int[] largest;   // to record the largest element in the connected component

  public WQUFindLargest(int n) {
      count = n;
      parent = new int[n];
      size = new int[n];
      largest = new int[n];
      for (int i = 0; i < n; i++) {
          parent[i] = i;
          size[i] = 1;
          // initialize each connected component's largest value to be themselves
          // since there is only one element in each component initially
          largest[i] = i;
      }
  }

  public int count() {
      return count;
  }

  public int root(int p) {
     validate(p);
     while (p != parent[p])
         p = parent[p];
     return p;
  }

  public void union(int p, int q) {
      int rootP = root(p);
      int rootQ = root(q);
      int largestP = largest[rootP];
      int largestQ = largest[rootQ];
      if (rootP == rootQ) return;

      // make smaller root point to larger one
      if (size[rootP] < size[rootQ]) {
          parent[rootP] = rootQ;
          size[rootQ] += size[rootP];
          // if the largest value in the connected component containing p
          // is larger than q's, then set the largest value in the newly unioned
          // connected component to be that of p; otherwise remains q's
          // the largest value in the whole connected component is recorded in the root only
          if (largestP > largestQ)
              largest[rootQ] = largestP;
      }
      else {
          parent[rootQ] = rootP;
          size[rootP] += size[rootQ];
          if (largestQ > largestP)
              largest[rootP] = largestQ;
      }
      count--;
  }

  private void validate(int p) {
      int n = parent.length;
      if (p < 0 || p >= n) {
          throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
      }
  }

  public boolean connected(int p, int q) {
      return root(p) == root(q);
  }

  // find the largest element in the connected component containing i
  public int find(int p) {
      return largest[root(p)];
  }


  public static void main(String[] args) {
      int n = 8;
      WQUFindLargest uf = new WQUFindLargest(n);
      uf.union(1, 3);
      uf.union(4, 3);
      System.out.println("Largest in {1, 3, 4}: " + uf.find(1));
      uf.union(2, 0);
      uf.union(6, 0);
      System.out.println("Largest in {0, 2, 6}: " + uf.find(2));
      uf.union(2, 5);
      System.out.println("Largest in {0, 2, 5, 6}: " + uf.find(5));
      uf.union(3, 7);
      System.out.println("Largest in {1, 3, 4, 7}: " + uf.find(4));
      uf.union(5, 1);
      System.out.println("Largest in {0, 1, 2, 3, 4, 5, 6 ,7}: " + uf.find(0));
  }
}
