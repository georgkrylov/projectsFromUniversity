

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author georgiy.krylov
 */
package wolvess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FemaleWolfAgent extends AgentAbstract {

	WolfRunTime rt;
	int lifeTime;
	int destinationx;
	int destinationy;
	int dx,distx,disty,dy;

	int pregnant;
	int pregnancyTime;
	Random rn;

	public FemaleWolfAgent(int lifeTime, WolfRunTime rt,int x, int y) {
		this.rt = rt;
		this.lifeTime = lifeTime;
		this.x=x;
		this.y=y;
		pregnant=0;
		rn = new Random();
		rn= new Random();
		distx = rn.nextInt(80)-40;
		disty = rn.nextInt(80)-40;
		pregnancyTime=0;
		buffer = "Female created; ";
	}
	@Override
	public void step() {
		buffer = "";
		lifeTime = lifeTime - 1;
		if (this.lifeTime>0) {
			move();
			//System.out.println("its female's step"+Integer.toString(rt.currentTime));
			if (pregnant == 0){
				buffer += " Seeking for a wolf; ";
				synchronized(rt.agents){
				Iterator it = rt.agents.iterator();
				while (it.hasNext()){
				AgentAbstract temp = (AgentAbstract) it.next();
					if (temp.getClass() == WolfAgent.class ) {
						WolfAgent tempR = (WolfAgent) temp;
						if (tempR.busy==0) {
							buffer += "Found a wolf, became pregnant; ";
							tempR.setBusy();
							this.pregnancyTime=rt.currentTime+30;
							this.pregnant=1;


							break;
						}
					}
					if (temp.getClass() == AlphaAgent.class){
						AlphaAgent tempR = (AlphaAgent) temp;
						if (tempR.busy==0) {
							tempR.setBusy();
							buffer += "Found an alpha wolf, became pregnant; ";
							this.pregnancyTime=rt.currentTime+30;
							this.pregnant=1;


							break;
						}
					}
					
				}
				}
			
			} else {
				//System.out.println("pregnant female turn"+ Integer.toString(rt.currentTime));

				if (pregnancyTime==rt.currentTime){
					buffer += "Produced 6 pups; ";
					for (int i=0;i<6;i++) {
						PupAgent t = new PupAgent(3650,rt, rn.nextInt(2),x-50,y-50);
						rt.buff.add(t);
						rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+i*10));
						
					}

				}
				if (rt.currentTime==this.pregnancyTime+335) {pregnant=0;  
				synchronized(rt.agents){
				Iterator it = rt.agents.iterator();
				while (it.hasNext()) {
					AgentAbstract temp = (AgentAbstract) it.next();
					if (temp.getClass() == WolfAgent.class) {
						WolfAgent tempR = (WolfAgent) temp;
						if (tempR.busy==1) {
							tempR.setFree();
							buffer += " Ready to become pregnant; ";
							this.pregnant=0;
							break;

						}

					}
					if (temp.getClass() == AlphaAgent.class){
						AlphaAgent tempRR = (AlphaAgent) temp;
						if (tempRR.busy==1) {
							tempRR.setFree();
							buffer += " Ready to become pregnant; ";
							this.pregnant=0;
							break;
						}
					}
				}

				}
				}
				//rt.agents.remove(this);
			}
		//	System.out.println("FInished female step"+Integer.toString(rt.currentTime));
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
		} else  {rt.buff2.add(this);							buffer += "Female wolf died; ";}


	}
	 public void move(){
			buffer += " Following alpha; ";
	    	synchronized(rt.agents){
				Iterator it = rt.agents.iterator();
				while (it.hasNext()){
					AgentAbstract temp = (AgentAbstract) it.next();
					if (temp.getClass()==AlphaAgent.class){
						AlphaAgent tempR = (AlphaAgent) temp;
					
						if (this.x != tempR.x+distx){
							
					    	dx=(tempR.x+distx-x);
							if (dx>0){
								x=x+1;
								}
				    		if (dx<0){
								x=x-1;
								}
				    		}
							if (this.y != tempR.y+disty){
						    	dy=(tempR.y+disty-y);
								
								if (dy>0){
									y=y+1;
									}
					    		if (dy<0){
									y=y-1;
									}
					    		}
			    								
						}
					}
				}
			}

}
