
public class FairWriter extends Agent {

	private String writerString="";
	private int pos;
	private int number;
	FairRunTime rt;
	int state;

	private Buffer buffer;
	public FairWriter(String writerString,Buffer buffer,int number,FairRunTime rt){
		this.writerString=writerString;
		pos=0;
		this.buffer=buffer;
		this.number = number;
		this.rt = rt;
		state=5;

	}
	public void write(int time){
		if (pos>=writerString.length()){}else{
			buffer.append(writerString.substring(pos, Math.min(pos+rt.chpw, writerString.length())), time);
			pos+=rt.chpw;}
	}

	public void step(int simTime){
		rt.states[2+number]=state;
		if (state == 5 ) {	
			if ( rt.r.wait("writer")<=0){ 
				rt.states[2+number]=state;
				return;
			} 
			state++;		
			rt.states[2+number]=state; return;
		} 
		if (state == 6 ) {
			if (rt.w.wait("writer")<=0){
				rt.states[2+number]=state; 
				return;
			} 
			state++;	
			rt.states[2+number]=state;
			return; 
		} 
		if (state == 7){
			this.setActive();
			rt.rtv.repaint();
			write(simTime);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setInactive();

			rt.w.signal();  
			rt.r.signal();

			state=5;
			rt.states[2+number]=state;
		}
	}
	public String jobLeft(){
		return writerString.substring(Math.min(pos,writerString.length()), writerString.length());
	}
}


