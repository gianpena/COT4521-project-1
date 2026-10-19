
import java.awt.Graphics;

/**
 * This class represents a rectangle given by its four corners.
 * The corners are stored in order around the perimeter (either clockwise
 * or counter-clockwise). Rectangles may be axis-aligned or rotated.
 *
 * Based on code from the COT4521 course materials.
 *
 * @author Gian Peña (PID 6396658)
 * @author Henrique Pitta Laranjinha (PID XXXXXXX)
 */
public class Rectangle extends GeometricObject implements RigidlyTransformable
{
    // Four corners of the rectangle, in order around the perimeter.
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    /**
     * Default constructor; creates a unit square with corners
     * (0,0), (1,0), (1,1), (0,1).
     */
    public Rectangle()
    {
        p1 = new Point(0, 0);
        p2 = new Point(1, 0);
        p3 = new Point(1, 1);
        p4 = new Point(0, 1);
    }

    /**
     * Constructor from two opposite corners. The resulting rectangle is
     * axis-aligned.
     *
     * @param b one corner
     * @param e opposite corner
     */
    public Rectangle(Point b, Point e)
    {
        double minX = Math.min(b.getX(), e.getX());
        double minY = Math.min(b.getY(), e.getY());
        double maxX = Math.max(b.getX(), e.getX());
        double maxY = Math.max(b.getY(), e.getY());

        p1 = new Point(minX, minY);
        p2 = new Point(maxX, minY);
        p3 = new Point(maxX, maxY);
        p4 = new Point(minX, maxY);
    }

    /**
     * Constructor from four corners. The points should be given in order
     * around the perimeter of the rectangle.
     *
     * @param p1 first corner
     * @param p2 second corner
     * @param p3 third corner
     * @param p4 fourth corner
     */
    public Rectangle(Point p1, Point p2, Point p3, Point p4)
    {
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
        this.p3 = new Point(p3.getX(), p3.getY());
        this.p4 = new Point(p4.getX(), p4.getY());
    }

    /**
     * Draws this rectangle.
     *
     * @param g graphics context
     */
    @Override
    public void draw(Graphics g)
    {
        int[] x = {(int)p1.getX(), (int)p2.getX(), (int)p3.getX(), (int)p4.getX()};
        int[] y = {(int)p1.getY(), (int)p2.getY(), (int)p3.getY(), (int)p4.getY()};

        g.setColor(getInteriorColor());
        g.fillPolygon(x, y, 4);

        g.setColor(getBoundaryColor());
        g.drawPolygon(x, y, 4);
    }

    /**
     * Returns the first point defining this rectangle.
     *
     * @return a corner of this rectangle
     */
    public Point getBegin()
    {
        return new Point(p1.getX(), p1.getY());
    }

    /**
     * Returns the second point defining this rectangle.
     *
     * @return the corner opposite the first point
     */
    public Point getEnd()
    {
        return new Point(p3.getX(), p3.getY());
    }

    /**
     * Returns the i-th corner of this rectangle (1-based index).
     *
     * @param i corner index, must be 1, 2, 3, or 4
     * @return the i-th corner
     * @throws IllegalArgumentException if i is not in [1,4]
     */
    public Point getCorner(int i)
    {
        switch (i)
        {
            case 1: return new Point(p1.getX(), p1.getY());
            case 2: return new Point(p2.getX(), p2.getY());
            case 3: return new Point(p3.getX(), p3.getY());
            case 4: return new Point(p4.getX(), p4.getY());
            default: throw new IllegalArgumentException("Corner index must be 1-4");
        }
    }

    /**
     * Determines the greatest x.
     *
     * @return the greatest x
     */
    public double greatestX()
    {
        return Math.max(Math.max(p1.getX(), p2.getX()), Math.max(p3.getX(), p4.getX()));
    }

    /**
     * Determines the greatest y.
     *
     * @return the greatest y
     */
    public double greatestY()
    {
        return Math.max(Math.max(p1.getY(), p2.getY()), Math.max(p3.getY(), p4.getY()));
    }

    /**
     * Calculates the height of this rectangle (length of the side between
     * the second and third corners).
     *
     * @return the height of this rectangle
     */
    public double height()
    {
        return p2.distance(p3);
    }

    /**
     * Sets the the first point defining this rectangle.
     *
     * @param p a corner of this rectangle
     */
    public void setBegin(Point p)
    {
        double dx = p.getX() - p1.getX();
        double dy = p.getY() - p1.getY();
        Vector v = new Vector(dx, dy);
        translate(v);
    }

    /**
     * Sets the the second point defining this rectangle.
     *
     * @param p the corner opposite the first point
     */
    public void setEnd(Point p)
    {
        double dx = p.getX() - p3.getX();
        double dy = p.getY() - p3.getY();
        Vector v = new Vector(dx, dy);
        translate(v);
    }

    /**
     * Determines the smallest x.
     *
     * @return the smallest x
     */
    public double smallestX()
    {
        return Math.min(Math.min(p1.getX(), p2.getX()), Math.min(p3.getX(), p4.getX()));
    }

    /**
     * Determines the smallest y.
     *
     * @return the smallest y
     */
    public double smallestY()
    {
        return Math.min(Math.min(p1.getY(), p2.getY()), Math.min(p3.getY(), p4.getY()));
    }

    /**
     * Constructs a String description of this rectangle.
     *
     * @return String containing a description of this rectangle
     */
    @Override
    public String toString()
    {
        String str = "RECTANGLE " + super.toString() + "\n";
        str += p1 + "\n" + p2 + "\n" + p3 + "\n" + p4;

        return str;
    }

    /**
     * Translates this rectangle by given vector.
     *
     * @param v given vector
     */
    @Override
    public void translate(Vector v)
    {
        p1.translate(v);
        p2.translate(v);
        p3.translate(v);
        p4.translate(v);
    }

    /**
     * Rotates this rectangle around the given point by the given angle.
     *
     * @param p center of rotation
     * @param angle given angle in degrees
     */
    @Override
    public void rotate(Point p, double angle) {
        p1.rotate(p, angle);
        p2.rotate(p, angle);
        p3.rotate(p, angle);
        p4.rotate(p, angle);
    }

    @Override
    public void setBearing(double theta) {
        double current = Math.toDegrees(Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX()));
        rotate(Point.ORIGIN, theta - current);
    }

    /**
     * Calculates the width of this rectangle (length of the side between
     * the first and second corners).
     *
     * @return the width of this rectangle
     */
    public double width()
    {
        return p1.distance(p2);
    }

    /**
     * Determines if given point is contained in this rectangle.
     *
     * @param p given point
     * @return true iff p is on the boundary or the interior of this rectangle.
     */
    boolean isPointInRectangle(Point p)
    {
        Point[] corners = {p1, p2, p3, p4};
        double[] crossProducts = new double[4];

        for (int i = 0; i < 4; i++)
        {
            Point a = corners[i];
            Point b = corners[(i + 1) % 4];

            double ax = b.getX() - a.getX();
            double ay = b.getY() - a.getY();
            double bx = p.getX() - a.getX();
            double by = p.getY() - a.getY();

            crossProducts[i] = ax * by - ay * bx;
        }

        boolean allNonNegative = true;
        boolean allNonPositive = true;

        for (double cp : crossProducts)
        {
            if (cp < -1e-9)
            {
                allNonNegative = false;
            }
            if (cp > 1e-9)
            {
                allNonPositive = false;
            }
        }

        return allNonNegative || allNonPositive;
    }

    public Rectangle boundingBox(Rectangle r) {
        double smallestx = Math.min(smallestX(), r.smallestX());
        double smallesty = Math.min(smallestY(), r.smallestY());

        double greatestx = Math.max(greatestX(), r.greatestX());
        double greatesty = Math.max(greatestY(), r.greatestY());

        return new Rectangle(new Point(smallestx, smallesty), new Point(greatestx, greatesty));
    }

    public Rectangle sum(Rectangle r) {
        double smallestx_sum = smallestX() + r.smallestX();
        double smallesty_sum = smallestY() + r.smallestY();

        double greatestx_sum = greatestX() + r.greatestX();
        double greatesty_sum = greatestY() + r.greatestY();

        return new Rectangle(new Point(smallestx_sum, smallesty_sum), new Point(greatestx_sum, greatesty_sum));

    }
}
