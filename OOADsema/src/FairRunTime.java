import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class FairRunTime extends AbstractRunTime {
	ArrayList<Agent> agents;
	int readcount=0; // (initial value = 0)
	Semaphore mutex_rdcnt, r, w; // ( initial value = 1 ) 
	public FairRunTime() {
		time=-1;
	}
	public void initRunTime(ArrayList<Agent> agents, gui gui){
		super.agents=agents;
		this.agents=agents;
		mutex_rdcnt =new Semaphore(this,"mutex_rdcnt");
		r = new Semaphore (this,"r");
		w = new Semaphore (this,"w");
		super.gui = gui;
	}
//	@Override
	public void execute(){
		synchronized(agents){
		while (time<300){
				if (runBoolean==false) {break;}
				time++;
				for(Agent a:agents){
					a.step(time);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} 
				StateClass st = new StateClass(this);

				st.execute();
				rtv.repaint();
			}
		}
	}


	public void step(){
		time++;
		for(Agent a:agents){
			a.step(time);
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		StateClass st = new StateClass(this);
		st.execute();
		rtv.repaint();
	
	}





}

