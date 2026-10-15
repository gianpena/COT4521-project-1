import java.awt.Graphics;

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
        this.center = new Point(center.getX(), center.getY());
    }

    public Car(Point center, double heading) {
        this(center);
        this.heading = heading;
    }

    public void update() {
        body = new Rectangle(new Point(center.getX() - bodyLength/2, center.getY() - bodyWidth/2),
                             new Point(center.getX() + bodyLength/2, center.getY() + bodyWidth/2));
        
        nose = new Triangle(new Point(center.getX() - bodyLength/2, center.getY() - bodyWidth/2),
                            new Point(center.getX() - bodyLength/2 - noseWidth, center.getY()),
                            new Point(center.getX() - bodyLength/2, center.getY() + bodyWidth/2));

        frontRightWheel = new Rectangle(new Point(center.getX() - bodyLength/2, center.getY() - bodyWidth/2 - wheelWidth),
                                        new Point(center.getX() - bodyLength/2 + wheelLength, center.getY() - bodyWidth/2));
        
        frontLeftWheel = new Rectangle(new Point(center.getX() - bodyLength/2, center.getY() + bodyWidth/2),
                                       new Point(center.getX() - bodyLength/2 + wheelLength, center.getY() + bodyWidth/2 + wheelWidth));

        rearRightWheel = new Rectangle(new Point(center.getX() + bodyLength/2 - wheelLength, center.getY() - bodyWidth/2 - wheelWidth),
                                       new Point(center.getX() + bodyLength/2, center.getY() - bodyWidth/2));

        rearLeftWheel = new Rectangle(new Point(center.getX() + bodyLength/2 - wheelLength, center.getY() + bodyWidth/2),
                                      new Point(center.getX() + bodyLength/2, center.getY() + bodyWidth/2 + wheelWidth));

        // translate, rotate, translate back
        Vector v = center.to(Point.ORIGIN);
        for(RigidlyTransformable obj : new RigidlyTransformable[]{body, nose, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel}) {
            obj.translate(v);
            obj.setBearing(heading);
            obj.translate(v.neg());
        }

    }

    public Rectangle getBody() { return body; }
    public Rectangle getFrontLeftWheel() { return frontLeftWheel; }
    public Rectangle getFrontRightWheel() { return frontRightWheel; }
    public Rectangle getRearLeftWheel() { return rearLeftWheel; }
    public Rectangle getRearRightWheel() { return rearRightWheel; }
    public Triangle getNose() { return nose; }

    @Override
    public void draw(Graphics g) {
        update();

        for(GeometricObject obj : new GeometricObject[]{nose, body, frontLeftWheel, frontRightWheel, rearLeftWheel, rearRightWheel}) {
            obj.setInteriorColor(getInteriorColor());
            obj.setBoundaryColor(getBoundaryColor());
            obj.draw(g);
        }
    }

}