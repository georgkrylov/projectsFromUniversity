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
		} else {/*System.out.println("Time is: "+time+" nothing happened");*/}
		rtv.repaint();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Apply Strategy"){System.out.println("Not Implemented yet");}
		if (e.getActionCommand()=="Step"){
			if (runBoolean==true) {
				runBoolean=false;
				gui.jButton5.setText("Play");
			} 
			StepClass worker = new StepClass(this);
			worker.execute();
		}
		if (e.getActionCommand()=="Apply"){
			if ((gui.jTextField1.getText().matches(".*\\d+.*"))){
			int i = 1;
			i = Integer.parseInt(gui.jTextField1.getText());
		//	System.out.println(i);
			chpw = i;
			}
		}
		if (e.getActionCommand()=="Speed up"){if (speed>50){speed-=50;}};
		if (e.getActionCommand()=="Speed down"){speed+=50;};
		if (e.getActionCommand() == "Play" || e.getActionCommand() == "Pause" ){
			if (runBoolean==true) {
				runBoolean=false;

				gui.jButton5.setText("Play");

			} else {
				runBoolean=true;
				gui.jButton5.setText("Pause");
				PausePlayClass worker = new PausePlayClass(this);
				worker.execute();

			}
		}

	}
	public int getTime(){
		return time;
	}

}
