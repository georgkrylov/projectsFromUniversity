
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
	public void write(){
		for (int i =0;i<rt.chpw;i++){
			if (pos==writerString.length()){break;}
			buffer.append(this.writerString.charAt(pos));
			pos++;}

	}

	public void step(int simTime){
		//		System.out.println(simTime+"  writer "+number+"writes");
		System.out.println(simTime+"writer "+number+" state "+state);
		if (state == 5 ) {	if ( rt.r.wait("writer "+Integer.toString(number))<=0){ 			rt.states[2+number]=state;
 return;} state++;		rt.states[2+number]=state; return;} 
		if (state == 6 ) {	if (rt.w.wait("writer"+Integer.toString(number))<=0){rt.states[2+number]=state; return;} state++;	rt.states[2+number]=state;	return; } 
		if (state == 7){
		this.setActive();
		rt.rtv.repaint();
    	write();
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
		// rt.pq.add(new ScheduledSample(this,simTime+4)); else rt.somethingtowrite--;
	}
	public String jobLeft(){
		//		System.out.println(simTime+"  writer "+number+"writes");
		return writerString.substring(pos, writerString.length());
	}
}


