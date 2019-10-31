/* *****************************************************************************
 *  Name: JMian
 *  Date: 29 October 2019
 *  Description: OlympicAthlete.java, Week6 Hash Tables, Algorithms Part 1 Coursera

 Hashing with wrong hashCode() or equals(). Suppose that you implement a data type
 OlympicAthlete for use in a java.util.HashMap.
 - Describe what happens if you override hashCode() but not equals().
 - Describe what happens if you override equals() but not hashCode().
 - Describe what happens if you override hashCode() but implement
   public boolean equals(OlympicAthelete that) instead of
   public boolean equals(Object that).

 **************************************************************************** */

import java.util.HashMap;

public class OlympicAthlete {
    private String name;
    private int id;

    public OlympicAthlete(String name, int id) {
        this.name = name;
        this.id = id;
    }

     public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }

    /* public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        OlympicAthlete that = (OlympicAthlete) obj;
        return name.equals(that.name) && id == that.id;
    }

     */

    public boolean equals(OlympicAthlete that) {
        if (that == null || this.getClass() != that.getClass()) {
            System.out.println("I am here");
            return false;
        }
        return name.equals(that.name) && id == that.id;
    }


    public static void main(String[] args) {
        OlympicAthlete athlete1 = new OlympicAthlete("Roman", 1);
        OlympicAthlete athlete2 = new OlympicAthlete("Brendan", 2);
        OlympicAthlete athlete3 = new OlympicAthlete("Roman", 1);
        HashMap<OlympicAthlete, Integer> hashmap = new HashMap<>();
        hashmap.put(athlete1, 65);
        hashmap.put(athlete2, 78);
        System.out.println(hashmap.containsKey(athlete3));
        System.out.println(athlete1.equals(athlete3));
        System.out.println(athlete1.hashCode());
        System.out.println(athlete3.hashCode());
    }

}
