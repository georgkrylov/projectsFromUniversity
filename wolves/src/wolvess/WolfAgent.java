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
public class WolfAgent extends AgentAbstract implements Steppable{

	WolfRunTime rt;
	int lifeTime;
	int destinationx;
	int destinationy;
	int dx,dy,distx,disty;
	int busy;
	Random rn;


	public WolfAgent(int lifeTime, WolfRunTime rt, int x, int y) {
		this.rt = rt;
		this.lifeTime = lifeTime;
		this.x=x;
		this.y=y;
		rn= new Random();
		distx = rn.nextInt(80)-40;
		disty = rn.nextInt(80)-40;
		busy=0;
		buffer = "Wolf is created";



	}
	@Override
	public void step() {
		buffer="";
		this.lifeTime = this.lifeTime - 1;
		//System.out.println("Wolf's turn"+ Integer.toString(rt.currentTime));
		if (this.lifeTime>0) {

			createAlpha();

			if (this.lifeTime>0) {	move();rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));}

		} else {	
			buffer += " This wolf died; ";
			rt.buff2.add(this);

		}


	}
	public void createAlpha(){
		int i=0;
		WolfAgent tempR;
		
		synchronized(rt.agents){
			Iterator it = rt.agents.iterator();
			//System.out.println("wolf Iterator created!"+ Integer.toString(rt.currentTime));
			//rt.agents.contains()
			while(it.hasNext()){
				//System.out.println(temp);
				//System.out.println("Before call of iterator!"+rt.agents.size());
				AgentAbstract temp =(AgentAbstract)it.next();
				i=i+1;
				//System.out.println("After call of iterator!"+ i);
				//procBuilder.redirectErrorStream(true);
				if (temp == null){System.out.println("Error is here!");}
				if (temp.getClass()==AlphaAgent.class){
				//	System.out.println("FoundAlpha!"+ Integer.toString(rt.currentTime));
					return;
				}
			}
		}
		int eldest = 3650;

		WolfAgent eldestR = this;
	 
	 	synchronized (rt.agents){
	 		Iterator it = rt.agents.iterator();
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


	//	System.out.println(eldestR);
		AlphaAgent t = new AlphaAgent(eldestR.lifeTime,this.rt,eldestR.x,eldestR.y);
	//	System.out.println("wahaha"+ Integer.toString(rt.currentTime));
	//	System.out.println(t.lifeTime);
		rt.buff.add(t);
		buffer += " Due to elections, this wolf became an alpha!; ";
		rt.buff2.add(eldestR);

		if (this!=eldestR) {rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));}
		rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+1));


	}
	public void move(){
		buffer += " Following Alpha ; ";
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

	public void setBusy(){
		busy=1;
	}
	public void setFree(){
		busy=0;
	}
}
