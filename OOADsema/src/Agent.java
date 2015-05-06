
public abstract class Agent {
	boolean active = false;
	int state;
	public void step(int simTime,int i) {
		// TODO Auto-generated method stub
		
	}
	// whenever Agent is active, it is highlighted
	public void setActive(){
		active = true;
	}
	public void setInactive(){
		active = false;
	}
	
	//States are required to display in history
	public void setState(int i){
		state=i;
	}
	public int getState(){
		return state;
	}
	public void step(int time) {
		// TODO Auto-generated method stub
		
	}
}
