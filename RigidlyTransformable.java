public interface RigidlyTransformable {
    void translate(Vector v);
    void rotate(Point p, double angle);
    void setBearing(double theta);
}