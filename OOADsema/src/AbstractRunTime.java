import java.util.ArrayList;



abstract class AbstractRunTime {
	Boolean runBoolean=false;
	int time;
	int chpw = 1;
	int speed = 500;
	gui gui;
	ArrayList<Agent> agents;
	int [] states =new int [4];
	public AbstractView rtv;
	public void execute(){};
	public void step(){};

	public int getTime(){
		return time;
	}
	public void passStates(){
		System.out.println(time);
		for (int i =0;i<4;i++){System.out.print(states[i]+" ");}
		
		gui.jPanel8.appendCurrentState(time,states);

	}
}
