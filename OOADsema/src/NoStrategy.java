import java.util.ArrayList;
import java.util.PriorityQueue;


public class NoStrategy implements SolutionStrategy {

	@Override
	public void execute(gui gui) {
		RunTime rt = new RunTime();
		agents.clear();

		pq.clear();
		Buffer buff = new Buffer(1000);
		int Somethingtowrite=0;
		Writer writer = new Writer("Hello everyone our ",buff,0,rt);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,0));

		writer = new Writer("World is a brilliant thing!",buff,1,rt);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,1));
		for (int i = 0; i<2;i++){
			Reader reader = new Reader(buff,i,rt);
			agents.add(reader);
			pq.add(new ScheduledSample(reader,i));
		}
		agents.add(buff);

		rt.initRunTime(pq, agents,Somethingtowrite);
		RunTimeView rtv = new RunTimeView(rt,gui);
		gui.jButton5.addActionListener(rtv);


		gui.jButton6.addActionListener(rtv);
		gui.jButton2.addActionListener(rtv);
		gui.jButton3.addActionListener(rtv);
		gui.jButton8.addActionListener(rtv);
		rt.time=0;
	}


}
