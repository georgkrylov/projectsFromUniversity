import java.util.HashMap;
import java.util.Map;


public class Buffer extends Agent {
	private static Map<Integer,String> data = new HashMap<Integer,String>();
	int size;

 public Buffer(int size){
	 this.size=size;
 }
 
//this one appends the string of writer to buffer
public void append(String s,int time){
	data.put(time,s);
}
//this is to retrieve all buffer contents
public String read(int time){
	String result="";
	for (int i=0;i<time;i++){
		if (data.containsKey(i)) result+=data.get(i);
	}
	return result;
}


}
