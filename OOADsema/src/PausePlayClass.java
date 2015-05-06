import javax.swing.SwingWorker;



public class PausePlayClass extends SwingWorker<Void,Void> {
	AbstractRunTime rt;
	public PausePlayClass(AbstractRunTime rt) {
		this.rt = rt;
	}
	//this class is required to create thread to do execute method of rt.
	@Override
	protected Void doInBackground() throws Exception {
		rt.execute();
		return null;
	}

}