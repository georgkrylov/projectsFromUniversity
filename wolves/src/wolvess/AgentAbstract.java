package wolvess;

import java.util.ArrayList;

public abstract class AgentAbstract implements Steppable {
	
	public ArrayList<AgentAbstract> agents;
	String buffer;
	public int x;
	public int y;
	public boolean isSelected;
		
	public abstract void step();

}
