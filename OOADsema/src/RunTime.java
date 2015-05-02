import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class RunTime {
	Random rn;
	Boolean runBoolean=false;
	static PriorityQueue<ScheduledSample> pq;
	static ArrayList<Agent> agents;
	int time;
	int chpw = 1;
	int speed = 500;
	gui gui;
	RunTimeView rtv;
	public int somethingtowrite;
	public RunTime() {
		time=0;
		rn = new Random();
	}
	public void initRunTime(PriorityQueue<ScheduledSample> pq,ArrayList<Agent> agents, int somethingtowrite){
		this.pq=pq;
		this.agents=agents;
		this.somethingtowrite=somethingtowrite;

	}
	public void execute(){
		synchronized(pq){
			while (pq.size()!=0 ){
				if (runBoolean==false) {break;}

				time++;
				if (time>=pq.peek().getTimeOfEvent()){
					int i = rn.nextInt(20);
					pq.peek().agent.setActive();
					rtv.repaint();
					pq.poll().step(i);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {/*System.out.println("Time is: "+time+" nothing happened");*/}
				rtv.repaint();

			}}
	}

	public void step(){

		if (pq.size()!=0 ){
			time = pq.peek().getTimeOfEvent();
			int i = rn.nextInt(20);
			pq.peek().agent.setActive();
			rtv.repaint();
			pq.poll().step(i);
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {/*System.out.println("Time is: "+time+" nothing happened");*/}
		rtv.repaint();

	}



	public int getTime(){
		return time;
	}

}
