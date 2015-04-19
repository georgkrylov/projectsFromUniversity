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
	for (int i =0;i<rt.chpw;i++){
	if (pos==writerString.length()){break;}
	buffer.append(this.writerString.charAt(pos));
	pos++;}
	
}

public void step(int simTime,int i){
//	System.out.println(simTime+"  writer "+number+"writes");
	write();
	try {
		Thread.sleep(300);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.setInactive();
	if (pos<writerString.length()) rt.pq.add(new ScheduledSample(this,simTime+i)); else rt.somethingtowrite--;
}
public String jobLeft(){
//	System.out.println(simTime+"  writer "+number+"writes");
	return writerString.substring(pos, writerString.length());
	}
}
