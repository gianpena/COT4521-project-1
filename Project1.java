import java.awt.Color;

public class Project1 {
    public static void main(String[] args) {

        Car car = new Car(new Point(150, 200));
        car.setInteriorColor(new Color(52, 105, 201));
        car.setBoundaryColor(new Color(125, 125, 125));
        GeometricObject[] objects = new GeometricObject[]{ car };

        FrameDisplay frame = new FrameDisplay(objects);
        frame.setVisible(true);
    }
}