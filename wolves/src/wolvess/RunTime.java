package wolvess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RunTime extends RunTimeAbstract {
	private StringBuilder test;
	public RunTimeView rtv;
	public int lastMove;
	public int statecontroller;
	public ArrayList<AgentAbstract> agents = new ArrayList<AgentAbstract>();

	@Override
	public void initRunTime() {
		
		lastMove = 0;
		
		statecontroller = 0;
		
		for (int i=0;i<10;i++) {
			agents.add(new AgentRandom(600,this));
		}
		
		agents.add(new AgentOverview(600,this));
		
		Iterator it = agents.iterator();
		
		while (it.hasNext()) {
			AgentAbstract tempA = (AgentAbstract) it.next();
			// AgentRandom temp = (AgentRandom)it.next();
			tempA.agents = agents;
			this.addToSchedule(new ScheduledSimple((AgentAbstract) tempA,1));
		}
		
		rtv.agents = agents;
		
	}
	
	@Override 
	public void execute() {
		while (this.pq.size() > 0) {
			ScheduledSimple temp = this.pq.poll();
			currentTime = temp.simTime;
			temp.step();
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) { }
			
			rtv.repaint();
		}
		
		this.conclude();
	}

	@Override
	public void conclude() {
	//	System.out.println(currentTime);
		
	}
	
	public void setView(RunTimeView r) {
		this.rtv = r;
	}
	

	
	

}
