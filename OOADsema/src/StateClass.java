import javax.swing.SwingWorker;



public class StateClass extends SwingWorker<Void,Void> {
	AbstractRunTime rt;
	public StateClass(AbstractRunTime rt) {
		this.rt = rt;
	}
	// pass states to historyPanel in separate thread
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.passStates();
		return null;
	}



}