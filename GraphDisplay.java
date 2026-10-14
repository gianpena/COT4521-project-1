
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphDisplay extends JPanel implements MouseMotionListener
{
    GeometricObject[] gArray; //geometric objects
    private BufferedImage image; //background image
    String description; //description of map element
    
    /**
     * Parameterized constructor.
     */
    public GraphDisplay(int frameWidth, int frameHeight, GeometricObject[] g)
    {
        gArray = g;
        description = "";
        Dimension d = new Dimension(frameWidth, frameHeight);
        setPreferredSize(d); //sets the preferred size of this panel
        addMouseMotionListener(this);
        
        //opens/read image file
        try
        {
            image = ImageIO.read(new File("fiu map.png"));
        }
        catch (IOException ex)
        {
            System.out.println("Image file not found!");
        }                
    }
    
    public void mouseDragged(MouseEvent e){}

    /**
     * Determines which element the mouse button is hovering on and sets 
     * output string accordingly.
     * 
     * @param e contains current location of mouse
     */
    public void mouseMoved(MouseEvent e)
    {
        //only for coordinates lookup; remove after project completion
        System.out.println("(" + e.getPoint().x + ", " +
                           e.getPoint().y + ")");
        
        //is current mouse location inside a geometric object?
        //Set description accordingly
        int x = e.getPoint().x;
        int y = e.getPoint().y;
        Point p = new Point(e.getPoint().x, e.getPoint().y);
               
        ////////////////////////////////////////////////////////////////////////
        //DUMMY code (replace with code implementing solution to the assignment)
        if (p.getX() < 100) description = "West of Campus";
        else
            if (p.getX() > 350) description = "East of Campus";
            else description = "";
        ////////////////////////////////////////////////////////////////////////
        
        repaint();
    }
    
    /**
     * Paints this panel; the system invokes it every time
     * it deems necessary to redraw the panel.
     */
    @Override
    public void paint(Graphics g)
    {        
        super.paint(g); //clears window
        
        //draws background image
        Dimension d = getSize();
        g.drawImage(image, 0, 0, d.width, d.height, this);
        
        //outputs description
        g.setColor(new Color(64, 64, 64, 128));
        g.fillRoundRect(0, 420, 150, 30, 5, 5); //bottomleft textfield for output
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString(description, 20, 440); //outputs description of map element
    }
}
