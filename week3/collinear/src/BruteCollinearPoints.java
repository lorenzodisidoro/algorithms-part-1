package week3.collinear.src;

// import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] segments;
    private int numberOfSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] argPoints) {
        checkPoints(argPoints);

        Arrays.sort(this.points);

        points = argPoints.clone();
        numberOfSegments = 0;
        segments = new LineSegment[2];

        // it is possible to solve the problem much faster than the brute-force solution :)
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length - 1; j++)
                for (int k = j + 1; k < points.length - 1; k++)
                    for (int z = k + 1; z < points.length - 1; z++) {
                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(this.points[k]) &&
                                points[j].slopeTo(points[k]) == points[k].slopeTo(points[z])) {

                            enqueue(new LineSegment(points[i], points[z]));

                            points[i].drawTo(points[z]);
                            // StdDraw.show();
                        }
                    }
    }

    private void resize() {
        segments = Arrays.copyOf(segments, segments.length + 1);
    }

    private void enqueue(LineSegment newLineSegment) {
        if (newLineSegment == null) {
            throw new IllegalArgumentException();
        }

        resize();
        segments[numberOfSegments++] = newLineSegment;
    }

    /*
     * Corner cases. Throw an IllegalArgumentException if the argument to the
     * constructor is null, if any point in the array is null, or if the argument
     * to the constructor contains a repeated point.
     *
     * @throw IllegalArgumentException
     */
    private void checkPoints(Point[] argPoints) {
        if (argPoints == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < argPoints.length; i++)
            for (int j = 0; j < points.length; j++) {
                if (points[i] == null || points[j] == null)
                    throw new IllegalArgumentException();

                if (i != j && points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }

    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {
        Point p1 = new Point(4, 2);
        Point p2 = new Point(5,1);
        Point p3 = new Point(3,2);

        Point[] points = new Point[]{p1, p2, p3};

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);

        assert bruteCollinearPoints.numberOfSegments() == 1;
        assert bruteCollinearPoints.segments().length == 1;

        // ToDo: add other test cases
    }
}
