
import javax.swing.*;
import java.awt.*;

/**
 * This class represents the panel where geometric objects are drawn.
 *
 * Based on code from the COT4521 course materials.
 *
 * @author Gian Peña (PID 6396658)
 * @author Henrique Pitta Laranjinha (PID 6527270)
 */
public class GraphDisplay extends JPanel
{
    GeometricObject[] gArray; //geometric objects

    /**
     * Parameterized constructor.
     */
    public GraphDisplay(int frameWidth, int frameHeight, GeometricObject[] g)
    {
        gArray = g;
        Dimension d = new Dimension(frameWidth, frameHeight);
        setPreferredSize(d); //sets the preferred size of this panel
    }

    /**
     * Paints this panel; the system invokes it every time
     * it deems necessary to redraw the panel.
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g); //clears window

        //draws the geometric objects given to this panel
        for (GeometricObject obj : gArray)
        {
            if (obj != null)
            {
                obj.draw(g);
            }
        }
    }
}
