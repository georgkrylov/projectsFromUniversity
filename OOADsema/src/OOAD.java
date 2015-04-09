import java.util.ArrayList;
import java.util.PriorityQueue;


public class OOAD {

	static ArrayList<Agent> agents =new ArrayList();
	static PriorityQueue<ScheduledSample> pq = new PriorityQueue();
	public static void main(String[] args) 
	{
		System.out.println("hi");
		RunTime rt = new RunTime();
		Buffer buff = new Buffer(1000);
		
			Writer writer = new Writer("Hello everyone our ",buff,0,rt);
			agents.add(writer);
			pq.add(new ScheduledSample(writer,0));

			writer = new Writer("World is a brilliant thing!",buff,1,rt);
			agents.add(writer);
			pq.add(new ScheduledSample(writer,1));
		for (int i = 0; i<2;i++){
			Reader reader = new Reader(buff,i,rt);
			agents.add(reader);
			pq.add(new ScheduledSample(reader,i));
		}
		agents.add(buff);

		rt.initRunTime(pq, agents);
		rt.execute();
		System.out.println("bye");
		
		}
}
