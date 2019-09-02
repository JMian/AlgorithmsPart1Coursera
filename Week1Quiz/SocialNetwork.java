/* *****************************************************************************
 *  Name: JMian
 *  Date: 27 August 2019
 *  Description: SocialNetwork.java, Week1 Union-Find Quiz01, Algorithms Part 1 Coursera

 Social network connectivity. Given a social network containing n members and a
 log file containing m timestamps at which times pairs of members formed friendships,
 design an algorithm to determine the earliest time at which all members are
 connected (i.e., every member is a friend of a friend of a friend ... of a friend).
 Assume that the log file is sorted by timestamp and that friendship is an equivalence
 relation. The running time of your algorithm should be mlogn or better and use
 extra space proportional to n.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SocialNetwork {
    private WeightedQuickUnionUF socialUF;
    private File logFile;

    public SocialNetwork(int n, File logFile) {
        this.logFile = logFile;
        socialUF = new WeightedQuickUnionUF(n);
    }

    // to get the earliest time where all members are connected
    public String getEarliestAllConnected() throws FileNotFoundException{
        Scanner scanLog = new Scanner(logFile);
        String allConnectedTime = null;
        while(scanLog.hasNextLine()) {
            // assumming each record occupies a line, and only has three inouts
            // in the format of timestamp, person1, person2
            String currentRecord = scanLog.nextLine();
            String[] splitRecord = currentRecord.split(" ");
            String timeStamp = splitRecord[0];
            int p = Integer.parseInt(splitRecord[1]);
            int q = Integer.parseInt(splitRecord[2]);
            if (socialUF.connected(p, q))
                continue;
            socialUF.union(p ,q);
            // when the component in socialUF is only 1, all people are connected
            if (socialUF.count() == 1) {
                allConnectedTime = timeStamp;
                break;
            }
        }
        return allConnectedTime;
    }

    // just for testing using my own logFile
    public static void main(String[] args) throws FileNotFoundException {
        int n = 8;
        File logFile = new File("socialLog.txt");
        SocialNetwork network = new SocialNetwork(n, logFile);
        String earliestTime = network.getEarliestAllConnected();
        System.out.println("The earlist time at where all people "
                            + "are connected is " + earliestTime);
    }
}
