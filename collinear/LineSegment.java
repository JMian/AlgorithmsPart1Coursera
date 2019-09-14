public class LineSegment {
    private final Point p;
    private final Point q;

    public LineSegment(Point p, Point q) {
        if (p == null || q == null)
            throw new NullPointerException("argument is null");
        this.p = p;
        this.q = q;
    }

    // Draws this line segment to standard draw
    public void draw() {
        p.drawTo(q);
    }

    // Return a string representation of this line segment
    public String toString() {
        return p + " -> " + q;
    }

    // throws UnsupportedOperationException if called
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}
