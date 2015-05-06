import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OOAD {

	public static void main(String[] args) 
	{
		createGUI();
	}
	// You should plug all your strategies here
	public static void createGUI(){
		final gui gui = new gui();

		gui.setVisible(true);
		final Strategy executionStrategy =new Strategy(gui);
		gui.jButton1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (gui.jRadioButton1.isSelected()) {
					executionStrategy.setStrategy(new NoStrategy());
				}	
				if (gui.jRadioButton2.isSelected()) {
					executionStrategy.setStrategy(new FairStrategy());        		
				}
				executionStrategy.executeCurrentStrategy();
			}

		});

	}

}
