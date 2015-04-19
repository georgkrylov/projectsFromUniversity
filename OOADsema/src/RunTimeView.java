import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;



public class RunTimeView {
	static ArrayList<JLabel> readers;
	static ArrayList<JLabel> writers;
	static JLabel buffer;
	RunTime rt;
	public RunTimeView(RunTime rt) {
		readers = new ArrayList();
		writers = new ArrayList();
		this.rt = rt;
		Iterator it =  rt.agents.iterator();
		int readerscount = -1;
		int writerscount = -1;
		while (it.hasNext()){
			Agent temp = (Agent) it.next();
			if (temp.getClass()== Reader.class) {
				readerscount++;
				JLabel readerss = new JLabel("Reader "+ Integer.toString(readerscount));
				readerss.setVisible(true);
		        Dimension d = new Dimension(400,10);
		        readerss.setMinimumSize(d);
				rt.gui.jPanel1.add(readerss);
				readers.add(readerss );


			}
			if (temp.getClass()== Writer.class) {
				writerscount++;
				JLabel writerss = new JLabel("Writer "+ Integer.toString(writerscount));
				writerss.setVisible(true);
		        Dimension d = new Dimension(400,10);
		       writerss.setMinimumSize(d);
				rt.gui.jPanel2.add(writerss);
				writers.add(writerss );


			}
			if (temp.getClass()== Buffer.class) {
				buffer = rt.gui.jLabel3;
				Buffer bufferr = (Buffer)temp;
				buffer.setText(bufferr.read());


			}
		}
	}
	public void repaint(){
		while (1==1){
			Iterator it =  rt.agents.iterator();
			int readerscount = -1;
			int writerscount = -1;
			while (it.hasNext()){
				Agent temp = (Agent) it.next();
				if (temp.getClass()== Reader.class) {
					readerscount++;
					Reader readerr = (Reader)temp;
					
					readers.get(readerscount).setText(readerr.readerString);


				}
				if (temp.getClass()== Writer.class) {
					writerscount++;
					Writer writerr = (Writer)temp;
					writers.get(writerscount).setText("Writer " + Integer.toString(writerscount)+writerr.jobLeft());


				}
				if (temp.getClass()== Buffer.class) {
					buffer = rt.gui.jLabel3;
					Buffer bufferr = (Buffer)temp;
					buffer.setText(bufferr.read());


				}
			}
		}
	}
}
