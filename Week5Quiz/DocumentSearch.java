/* *****************************************************************************
 *  Name: JMian
 *  Date: 07 October 2019
 *  Description: DocumentSearch.java, Week5 Balanced Search Trees, Algorithms Part 1 Coursera

 Document search. Design an algorithm that takes a sequence of n document words and a sequence of
 m query words and find the shortest interval in which the m query words appear in the document
 in the order given. The length of an interval is the number of words in that interval.

 Hint: for each word, maintain a sorted list of the indices in the document in which that
 word appears. Scan through the sorted lists of the query words in a judicious manner.
 **************************************************************************** */

import edu.princeton.cs.algs4.BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class DocumentSearch {

    public static void main(String[] args) throws FileNotFoundException {
        BST<String, ArrayDeque<Integer>> documentTree = new BST<>();
        File document = new File("document.txt");
        Scanner scanFile = new Scanner(document);
        int count = -1;
        while (scanFile.hasNext()) {
            String current = scanFile.next();
            count++;
            if (!documentTree.contains(current)) {
                ArrayDeque<Integer> indices = new ArrayDeque<Integer>();
                indices.add(count);
                documentTree.put(current, indices);
            }
            else {
                ArrayDeque<Integer> indices = documentTree.get(current);
                indices.add(count);
                documentTree.put(current, indices);
            }
        }
        ArrayDeque<String> queryWords = new ArrayDeque<>();
        Scanner inWords = new Scanner(System.in);
        System.out.println("Enter query words, Ctrl+D when done");
        while (inWords.hasNext()) {
            queryWords.add(inWords.next());
        }
        String[] queryWords2 = queryWords.toArray(new String[0]);
        ArrayDeque<Integer>[] queryWordsInd = new ArrayDeque[queryWords2.length];
        for (int i = 0; i < queryWords.size(); i++) {
            if (!documentTree.contains(queryWords2[i]))
                System.out.println("The word '" + queryWords2[i] + "' does not exist in document");
            queryWordsInd[i] = documentTree.get(queryWords2[i]);
        }

        int firstInd = -1;
        int lastInd = count+1;
        ArrayDeque<Integer> firstWordIndices = documentTree.get(queryWords2[0]);

        for (Integer firstWordIndex : firstWordIndices) {
            boolean endOK = true;
            int firstTemp = firstWordIndex;
            int lastTemp = firstTemp;
            for (int i = 1; i < queryWordsInd.length; i++) {
                while (!queryWordsInd[i].isEmpty() && queryWordsInd[i].peek() <= lastTemp)
                    queryWordsInd[i].remove();
                if (queryWordsInd[i].isEmpty()) {
                    endOK = false;
                    break;
                }
                else
                    lastTemp = queryWordsInd[i].peek();
            }
            if (endOK && lastTemp - firstTemp < lastInd - firstInd) {
                firstInd = firstTemp;
                lastInd = lastTemp;
            }
        }
        if (firstInd >= 0) {
            int interval = lastInd - firstInd;
            System.out.println("shortest interval: " + interval);
            Scanner scanner = new Scanner(document);
            count = -1;
            while (scanner.hasNext() && count < lastInd) {
                String current = scanner.next();
                count++;
                if (count >= firstInd) {
                    if (count == firstInd)
                        System.out.print("... ");
                    System.out.print(current + " ");
                    if (count == lastInd)
                        System.out.println("...");
                }
            }
        }
        else
            System.out.println("The query words in the given order not exist in document");

    }

}
