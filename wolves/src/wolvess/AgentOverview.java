package wolvess;

import java.util.ArrayList;
import java.util.Iterator;

public class AgentOverview extends AgentAbstract implements Steppable {
	
	private RunTime rt;
	private int lifeTime;
	
	
	public AgentOverview(int lifeTime, RunTime rt) {
		this.rt = rt;
		this.lifeTime = lifeTime;
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		lifeTime = lifeTime - 1;
		
        Iterator it = agents.iterator();
		
		int avgx = 0;
		int avgy = 0;
		
		while (it.hasNext()) {
			AgentAbstract temp = (AgentAbstract) it.next();
			
			if (temp.getClass() == AgentRandom.class) {
				AgentRandom tempR = (AgentRandom) temp;
			
			avgx += tempR.x;
			avgy += tempR.y;
			
			}
	
		}
		
		avgx = avgx / agents.size();
		avgy = avgy / agents.size();
		
		
		
		if (avgy > 210) {
			if (agents.get(0).getClass() == AgentRandom.class) {
				AgentRandom temp = (AgentRandom) agents.get(0);
				temp.toggle = 2;
			} else {
				AgentRandom temp = (AgentRandom) agents.get(1);
				temp.toggle = 2;
			}
			System.out.println("Toggled 1");
		}
		
		if (avgy < 190) {
			if (agents.get(0).getClass() == AgentRandom.class) {
				AgentRandom temp = (AgentRandom) agents.get(0);
				temp.toggle = 1;
			} else {
				AgentRandom temp = (AgentRandom) agents.get(1);
				temp.toggle = 1;
			}
			System.out.println("Toggled 2");
		}
		
		
		if (lifeTime > 0) {
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
		}
		
		
	}

}
