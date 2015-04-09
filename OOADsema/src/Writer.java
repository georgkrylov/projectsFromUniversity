import java.util.Random;


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
public void write(){
	buffer.append(this.writerString.charAt(pos));
	pos++;
}

public void step(int simTime,int i){
//	System.out.println(simTime+"  writer "+number+"writes");
	write();
	if (pos<writerString.length()) rt.pq.add(new ScheduledSample(this,simTime+i));
}
}
