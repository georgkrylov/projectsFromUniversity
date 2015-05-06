public class FairReader extends Agent {
	private int number;
	private Buffer buffer;
	FairRunTime rt;
	String readerString;
	//int state;
	public FairReader(Buffer buffer,int number,FairRunTime rt){
		this.buffer=buffer;
		this.number = number;
		this.rt=rt;
		state=0;
	}
	public void read(int simTime){
		readerString=buffer.read(simTime);

	}
	
	// The fair case simulation is executed like this: each action does it's step. if it is blocked by
	// semaphore, it does nothing, otherwise increases its state, and returns. (actually, each step only part of
	//the code is done, depending on state
	public void step(int simTime){
		rt.states[number]=state;
		if (state == 0 ) {if (rt.r.wait("Reader "+Integer.toString(number))<=0) {
			return; 
		} 
		state++;
		rt.states[number]=state;
		return;
		} 
		if (state == 1 ) {if (rt.mutex_rdcnt.wait("Reader "+Integer.toString(number))<=0){		rt.states[number]=state; return;}state++;			rt.states[number]=state;	return;}
		if (state == 2) {
			rt.readcount++;
			if (rt.readcount == 1)
				if(rt.w.wait("Reader "+Integer.toString(number))<=0){ 		rt.states[number]=state;return;} state++;			rt.states[number]=state;	return;}
		if (state ==3) {
			rt.mutex_rdcnt.signal();          
			rt.r.signal();
			this.setActive();
			rt.rtv.repaint();
			read(simTime);
			// this sleep is needed to highlight
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.setInactive(); 
			if (rt.mutex_rdcnt.wait(
					"Reader "+Integer.toString(number))<=0){ 
				return;
				} state++;			
				rt.states[number]=state;	
				return;}
		if (state ==4){
			rt.readcount--;
			if (rt.readcount == 0)
				rt.w.signal();
			rt.mutex_rdcnt.signal();  

			
			state=0;	
		}
		rt.states[number]=state;
	}
}
