package wolvess;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

public class WolvesMain {

	
	 private static JWindow queueFrame;
	private static JTextArea textArea;
	private static JTextArea dataArea;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WolfRunTime rt = new WolfRunTime();
		WolfView rtv = new WolfView();
		queueFrame = new JWindow();
		queueFrame.setSize(600,800);
		queueFrame.setVisible(false);
		textArea = new JTextArea();
		dataArea = new JTextArea();
	//	queueFrame.add(textArea);
		
		rt.setView(rtv);
		rt.initRunTime();
		JFrame frame = new JFrame("Run Time");
		JButton button = new JButton("Play");
		JLabel label = new JLabel("---");
		
		rt.button = button;
		rt.label = label;
		rt.textArea=textArea;
		rt.queueFrame=queueFrame;
		rt.dataArea= dataArea;

JScrollPane thePane = new JScrollPane(textArea);
queueFrame.add(thePane);
	

		button.addActionListener(rt);
		
		JPanel p = new JPanel();
		p.setSize(100, 100);
		p.addMouseListener(rt);
		p.setBorder(BorderFactory.createTitledBorder("Simulation"));
		
		p.add(rtv);
		frame.getContentPane().add(p);
		frame.getContentPane().add(label);
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(button);
		frame.getContentPane().add(dataArea);

		frame.setSize(800,600);
		frame.setVisible( true );
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		
		// rt.execute();

	}

}
