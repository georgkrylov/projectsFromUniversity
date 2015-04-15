import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JButton;


public class RunTime  implements ActionListener {
	Random rn;
	static Boolean runBoolean=false;
	static PriorityQueue<ScheduledSample> pq;
	static ArrayList<Agent> agents;
	int time;
	JButton button;
	gui gui;
	public RunTime() {
		time=0;
		rn = new Random();
	}
	public void initRunTime(PriorityQueue<ScheduledSample> pq,ArrayList<Agent> agents){
		this.pq=pq;
		this.agents=agents;
		
	}
	public void execute(){
		while (pq.size()!=0 ){
			if (runBoolean==false) {continue;}
			time++;
			//System.out.println(pq.peek().getTimeOfEvent()+" "+Integer.toString(time));
			if (time>=pq.peek().getTimeOfEvent()){
				int i = rn.nextInt(20);
				pq.poll().step(i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {/*System.out.println("Time is: "+time+" nothing happened");*/}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//	System.out.println(e);
		//System.out.println(agents);
		//*	System.out.println(pq);*/
		if (runBoolean==true) {
			runBoolean=false;
		
		//	gui.button.setText("Play");

		} else {
			runBoolean=true;
			//gui.button.setText("Pause");
			PausePlayClass worker = new PausePlayClass(this);
			worker.execute();
		}


	}

}
