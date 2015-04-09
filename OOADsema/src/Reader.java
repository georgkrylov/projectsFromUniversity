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
public void read(){
	readerString=buffer.read();
	
	System.out.println("Reader "+number +":" + readerString);
}
@Override
public void step(int simTime,int i){
	read();
	if (simTime < 250) rt.pq.add(new ScheduledSample(this,simTime+i));
}
}


