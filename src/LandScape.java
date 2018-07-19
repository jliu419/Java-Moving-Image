import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class LandScape extends JPanel implements KeyListener, MouseListener
{
    
	private Image house, tree;
	private boolean housef, treef;// flkag is true draw the house flag is false -
										// draw the pictures using the graphics primitives
	
	private MovingImage viking, clash, sun;
	
	public LandScape()
	{
		super();
		tree = (new ImageIcon("tree.gif")).getImage();
		sun = new MovingImage("sun.jpg", 600, 275, 85, 190);
		house = (new ImageIcon("house.jpg")).getImage();
		viking = new MovingImage("viking.jpg", 600, 275, 85, 190);
		clash = new MovingImage("clash.jpg", 20, 275, 85, 190);
		
		housef = false;
		treef = false;
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_3)
		{
			sun.toggleVisibility(); 
		}
		else if (code == KeyEvent.VK_2)
		{
			treef = !treef;
		}
		else if (code == KeyEvent.VK_1)
		{
			housef = !housef; 
		}
		else if (code == KeyEvent.VK_LEFT)
		{
			viking.moveByAmount(-10, 0);
		}
		else if (code == KeyEvent.VK_RIGHT)
		{
			viking.moveByAmount(10, 0);
		}
		else if (code == KeyEvent.VK_A)
		{
			clash.moveByAmount(-10, 0);
		}
		else if (code == KeyEvent.VK_D)
		{
			clash.moveByAmount(10, 0);
		}
		repaint(); 
	}

	public void keyReleased(KeyEvent e)
	{
		
	}


	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		int w = getWidth();
		int h = getHeight();
		double rx = (double)w/800;
		double ry = (double)h/600;
		
		if (viking.isPointInImage(mx/rx, my/ry) == true)
		{
			viking.moveToLocation(500, 275);
		}
		if (clash.isPointInImage(mx/rx, my/ry) == true)
		{
			clash.moveToLocation(200, 200);
		}
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    int width = getWidth();
    int height = getHeight();
    
    double ratiox = width/800.0;
    double ratioy = height/600.0;
    Graphics2D g2 = (Graphics2D)g;
    g2.scale(ratiox, ratioy);
    // draw stuff
    // sun
    if (sun.isVisible())
    {
    	sun.draw(g, this); 
    }
    else
    {
    	g.setColor(Color.orange);
    	g.drawOval(600, 75, 100, 100); 
    }
    // trunk and tree
    if (treef)
    {
    	g.drawImage(tree, 100, 175, 300, 300, this);
    }
    else
    {  
        g.setColor(Color.black);
        g.fillRect(145, 450-175, 50, 175);
        g.setColor(Color.green);
        g.fillOval(100, 200, 150, 100);
    }
    // ceiling house
    if (housef)
    {
    	g.drawImage(house, 400, 200, 250, 250, this);
    }
    else
    {  
        g.setColor(Color.CYAN);
        g.fillRect(550, 300, 150, 150);

        g.setColor(Color.blue);
        Polygon p = new Polygon();
        p.addPoint(550, 300);
        p.addPoint(700, 300);
        p.addPoint(620, 250);
        g.fillPolygon(p);
    }
    
    // lawn
    g.setColor(Color.green);
    g.drawLine(0, 450, 800, 450);
    g.setColor(new Color(125, 0, 125));
    g.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
    g.drawString("Sunnyvale, CA, A scenic Landscape", 100, 525);
    // heros
    viking.draw(g, this);    
    clash.draw(g, this); 
  }

  // As your program grows, you may want to...
  //   1) Move this main method into its own 'main' class
  //   2) Customize the JFrame by writing a class that extends it, then creating that type of object in your main method instead.
  //   3) Rename this class (SimpleWindow is not a good name - this class actually represents the *Panel*, not the window)
  public static void main(String[] args)
  {
		JFrame w = new JFrame("Scenic Landscape");
		w.setBounds(100, 100, 800, 600);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LandScape panel = new LandScape();
		w.addKeyListener(panel);
		panel.setBackground(Color.white);
		w.add(panel);
		w.setVisible(true);
		w.setResizable(true);	
		panel.addMouseListener(panel);
  }
}
