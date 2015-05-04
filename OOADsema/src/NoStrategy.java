

public class NoStrategy implements SolutionStrategy {

	@Override
	public void execute(gui gui) {
		RunTime rt = new RunTime();
		agents.clear();

		pq.clear();
		Buffer buff = new Buffer(1000);
		int Somethingtowrite=0;
		Writer writer = new Writer("Hello everyone our ",buff,0,rt);
		writer.setState(1);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,0));

		writer = new Writer("World is a brilliant thing!",buff,1,rt);
		writer.setState(1);
		agents.add(writer);
		Somethingtowrite++;
		pq.add(new ScheduledSample(writer,1));
		for (int i = 0; i<2;i++){
			Reader reader = new Reader(buff,i,rt);
			reader.setState(0);
			agents.add(reader);

			pq.add(new ScheduledSample(reader,10*i));
		}
		agents.add(buff);
		gui.jPanel8.clearStates();
		gui.jPanel8.addState("Idle");
		gui.jPanel8.addState("Reader reads");
		gui.jPanel8.addState("Writer writes");

		rt.initRunTime(pq, agents,Somethingtowrite,gui);
		AbstractView rtv = new AbstractView(rt,gui);
		gui.jButton5.addActionListener(rtv);


		gui.jButton6.addActionListener(rtv);
		gui.jButton2.addActionListener(rtv);
		gui.jButton3.addActionListener(rtv);
		gui.jButton8.addActionListener(rtv);
		rt.time=0;
	}


}
