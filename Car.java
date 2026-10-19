import java.awt.Graphics;

/**
 * This class represents the robot: a rectangular body, a triangular
 * bumper, and four rectangular wheels.
 *
 * @author Gian Peña (PID 6396658)
 * @author Henrique Pitta Laranjinha (PID XXXXXXX)
 */
public class Car extends GeometricObject {

    private Point center;
    private double heading; // given in degrees

    final private int bodyLength = 100;
    final private int bodyWidth = 50;
    private Rectangle body;

    final private int noseWidth = 10;
    private Triangle nose;

    final private int wheelWidth = 10;
    final private int wheelLength = 20;
    private Rectangle frontLeftWheel;
    private Rectangle frontRightWheel;
    private Rectangle rearLeftWheel;
    private Rectangle rearRightWheel;

    public Car(Point center) {
        this(center, 0);
    }

    public Car(Point center, double heading) {
        this.center = new Point(center.getX(), center.getY());
        build();
        rotate(this.center, heading);
    }

    // builds the parts around the center with the car facing right (heading 0)
    private void build() {
        double x = center.getX();
        double y = center.getY();

        body = new Rectangle(new Point(x - bodyLength/2, y - bodyWidth/2),
                             new Point(x + bodyLength/2, y + bodyWidth/2));

        nose = new Triangle(new Point(x + bodyLength/2, y - bodyWidth/2),
                            new Point(x + bodyLength/2 + noseWidth, y),
                            new Point(x + bodyLength/2, y + bodyWidth/2));

        frontLeftWheel = new Rectangle(new Point(x + bodyLength/2 - wheelLength, y - bodyWidth/2 - wheelWidth),
                                       new Point(x + bodyLength/2, y - bodyWidth/2));

        frontRightWheel = new Rectangle(new Point(x + bodyLength/2 - wheelLength, y + bodyWidth/2),
                                        new Point(x + bodyLength/2, y + bodyWidth/2 + wheelWidth));

        rearLeftWheel = new Rectangle(new Point(x - bodyLength/2, y - bodyWidth/2 - wheelWidth),
                                      new Point(x - bodyLength/2 + wheelLength, y - bodyWidth/2));

        rearRightWheel = new Rectangle(new Point(x - bodyLength/2, y + bodyWidth/2),
                                       new Point(x - bodyLength/2 + wheelLength, y + bodyWidth/2 + wheelWidth));
    }

    private RigidlyTransformable[] parts() {
        return new RigidlyTransformable[]{body, nose, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel};
    }

    /**
     * Translates this car by given vector.
     *
     * @param v given vector
     */
    public void translate(Vector v) {
        center.translate(v);
        for(RigidlyTransformable obj : parts()) {
            obj.translate(v);
        }
    }

    /**
     * Rotates this car around the given point by the given angle.
     *
     * @param p center of rotation
     * @param angle given angle in degrees (positive is clockwise on screen)
     */
    public void rotate(Point p, double angle) {
        center.rotate(p, angle);
        for(RigidlyTransformable obj : parts()) {
            obj.rotate(p, angle);
        }
        heading += angle;
    }

    public Point getCenter() { return new Point(center.getX(), center.getY()); }
    public double getHeading() { return heading; }

    public Rectangle getBody() { return body; }
    public Rectangle getFrontLeftWheel() { return frontLeftWheel; }
    public Rectangle getFrontRightWheel() { return frontRightWheel; }
    public Rectangle getRearLeftWheel() { return rearLeftWheel; }
    public Rectangle getRearRightWheel() { return rearRightWheel; }
    public Triangle getNose() { return nose; }

    @Override
    public void draw(Graphics g) {
        for(GeometricObject obj : new GeometricObject[]{nose, body, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel}) {
            obj.setInteriorColor(getInteriorColor());
            obj.setBoundaryColor(getBoundaryColor());
            obj.draw(g);
        }
    }

}