import java.util.ArrayList;
import java.util.PriorityQueue;


public interface SolutionStrategy {
	 ArrayList<Agent> agents  = new ArrayList();
	 PriorityQueue<ScheduledSample> pq =  new PriorityQueue();
	public void execute(gui gui);
}
