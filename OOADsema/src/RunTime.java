import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class RunTime extends AbstractRunTime {
	Random rn;

	static PriorityQueue<ScheduledSample> pq;




	public int somethingtowrite;
	public RunTime() {
		time=-1;
		rn = new Random();
	}
	
	public void initRunTime(PriorityQueue<ScheduledSample> pq,ArrayList<Agent> agents, int somethingtowrite,gui gui){
		super.agents=agents;
		super.gui = gui;
		this.pq=pq;

	}
	public void execute(){
		synchronized(agents){

			while (time<300 ){
			
				if (runBoolean==false) {break;}
				
				time=pq.peek().getTimeOfEvent();
				for (int i=0;i<4;i++)states[i]=0;
				while (time>=pq.peek().getTimeOfEvent()){
					int i = rn.nextInt(20);
					pq.peek().agent.setActive();
					rtv.repaint();
					pq.poll().step(i);
				
				} 

				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				StateClass st = new StateClass(this);

				st.execute();
				rtv.repaint();

			}
		}
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
		} else {	for (int i=0;i<3;i++)states[i]=3;}
		StateClass st = new StateClass(this);
		st.execute();
		rtv.repaint();

	}





}
