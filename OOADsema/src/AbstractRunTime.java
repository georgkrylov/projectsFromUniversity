import java.util.ArrayList;



abstract class AbstractRunTime {
	Boolean runBoolean=false;
	int time;
	int chpw = 1;
	int speed = 500;
	gui gui;
	int readerscount;
	int writerscount;
	ArrayList<Agent> agents;
	int [] states;
	public AbstractView rtv;
	public void execute(){};
	public void step(){};

	public int getTime(){
		return time;
	}
	// This function gives states of each agent reader-writer to gui class 
	public void passStates(){
		gui.jPanel8.appendCurrentState(time,states);

	}
}
