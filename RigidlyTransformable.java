/**
 * Operations of objects that can be translated and rotated.
 *
 * @author Gian Peña (PID 6396658)
 * @author Henrique Pitta Laranjinha (PID XXXXXXX)
 */
public interface RigidlyTransformable {
    void translate(Vector v);
    void rotate(Point p, double angle);
    void setBearing(double theta);
}