import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.Arrays;

import javax.swing.JPanel;

class HistoryPanel extends JPanel {
	private String[] states;
	private int statecount=0;
	private Color [] colors ;
	int agentscount;
	private int currentTime;
	private int [] [] StateHistory;
	HistoryPanel() {
		// set a preferred size for the custom panel.
		setPreferredSize(new Dimension(6000,420));
		states = new String [20];
		colors = new Color [20];

	}

	//This repaints the components and draws name of states
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.setBackground(Color.WHITE);
		int offsetx=0;
		int offsety=0;
		for (int i=0; i<statecount;i++){
			String text = states[i];
			AffineTransform affinetransform = new AffineTransform();     
			FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
			Font font = g.getFont();
			int textWidth = (int)(font.getStringBounds(text, frc).getWidth());
			int textHeight = (int)(font.getStringBounds(text, frc).getHeight());
			g.setColor(colors[i]);
			g.drawString(states[i], offsetx, 10);
			offsetx+=textWidth+10;
			offsety=textHeight;
		}
		for (int i=0;i<Math.min(currentTime, 300);i++){
			for (int j=0;j<agentscount;j++){
				g.setColor(colors[StateHistory[i][j]]);
				g.drawRect(20*(i), offsety+20*(j), 20, 20);
				g.fillRect(20*(i),offsety+20*(j), 20, 20);
			}
			g.setColor(Color.black);
			g.drawString(Integer.toString(i), 20*(i-1),  5+20*(agentscount+1));
		}

		repaint();
	}
	//upon creation you should specify all states. in my design reader states go first
	public void addState(String s){
		states[statecount]=s;
		int R = (int) (Math.random( )*256);
		int G = (int)(Math.random( )*256);
		int B= (int)(Math.random( )*256);
		Color randomColor = new Color(R, G, B);
		colors[statecount]=randomColor;
		statecount ++;

	}
	// initialization function
	public void clearStates(int agentscount){
		statecount =0;
		this.agentscount = agentscount;
		StateHistory = new int [300][agentscount];

	}
	// append current state
	public void appendCurrentState(int simTime,int [] states){
		currentTime=simTime;
		StateHistory[simTime]=Arrays.copyOf(states, agentscount);

	}
}