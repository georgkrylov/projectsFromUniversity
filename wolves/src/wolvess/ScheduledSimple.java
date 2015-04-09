package wolvess;

public class ScheduledSimple implements Steppable, Comparable {
	public AgentAbstract agentA;
	public int simTime;
	
	public ScheduledSimple(AgentAbstract a, int t) {
		agentA = a; 
		simTime = t;
	}
	
	public void step() {
		if (agentA == null) {System.out.println("error"); return;}
		if (agentA!=null)		agentA.step();
	}

	public String getDescription(){
		if (agentA == null) {System.out.println("error"); return "Some kind of bad referencing occurred";}
		return agentA.getClass().toString()+agentA.buffer+Integer.toString(simTime);
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		ScheduledSimple otherObject = (ScheduledSimple) o;
		return (this.simTime - otherObject.simTime);
	}

}
