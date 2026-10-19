/**
 * Operations of objects that can be translated and rotated.
 *
 * @author Gian Peña (PID 6396658)
 * @author Henrique Pitta Laranjinha (PID 6527270)
 */
public interface RigidlyTransformable {
    void translate(Vector v);
    void rotate(Point p, double angle);
    void setBearing(double theta);
}