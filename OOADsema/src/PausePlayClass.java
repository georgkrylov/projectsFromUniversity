import javax.swing.SwingWorker;



public class PausePlayClass extends SwingWorker<Void,Void> {
	AbstractRunTime rt;
	int index;
	public PausePlayClass(AbstractRunTime rt) {
		this.rt = rt;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.execute();
		return null;
	}

}