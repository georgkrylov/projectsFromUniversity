package wolvess;

import java.util.PriorityQueue;

public abstract class RunTimeAbstract {
	
	protected PriorityQueue<ScheduledSimple> pq;
	public int currentTime;
	
	public RunTimeAbstract() {
		this.pq = new PriorityQueue<>();
		this.currentTime = 0;
	}
	
	public void execute() {
		while (pq.size() > 0) {
			ScheduledSimple temp = pq.poll();
		
			currentTime = temp.simTime;
			temp.step();
		}
		
		this.conclude();
	}
	
	public void addToSchedule(ScheduledSimple ss) {
		if (ss.simTime >= this.currentTime) {
			this.pq.offer(ss);
		}
	}
	
	public abstract void initRunTime(); 
	
	public abstract void conclude();

}
