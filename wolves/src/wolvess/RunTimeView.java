package wolvess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;

public class RunTimeView extends JComponent {
	public int x = 100;
	public int y = 100;
	public ArrayList<AgentAbstract> agents;
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
        
        g2.setPaint(Color.black);
        g2.fillRect(x, y, 12, 12);
        g2.setPaint(Color.green);
        g2.fillRect(100, 200, 12, 12);
        
        x=0;
        y=0;
        
        Iterator it = agents.iterator();
		
        g2.setPaint(Color.magenta);
		while (it.hasNext()) {
			AgentAbstract temp = (AgentAbstract) it.next();
			
			if (temp.getClass() == AgentRandom.class) {
				AgentRandom tempR = (AgentRandom) temp;
			
				x += tempR.x;
				y += tempR.y;
			
				g2.fillRect(tempR.x, tempR.y, 12, 12);
			
			}
			
		}
		x=x / agents.size();
		y=y / agents.size();
		
		g2.setPaint(Color.black);
        g2.fillRect(x, y, 12, 12);
        
     
	}


}
