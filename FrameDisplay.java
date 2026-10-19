import javax.swing.*;

public class FrameDisplay extends JFrame
{   
    int WIDTH = 450;
    int HEIGHT = 450;
    
    private GraphDisplay panel;

    /**
     * Creates an initializes the window frame with no geometric objects.
     */
    public FrameDisplay()
    {
        this(new GeometricObject[0]);
    }

    /**
     * Creates an initializes the window frame.
     *
     * @param g geometric objects to be depicted in this frame
     */
    public FrameDisplay(GeometricObject[] g)
    {
        setTitle("Project 1");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

        panel = new GraphDisplay(WIDTH, HEIGHT, g);        
        add(panel);

        pack(); //sizes the frame so that the panel is at, or above,
                //the preferred size
    }
}
