
public class Strategy {
	SolutionStrategy s;
	RunTime rt;
	gui gui;
	public Strategy(gui gui){
	this.gui =gui;
	}
	
	public void setStrategy(SolutionStrategy s){
		this.s = s;

	}
	public void executeCurrentStrategy(){
		s.execute(gui);
	}
}
