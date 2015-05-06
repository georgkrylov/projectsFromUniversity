
public class FairStrategy implements SolutionStrategy {

	@Override
	public void execute(gui gui) {
// 	Initialization functions
//	first you have to plug all writers, increasing writerscount
// then you plug all readers increasing readerscount
//	then call clear states and later add buffer	
//		
		FairRunTime rt = new FairRunTime();
		agents.clear();
		int writerscount =0;
		int readerscount =0;
		pq.clear();
		Buffer buff = new Buffer(1000);
		FairWriter writer = new FairWriter("Hello everyone our ",buff,0,rt);
		agents.add(writer);
		writerscount++;
		writer = new FairWriter("wedasdas",buff,1,rt);
		agents.add(writer);
		writer = new FairWriter("World is a brilliant thing!",buff,2,rt);
		agents.add(writer);
		writer = new FairWriter("weddaslk mew qke qw das",buff,3,rt);
		agents.add(writer);
		for (int i = 0; i<3;i++){
			FairReader reader = new FairReader(buff,i,rt);
			agents.add(reader);
			readerscount++;
		}
		rt.readerscount = readerscount;
		rt.writerscount = writerscount;
		gui.jPanel8.clearStates(agents.size());
		agents.add(buff);
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
