public class FairReader extends Agent {
	private int number;
	private Buffer buffer;
	FairRunTime rt;
	String readerString;
	int state;
	public FairReader(Buffer buffer,int number,FairRunTime rt){
		this.buffer=buffer;
		this.number = number;
		this.rt=rt;
		state=0;
	}
	public void read(){
		readerString=buffer.read();

		//	System.out.println("Reader "+number +":" + readerString);
	}
	public void step(int simTime){
	//	System.out.println("reader "+number+" state "+state);
		if (state == 0 ) {if (rt.r.wait("Reader "+Integer.toString(number))<=0) {
		//	System.out.println("Reader"+number+" waits  r");
			return; 
		} 
		state++;
		return;
		} 
		if (state == 1 ) {if (rt.mutex_rdcnt.wait("Reader "+Integer.toString(number))<=0){ return;}state++;		return;}
		if (state == 2) {
			rt.readcount++;
			if (rt.readcount == 1)
				if(rt.w.wait("Reader "+Integer.toString(number))<=0){ return;} state++;		return;}
		if (state ==3) {
			rt.mutex_rdcnt.signal();          
			rt.r.signal();
			this.setActive();
			rt.rtv.repaint();
			read();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setInactive(); 
			if (rt.mutex_rdcnt.wait("Reader "+Integer.toString(number))<=0){ return;} state++;		return;}
		if (state ==4){
			rt.readcount--;
			if (rt.readcount == 0)
				rt.w.signal();
			rt.mutex_rdcnt.signal();  


			
			state=0;		return;
		}
	}
}
