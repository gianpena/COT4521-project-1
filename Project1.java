import java.awt.Color;

public class Project1 {
    public static void main(String[] args) {

        Point p = new Point(100,200);
        p.setInteriorColor(Color.BLUE);
        GeometricObject[] objects = new GeometricObject[]{ p };

        FrameDisplay frame = new FrameDisplay(objects);
        frame.setVisible(true);
    }
}