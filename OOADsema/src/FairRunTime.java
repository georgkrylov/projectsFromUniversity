import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class FairRunTime {
	Random rn;
	Boolean runBoolean=false;
	static PriorityQueue<ScheduledSample> pq;
	static ArrayList<Agent> agents;
	int time;
	boolean writeroneDone = false;
	boolean writertwoDone = false;
	
	int chpw = 1;
	int speed = 500;
	gui gui;
	FairView rtv;
	public int somethingtowrite;
	int readcount=0; // (initial value = 0)
	Semaphore mutex_rdcnt, r, w; // ( initial value = 1 ) 
	public FairRunTime() {
		time=0;
		rn = new Random();
	}
	public void initRunTime(PriorityQueue<ScheduledSample> pq,ArrayList<Agent> agents, int somethingtowrite){
		this.pq=pq;
		this.agents=agents;
		this.somethingtowrite=somethingtowrite;
		mutex_rdcnt =new Semaphore(this,"mutex_rdcnt");
		r = new Semaphore (this,"r");
		w = new Semaphore (this,"w");
	}
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
				rtv.repaint();
			}
		}
	}


	public void step(){
		time++;
		for(Agent a:agents){
			a.setActive();
			rtv.repaint();
			a.step(time);
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		rtv.repaint();
		System.out.println();System.out.println();

	}



	public int getTime(){
		return time;
	}

}

