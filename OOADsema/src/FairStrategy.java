
public class FairStrategy implements SolutionStrategy {

	@Override
	public void execute(gui gui) {
		FairRunTime rt = new FairRunTime();
		agents.clear();

		pq.clear();
		Buffer buff = new Buffer(1000);
		int Somethingtowrite=0;
		FairWriter writer = new FairWriter("Hello everyone our ",buff,0,rt);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,0));

		writer = new FairWriter("World is a brilliant thing!",buff,1,rt);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,1));
		for (int i = 0; i<2;i++){
			FairReader reader = new FairReader(buff,i,rt);
			agents.add(reader);
			pq.add(new ScheduledSample(reader,2+i));
		}
		agents.add(buff);

		rt.initRunTime(pq, agents,Somethingtowrite);
		FairView rtv = new FairView(rt,gui);
		gui.jButton5.addActionListener(rtv);


		gui.jButton6.addActionListener(rtv);
		gui.jButton2.addActionListener(rtv);
		gui.jButton3.addActionListener(rtv);
		gui.jButton8.addActionListener(rtv);
	}
}
