import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class RunTime {
	Random rn;
	static PriorityQueue<ScheduledSample> pq;
	static ArrayList<Agent> agents;
	int time;
	public RunTime() {
		time=0;
		rn = new Random();
	}
	public void initRunTime(PriorityQueue<ScheduledSample> pq,ArrayList<Agent> agents){
		this.pq=pq;
		this.agents=agents;
		
	}
	public void execute(){
		while (pq.size()!=0){
			time++;
			//System.out.println(pq.peek().getTimeOfEvent()+" "+Integer.toString(time));
			if (time>=pq.peek().getTimeOfEvent()){
				int i = rn.nextInt(20);
				pq.poll().step(i);
		
			} else {/*System.out.println("Time is: "+time+" nothing happened");*/}
		}
	}
}
