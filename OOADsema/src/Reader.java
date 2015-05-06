public class Reader extends Agent {
private int number;
private Buffer buffer;
RunTime rt;
String readerString;
 public Reader(Buffer buffer,int number,RunTime rt){
	 this.buffer=buffer;
	 this.number = number;
	 this.rt=rt;
 }
public void read(int simTime){
	readerString=buffer.read(simTime);
	
//	System.out.println("Reader "+number +":" + readerString);
}
@Override
public void step(int simTime,int i){
	rt.states[number]=1;
	state=1;
	read(simTime);
	try {
		Thread.sleep(300);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.setInactive();
	if (rt.time<300) rt.pq.add(new ScheduledSample(this,simTime+i));
}
}


