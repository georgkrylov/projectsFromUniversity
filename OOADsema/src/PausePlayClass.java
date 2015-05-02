import javax.swing.SwingWorker;



public class PausePlayClass extends SwingWorker<Void,Void> {
	RunTime rt;
	FairRunTime rt2;
	int index;
	
	public PausePlayClass(RunTime rt) {
		this.rt = rt;
		index=0;
	}
	
	public PausePlayClass(FairRunTime rt2) {
		// TODO Auto-generated constructor stub
		this.rt2 = rt2;
		index=1;
	}

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		if (index ==0) {rt.execute();}
		if (index ==1) {rt2.execute();}
		return null;
	}



}