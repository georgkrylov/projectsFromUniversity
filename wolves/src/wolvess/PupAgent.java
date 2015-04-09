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
public class PupAgent extends AgentAbstract {
    
    WolfRunTime rt;
    int lifeTime;
    int destinationx;
    int destinationy;
    int gender;
    int distx,disty,dx,dy;
    double pofsurv = 0.86;
      Random rn;
    
    public PupAgent(int lifeTime, WolfRunTime rt, int gender,int x,int y) {
		this.rt = rt;
    	buffer = "Pup's created";
		this.lifeTime = lifeTime;
                this.gender = gender;
             
               rn = new Random();
		this.x =  x+rn.nextInt(100);
this.y= y+rn.nextInt(100);

distx = rn.nextInt(80)-40;
disty = rn.nextInt(80)-40;

	
    }
    @Override
    public void step() {
    	buffer="";
    	//buffer = "Pup's turn started; ";
        this.lifeTime = this.lifeTime - 1;	
        if ((3650-this.lifeTime)<=365) {
        	move();
        		if (lifeTime%31==0){
                           double tossTheBones=rn.nextInt(100);
                           tossTheBones=tossTheBones/100;
                           if (tossTheBones>pofsurv){
                        	   rt.buff2.add(this);
                        	   buffer += " Unfortunately, this pup had not survived; ";
                        	   return;
                           } else rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
                               
                        } else rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));
        } else {
        	buffer += "Pup's grown up; ";
            
            if (this.gender==1){
        		buffer += "It's a female!; ";
                FemaleWolfAgent t = new FemaleWolfAgent(lifeTime,rt,x,y);
	           rt.buff.add(t);
            rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+1));
            } else {
        		buffer += "It's a male!;";
        	       WolfAgent t = new WolfAgent(lifeTime,rt,x,y);
                   rt.buff.add(t);
                   rt.addToSchedule(new ScheduledSimple(t,rt.currentTime+1));

            }
            rt.buff2.add(this);
        }
        
    }
    public void move(){
		buffer += " Following Alpha!; ";
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
