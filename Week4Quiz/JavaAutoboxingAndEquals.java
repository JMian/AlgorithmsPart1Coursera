/* *****************************************************************************
 *  Name: JMian
 *  Date: 03 October 2019
 *  Description: JavaAutoboxingAndEquals.java, Week4 Elementary Symbol Tables, Algorithms Part 1 Coursera

 Java autoboxing and equals(). Consider two double values a and b and their corresponding
 Double values x and y.
 Find values such that (a == b) is true but x.equals(y) is false.
 Find values such that (a == b) is false but x.equals(y) is true.

 Hint: IEEE floating point arithmetic has some peculiar rules for 0.0, -0.0, and NaN.
 Java requires that equals() implements an equivalence relation.
 **************************************************************************** */
 /*
  * https://docs.oracle.com/javase/6/docs/api/java/lang/Double.html#equals%28java.lang.Object%29
    public boolean equals(Object obj) {
        return (obj instanceof Double)
        && (doubleToLongBits(((Double)obj).value) ==
        doubleToLongBits(value));
        }

   https://stackoverflow.com/questions/8819738/why-does-double-nan-double-nan-return-false
   @falsarella

  */

import java.util.ArrayList;

public class JavaAutoboxingAndEquals {
    public static void main(String[] args) {
        double a1 = +0.0;
        double b1 = -0.0;
        Double x1 = a1;
        Double y1 = b1;
        System.out.println("a = +0.0, b = -0.0:");
        System.out.println("a == b: " + (a1 == b1) + "; x.equals(y): " + (x1.equals(y1)));

        double a2 = Double.NaN;
        double b2 = Double.NaN;
        Double x2 = a2;
        Double y2 = b2;
        System.out.println("a = Double.NaN, b = Double.NaN:");
        System.out.println("a == b: " + (a2 == b2) + "; x.equals(y): " + (x2.equals(y2)));
    }
}
