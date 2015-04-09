package wolvess;

import javax.swing.SwingWorker;

public class PausePlayClass extends SwingWorker<Void,Void> {

	WolfRunTime rt;
	
	public PausePlayClass(WolfRunTime rt) {
		this.rt = rt;
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		rt.execute();
		return null;
	}

	


}
