import javax.swing.SwingWorker;



public class StepClass extends SwingWorker<Void,Void> {
	RunTime rt;
	FairRunTime rt2;
	int index;
	public StepClass(RunTime rt) {
		this.rt = rt;
		index=0;
	}
	
	public StepClass(FairRunTime rt2) {
		// TODO Auto-generated constructor stub
		this.rt2 = rt2;
		index =1;
	}

	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		if (index ==0) {rt.step();}
		if (index ==1) {rt2.step();}
		return null;
	}



}