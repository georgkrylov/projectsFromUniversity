package wolvess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JWindow;

public class WolfRunTime extends RunTimeAbstract implements ActionListener, MouseListener  {

	public WolfView rtv;
	public ArrayList<AgentAbstract> agents = new ArrayList<AgentAbstract>();
	public ArrayList<AgentAbstract> buff = new ArrayList<AgentAbstract>();
	public ArrayList<AgentAbstract> buff2 = new ArrayList<AgentAbstract>();
	private boolean runBoolean = false;
	StringBuilder sb;
	public JButton button;
	public JLabel label;
	public JWindow queueFrame;
	public JTextArea textArea;
	public JTextArea dataArea;

	@Override
	public void initRunTime() {



		Random rn = new Random();

		for (int i=0;i<20;i++) {
			agents.add(new PupAgent(3650,this, rn.nextInt(2),0,0));
		}


		Iterator it = agents.iterator();

		int l = 1;

		while (it.hasNext()) {
			AgentAbstract tempA = (AgentAbstract) it.next();
			tempA.agents = agents;
			this.addToSchedule(new ScheduledSimple((AgentAbstract) tempA,l));
			l += 10;
		}

		rtv.agents = agents;
		rtv.buff = buff;
		rtv.buff2 = buff2;


	}


	// @Override 
	public void execute() {
		sb = new StringBuilder();
		while ((this.pq.size() > 0) && runBoolean) {
			ScheduledSimple temp = this.pq.poll();
			//	System.out.println("poll is ok"+ Integer.toString(currentTime));
			if (!agents.contains(temp.agentA)) continue;
			if (temp == null) {	System.out.println("TempIsNull"+ Integer.toString(currentTime));continue;}
			if (temp.agentA==null) {System.out.println("TempIsNull"+ Integer.toString(currentTime));continue;}
			//System.out.println(this.pq.size());
			currentTime = temp.simTime;

			sb.append("\n");
			temp.step();
			sb.append(temp.getDescription());
			//	System.out.println("Step is ok"+ Integer.toString(currentTime));
			label.setText(Integer.toString(currentTime));
			//	System.out.println("Step is ok"+ Integer.toString(currentTime));
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) { System.out.println("Error with sleep");}

			rtv.repaint();
			//	System.out.println("Repaint is ok"+ Integer.toString(currentTime));
			//	System.out.println(runBoolean);
		}

		this.conclude();
	}

	@Override
	public void conclude() {
		//	System.out.println(currentTime);

	}

	public void setView(WolfView r) {
		this.rtv = r;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//	System.out.println(e);
		//System.out.println(agents);
		//*	System.out.println(pq);*/
		if (runBoolean==true) {
			runBoolean=false;
			textArea.setText(sb.toString());
			queueFrame.setVisible(true);
			button.setText("Play");

		} else {
			runBoolean=true;
			button.setText("Pause");
			queueFrame.setVisible(false);
			PausePlayClass worker = new PausePlayClass(this);
			worker.execute();
		}


	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mX = e.getX()-5;int mY = e.getY()-10;double distMin = 10000.0;double distTemp = 0.;
		AgentAbstract nearest;
		synchronized(agents){
			nearest = agents.get(0);
			Iterator it = agents.iterator();
			while (it.hasNext()) {
				AgentAbstract tempA = (AgentAbstract) it.next();
				tempA.isSelected = false;
				distTemp = Math.sqrt(Math.pow((double) tempA.x - mX ,2.)
						+ Math.pow((double) tempA.y - mY ,2.));
				if (distTemp < distMin) {
					distMin = distTemp;
					nearest = tempA;
					System.out.println("Whatever");
				}
			}
			nearest.isSelected = true;
		}
		String phrase = nearest.getClass().toString();
		String delims = "[.]";
		String[] tokens = phrase.split(delims);

		phrase=tokens[1];
		System.out.println(phrase);
		System.out.println(mX);
		System.out.println(mY);
		switch (phrase)
		{
		case "PupAgent": 
		{	
			PupAgent temp = (PupAgent) nearest;
			dataArea.setText("Pup"+"\n " +
					Integer.toString(nearest.x)+" "+Integer.toString(nearest.y));
			dataArea.append("\n "+Integer.toString(3650-temp.lifeTime)+" days old");
		}
		break;
		case "WolfAgent": 
		{	
			WolfAgent temp = (WolfAgent) nearest;
			dataArea.setText("Wolf"+"\n " +
					Integer.toString(temp.x)+" "+Integer.toString(nearest.y));
			dataArea.append("\n "+Integer.toString(3650-temp.lifeTime)+" days old");
		}
		break;
		case "FemaleWolfAgent": 
		{	
			FemaleWolfAgent temp = (FemaleWolfAgent) nearest;
			dataArea.setText("Female wolf"+"\n " +
					Integer.toString(nearest.x)+" "+Integer.toString(nearest.y));
			dataArea.append("\n "+Integer.toString(3650-temp.lifeTime)+" days old");
			if (temp.pregnant==1) dataArea.append("\n Is pregnant!"); else dataArea.append("\n Is looking for wolf");
		}
		break;
		case "AlphaAgent": 
		{	
			AlphaAgent temp = (AlphaAgent ) nearest;
			dataArea.setText("Alpha wolf"+"\n " +
					Integer.toString(nearest.x)+" "+Integer.toString(nearest.y));
			dataArea.append("\n "+Integer.toString(3650-temp.lifeTime)+" days old");
			dataArea.append("\n" +"Is looking for food!");
		}
		break;
		case "FoodAgent": 
		{	
			FoodAgent temp = (FoodAgent) nearest;
			dataArea.setText("Food"+"\n " +
					Integer.toString(nearest.x)+" "+Integer.toString(nearest.y));
			dataArea.append("\n" +Integer.toString(temp.amount)+" will be fed!");
		}
		break;
		}
		//rtv.repaint();
		//System.out.println("Heya!");	
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
