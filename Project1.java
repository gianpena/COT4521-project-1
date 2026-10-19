import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Project1 {

    private static final int DELAY = 15; // ms between frames
    private static final double TURN_STEP = 2; // degrees turned / frame
    private static final double MOVE_STEP = 2; // pixels moved / frame

    private Car car;
    private FrameDisplay frame;
    private Timer timer;

    private ArrayList<Integer> commands;
    private int next; 
    private double remaining; 
    private boolean started;

    public Project1() {
        Scanner input = promptForFile();
        if (input == null) {
            System.exit(0);
        }

        try {
            readInput(input);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "The input file is not in the expected format.", "Project 1", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } finally {
            input.close();
        }

        car.setInteriorColor(new Color(52, 105, 201));
        car.setBoundaryColor(new Color(125, 125, 125));
        GeometricObject[] objects = new GeometricObject[]{ car };

        frame = new FrameDisplay(objects);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        timer = new Timer(DELAY, e -> step());
        timer.setInitialDelay(500);
        timer.start();
    }

    // asks for a file name
    private Scanner promptForFile() {
        String message = "Enter the name of the input file:";
        while (true) {
            String name = JOptionPane.showInputDialog(null, message, "Project 1", JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                return null;
            }

            try {
                return new Scanner(new File(name.trim()));
            } catch (FileNotFoundException e) {
                message = "Could not open \"" + name.trim() + "\". Enter the name of the input file:";
            }
        }
    }

    private void readInput(Scanner in) {
        int lines = in.nextInt();
        car = new Car(new Point(in.nextInt(), in.nextInt()));

        commands = new ArrayList<>();
        for (int i = 2; i < lines && in.hasNextInt(); i++) {
            commands.add(in.nextInt());
        }
    }

    // advances the animation by one frame
    private void step() {
        if (next >= commands.size()) {
            timer.stop();
            return;
        }

        boolean turning = next % 2 == 0;
        if (!started) {
            remaining = turning ? turnAmount(commands.get(next)) : commands.get(next);
            started = true;
        }

        double stepSize = turning ? TURN_STEP : MOVE_STEP;
        double amount = Math.signum(remaining) * Math.min(stepSize, Math.abs(remaining));

        if (turning) {
            car.rotate(car.getCenter(), amount);
        } else {
            double radians = Math.toRadians(car.getHeading());
            car.translate(new Vector(amount * Math.cos(radians), amount * Math.sin(radians)));
        }

        remaining -= amount;
        if (remaining == 0) {
            next++;
            started = false;
        }

        frame.repaint();
    }

    // turn in degrees from the heading to the given heading
    private double turnAmount(double target) {
        double delta = (target - car.getHeading()) % 360;
        if (delta > 180) {
            delta -= 360;
        } else if (delta <= -180) {
            delta += 360;
        }
        return delta;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Project1::new);
    }
}
