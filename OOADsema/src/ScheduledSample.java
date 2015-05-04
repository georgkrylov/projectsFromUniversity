


public class ScheduledSample implements Steppable, Comparable {
	int time;
	Agent agent;
	ScheduledSample(Agent agent, int time){
		this.agent=agent;
		this.time=time;
	}
	public int getTimeOfEvent(){
		return time;
	}

	@Override
	public int compareTo(Object o) {
		ScheduledSample otherObject = (ScheduledSample) o;
		return this.time-otherObject.time;
	}
	@Override
	public void step(int i) {
		agent.step(time,i);
		
	}
}
