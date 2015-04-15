
public class Buffer extends Agent {
private String data="";
int size;
 public Buffer(int size){
	 this.size=size;
 }
public void append(char c){
	if (size<=data.length()) {System.out.println("Error appending,overflow"); return; }
	data=data+c;
}
public String read(){
	return data;
}


}
