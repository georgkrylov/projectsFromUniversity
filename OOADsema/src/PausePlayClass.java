import javax.swing.SwingWorker;



public class PausePlayClass extends SwingWorker<Void,Void> {
	RunTime rt;
	
	public PausePlayClass(RunTime rt) {
		this.rt = rt;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.execute();
		return null;
	}



}