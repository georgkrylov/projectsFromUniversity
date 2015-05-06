public class Writer extends Agent{
private String writerString="";
private int pos;
private int number;
RunTime rt;

private Buffer buffer;
 public Writer(String writerString,Buffer buffer,int number,RunTime rt){
	 this.writerString=writerString;
	 pos=0;
	 this.buffer=buffer;
	 this.number = number;
	 this.rt = rt;

	 
 }
public void write(int time){
	if (pos>=writerString.length()){return;}
	buffer.append(writerString.substring(pos, Math.min(pos+rt.chpw, writerString.length())), time);
	pos+=rt.chpw;
	
}

public void step(int simTime,int i){
	rt.states[2+number]=2;
	state=2;	
	write(simTime);
	try {
		Thread.sleep(300);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	this.setInactive();
	if (pos<writerString.length()) rt.pq.add(new ScheduledSample(this,simTime+i)); else rt.somethingtowrite--;
}
public String jobLeft(){
	return writerString.substring(Math.min(pos,writerString.length()), writerString.length());
	}
}
