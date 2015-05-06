import java.util.HashMap;
import java.util.Map;


public class Buffer extends Agent {
//private String data="";
	private static Map<Integer,String> data = new HashMap<Integer,String>();
int size;
 public Buffer(int size){
	 this.size=size;
 }
public void append(String s,int time){
	data.put(time,s);
}
public String read(int time){
	String result="";
	for (int i=0;i<time;i++){
		if (data.containsKey(i)) result+=data.get(i);
	}
	return result;
}


}
