import javax.swing.SwingWorker;



public class StateClass extends SwingWorker<Void,Void> {
	AbstractRunTime rt;
	public StateClass(AbstractRunTime rt) {
		this.rt = rt;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.passStates();
		return null;
	}



}