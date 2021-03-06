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
		states = new int [agents.size()-1];

	}
	//Random runTime - scheduled Sample is creaated and put in the queue
	public void execute(){
		synchronized(agents){

			while (pq.size()>0 ){
			
				if (runBoolean==false) {break;}
				
				time=pq.peek().getTimeOfEvent();
				for (int i=0;i<agents.size()-1;i++)states[i]=0;
				while (time>=pq.peek().getTimeOfEvent()){
					int i = rn.nextInt(10)+1;
					pq.peek().agent.setActive();
					rtv.repaint();
					pq.poll().step(i);


				} 

				StateClass st = new StateClass(this);

				st.execute();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rtv.repaint();

			}
		}
	}
	// one step, one next second(or previous, it is random)
	
	public void step(){

		if (pq.size()!=0 ){
			time = pq.peek().getTimeOfEvent();
			for (int i=0;i<agents.size()-1;i++)states[i]=0;
			while(time>=pq.peek().getTimeOfEvent()){
				int i = rn.nextInt(20);
				pq.peek().agent.setActive();
				rtv.repaint();
				pq.poll().step(i);

				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				rtv.repaint();
		} 
			
		StateClass st = new StateClass(this);
		st.execute();
		rtv.repaint();
		}
	}





}
