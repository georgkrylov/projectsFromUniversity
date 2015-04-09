package wolvess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AgentRandom extends AgentAbstract implements Steppable {
	private RunTime rt;
	private int lifeTime; 
	public int x=100;
	public int y=200;
	public int toggle;
	private Random rn = new Random();
	
	
	AgentRandom(int lifeTime, RunTime rt) {
		this.rt = rt;
		this.lifeTime = lifeTime;
		toggle = 0;
	}
	
	public void step() {
		lifeTime = lifeTime - 1;
		
		
        Iterator it = agents.iterator();
		
        double founddist = 300.0;
		double dist = 300.0;
		AgentRandom foundAgent;
		foundAgent = this;
		
		int avgx = 0;
		int avgy = 0;
		
		while (it.hasNext()) {
			AgentAbstract temp = (AgentAbstract) it.next();
			
			if (temp.getClass() == AgentRandom.class) {
				AgentRandom tempR = (AgentRandom) temp;
			
			avgx += tempR.x;
			avgy += tempR.y;
			
			if (temp != this) {
				dist = Math.sqrt(Math.pow((double)this.x - tempR.x, 2.) + Math.pow((double)this.y-tempR.y, 2.));
				if (dist < founddist) {
					founddist = dist;
					foundAgent = tempR;
				}
			}	
			
			}
			
		}
		
		avgx = avgx / agents.size();
		avgy = avgy / agents.size();
		
		if (Math.abs(avgx - this.x) > 100) {
			
			if (this.x < avgx) {
				x=x+2;
			}
			
			if (this.x > avgx) {
				x=x-2;
			}
			
		}
		
		
		
		if (toggle == 1) {
			y=y+5;
		}
		
		if (toggle == 2) {
			y=y-5;
		}
		
		
		if (Math.abs(avgy - this.y) > 100) {
			
			if (this.y < avgy) {
				y=y+1;
			}
			
			if (this.y > avgy) {
				y=y-1;
			}
			
		}

		
		if (Math.abs(avgy - this.y) > 100) {
			
		}
		
		if (this.x < foundAgent.x) {
			x=x-1;
		}
		
		if (this.x > foundAgent.x) {
			x=x+1;
		}
		
		if (this.y < foundAgent.y) {
			y=y-1;
		}
		
		if (this.y > foundAgent.y) {
			y=y+1;
		}
		
		
		switch (rn.nextInt(5)) {
		case 0:
			x=x+5;
			rt.lastMove = 0;
			break;
		case 1:
			x=x-5;
			rt.lastMove = 1;
			break;
		case 2:
			y=y+5;
			rt.lastMove = 2;
			break;
		case 3:
			y=y-5;
			rt.lastMove = 3;
			break;
		case 4:
			x=x+6;
			rt.lastMove = 4;
		}
		
		
		
		if (lifeTime > 0) {
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
		}
	}

}
