/* *****************************************************************************
 *  Name: JMian
 *  Date: 17 September 2019
 *  Description: SelectionInTwoSortedArrays.java, Week3 Quicksort, Algorithms Part 1 Coursera

 Given two sorted arrays a[] and b[], of lengths n1 and n2 and an integer 0 <= k < n1 + n2,
 design an algorithm to find a key of rank k (the kth largest key). The order of growth of
 the worst case running time of your algorithm should be logn, where n = n1 + n2.
    Version 1: n1=n2 (equal length arrays) and k=n/2 (median).
    Version 2: k=n/2 (median).
    Version 3: no restrictions.
 **************************************************************************** */

public class SelectionInTwoSortedArrays {
    public static int findKLargest(int[] A, int[] B, int k) {
        int nA = A.length;
        int nB = B.length;
        int n = nA + nB;   // there must be n-k smaller elements
        int key;
        int headA = 0;
        int endA = nA - 1;

        while (true) {
            if (k == 1) {
                key = Math.max(A[nA-1], B[nB-1]);
                return key;
            }
            else if (k == n) {
                key = Math.min(A[0], B[0]);
                return key;
            }
            int midA = headA + (endA - headA)/2;
            while (n - k - midA >= nB) {
                headA = midA + 1;
                midA = headA + (endA - headA)/2;
            }
            int indB = n - k - midA;

            if (indB <= 0) {
                if (indB == 0 && A[midA - 1] <= B[0])
                    return Math.min(A[midA], B[indB]);
                else if (A[midA - 1] >= B[0])
                    endA = midA - 1;
                else
                    return A[n-k];
            }
            else if  (midA == 0) {
                if (B[indB - 1] <= A[0])
                    return Math.min(A[midA], B[indB]);
                else if (A[midA + 1] <= B[indB])
                    headA = midA + 1;
                else
                    return B[n-k];
            }
            else if ((B[indB - 1] <=  A[midA] && A[midA - 1] <= B[indB])) {
                key = Math.min(A[midA], B[indB]);
                return key;
            }
            else if ( A[midA - 1] > B[indB]) {
                endA = midA - 1;
                if (endA < headA && A[midA - 1] > B[indB]) {
                    key = A[nA - k];
                    return key;
                }
            }
            else if ( B[indB - 1] > A[midA]) {
                headA = midA + 1;
                if (headA >= nA) {
                    key = B[nB-k];
                    return key;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {0, 2, 3, 8, 9, 10};
        int[] B = {1, 4, 5, 6, 7, 11};
        int[] C = {0, 1, 2, 3, 4, 6, 7, 8};
        int[] D = {5, 9, 10, 11};
        int[] E = {6, 7, 8, 9, 10, 11};
        int[] F = {0, 1, 2, 3, 4, 5};
        int[] G = {1, 5, 9};
        int[] H = {0, 2, 3, 4, 5, 7, 8, 10, 11};

       int n = G.length + H.length;
        for (int i = 1; i <= n; i++) {
            int key = findKLargest(H, G, i);
            System.out.println("the " + i + "th largest key is " + key);
        }
    }
}
