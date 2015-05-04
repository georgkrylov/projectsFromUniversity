
public class FairStrategy implements SolutionStrategy {

	@Override
	public void execute(gui gui) {
		FairRunTime rt = new FairRunTime();
		agents.clear();

		pq.clear();
		Buffer buff = new Buffer(1000);
		FairWriter writer = new FairWriter("Hello everyone our ",buff,0,rt);
		agents.add(writer);

		writer = new FairWriter("World is a brilliant thing!",buff,1,rt);
		agents.add(writer);
		for (int i = 0; i<2;i++){
			FairReader reader = new FairReader(buff,i,rt);
			agents.add(reader);
			pq.add(new ScheduledSample(reader,2+i));
		}
		agents.add(buff);
		gui.jPanel8.clearStates();
		gui.jPanel8.addState("Reader waits for resource R");
		gui.jPanel8.addState("Reader waits for resource M");
		gui.jPanel8.addState("Reader waits for resource W");
		gui.jPanel8.addState("Reader releases M and R, reads and then waits for M");
		gui.jPanel8.addState("Reader releases W and M");
		gui.jPanel8.addState("Writer waits R");	
		gui.jPanel8.addState("Writer waits W");	
		gui.jPanel8.addState("Writer writes and releases W and R");	
		rt.initRunTime(agents,gui);
		AbstractView rtv = new AbstractView(rt,gui);
		gui.jButton5.addActionListener(rtv);

		gui.jButton6.addActionListener(rtv);
		gui.jButton2.addActionListener(rtv);
		gui.jButton3.addActionListener(rtv);
		gui.jButton8.addActionListener(rtv);

	}
}
