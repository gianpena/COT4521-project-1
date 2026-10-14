
import java.awt.*;

/**
 * This class represents a point given by its x and y coordinates.
 */
public class Point extends GeometricObject
{
    private double x;
    private double y;

    /**
     * Instantiates a Point object with coordinates (0, 0) and parent's
     * default options.
     */
    public Point()
    {
        x = y = 0;
    }

    /**
     * Instantiates a Point object with coordinates (x, y) and parent's
     * default options.
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of this point.
     * 
     * @return x-coordinate
     */
    public double getX()
    {
        return x;
    }

    /**
     * Returns the y coordinate of this point.
     * 
     * @return y-coordinate
     */
    public double getY()
    {
        return y;
    }

    /**
     * Sets the x-coordinate of this point.
     * 
     * @param x x-coordinate
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     * 
     * @param y y-coordinate 
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Translates this point by the given vector.
     *
     * @param v translation vector
     */
    public void translate(Vector v)
    {
        x += v.getX();
        y += v.getY();
    }

    /**
     * Constructs a String description of this point.
     * 
     * @return String containing a description of this point
     */
    @Override
    public String toString()
    {
        return "POINT (" + x + ", " + y + ") " + super.toString();
    }

    public void draw(Graphics g)
    {
        int radius = 5;

        g.setColor(getInteriorColor());
        g.fillOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
        g.setColor(getBoundaryColor());
        g.drawOval((int)x - radius, (int)y - radius, 2 * radius, 2 * radius);
    }

    /**
     * Rotates this point about the origin by the given angle.
     *
     * @param angle given angle
     */
    public void rotate(double angle)
    {
        double angleRadians = Math.toRadians(angle);

        double sine = Math.sin(angleRadians);
        double cosine = Math.cos(angleRadians);

        double x1 = x*cosine - y*sine;
        double y1 = x*sine + y*cosine;

        x = x1;
        y = y1;
    }

    /**
     * Calculates the Euclidean distance between this point and the given one.
     *
     * @param p given point
     *
     * @return distance between this point and p
     */
    public double distance(Point p)
    {
        return Math.hypot(this.x - p.x, this.y - p.y);
    }

    public Point closestPoint(LineSegment ls) {
        double bx = ls.getBegin().getX();
        double by = ls.getBegin().getY();

        double vx = ls.getEnd().getX() - bx;
        double vy = ls.getEnd().getY() - by;
        double wx = this.x - bx;
        double wy = this.y - by;

        Vector v = new Vector(vx, vy);
        Vector w = new Vector(wx, wy);

        double t = v.dotProduct(w) / v.magnitudeSquared();

        t = Math.max(0, Math.min(1, t));

        return new Point(bx + t * vx, by + t * vy);        
    }

    public Point closestPoint(LineSegment[] segments) throws Exception{
        if(segments.length == 0) {
            throw new Exception("Array of line segments is empty.");
        }

        Point closest = this.closestPoint(segments[0]);
        for(int i=1; i<segments.length; ++i) {
            Point candidate = this.closestPoint(segments[i]);
            if(this.distance(candidate) < this.distance(closest))
                closest = candidate;
        }

        return closest;
    }
}
