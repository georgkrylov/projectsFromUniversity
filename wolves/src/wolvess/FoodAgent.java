package wolvess;

import java.util.Random;

public class FoodAgent extends AgentAbstract {
	int amount;
	int lifeTime;
	WolfRunTime rt;
	
	public FoodAgent(int lifeTime,WolfRunTime rt) {
		Random rn = new Random();
		x=rn.nextInt(500);
		y=rn.nextInt(500);
		amount = 10+rn.nextInt(20);
		this.lifeTime=lifeTime;
		this.rt = rt;
	}

	@Override
	public void step() {
		this.lifeTime = this.lifeTime - 1;
		if (this.lifeTime>0) {
			if (rt.agents.size() == 1){
			rt.addToSchedule(new ScheduledSimple(this,rt.currentTime+1));}
		} else {	
	
			rt.buff2.add(this);

			}
		
	}

}
