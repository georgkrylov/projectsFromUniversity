/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wolvess;

import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author georgiy.krylov
 */
public class AlphaAgent extends AgentAbstract{
	int lifeTime;
	int hungry;
	WolfRunTime rt;
	AlphaAgent t = null;
	int distx;
	int disty;
	int dx,dy;
	int busy;
	int huntin;
	Random rn;
	AlphaAgent(int lifeTime , WolfRunTime rt,int x,int y){
		this.lifeTime = lifeTime;
		this.hungry=100;
		this.rt=rt;
		this.x = x;
		rn = new Random();
		distx=0;
		disty=0;
		this.y = y;
		busy = 0;   
		Iterator it = rt.agents.iterator();
		synchronized(rt.agents){
			while (it.hasNext() ){
				AgentAbstract temp = (AgentAbstract) it.next();
				if (FoodAgent.class== temp.getClass()){

					rt.buff2.add(temp);
				}
			}

		}
		huntin=0;
		buffer = " Alpha is created ";
	}
	@Override
	public void step() {
		buffer = "";
		// TODO Auto-generated method stub
		this.lifeTime = this.lifeTime - 1;
		if (this.lifeTime>0) {
			hungry--;
			if (hungry == 0) createFood();
			check();
			hunt();
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
		} else {	
			updateAlpha();
			rt.buff2.add(this);
			buffer+=" This wolf had died;";}


	}
	public void updateAlpha(){
		buffer += " New elections of alpha wolf";
		WolfAgent tempR;
		
		synchronized(rt.agents){
			Iterator it = rt.agents.iterator();

		while(it.hasNext()){
			AgentAbstract temp =(AgentAbstract)it.next();
			if (temp.getClass()==AlphaAgent.class){
				return;
			}
		}
		int eldest = 3650;

		WolfAgent eldestR = null;
		it = rt.agents.iterator();
		synchronized(rt.agents){
			while(it.hasNext()){

				AgentAbstract temp =(AgentAbstract)it.next();
				if (temp.getClass()==WolfAgent.class){
					tempR= (WolfAgent) temp;


					if (tempR.lifeTime<=eldest){
						eldest=tempR.lifeTime;
						eldestR=tempR;
					}
				}
			}


		}



		AlphaAgent t = new AlphaAgent(eldestR.lifeTime,this.rt,eldestR.x,eldestR.y);
		System.out.println(t.lifeTime);
		rt.buff.add(t);
		rt.buff2.add(eldestR);
		rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+1));
		}

	}
	public void setBusy(){
		busy=1;
	}
	public void createFood(){
		buffer += " Food is going to be hunted";
		FoodAgent t = new FoodAgent(36500,this.rt);
		rt.buff.add(t);
		rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+30));
		//System.out.println("Food was created"+this.rt.currentTime);
	}
	public void setFree(){
		busy=0;
	}
	public void hunt(){
		buffer += " Huntin!";
		FoodAgent tempR=null;
		synchronized(rt.agents){
			huntin=1;
			Iterator it = rt.agents.iterator();
			while (it.hasNext()){
				AgentAbstract temp = (AgentAbstract) it.next();
				if (temp.getClass()==FoodAgent.class){
					huntin=0;
					tempR = (FoodAgent) temp;
					if (this.x != tempR.x+distx){
						dx=(tempR.x+distx-x);
						huntin=1;
						if (dx>0){
							x=x+1;
						}
						if (dx<0){
							x=x-1;
						}
					}
					if (this.y != tempR.y+disty){
						huntin=1;
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

		if (huntin==0) {
			hungry=100;
			synchronized(rt.agents){
				buffer += " Found food, some wolfs wont remain in the pack due to lack of it; ";
				Iterator it = rt.agents.iterator();
				int foodremained = rt.agents.size()-tempR.amount;
				while (it.hasNext() && foodremained>0){
					AgentAbstract temp = (AgentAbstract) it.next();

					if (AlphaAgent.class != temp.getClass()){
						foodremained--;

					}
					//	if (PupAgent.class== temp.getClass())continue;
					if (FoodAgent.class== temp.getClass())continue;
					if (AlphaAgent.class== temp.getClass())continue;
					rt.buff2.add(temp);
				}
			}
			rt.buff2.add(tempR);
		}
	//	System.out.println("finished huntin"+this.rt.currentTime);
	}

	public void check(){
		synchronized(rt.agents){
			int i=0;
			Iterator it = rt.agents.iterator();
			synchronized(rt.agents){
				while (it.hasNext() ){
					AgentAbstract temp = (AgentAbstract) it.next();
					if (FoodAgent.class== temp.getClass()){
						i++;
						if (i>1) rt.buff2.add(temp);
					}
				}

			}}
	}

}
