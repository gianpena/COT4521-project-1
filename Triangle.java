import java.awt.Graphics;

public class Triangle extends GeometricObject implements Translatable {
    private Point p1;
    private Point p2;
    private Point p3;

    public Triangle() {
        p1 = new Point(0,0);
        p2 = new Point(1,1);
        p3 = new Point(2,0);
    }

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = new Point(p1.getX(), p1.getY());
        this.p2 = new Point(p2.getX(), p2.getY());
        this.p3 = new Point(p3.getX(), p3.getY());
    }

    @Override
    public void draw(Graphics g) {
        int[] x = {(int)p1.getX(), (int)p2.getX(), (int)p3.getX()};
        int[] y = {(int)p1.getY(), (int)p2.getY(), (int)p3.getY()};
        g.setColor(getInteriorColor());
        g.fillPolygon(x, y, 3);

        g.setColor(getBoundaryColor());
        g.drawPolygon(x, y, 3);
    }

    @Override
    public void translate(Vector v) {
        p1.translate(v);
        p2.translate(v);
        p3.translate(v);
    }

    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public Point getP3() { return p3; }
}