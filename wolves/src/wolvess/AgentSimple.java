package wolvess;

// import java.util.PriorityQueue;

public class AgentSimple extends AgentAbstract implements Steppable {
	public StringBuilder sb;
	private String s; 
	private int N;
	private RunTime rt;
	
	AgentSimple(StringBuilder sb, String s, int N, RunTime rt) {
		this.sb = sb;
		this.s = s;
		this.N = N;
		this.rt = rt;
	}
	
	public void step() {
		sb.append(" " + s);
		N = N - 1;
		if (N > 0) {
			// rt.pq.offer(new ScheduledSimple(this,rt.currentTime+1));
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
		}
	}

}
