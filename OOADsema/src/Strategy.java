
public class Strategy {
	// context class for strategy design pattern
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
