import javax.swing.SwingWorker;



public class StepClass extends SwingWorker<Void,Void> {
	AbstractRunTime rt;
	public StepClass(AbstractRunTime rt) {
		this.rt = rt;
	}
	// do a step of RunTime in separate thread
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.step();
		return null;
	}



}