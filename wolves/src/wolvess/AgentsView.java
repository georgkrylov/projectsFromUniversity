package wolvess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class AgentsView extends JComponent {
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

        int w = 12;
        int h = 12;
        
        int x= 100;
        int y= 100;
        
        g2.setPaint(Color.black);
        g2.fillRect(x, y, w, h);
        
     
	}

}
