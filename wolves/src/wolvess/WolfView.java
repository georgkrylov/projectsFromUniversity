package wolvess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.stream.Collectors;



import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

public class WolfView extends JComponent {
	
	public int x = 100;
	public int y = 100;
	public ArrayList<AgentAbstract> agents;
	public ArrayList<AgentAbstract> buff;
	public ArrayList<AgentAbstract> buff2;
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(500,500);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(500,500);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
        /*      
        g2.setPaint(Color.green);
        g2.fillRect(AntAgent.A[0], AntAgent.A[1], 12, 12);
        g2.setPaint(Color.green);
        g2.fillRect(AntAgent.B[0], AntAgent.B[1], 12, 12);
        g2.setPaint(Color.green);
        g2.fillRect(AntAgent.C[0], AntAgent.C[1], 12, 12);
        */
        x=0;
        y=0;
        
    /*    List<AntAgent> aa = agents.stream().filter((AgentAbstract a) -> a.getClass() == AntAgent.class)
        .map((AgentAbstract a) -> (AntAgent) a)
        .collect(Collectors.toList());
        */
      
	/*	
        g2.setPaint(Color.magenta);
		while (it.hasNext()) {
			
			AntAgent tempR = (AntAgent) it.next();
			
			g2.fillRect(tempR.x, tempR.y, 12, 12);
		
			
		}
	*/	
        g2.setPaint(Color.white);
     //   g2.clearRect(0, 0, 500,500);
        g2.fillRect(0,0,500,500);
        synchronized(agents){
        Iterator it = agents.iterator();
		while (it.hasNext()) {
			AgentAbstract temp = (AgentAbstract) it.next();
			if (temp!=null){
			if (temp.getClass() == PupAgent.class) {
				PupAgent tempR = (PupAgent) temp;
			
                                g2.setPaint(Color.blue);
				g2.fillRect(tempR.x, tempR.y, 12, 12);
			
			}
                        if (temp.getClass() == WolfAgent.class) {
				WolfAgent tempR = (WolfAgent) temp;
                                 g2.setPaint(Color.black);
                                g2.fillRect(tempR.x, tempR.y, 12, 12);
                                }
                        if (temp.getClass() == FemaleWolfAgent.class) {
				FemaleWolfAgent tempR = (FemaleWolfAgent) temp;
			
                                 g2.setPaint(Color.pink);
				g2.fillRect(tempR.x, tempR.y, 12, 12);
			
			}
                     if (temp.getClass() == AlphaAgent.class) {
				AlphaAgent tempR = (AlphaAgent) temp;
			
                                 g2.setPaint(Color.green);
				g2.fillRect(tempR.x, tempR.y, 12, 12);
				}
                     if (temp.getClass() == FoodAgent.class) {
         				FoodAgent tempR = (FoodAgent) temp;
         			
                                          g2.setPaint(Color.orange);
         				g2.fillRect(tempR.x, tempR.y, 12, 12);
                     }
			} else continue;
		}
		agents.addAll(buff);
		buff.clear();
		agents.removeAll(buff2);
		buff2.clear();
        }
	}

}
